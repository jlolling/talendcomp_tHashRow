package de.cimt.talendcomp.checksum;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

public class TestNormalization {

	private HashNormalization md5Base;
	private NormalizeObjectConfig itemConfig;
	private NormalizeConfig config;
	
	@Before
	public void setup() {
		config = new NormalizeConfig(";", "", true, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 15, false, null, false, false);		
		md5Base = new HashNormalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
	}

    @Test
    public void testLowerCaseString() throws Exception {
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.LOWER_CASE);
        String result = md5Base.normalize("Test", itemConfig);
        assertEquals("\"test\"", result);
    }

    @Test
    public void testUpperCaseString() throws Exception {
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	String result = md5Base.normalize("Test", itemConfig);
        assertEquals("\"TEST\"", result);
    }

    @Test
    public void testCaseSensitiveString() throws Exception {
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.CASE_SENSITIVE);
        String result = md5Base.normalize("TeSt", itemConfig);
        assertEquals("\"TeSt\"", result);
    }
    
    @Test
    public void testSpecialCharacters() throws Exception {
    	
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	itemConfig.setTrimming(true);
    	
    	assertEquals("\"STRASSE\"", md5Base.normalize("Straße",itemConfig));
    	assertEquals("\"MÜSSIGKEIT\"", md5Base.normalize("Müßigkeit",itemConfig));
    	assertEquals("\"TEST!?&%$§#+*~{}[]()/\\<>|^°\"", md5Base.normalize("TEST!?&%$§#+*~{}[]()/\\<>|^°",itemConfig));
    }

    @Test
    public void testTrimming() throws Exception {

    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	itemConfig.setTrimming(true);
    	
    	
    	// left trimming
        assertEquals("\"TEST\"", md5Base.normalize(" test", itemConfig));
        assertEquals("\"TEST\"", md5Base.normalize("   test", itemConfig));
        assertEquals("\"TEST\"", md5Base.normalize("\ttest", itemConfig));

        // right trimming
        assertEquals("\"TEST\"", md5Base.normalize("test ", itemConfig));
        assertEquals("\"TEST\"", md5Base.normalize("test   ", itemConfig));
        assertEquals("\"TEST\"", md5Base.normalize("test\t", itemConfig));
        
    }

    @Test
    public void testQuoting() throws Exception {

    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	itemConfig.setTrimming(true);
    	
        assertEquals("\"ABC\"", md5Base.normalize("abc", itemConfig));
        assertEquals("\"COLUMN;ONE\"", md5Base.normalize("column;one", itemConfig));
        assertEquals("\"COLUMN\"\"TWO\"", md5Base.normalize("column\"two", itemConfig));

        config.setQuotationCharacter("#");
        assertEquals("#ABC#", md5Base.normalize("abc", itemConfig));
        assertEquals("#COLUMN;ONE#", md5Base.normalize("column;one", itemConfig));
        assertEquals("#COLUMN\"TWO#", md5Base.normalize("column\"two", itemConfig));
        
        config.setQuotingEnabled(false);
        assertEquals("ABC", md5Base.normalize("abc", itemConfig));
        assertEquals("COLUMN;ONE", md5Base.normalize("column;one", itemConfig));
        assertEquals("COLUMN\"TWO", md5Base.normalize("column\"two", itemConfig));
        
    }        
    
    @Test
    public void testChar() throws Exception {
    
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	itemConfig.setTrimming(true);
    	
    	assertEquals("\"A\"", md5Base.normalize('A', itemConfig));
        assertEquals("\"A\"", md5Base.normalize('a', itemConfig));
        
        itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.LOWER_CASE);
        assertEquals("\"a\"", md5Base.normalize('A', itemConfig));
    }

    @Test
    public void testNumericValues() throws IllegalArgumentException {

    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	itemConfig.setTrimming(true);
    	
    	// default normalization
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("123", itemConfig);
        assertEquals("\"TEST\";\"123\"", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(123, itemConfig);
        assertEquals("\"TEST\";123", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(123L, itemConfig);
    	assertEquals("\"TEST\";123", md5Base.getNormalizedString());

        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(123f, itemConfig);
        assertEquals("\"TEST\";123", md5Base.getNormalizedString());

        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(123.5f, itemConfig);
        assertEquals("\"TEST\";123.5", md5Base.getNormalizedString());

        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(123d, itemConfig);
        assertEquals("\"TEST\";123", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(123.5d, itemConfig);
        assertEquals("\"TEST\";123.5", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(1.0/9, itemConfig);
        assertEquals("\"TEST\";0.111111111111111", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(2.0/9, itemConfig);
        assertEquals("\"TEST\";0.222222222222222", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(3.0/9, itemConfig);
        assertEquals("\"TEST\";0.333333333333333", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(4.0/9, itemConfig);
        assertEquals("\"TEST\";0.444444444444444", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(5.0/9, itemConfig);
        assertEquals("\"TEST\";0.555555555555556", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(6.0/9, itemConfig);
        assertEquals("\"TEST\";0.666666666666667", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(9.0/9, itemConfig);
        assertEquals("\"TEST\";1", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(13d/3, itemConfig);
        assertEquals("\"TEST\";4.333333333333333", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(13f/3, itemConfig);
        assertEquals("\"TEST\";4.3333335", md5Base.getNormalizedString());
        
        md5Base.reset();
        md5Base.add("Test", itemConfig);
    	md5Base.add(5555432100L, itemConfig);
        assertEquals("\"TEST\";5555432100", md5Base.getNormalizedString());
        
    }
    
    @Test
    public void testShort() {
        assertEquals("0", md5Base.normalize((short) 0));
        assertEquals("127", md5Base.normalize((short) 127));
        assertEquals("255", md5Base.normalize((short) 255));
        assertEquals("-32768", md5Base.normalize(Short.MIN_VALUE));
        assertEquals("32767", md5Base.normalize(Short.MAX_VALUE));
    }

    @Test
    public void testLargeAndSmallBigDecimals() {
    	BigDecimal b = new BigDecimal("-47.11e16");
        assertEquals("-471100000000000000", md5Base.normalize(b));
        assertEquals("-471100000000000000", md5Base.normalize(b));
        
        BigDecimal b2 = new BigDecimal("-47.11e-16");
        assertEquals("-0.000000000000004711", md5Base.normalize(b2));
        assertEquals("-0.000000000000004711", md5Base.normalize(b2));
        
        BigDecimal b3 = new BigDecimal("15.15");
        assertEquals("15.15", md5Base.normalize(b3));
        assertEquals("15.15", md5Base.normalize(b3));
        
        BigDecimal b4 = new BigDecimal("15.1500000000000000000000");
        assertEquals("15.1500000000000000000000", md5Base.normalize(b4));
        assertEquals("15.1500000000000000000000", md5Base.normalize(b4));
    }
    
    @Test
    public void testBool() throws IllegalArgumentException {
        assertEquals("true", md5Base.normalize(true));
    }

    @Test
    public void testNull() throws IllegalArgumentException {
        String s = null;
        Integer i = null;
        Float f = null;
        Double d = null;
        
        config.setNullReplacement("");
        itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	itemConfig.setTrimming(true);
        
        assertEquals("", md5Base.normalize(s, itemConfig));
        assertEquals("", md5Base.normalize(i));
        assertEquals("", md5Base.normalize(f));
        assertEquals("", md5Base.normalize(d));
 
        config.setNullReplacement("null");
        assertEquals("null", md5Base.normalize(s,itemConfig));
        assertEquals("null", md5Base.normalize(i));
        assertEquals("null", md5Base.normalize(f));
        assertEquals("null", md5Base.normalize(d));
    }
    
    @Test
    public void testNullNull() throws IllegalArgumentException {
        
    	config.setNullReplacement("");
        itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	itemConfig.setTrimming(true);
    	
    	md5Base.reset();
    	md5Base.add(null, itemConfig);
    	md5Base.add(null, itemConfig);
    	
    	assertEquals(";", md5Base.getNormalizedString());
    }
    
    @Test
    public void testCaseSensitiveNormalization() throws IllegalArgumentException {
        
    	config.setNullReplacement("");
        itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.CASE_SENSITIVE);
    	itemConfig.setTrimming(true);
    	
    	md5Base.reset();
    	md5Base.add(123, itemConfig);
    	md5Base.add("Test", itemConfig);
    	md5Base.add("Lorem ipsum dolor sit amet", itemConfig);
    	
    	assertEquals("123;\"Test\";\"Lorem ipsum dolor sit amet\"", md5Base.getNormalizedString());
    }
    
    @Test
    public void testDate() {
    	
    	config.setDateInMillis(false);
    	
    	Calendar calendar = GregorianCalendar.getInstance();
    	// Clear all fields, especially milliseconds
    	calendar.clear();
    	
    	// 2017-01-01T12:05:30 (month in calendar is zero-based)
    	calendar.set(2017, 00, 01, 12, 5, 30);
		assertEquals("2017-01-01T12:05:30.000", md5Base.normalize(calendar.getTime()));
		
		calendar.clear();
		calendar.set(2017, 05, 18, 13, 42, 05);
		assertEquals("2017-06-18T13:42:05.000", md5Base.normalize(calendar.getTime()));
	}
    
    @Test
    public void testDateInMillis(){
    	
    	config.setDateInMillis(true);
    	
    	Calendar calendar = GregorianCalendar.getInstance();
    	// Clear all fields, especially milliseconds
    	calendar.clear();
    	
    	// 2017-01-01T12:05:30 (month in calendar is zero-based)
    	calendar.set(2017, 00, 01, 12, 00, 00);
		assertEquals("1483268400000", md5Base.normalize(calendar.getTime()));
    	
    }

    
    @Test
    public void testCutOffEmptyTrailingWithQuotation(){
    	
    	config.setCutOffEmptyTrailingObjects(true);
    	config.setQuotingEnabled(true);
    	
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	assertEquals("\"TEST\"", md5Base.getNormalizedString());
    	
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	md5Base.add("", itemConfig);
    	assertEquals("\"TEST\"", md5Base.getNormalizedString());

    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add(null, itemConfig);
    	md5Base.add("", itemConfig);
    	assertEquals("\"TEST\"", md5Base.getNormalizedString());
    	
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	md5Base.add(null, itemConfig);
    	assertEquals("\"TEST\"", md5Base.getNormalizedString());
    	
    	config.setCutOffEmptyTrailingObjects(false);
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	md5Base.add("", itemConfig);
    	assertEquals("\"TEST\";\"\";\"\"", md5Base.getNormalizedString());
    }
    
    @Test
    public void testCutOffEmptyTrailingWithOutQuotation(){
    	
    	config.setCutOffEmptyTrailingObjects(true);
    	config.setQuotingEnabled(false);

    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	assertEquals("TEST", md5Base.getNormalizedString());
    	
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	md5Base.add("", itemConfig);
    	assertEquals("TEST", md5Base.getNormalizedString());

    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add(null, itemConfig);
    	md5Base.add("", itemConfig);
    	assertEquals("TEST", md5Base.getNormalizedString());
    	
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	md5Base.add(null, itemConfig);
    	assertEquals("TEST", md5Base.getNormalizedString());
    	
    	config.setCutOffEmptyTrailingObjects(false);
    	md5Base.reset();
    	md5Base.add("Test", itemConfig);
    	md5Base.add("", itemConfig);
    	md5Base.add("", itemConfig);
    	
    	assertEquals("TEST;;", md5Base.getNormalizedString());
    }
    
//    @Ignore
//    @Test
//    public void testMassiveUse() {
//    	
//        itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.CASE_SENSITIVE);
//    	itemConfig.setTrimming(true);
//    	
//    	Calendar calendar = GregorianCalendar.getInstance();
//    	// Clear all fields, especially milliseconds
//    	calendar.clear();
//    	
//    	// 2017-01-01T12:05:30 (month in calendar is zero-based)
//    	calendar.set(2017, 00, 01, 12, 5, 30);
//    	
//    	for (int i = 0; i < 100000; i++) {
//    		
//    		md5Base.reset();
//    		md5Base.add("Test",itemConfig);
//    		md5Base.add(i,itemConfig);
//    		md5Base.add(calendar.getTime(),itemConfig);
//    		md5Base.add("Lorem ipsum dolor sit amet",itemConfig);
//    		
//    		assertEquals("\"Test\";" + i + ";2017-01-01T12:05:30.000;\"Lorem ipsum dolor sit amet\"",md5Base.getNormalizedString());
//		}
//		
//	}
	
}
