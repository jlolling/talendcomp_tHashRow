/**
 * Copyright 2015 Jan Lolling jan.lolling@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.cimt.talendcomp.checksum;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

/**
 * Unit test for HashBuilder.
 * @author Jan Lolling <jan.lolling@gmail.com>
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
    	String expected = "efafff817a2a56bad871f282fcbc19b1";
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
    	String expected = "1e9b3a956e4f8b0331314b2a4a12561cef1582db";
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
