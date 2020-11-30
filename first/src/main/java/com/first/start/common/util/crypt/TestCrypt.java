package com.first.start.common.util.crypt;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.apache.tomcat.util.codec.binary.Base64;

public class TestCrypt {

	public static void main(String[] args) {
		try {
			// 生成密钥对
			KeyPair keyPair = RSAEncrypt.getKeyPair();
			String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
			String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
			System.out.println("私钥:" + privateKey);
			System.out.println("公钥:" + publicKey);

			// 得到私钥
			RSAPrivateKey privateKey1 = (RSAPrivateKey) keyPair.getPrivate();
			// 得到公钥
			RSAPublicKey publicKey1 = (RSAPublicKey) keyPair.getPublic();
			// 得到公钥字符串
			String publicKeyString = BASE64.encode(publicKey1.getEncoded());
			// 得到私钥字符串
			String privateKeyString = BASE64.encode(privateKey1.getEncoded());
			System.err.println(publicKeyString);
			System.err.println(privateKeyString);

			// RSA加密
			String data = "1111";
			String encryptData = new String(RSAEncrypt.encrypt(publicKey1, data.getBytes()));
			System.out.println("加密后内容:" + encryptData);
             // RSA解密
             String decryptData = new String(RSAEncrypt.decrypt(privateKey1,encryptData.getBytes()));
             System.out.println("解密后内容:" + decryptData);
//             
//             // RSA签名
//             String sign = sign(data, getPrivateKey(privateKey));
//             // RSA验签
//             boolean result = verify(data, getPublicKey(publicKey), sign);
//             System.out.print("验签结果:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("加解密异常");
		}
	}

}
