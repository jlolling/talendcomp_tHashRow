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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class HashBuilder {
	
	private final StringBuilder content = new StringBuilder();
	private boolean caseInsensitive = false;
	private final DecimalFormat nf;
	private static Charset cs = null;
	private final MessageDigest mDigest;
	private boolean allNull = true;
	private String nullReplacement = "null";
	
	private HashBuilder(String hashMethod) throws NoSuchAlgorithmException {
		nf = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setGroupingUsed(false);
		if (cs == null) {
			cs = Charset.forName("UTF-8");
		}
		mDigest = MessageDigest.getInstance(hashMethod);
	}
	
	public static HashBuilder getMD5HashBuilder() throws NoSuchAlgorithmException {
		return new HashBuilder("MD5");
	}
	
	public static HashBuilder getSHA1HashBuilder() throws NoSuchAlgorithmException {
		return new HashBuilder("SHA1");
	}
	
	public static HashBuilder getSHA256HashBuilder() throws NoSuchAlgorithmException {
		return new HashBuilder("SHA-256");
	}

	public HashBuilder reset() {
		content.setLength(0);
		allNull = true;
		return this;
	}
	
	public HashBuilder caseInsensitive() {
		caseInsensitive = true;
		return this;
	}

	public HashBuilder add(final String value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			if (caseInsensitive) {
				content.append(value.trim().toLowerCase(Locale.ENGLISH));
			} else {
				content.append(value.trim());
			}
			allNull = false;
		}
		content.append("|");
		return this;
	}
	
	public HashBuilder add(final Integer value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(nf.format(value));
			allNull = false;
		}
		content.append("|");
		return this;
	}
	
	public HashBuilder add(final Long value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(nf.format(value));
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Short value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(nf.format(value));
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Float value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(nf.format(value));
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Double value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(nf.format(value));
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final BigDecimal value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(nf.format(value));
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final BigInteger value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(nf.format(value));
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Date value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(value.getTime());
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Boolean value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(value);
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final byte[] value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(value);
			allNull = false;
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Byte value) {
		if (value == null) {
			content.append(nullReplacement);
		} else {
			content.append(value);
			allNull = false;
		}
		content.append("|");
		return this;
	}
	
    public String build() throws NoSuchAlgorithmException {
    	if (content.length() == 0) {
    		return null;
    	}
        final byte[] result = mDigest.digest(content.toString().getBytes(cs));
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

	public boolean allValuesAreNull() {
		return allNull;
	}

	public String getNullReplacement() {
		return nullReplacement;
	}

	public void setNullReplacement(String nullReplacement) {
		if (nullReplacement != null) {
			this.nullReplacement = nullReplacement;
		} else {
			this.nullReplacement = "null";
		}
	}

}
