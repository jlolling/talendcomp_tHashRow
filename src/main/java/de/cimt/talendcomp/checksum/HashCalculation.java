
package de.cimt.talendcomp.checksum;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class HashCalculation {
	
	public static String getMD5Hash(String content) {
        return digest("MD5", content);
    }
    
    public static String getSHA1Hash(String content) {
        return digest("SHA1", content);
    }
    
    public static String getSHA256Hash(String content) {
        return digest("SHA-256", content);
    }

    private static String digest(String digestAlgorithm, String content) {
    	Objects.requireNonNull(digestAlgorithm, "digestAlgorithm must not be null");
    	Objects.requireNonNull(content, "content must not be null");
    	
    	MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(digestAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Digest Algorithm " + digestAlgorithm + " could not be found in this environment.", e);
		}
    	
        return digest(messageDigest, content);
    }

	private static String digest(MessageDigest messageDigest, String content) {
		final byte[] result = messageDigest.digest(content.getBytes(Charset.forName("UTF-8")));
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < result.length; i++) {

            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));

        }

        return sb.toString();
	}
	
}
