package com.djcps.wms.commons.utils;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 加密公共类
 * @author
 */
public class RsaSecret {
	/**
	 * 公钥加密
	 * 
	 * @param content 要加密的文本
	 * @param pubkey 公钥
	 * @return 密文
	 */
	public static String encrypt(String content, String pubkey) {
		byte[] cipherText = null;
		String text = "";
		try {
			// get an RSA cipher object and print the provider
			Cipher cipher = Cipher.getInstance("RSA");
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(pubkey));
			cipherText = cipher.doFinal(content.getBytes());
			text = new sun.misc.BASE64Encoder().encode(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}


	
	/**
	 * 得到私钥 PKCS8
	 * 
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKeyPKCS8(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = new sun.misc.BASE64Decoder().decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		return privateKey;
	}

	/**
	 * 得到公钥
	 * 
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = new sun.misc.BASE64Decoder().decodeBuffer(key);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

}
