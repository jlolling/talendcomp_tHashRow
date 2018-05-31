package de.cimt.talendcomp.checksum;

import static org.junit.Assert.*;

import org.junit.Test;

import de.cimt.talendcomp.checksum.HashCalculation.HASH_OUTPUT_ENCODINGS;

import static org.hamcrest.CoreMatchers.*;

public class TestSpecialBehaviour {

	private HashNormalization md5Base;
	private NormalizeObjectConfig itemConfig;
	private NormalizeConfig config;
   
	/**
	 * Test null with modified output
	 */
	@Test
	public void testModifyOutput1(){
		
		config = new NormalizeConfig(";", "", true, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 15, true, HASH_OUTPUT_REPLACEMENT,false, false);		
		md5Base = new HashNormalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
		
		String result;
		
		md5Base.reset();
		md5Base.add(null, itemConfig);
		
		result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);	
		assertEquals(HASH_OUTPUT_REPLACEMENT, result);
		
	}
	
	/**
	 * Test "" with modified output and quotation disabled
	 */
	@Test
	public void testModifyOutput2(){
		
		config = new NormalizeConfig(";", "", false, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 15, true, HASH_OUTPUT_REPLACEMENT, false, false);		
		md5Base = new HashNormalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
		
		String result;
		
		md5Base.reset();
		md5Base.add("", itemConfig);
		
		result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);	
		assertEquals(HASH_OUTPUT_REPLACEMENT, result);
		
	}
	
	/**
	 * Test "" with modified output and quotation disabled
	 */
	@Test
	public void testModifyOutputAllNull(){
		
		config = new NormalizeConfig(";", "", false, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 15, true, HASH_OUTPUT_REPLACEMENT, false, false);		
		md5Base = new HashNormalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
		
		String result;
		
		md5Base.reset();
		md5Base.add(null, itemConfig);
		md5Base.add(null, itemConfig);
		md5Base.add(null, itemConfig);
		
		result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);	
		assertEquals(HASH_OUTPUT_REPLACEMENT, result);
		
	}
	
	/**
	 * Test "" with modified output and quotation disabled
	 */
	@Test
	public void testModifyOutputAllNullWithCutOf(){
		
		config = new NormalizeConfig(";", "", false, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 15, true, HASH_OUTPUT_REPLACEMENT, false, true);		
		md5Base = new HashNormalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
		
		String result;
		
		md5Base.reset();
		md5Base.add(null, itemConfig);
		md5Base.add(null, itemConfig);
		md5Base.add(null, itemConfig);
		
		result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);	
		assertEquals(HASH_OUTPUT_REPLACEMENT, result);
		
	}
	
	/**
	 * Test "" with modified output and quotation disabled
	 */
	@Test
	public void testModifyOutputNotAllNull(){
		
		config = new NormalizeConfig(";", "", false, "\"", "yyyy-MM-dd'T'HH:mm:ss.SSS", "ENGLISH", 7, 15, true, HASH_OUTPUT_REPLACEMENT, false, false);		
		md5Base = new HashNormalization(config);
		itemConfig = new NormalizeObjectConfig("UPPER_CASE", true);
		
		String result;
		
		md5Base.reset();
		md5Base.add(null, itemConfig);
		md5Base.add("Test", itemConfig);
		md5Base.add(null, itemConfig);
		
		result = md5Base.calculateHash("MD5", HASH_OUTPUT_ENCODINGS.HEX);	
		assertThat(result, is(not(HASH_OUTPUT_REPLACEMENT)));
		assertEquals("6e618343fec0d14c498ecd6b1a2c5c41",result);
	}
	
	/**
	 * Hashoutput replacement
	 */
	private static final String HASH_OUTPUT_REPLACEMENT = "###";
	
}
