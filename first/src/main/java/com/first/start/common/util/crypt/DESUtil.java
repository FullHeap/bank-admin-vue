package com.first.start.common.util.crypt;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
//import javax.crypto.spec.IvParameterSpec;


/**
 * @ClassName: DESUtil
 * @Description: des工具类
 * @author 忙碌的菠萝
 * @date 2020年11月30日 上午9:42:14
 *
 */
public class DESUtil {

//	private static final String IV_PARAMETER = "12345678";
	private static final String DEFAULT_KEY = "kxIfOIZT";
	
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";
//    private static final String CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding";
    /**
     * 默认编码
     */
    private static final String CHARSET = "UTF-8";
	
	/**
	* @Title: generateKey
	* @Description: 生成SecretKey
	* @param key字符串
	* @return SecretKey
	* @throws
	*/
	private static SecretKey generateKey(String secretKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());
		keyFactory.generateSecret(keySpec);
		return keyFactory.generateSecret(keySpec);
	}

	/**
	* @Title: encrypt
	* @Description: 默认密码加密
	* @param 
	* @return String
	* @throws Exception
	*/
	public static String encrypt(String content) throws Exception {
		byte[] encrypted = encrypt(content.getBytes(), DEFAULT_KEY.getBytes());
		if (encrypted == null) {
			return null;
		}
		return byteToHexString(encrypted);
	}

	/**
	* @Title: encrypt
	* @Description: 加密
	* @param content 加密内容
	* @param key 密key
	* @return String
	* @throws
	*/
	public static String encrypt(String content, String key) throws Exception{
		byte[] encrypted = encrypt(content.getBytes(), key.getBytes());
		if (encrypted == null) {
			return null;
		}
		return byteToHexString(encrypted);
	}

	/**
	* @Title: decrypt
	* @Description: 默认密码解密
	* @param 
	* @return String
	* @throws
	*/
	public static String decrypt(String content) throws Exception {
		return decrypt(content, DEFAULT_KEY);
	}
	
	/**
	* @Title: decrypt
	* @Description: 解密
	* @param content 解密内容
	* @param key 密key
	* @return String
	* @throws
	*/
	public static String decrypt(String content, String key) throws Exception {
		try {
			
//			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
//			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
//			cipher.init(Cipher.DECRYPT_MODE, generateKey(key),iv);
			
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
			cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
			byte[] buf = cipher.doFinal(hexStr2Bytes(content));
			return new String(buf, CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("DES解密异常：" + e.getMessage());
		}
	}

	/**
	* @Title: encrypt
	* @Description: 加密方法
	* @param 
	* @return byte[]
	* @throws
	*/
	public static byte[] encrypt(byte[] content, byte[] keyBytes) throws Exception{
		try {

			DESKeySpec keySpec = new DESKeySpec(keyBytes);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey key = keyFactory.generateSecret(keySpec);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
//			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
//			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
//			cipher.init(Cipher.ENCRYPT_MODE, key ,iv );
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("DES加密异常："+e.getMessage());
		}
	}

	

	public static byte[] hexStr2Bytes(String src) {
		src = src.trim().replace(" ", "").toUpperCase(Locale.US);
		int m = 0, n = 0;
		int iLen = src.length() / 2; // 计算长度
		byte[] ret = new byte[iLen]; // 分配存储空间

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	public static String byteToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length);
		String sTemp;
		for (int i = 0; i < bytes.length; i++) {
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(decrypt("5f0ecd97486b7d69642dfa238e3f261d"));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}