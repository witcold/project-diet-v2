package com.dataart.spring.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * @author vmeshcheryakov
 *
 */
public class PasswordHashing {

	public static final String SALT_STRING = "1243418ab49d816ddb58c9b5b7fba0308af8132d";

	public static String encode(String data) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
		}
		if (data != null && !data.isEmpty()) {
			return new String(Hex.encodeHex(md.digest((data + SALT_STRING).getBytes())));
		} else {
			return "";
		}
	}

	public static boolean check(String data, String hash) {
		return encode(data).equals(hash);
	}

}
