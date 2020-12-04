package com.first.start.common.util.crypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypt {
	public Crypt() {
		try {
			byte[] prvKeyBytes = loadBytes("E:\\dev\\workspace\\bank-admin-vue\\first\\src\\main\\java\\com\\first\\start\\common\\util\\crypt\\pri.key"); // PKCS#8 private key

			KeyFactory kf = KeyFactory.getInstance("RSA");
//			KeySpec keySpec = new X509EncodedKeySpec(prvKeyBytes);
			KeySpec keySpec = new PKCS8EncodedKeySpec(prvKeyBytes);
			PrivateKey prvKey = kf.generatePrivate(keySpec);

			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initSign(prvKey);
			sig.update("aaa".getBytes());
			byte[] sigBytes = sig.sign();
			System.out.println(sigBytes);

			saveBytes(sigBytes, "TSign.sig.bin");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static byte[] loadBytes(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			
			byte[] data = new byte[fis.available()];
			fis.read(data);
			System.out.println(new String(data));
			data = Base64.decodeBase64(data);
			fis.close();
			return data;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static void saveBytes(byte[] data, String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(data);
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Crypt t = new Crypt();
	}
}
