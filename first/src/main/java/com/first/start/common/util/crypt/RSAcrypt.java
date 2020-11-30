package com.first.start.common.util.crypt;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAcrypt {
	public static Map<Integer, String> keyMap = new HashMap<Integer, String>(); // 用于封装随机产生的公钥与私钥

	/**
	 * 签名算法
	 */
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	
	public static void main(String[] args) throws Exception {
		//生成公钥和私钥
//		genKeyPair();
		//加密字符串
		String message = "df723820";
//		System.out.println("随机生成的公钥为:" + keyMap.get(0));
//		System.out.println("随机生成的私钥为:" + keyMap.get(1));
//		String messageEn = encrypt(message,keyMap.get(0));
//		System.out.println(message + "\t加密后的字符串为:" + messageEn);
//		String messageDe = decrypt(messageEn,keyMap.get(1));
//		System.out.println("还原后的字符串为:" + messageDe);
		
		
		String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDblel5BFNPG+HTSGJgGOBhNsv2\r\n" + 
				"WOqwU7Dvxuj1A+nU3M3eXTLY/xFU7q9LyxG4yGiR3VvPgjaduiO2WGF2sZECpwf6\r\n" + 
				"Hjh4aNJSCsukFrkfClZ2CvissHVhxXv/DJfH2AZycBcvcFxKrKbUbU9WH46o8F7K\r\n" + 
				"AGruU0JkBTDaRAZMgQIDAQAB";
		String pri = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANuV6XkEU08b4dNI\r\n" + 
				"YmAY4GE2y/ZY6rBTsO/G6PUD6dTczd5dMtj/EVTur0vLEbjIaJHdW8+CNp26I7ZY\r\n" + 
				"YXaxkQKnB/oeOHho0lIKy6QWuR8KVnYK+KywdWHFe/8Ml8fYBnJwFy9wXEqsptRt\r\n" + 
				"T1YfjqjwXsoAau5TQmQFMNpEBkyBAgMBAAECgYAK/Ios6uF8MMnwt0tswXHgi0xT\r\n" + 
				"LAm5/sX4iTw4nhSB+PY37PPRVZm5ZSPSJ/vgAe7xdWDVlg9Dfv+wbOWbebDmmE8K\r\n" + 
				"6FLfXFqYjSRDca7Ib9P9LE/voLgml5rMzBaTsK6519WEXjWkxZzIbFac5b2ZIQdr\r\n" + 
				"T9IzWgxcrGK/Bi66QQJBAPsqV5VMhOy0U7/ATot9+relafQzvnnBwk6ifi7Lheo6\r\n" + 
				"e510y+XrvKc1y+LtG+T5x2lRxCfebi1xSScMOW2XjnkCQQDfz/SPfzh7uu03OnG7\r\n" + 
				"dk/DATpIziXUCQKOtY8Bmp7koTeoSnacoTIIIXmAizJ42ESNfPPK2JucHn8h9JTl\r\n" + 
				"6QxJAkB59oWxKgciKi7A3lFFy1cD9n8M5lOILF5+cMl1T78njl6Yhy67500kpSrs\r\n" + 
				"dtckyWXb7qih85Ds4CX1oCoC3aWBAkAPS8gcEobKtgDGWIEzXaef3TKdjTE6p478\r\n" + 
				"L95hLq8TUw1ZvBUVKVMhCSCjr1+4sJcm0FZdE6a26cKokG2otN+5AkEAkFso/cB5\r\n" + 
				"oJF3cVK2+LuBeeLCtWX6vLUWAFHKzEK3N1tJvp01oEiPw9BbEkVgFm4zvdiYVw0i\r\n" + 
				"C6FlHaQYqnycpQ==";
		
		
		
		String messageEn1 = encrypt(message,pub);
		String messageDe1 = decrypt(messageEn1,pri);
		System.out.println(message + "\t加密后的字符串为:" + messageEn1);
		System.out.println("还原后的字符串为:" + messageDe1);
		String x = "xf/xbAag7Er+DUmbvTvH8LU/rnpy2ddOVsCEDKhK1rZhNmymE2nxTcBskCF9OPUfPYgyTOIRK0e9pQubdjoim3G0i3G5DmyCOUORTAydD7h0rPbO/RkiJZdDYXfVFq6IxaJbuewxHHzIvaWbfQaZROVqgjVXN3LnG2NyHTtRO0E=";
		String messageDe2 = decrypt(x,pri);
		System.out.println(messageDe2);
		
		
		String signx = sign(message, pri);
		System.out.println(message + "\t签名后的字符串为:" + signx);
		boolean result = doCheck(message,signx, pub);
		System.out.println(message + "\t签名后的字符串为:" + result);
		
	}

	/**
	 * @return keyPair
	 * 
	 * getPrivate() 得到私钥
	 * getPublic() 得到公钥
	 */
	public static KeyPair getKeyPair() {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
			// 初始化密钥对生成器，密钥大小为96-1024位
			keyPairGen.initialize(1024, new SecureRandom());
			// 生成一个密钥对，保存在keyPair中
			KeyPair keyPair = keyPairGen.generateKeyPair();
			return keyPair;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 随机生成密钥对
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static void genKeyPair() throws NoSuchAlgorithmException {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 得到私钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 得到公钥
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		// 得到私钥字符串
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
		// 将公钥和私钥保存到Map
		keyMap.put(0, publicKeyString); // 0表示公钥
		keyMap.put(1, privateKeyString); // 1表示私钥
	}
	
	
	

	/**
	 * RSA公钥加密
	 * 
	 * @param str       加密字符串
	 * @param publicKey 公钥
	 * @return 密文
	 * @throws Exception 加密过程中的异常信息
	 */
	public static String encrypt(String str, String publicKey) throws Exception {
		// base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
		// RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}

	/**
	 * RSA私钥解密
	 * 
	 * @param str        加密字符串
	 * @param privateKey 私钥
	 * @return 铭文
	 * @throws Exception 解密过程中的异常信息
	 */
	public static String decrypt(String str, String privateKey) throws Exception {
		// 64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		// base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		// RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}
	
	
	/**
	 * RSA签名
	 * 
	 * @param content    待签名数据
	 * @param privateKey 私钥
	 * @param encode     字符集编码
	 * @return 签名值
	 */
	public static String sign(String content, String privateKey, String encode) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(BASE64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(priKey);
			signature.update(content.getBytes(encode));
			byte[] signed = signature.sign();
			return BASE64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据 content 和 私钥 生成签名
	 * 
	 * @param content    按字典顺序排序的内容
	 * @param privateKey ftp上保存的私钥
	 * @return
	 */
	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(BASE64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(priKey);
			signature.update(content.getBytes());
			byte[] signed = signature.sign();
			return BASE64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * RSA验签名检查
	 * 
	 * @param content   待签名数据
	 * @param sign      签名值
	 * @param publicKey 分配给开发商公钥
	 * @param encode    字符集编码
	 * @return 布尔值
	 */
	public static boolean doCheck(String content, String sign, String publicKey, String encode) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = BASE64.decode(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initVerify(pubKey);
			signature.update(content.getBytes(encode));
			boolean bverify = signature.verify(BASE64.decode(sign));
			return bverify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * RSA验签名检查
	 * 
	 * @param content   待签名数据
	 * @param sign      签名值
	 * @param publicKey 分配给开发商公钥
	 * @return 布尔值
	 */
	public static boolean doCheck(String content, String sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = BASE64.decode(publicKey);
			if (encodedKey == null) {
				return false;
			}
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			// 用私钥对信息生成数字签名
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initVerify(pubKey);
			signature.update(content.getBytes());
			// 验证方法 返回true则为比对成功
			boolean bverify = signature.verify(BASE64.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
