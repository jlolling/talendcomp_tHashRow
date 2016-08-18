package de.cimt.talendcomp.checksum;

import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for HashBuilder.
 * @author Jan Lolling <jan.lolling@cimt-ag.de>
 */
public class HashBuilderTest {

    /**
     * SHA-1 test
     * @throws Exception 
     */
    @Test
    public void testAllTypesMD5() throws Exception {
    	HashBuilder hb = HashBuilder.getMD5HashBuilder();
    	hb.caseInsensitive();
    	hb.reset();
    	hb.add(1);
    	hb.add(100000l);
    	hb.add((short) 3);
    	hb.add(4.5f);
    	hb.add(12.3456d);
    	hb.add(new java.math.BigDecimal("1.23456789"));
    	hb.add(true);
    	Date testDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-08-18 07:55:33");
    	hb.add(testDate);
    	hb.add("äöüß/\n\r\b");
    	String actual = hb.build();
    	System.out.println("Value test sha-1: " + actual);
    	String expected = "41b360a6a39edff84b94ff7640316c55";
    	assertEquals(expected, actual);
    }

    /**
     * SHA-1 test
     * @throws Exception 
     */
    @Test
    public void testAllTypesSHA1() throws Exception {
    	HashBuilder hb = HashBuilder.getSHA1HashBuilder();
    	hb.caseInsensitive();
    	hb.reset();
    	hb.add(1);
    	hb.add(100000l);
    	hb.add((short) 3);
    	hb.add(4.5f);
    	hb.add(12.3456d);
    	hb.add(new java.math.BigDecimal("1.23456789"));
    	hb.add(true);
    	Date testDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-08-18 07:55:33");
    	hb.add(testDate);
    	hb.add("äöüß/\n\r\b");
    	String actual = hb.build();
    	System.out.println("Values test sha-1: " + actual);
    	String expected = "458bbe30ac4536646f495ff7fc0ee945f72f0b74";
    	assertEquals(expected, actual);
    }

    /**
     * SHA-256 test
     * @throws Exception 
     */
    @Test
    public void testAllTypesSHA256() throws Exception {
    	HashBuilder hb = HashBuilder.getSHA256HashBuilder();
    	hb.caseInsensitive();
    	hb.reset();
    	hb.add(1);
    	hb.add(100000l);
    	hb.add((short) 3);
    	hb.add(4.5f);
    	hb.add(12.3456d);
    	hb.add(new java.math.BigDecimal("1.23456789"));
    	hb.add(true);
    	Date testDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-08-18 07:55:33");
    	hb.add(testDate);
    	hb.add("äöüß/\n\r\b");
    	String actual = hb.build();
    	System.out.println("Value test sha-256: " + actual);
    	String expected = "9dca1f7c3efea4799b7178f4fad17f8079539e55e7307e36b182ee593c6b9eb2";
    	assertEquals(expected, actual);
    }

    /**
     * SHA-256 test
     * @throws Exception 
     */
    @Test
    public void testAllTypesNull() throws Exception {
    	HashBuilder hb = HashBuilder.getSHA256HashBuilder();
    	hb.add((String) null);
    	String actual = hb.build();
    	System.out.println("Null test SHA-256: " + actual);
    	String expected = "74234e98afe7498fb5daf1f36ac2d78acc339464f950703b8c019892f982b90b";
    	assertEquals(expected, actual);
    }

}
