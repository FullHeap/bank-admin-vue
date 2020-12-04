package com.first.start.common.util.crypt;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.apache.tomcat.util.codec.binary.Base64;

public class TestCrypt {

	public static void main(String[] args) {
//		testRSA();
		testDES();
	}
	
	public static void testRSA() {
		try {
//			// 生成密钥对
//			KeyPair keyPair = RSAEncrypt.getKeyPair();
//			String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
//			String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
//			System.out.println("私钥:" + privateKey);
//			System.out.println("公钥:" + publicKey);
//
//			// 得到私钥
//			RSAPrivateKey privateKey1 = (RSAPrivateKey) keyPair.getPrivate();
//			// 得到公钥
//			RSAPublicKey publicKey1 = (RSAPublicKey) keyPair.getPublic();
//			// 得到公钥字符串
//			String publicKeyString = BASE64.encode(publicKey1.getEncoded());
//			// 得到私钥字符串
//			String privateKeyString = BASE64.encode(privateKey1.getEncoded());
//			System.err.println(publicKeyString);
//			System.err.println(privateKeyString);

			// RSA加密
			String data = "1111";

             String sign1 = "y69Axwpg0HTuSf/LGXFnQ+KibWI/bDHX4AHO/HVJQkfrvLVloKhaZI+hdi3xOJEvTcaNjv4h5emS4YXoPLhxXlUYR335TXOzDwEYiNJLZHdeLt3SpfDcBTYWmfMug0AcYYxuMdbbHvsrDkP9PL97Y9CYt/6ny8fYEHOe9Kq9r54=";
             String data1 = "{\"username\":\"admin\",\"password\":\"ebf4582d6ed99669b7194a70037050fb\"}";
             // RSA签名
             String sign = RSAUtil.sign(data, RSAUtil.DEFAULT_PRIK);
             // RSA验签
             boolean result = RSAUtil.verify(data, sign, RSAUtil.DEFAULT_PK);
             System.out.println("验签结果:" + result);
             
             boolean result1 = RSAUtil.verify(data1, sign1, RSAUtil.DEFAULT_PK);
             System.out.println("验签结果:" + result1);
             
             String key = "hfZOxBwe";
             String enlop = "eCV3Nlzm7/YEQHFOwtpjBor910Z7UEOrv68UZ0f/a79aq8NvzcvfcXIWvBQ788jBJfEh1/FEu0ozAOJYxkHNyvn80Yjn4vTTbhVdb/8etcY9eEYPCeU+vsnx5NxVjvmOvX5IixXucoBIbC3MVMN+9I7jy6tpBrZByFF1WZex04o=";
             
 			String encryptData = new String(RSAUtil.encrypt(key,RSAUtil.DEFAULT_PK));
 			System.out.println("加密后内容:" + encryptData);
 			// RSA解密
 			String decryptData = new String(RSAUtil.decrypt(enlop, RSAUtil.DEFAULT_PRIK));
 			System.out.println("解密后内容:" + decryptData);
             
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("加解密异常");
		}
	}
	
	public static void testDES() {
		try {
			
			String data = "admin123";
			String encryptdata = DESUtil.encrypt(data, "n3UQWqZY");
			String decryptdata = DESUtil.decrypt(encryptdata, "n3UQWqZY");
			System.out.println(encryptdata);
			System.out.println(decryptdata);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("加解密异常");
		}
	}

}
