package com.first.start.common.util.crypt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
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

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

/**
 * @ClassName: RSAUtil
 * @Description: RSA加密解密，加签解签工具类
 * @author 忙碌的菠萝
 * @date 2020年11月27日 下午1:19:55
 *
 */
public class RSAUtil {
	
	public static String DEFAULT_PK = 
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDblel5BFNPG+HTSGJgGOBhNsv2\r\n" + 
			"WOqwU7Dvxuj1A+nU3M3eXTLY/xFU7q9LyxG4yGiR3VvPgjaduiO2WGF2sZECpwf6\r\n" + 
			"Hjh4aNJSCsukFrkfClZ2CvissHVhxXv/DJfH2AZycBcvcFxKrKbUbU9WH46o8F7K\r\n" + 
			"AGruU0JkBTDaRAZMgQIDAQAB";
	public static String DEFAULT_PRIK = 
			"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANuV6XkEU08b4dNI\r\n" + 
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
	
	/* 
	 * 常见异常对照 
	 * NoSuchAlgorithmException 无此算法，一般是环境问题，编译环境或者运行环境
	 * InvalidKeyException 秘钥非法，秘钥数据或者格式有问题，---begin--- 这种不需要
	 * IllegalBlockSizeException 明文长度非法，秘钥大小决定了可加密明文的长度，长度不符合会报出该异常
	 * BadPaddingException，NoSuchPaddingException 明文数据非法
	 * UnsupportedEncodingException 不支持的编码方式
	 * */

	/**
	 * @Fields RSA : RSA算法
	 */
	public static final String RSA = "RSA";
	/**
	 * @Fields SIGN_ALGORITHMS : 签名算法类型
	 */
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	 * @Fields KEY_SIZE : 密钥长度 于原文长度对应 以及越长速度越慢
	 */
	public static final int KEY_SIZE = 1024;

	/**
	 * @Fields keyMap : 随机产生的公钥与私钥 0-公钥 1-私钥
	 */
	public static Map<Integer, String> keyMap = new HashMap<Integer, String>();

	/**
	 * @Title: genKeyPair
	 * @Description: Generator KeyPair
	 * @param getPrivate() 得到私钥 getPublic() 得到公钥
	 * @return KeyPair
	 * @throws NoSuchAlgorithmException 无此算法
	 */
	public static KeyPair genKeyPair() throws NoSuchAlgorithmException {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;
		keyPairGen = KeyPairGenerator.getInstance(RSA);
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(KEY_SIZE, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		return keyPair;
	}

	/**
	 * @Title: GeneratorKey
	 * @Description: 随机生成密钥对
	 * @param
	 * @return Map<Integer, String> keyMap 秘钥对 0-公钥 1-私钥
	 * @throws Exception
	 */
	public static void GeneratorKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
		keyPairGen.initialize(KEY_SIZE, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 生成秘钥对
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 得到私钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); // 得到公钥
		// base64转码
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
		keyMap.put(0, publicKeyString); // 0表示公钥
		keyMap.put(1, privateKeyString); // 1表示私钥
	}
	
	/**
	* @Title: loadKeyByFile
	* @Description: 从文件中获取秘钥串
	* @param fileName 文件路径
	* @return String 秘钥串
	* @throws
	*/
	public static String loadKeyByFile(String fileName) throws Exception {
		try {
			/*
			 * FTPClient ftpClient = new FTPClient(); ftpClient.connect(HOST);
			 * ftpClient.login(USERNAME,PASSWORD);
			 * ftpClient.changeWorkingDirectory(INVOICE_BASE_PATH); InputStream is =
			 * ftpClient.retrieveFileStream("privateKey.keystore");
			 */
			FileInputStream is = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	/**
	 * @Title: encrypt 
	 * @Description: RSA公钥加密 
	 * @param encryptData 待加密数据
	 * @param publicKey base64编码的公钥 
	 * @return String 加密后的数据 
	 * @throws
	 */
	public static String encrypt(String encryptData, String publicKey) throws Exception {
		try {
			// base64编码的公钥，需要先decode
			byte[] decoded = Base64.decodeBase64(publicKey);
			RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA)
					.generatePublic(new X509EncodedKeySpec(decoded));
			// RSA加密
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			String outStr = Base64.encodeBase64String(cipher.doFinal(encryptData.getBytes("UTF-8")));
			return outStr;
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException("无此加密算法，请检查环境");
		} catch (NoSuchPaddingException e) {
			throw new NoSuchPaddingException("明文数据未找到");
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException("加密秘钥非法，请检查");
		} catch (IllegalBlockSizeException e) {
			throw new IllegalBlockSizeException("明文长度非法");
		} catch (BadPaddingException e) {
			throw new BadPaddingException("明文数据已损坏");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("其他错误:", e);
		}
	}

	/**
	 * @Title: encrypt 
	 * @Description: RSA公钥加密
	 * @param encryptData 待加密数据
	 * @param publicKey base64编码的公钥 
	 * @param encoding 待加密数据编码 
	 * @return String 加密后的数据 
	 * @throws Exception
	 */
	public static String encrypt(String encryptData, String publicKey, String encoding) throws Exception {
		try {
			// base64编码的公钥，需要先decode
			byte[] decoded = Base64.decodeBase64(publicKey);
			RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA)
					.generatePublic(new X509EncodedKeySpec(decoded));
			// RSA加密
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			String outStr = Base64.encodeBase64String(cipher.doFinal(encryptData.getBytes(encoding)));
			return outStr;
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException("无此加密算法，请检查环境");
		} catch (NoSuchPaddingException e) {
			throw new NoSuchPaddingException("明文数据未找到");
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException("加密公钥非法，请检查");
		} catch (IllegalBlockSizeException e) {
			throw new IllegalBlockSizeException("明文长度非法");
		} catch (BadPaddingException e) {
			throw new BadPaddingException("明文数据已损坏");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("其他错误:", e);
		}
	}
	
    /**
    * @Title: encrypt
    * @Description: 大数据分片加密
    * @param 
    * @return String
    * @throws
    */
    public static String encrypt(String encryptData, String publicKey, boolean flag) throws Exception {
    	try {
			byte[] decoded = Base64.decodeBase64(publicKey);
			RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA)
					.generatePublic(new X509EncodedKeySpec(decoded));
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			int MaxBlockSize = KEY_SIZE / 8;
	        int len = (MaxBlockSize - 11) / 8;
	        String[] encryptDatas = splitString(encryptData, len);
	        StringBuffer outStr = new StringBuffer();
	        for (String sp : encryptDatas) {
	        	outStr.append(bcd2Str(cipher.doFinal(sp.getBytes())));
	        }
	        return outStr.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException("无此加密算法，请检查环境");
		} catch (NoSuchPaddingException e) {
			throw new NoSuchPaddingException("明文数据未找到");
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException("加密公钥非法，请检查");
		} catch (IllegalBlockSizeException e) {
			throw new IllegalBlockSizeException("明文长度非法");
		} catch (BadPaddingException e) {
			throw new BadPaddingException("明文数据已损坏");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("其他错误:", e);
		}
        

    }

    /**
	* @Title: decrypt
	* @Description: RSA私钥解密
	* @param privateKey base64编码的私钥
	* @return String
	* @throws Exception 解密过程中的异常信息
	*/
	public static String decrypt(String decryptData, String privateKey) throws Exception {
		// 64位解码 加密后的字符串
		byte[] inputByte = Base64.decodeBase64(decryptData.getBytes("utf-8"));
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA)
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
	
		String decryptStr = new String(cipher.doFinal(inputByte));
		return decryptStr;
	}
    
    
	/**
	* @Title: decrypt
	* @Description: RSA私钥解密
	* @param privateKey base64编码的私钥
	* @return String
	* @throws Exception 解密过程中的异常信息
	*/
	public static String decrypt(String decryptData, String privateKey, String encoding) throws Exception {
		// 64位解码 加密后的字符串
		byte[] inputByte = Base64.decodeBase64(decryptData.getBytes(encoding));
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA)
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String decryptStr = new String(cipher.doFinal(inputByte));
		return decryptStr;
	}
	
	/**
	* @Title: decrypt
	* @Description: RSA私钥解密
	* @param privateKey base64编码的私钥
	* @return String
	* @throws Exception 解密过程中的异常信息
	*/
	public static String decrypt(String decryptData, String privateKey, String encoding,boolean flag) throws Exception {
		// 64位解码 加密后的字符串
		byte[] inputByte = Base64.decodeBase64(decryptData.getBytes(encoding));
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA)
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String decryptStr = new String(cipher.doFinal(inputByte));
		return decryptStr;
	}

	/**
	* @Title: decrypt
	* @Description: 私钥解密
	* @param privateKey rsa私钥对象
	* @param cipherData 数据流
	* @return byte[] 解密后的数据流
	* @throws
	*/
	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(cipherData);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}
	
	/**
	* @Title: sign
	* @Description: RSA签名
	* @param signData 待签名数据
	* @param privateKey 私钥字符串
	* @param encoding 字符集编码
	* @return String 签名域
	* @throws
	*/
	public static String sign(String signData, String privateKey, String encoding) throws Exception {
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(BASE64.decode(privateKey));
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyf.generatePrivate(priPKCS8);
		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(priKey);
		signature.update(signData.getBytes(encoding));
		byte[] signed = signature.sign();
		return BASE64.encode(signed);
	}

	/**
	* @Title: sign
	* @Description: RSA签名
	* @param signData 待签名数据
	* @param privateKey 私钥字符串
	* @return String 签名域
	* @throws
	*/
	public static String sign(String content, String privateKey) throws Exception {
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(BASE64.decode(privateKey));
		KeyFactory keyf = KeyFactory.getInstance(RSA);
		PrivateKey priKey = keyf.generatePrivate(priPKCS8);
		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(priKey);
		signature.update(content.getBytes());
		byte[] signed = signature.sign();
		return BASE64.encode(signed);
	}

	/**
	* @Title: verify
	* @param content   待签名数据
	* @param sign      签名域
	* @param publicKey base64后的公钥字符串
	* @param encode    字符集编码
	* @return boolean 验签结果
	* @throws
	*/
	public static boolean verify(String signData, String sign, String publicKey, String encode) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = BASE64.decode(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
		signature.initVerify(pubKey);
		signature.update(signData.getBytes(encode));
		boolean bverify = signature.verify(BASE64.decode(sign));
		return bverify;
	}

	/**
	* @Title: verify
	* @Description: RSA验签名检查
	* @param content   待签名数据
	* @param sign      签名域
	* @param publicKey base64后的公钥字符串
	* @return boolean 验签结果
	* @throws
	*/
	public static boolean verify(String content, String sign, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
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
	}
	
    /**
    * @Title: splitString
    * @Description: 字符串分片
    * @param string 源字符串
    * @param len 单片的长度（keysize/8）
    * @return String[]
    * @throws
    */
    public static String[] splitString(String string, int len) {
        int x = string.length() / len;
        int y = string.length() % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for (int i = 0; i < x + z; i++) {
            if (i == x + z - 1 && y != 0) {
                str = string.substring(i * len, i * len + y);
            } else {
                str = string.substring(i * len, i * len + len);
            }
            strings[i] = str;
        }
        return strings;
    }
    
    /**
    * @Title: bcd2Str
    * @Description: bcd 转 Str
    * @param bytes
    * @return String
    * @throws
    */
    public static String bcd2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;
        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }
}
