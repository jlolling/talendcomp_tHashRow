package de.cimt.talendcomp.checksum;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import de.cimt.talendcomp.checksum.HashCalculation.HASH_OUTPUT_ENCODINGS;


public class TestSHA1Base64 {

	private Normalization sha1Base;
	private NormalizeObjectConfig itemConfig;
	private NormalizeConfig config;
	
	@Before
	public void setup() {
		config = new NormalizeConfig(";", "", true, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 1, false, null, false, false);		
		sha1Base = new Normalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
	}
	
    @Test
    public void testNumericValues() throws IllegalArgumentException {
        String result;

        itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
        
        sha1Base.reset();
        sha1Base.add("test", itemConfig);
        sha1Base.add("123", itemConfig);
        
        result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_123_STR, result);

        sha1Base.reset();
        sha1Base.add("test", itemConfig);
        sha1Base.add(123, itemConfig);
    
        result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_123, result);

        sha1Base.reset();
        sha1Base.add("test", itemConfig);
        sha1Base.add(123L, itemConfig);
        
        result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_123, result);

        sha1Base.reset();
        sha1Base.add("test", itemConfig);
        sha1Base.add(123f, itemConfig);
        
        result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_123, result);
        
        sha1Base.reset();
        config.setDateInMillis(false);
        Calendar calendar = GregorianCalendar.getInstance();
    	calendar.clear();
    	calendar.set(2017, 00, 01, 12, 5, 30);
    	sha1Base.add("Test", itemConfig);
        sha1Base.add(calendar.getTime(), itemConfig);
    	
        result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_DATE, result);
        
        sha1Base.reset();
        config.setDateInMillis(true);
        calendar = GregorianCalendar.getInstance();
    	calendar.clear();
    	calendar.set(2017, 00, 01, 12, 5, 30);
    	sha1Base.add("Test", itemConfig);
        sha1Base.add(calendar.getTime(), itemConfig);
    	
        result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_DATE_AS_MIILS, result);
    }

    @Test
    public void testLowerCaseString() throws IllegalArgumentException {
    	
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.LOWER_CASE);
    	
    	sha1Base.reset();
        sha1Base.add("test", itemConfig);
        
    	String result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_LOWER_CASE, result);
    }

    @Test
    public void testUpperCaseString() throws IllegalArgumentException {
    	
    	itemConfig.setCaseSensitive(NormalizeObjectConfig.CaseSensitive.UPPER_CASE);
    	
    	sha1Base.reset();
        sha1Base.add("test", itemConfig);
        
    	String result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TEST_UPPER_CASE, result);
    }

    @Test
    public void testNull() throws IllegalArgumentException {
    	
    	sha1Base.reset();
        sha1Base.add(null, itemConfig);
        
        String result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_EMPTY, result);
    }

    @Test
    public void testNullNull() throws IllegalArgumentException {
        
    	sha1Base.reset();
        sha1Base.add(null, itemConfig);
        sha1Base.add(null, itemConfig);
        
    	String result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_SEMICOLON, result);
    }

    @Test
    public void testEmptyString() throws IllegalArgumentException {
    	sha1Base.reset();
    	sha1Base.add("", itemConfig);
    	
    	String result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_SINGLE_EMPTY_STRING, result);
    }
    
    @Test
    public void testTwoEmptyStrings() throws IllegalArgumentException {
    	
    	sha1Base.reset();
        sha1Base.add("", itemConfig);
        sha1Base.add("", itemConfig);
        
    	String result = sha1Base.calculateHash("SHA1", HASH_OUTPUT_ENCODINGS.BASE64);
        assertEquals(SHA1_TWO_EMPTY_STRINGS, result);
    }
    
    /**
     * The MD5 hash for the empty string ("").
     */
    private static final String SHA1_EMPTY = "2jmj7l5rSw0yVb/vlWAYkK/YBwk=";

    /**
     * The MD5 hash for the string <code>"TEST"</code> (including quotation marks)
     */
    private static final String SHA1_TEST_UPPER_CASE = "BNDAXsnIjBgITpwo/I8TaQ6udcs=";

    /**
     * The MD5 hash for the string <code>"test"</code> (including quotation marks)
     */
    
    private static final String SHA1_TEST_LOWER_CASE = "UAbW+DAgAOi4f+9cUMBx1tl7Tog=";
    /**
     * The MD5 hash for the String <code>"TEST";123</code>.
     */
    private static final String SHA1_TEST_123 = "e9XRSSMBQ/wb6Z2IrA4BTqX5RTY=";
    
    /**
     * The MD5 hash for the String <code>"TEST";"123"</code>.
     */
    private static final String SHA1_TEST_123_STR = "GBc7xANaE4oNNq6OWY1vbsaYNR4=";

    /**
     * The MD5 hash for the string <code>";"</code> (without quotation marks).
     */
    private static final String SHA1_SEMICOLON = "LRSrl8w9wpTFHA1oFPTqRfS04xI=";
    
    /**
     * The MD5 hash for the string <code>""</code> (including quotation marks).
     */
    private static final String SHA1_SINGLE_EMPTY_STRING = "3Sns9SSwMKZSYeMFnEirnh7LJYU=";
    
    /**
     * The MD5 hash for the string <code>"";""</code> (including quotation marks).
     */
    private static final String SHA1_TWO_EMPTY_STRINGS = "VAYWUWcfUA4yAGnz545z5XsFrLw=";
    
    /**
     * The MD5 hash for the string <code>"TEST";2017-01-01T12:05:30.000</code> (including quotation marks).
     */
    private static final String SHA1_TEST_DATE = "7dkJB5ltTE7LRRzRjo44aux3MJo=";
    
    /**
     * The MD5 hash for the string <code>"TEST";1483268730000</code> (including quotation marks).
     */
    private static final String SHA1_TEST_DATE_AS_MIILS = "U5HKNDIkV6zHSQlPiG//uLr4EEs=";
    
}
