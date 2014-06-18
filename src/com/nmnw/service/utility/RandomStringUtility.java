package com.nmnw.service.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomStringUtility {
	private static final int TOKEN_LENGTH = 16; //32byte
	private static final int SALT_LENGTH = 8; //16byte

	public RandomStringUtility() {
	}

	/**
	 * Token����
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateToken() 
		throws NoSuchAlgorithmException {
		String value = generateRandomString(TOKEN_LENGTH);
		return value;
	}

	/**
	 * Salt����
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateSalt() 
			throws NoSuchAlgorithmException {
			String value = generateRandomString(SALT_LENGTH);
			return value;
	}

	/**
	 * �����_�������񐶐�
	 * @param length
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateRandomString(int length) 
			throws NoSuchAlgorithmException {
		byte token[] = new byte[length];
		StringBuffer buf = new StringBuffer();
		SecureRandom random = null;

		random = SecureRandom.getInstance("SHA1PRNG");
		random.nextBytes(token);
		for (int i = 0; i < token.length; i++) {
			buf.append(String.format("%02x", token[i]));
		}
		return buf.toString();
	}
}
