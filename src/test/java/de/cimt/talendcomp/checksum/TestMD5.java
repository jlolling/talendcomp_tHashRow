package de.cimt.talendcomp.checksum;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import de.cimt.talendcomp.checksum.HashCalculation.HASH_OUTPUT_ENCODINGS;


public class TestMD5 {

	private HashNormalization md5Base;
	private NormalizeObjectConfig itemConfig;
	private NormalizeConfig config;
	
	@Before
	public void setup() {
		config = new NormalizeConfig(";", "", true, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 1, false, null, false, false);		
		md5Base = new HashNormalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
	}
	
    @Test
    public void testNumericValues() throws IllegalArgumentException {
        String result;

        itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
        
        md5Base.reset();
        md5Base.add("test", itemConfig);
        md5Base.add("123", itemConfig);
        
        result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_123_STR, result);

        md5Base.reset();
        md5Base.add("test", itemConfig);
        md5Base.add(123, itemConfig);
    
        result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_123, result);

        md5Base.reset();
        md5Base.add("test", itemConfig);
        md5Base.add(123L, itemConfig);
        
        result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_123, result);

        md5Base.reset();
        md5Base.add("test", itemConfig);
        md5Base.add(123f, itemConfig);
        
        result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_123, result);
        
        md5Base.reset();
        config.setDateInMillis(false);
        Calendar calendar = GregorianCalendar.getInstance();
    	calendar.clear();
    	calendar.set(2017, 00, 01, 12, 5, 30);
    	md5Base.add("Test", itemConfig);
        md5Base.add(calendar.getTime(), itemConfig);
    	
        result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_DATE, result);
        
        md5Base.reset();
        config.setDateInMillis(true);
        calendar = GregorianCalendar.getInstance();
    	calendar.clear();
    	calendar.set(2017, 00, 01, 12, 5, 30);
    	md5Base.add("Test", itemConfig);
        md5Base.add(calendar.getTime(), itemConfig);
    	
        result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_DATE_AS_MIILS, result);
    }

    @Test
    public void testLowerCaseString() throws IllegalArgumentException {
    	
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.LOWER_CASE);
    	
    	md5Base.reset();
        md5Base.add("test", itemConfig);
        
    	String result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_LOWER_CASE, result);
    }

    @Test
    public void testUpperCaseString() throws IllegalArgumentException {
    	
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	
    	md5Base.reset();
        md5Base.add("test", itemConfig);
        
    	String result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TEST_UPPER_CASE, result);
    }

    @Test
    public void testNull() throws IllegalArgumentException {
    	
    	md5Base.reset();
        md5Base.add(null, itemConfig);
        
        String result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_EMPTY, result);
    }

    @Test
    public void testNullNull() throws IllegalArgumentException {
        
    	md5Base.reset();
        md5Base.add(null, itemConfig);
        md5Base.add(null, itemConfig);
        
    	String result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_SEMICOLON, result);
    }

    @Test
    public void testEmptyString() throws IllegalArgumentException {
    	md5Base.reset();
    	md5Base.add("", itemConfig);
    	
    	String result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_SINGLE_EMPTY_STRING, result);
    }
    
    @Test
    public void testTwoEmptyStrings() throws IllegalArgumentException {
    	
    	md5Base.reset();
        md5Base.add("", itemConfig);
        md5Base.add("", itemConfig);
        
    	String result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);
        assertEquals(MD5_TWO_EMPTY_STRINGS, result);
    }
    
    /**
     * The MD5 hash for the empty string ("").
     */
    private static final String MD5_EMPTY = "d41d8cd98f00b204e9800998ecf8427e";

    /**
     * The MD5 hash for the string <code>"TEST"</code> (including quotation marks)
     */
    private static final String MD5_TEST_UPPER_CASE = "4cf57dcd20acca8fe832775bdcdb4180";

    /**
     * The MD5 hash for the string <code>"TEST"</code> (including quotation marks)
     */
    
    private static final String MD5_TEST_LOWER_CASE = "303b5c8988601647873b4ffd247d83cb";
    /**
     * The MD5 hash for the String <code>"TEST";123</code>.
     */
    private static final String MD5_TEST_123 = "3bd106921f2a35c66c27e4b342de7b4e";
    
    /**
     * The MD5 hash for the String <code>"TEST";"123"</code>.
     */
    private static final String MD5_TEST_123_STR = "005c6c49b48561b6f2b4edd7fc9019b5";

    /**
     * The MD5 hash for the string <code>";"</code> (without quotation marks).
     */
    private static final String MD5_SEMICOLON = "9eecb7db59d16c80417c72d1e1f4fbf1";
    
    /**
     * The MD5 hash for the string <code>""</code> (including quotation marks).
     */
    private static final String MD5_SINGLE_EMPTY_STRING = "9d4568c009d203ab10e33ea9953a0264";
    
    /**
     * The MD5 hash for the string <code>"";""</code> (including quotation marks).
     */
    private static final String MD5_TWO_EMPTY_STRINGS = "9a3f6d9b3e70fbe3a0934365d3048b04";
    
    /**
     * The MD5 hash for the string <code>"TEST";2017-01-01T12:05:30.000</code> (including quotation marks).
     */
    private static final String MD5_TEST_DATE = "9b7bcdfa69aa3b0227949d881effa86c";
    
    /**
     * The MD5 hash for the string <code>"TEST";1483268730000</code> (including quotation marks).
     */
    private static final String MD5_TEST_DATE_AS_MIILS = "04c562d29e75ff3d0461cfd44da11c0a";
    
}
