/**
 * 
 */
package com.dataart.spring.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * @author vmeshcheryakov
 *
 */
public class PasswordHashing {

//	public static final String SALT_STRING = "dataart";
	// = encode("pass") with commented code
	public static final String SALT_STRING = "1243418ab49d816ddb58c9b5b7fba0308af8132d";

	public static String encode(String data) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
		}
//		byte[] hash = md.digest(data.getBytes());
//		byte[] hashsalt = md.digest(SALT_STRING.getBytes());
//		byte[] result = new byte[hash.length + hashsalt.length];
//		System.arraycopy(hash, 0, result, 0, hash.length);
//		System.arraycopy(hashsalt, 0, result, hash.length, hashsalt.length);
//		return new String(Hex.encodeHex(md.digest(result)));
		return new String(Hex.encodeHex(md.digest((data+SALT_STRING).getBytes())));
	}

	public static boolean check(String data, String hash) {
		return encode(data).equals(hash);
	}

}
