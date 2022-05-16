
package de.cimt.talendcomp.checksum;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

public class HashCalculation {
	
	private static String contentEncoding = "UTF-8"; 
	
	/**
	 * supported encoding methods in which the hash value will be represented
	 */
	public static enum HASH_OUTPUT_ENCODINGS {
		BASE64, HEX
	}
	
    public static String getMD5Hash(String content, HASH_OUTPUT_ENCODINGS hashOutputEncoding) {
        return digest("MD5", content, hashOutputEncoding);
    }
    
    public static String getSHA1Hash(String content, HASH_OUTPUT_ENCODINGS hashOutputEncoding) {
        return digest("SHA1", content, hashOutputEncoding);
    }
    
    public static String getSHA256Hash(String content, HASH_OUTPUT_ENCODINGS hashOutputEncoding) {
        return digest("SHA-256", content, hashOutputEncoding);
    }


    private static String digest(String digestAlgorithm, String content, HASH_OUTPUT_ENCODINGS hashOutputEncoding) {
    	
    	if(content == null)
    		return null;
    	
    	// argument checks
    	Objects.requireNonNull(digestAlgorithm, "digestAlgorithm must not be null");
    	Objects.requireNonNull(hashOutputEncoding, "encoding must not be null");
    	
    	// calculate hash
    	MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(digestAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Digest Algorithm " + digestAlgorithm + " could not be found in this environment.", e);
		}
    	
		final byte[] result = messageDigest.digest(content.getBytes(Charset.forName(contentEncoding)));
		
		// convert hash to requested encoding
		if(hashOutputEncoding.equals(HASH_OUTPUT_ENCODINGS.BASE64)) {
			return Base64.getEncoder().encodeToString(result);
		}else if (hashOutputEncoding.equals(HASH_OUTPUT_ENCODINGS.HEX)) {
			return hashAsHex(result);
		}
		
		return null;
    }

	private static String hashAsHex(byte[] hashAsByte) {
		
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hashAsByte.length; i++) {

            sb.append(Integer.toString((hashAsByte[i] & 0xff) + 0x100, 16).substring(1));

        }

        return sb.toString();
	}
	
}
