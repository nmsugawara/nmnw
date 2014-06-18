package com.nmnw.service.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;

public class CipherUtility {
	public static String enctypt (String target) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			return Base64.encodeBase64URLSafeString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
