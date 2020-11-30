package com.first.start.common.util.crypt;

import java.lang.reflect.Field;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA签名验签类
 */
public class RSASignature {

	/**
	 * 签名算法
	 */
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

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

	/**
	 * 对象转HashMap
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Field[] declaredFields = obj.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * @Description: 将签名的参数内容按参数名的字典顺序进行排序，并拼接为字符串
	 */
	public static String getContent(Map<String, Object> map) {
		// 得到第三方签名 第三方会把sign也放在json里，故转map的时候需要把sign删除
		map.remove("sign");
		map.entrySet().removeIf(entry -> entry.getValue() == null);
		// 将签名的参数内容按参数名的字典顺序进行排序，并拼接为字符串
		StringBuilder sb = new StringBuilder();
		map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
				.forEach(entry -> sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
		return sb.toString().substring(0, sb.length() - 1);
	}

	/**
	 * 签名方法 因为很多处都需要用，特地封装一下，方便调用
	 * 
	 * @param paramStr  按字典顺序拼接过的字符串
	 * @param publicKey 从数据库查询出来的第三方公钥
	 * @param sign      第三方签名
	 * @return
	 */
	public static boolean signVerify(String paramStr, String publicKey, String sign) {
		System.err.println(paramStr);
		try {
			// String privateSign = RSASignature.sign(paramStr,
			// RSAEncrypt.loadPrivateKeyByFile());
			// 使用公钥进行验签
			boolean result = RSASignature.doCheck(paramStr, sign, publicKey);
			if (result) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
