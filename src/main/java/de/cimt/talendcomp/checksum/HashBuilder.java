package de.cimt.talendcomp.checksum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HashBuilder {
	
	private final StringBuilder content = new StringBuilder();
	private boolean caseInsensitive = false;
	private final DecimalFormat nf;
	private final SimpleDateFormat df;
	private static Charset cs = null;
	private final MessageDigest mDigest;
	
	private HashBuilder(String hashMethod) throws NoSuchAlgorithmException {
		nf = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setGroupingUsed(false);
		df = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
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
		return this;
	}
	
	public HashBuilder caseInsensitive() {
		caseInsensitive = true;
		return this;
	}

	public HashBuilder add(final String value) {
		if (value == null) {
			content.append("null");
		} else {
			if (caseInsensitive) {
				content.append(value.trim().toLowerCase(Locale.ENGLISH));
			} else {
				content.append(value.trim());
			}
			content.append("|");
		}
		return this;
	}
	
	public HashBuilder add(final Integer value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(nf.format(value));
		}
		return this;
	}
	
	public HashBuilder add(final Long value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(nf.format(value));
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Short value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(nf.format(value));
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Float value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(nf.format(value));
		}
		return this;
	}

	public HashBuilder add(final Double value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(nf.format(value));
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final BigDecimal value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(nf.format(value));
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final BigInteger value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(nf.format(value));
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Date value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(df.format(value));
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Boolean value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(value);
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final byte[] value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(value);
		}
		content.append("|");
		return this;
	}

	public HashBuilder add(final Byte value) {
		if (value == null) {
			content.append("null");
		} else {
			content.append(value);
		}
		content.append("|");
		return this;
	}
	
    public String build() throws NoSuchAlgorithmException {
        final byte[] result = mDigest.digest(content.toString().getBytes(cs));
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
