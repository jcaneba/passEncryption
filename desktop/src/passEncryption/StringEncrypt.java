package passEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.*;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringEncrypt {

	
	private static final byte[] key = "J+TTPb0UW=Nu1KEY".getBytes();
	private static final byte[] initVector = "IVEAJPvHIv7w}AO]".getBytes();
    private static final byte[] desKey = "NsabN6Me2DiFI4eoZ*q#E4Yi".getBytes();

	/* NOTES
	 * Encriptado: DES y Base64 - BLOWFISH y Base64 - AES y Base64
	 * Desencriptado: Base64 y AES - Base64 y BLOWFISH - Base64 y DES
	*/


	public static String aesEncrypt(String plainText) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);

			byte[] bytes=plainText.getBytes();

			byte[] aesEncrypted = cipher.doFinal(bytes);

			byte[] base64Encrypt=Base64.getEncoder().withoutPadding().encode(aesEncrypted);
			
			String encrypted=new String(base64Encrypt);
			
			return encrypted;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String aesDecrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);

			byte[] bytes=encrypted.getBytes();

			byte[] base64Decrypt=Base64.getDecoder().decode(bytes);

			byte[] original = cipher.doFinal(base64Decrypt);

			String decrypted=new String(original);

			return decrypted;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String blowfishEncrypt(String plainText){
		try {

			SecretKeySpec skeySpec = new SecretKeySpec(key, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			byte[] bytes=plainText.getBytes();

			byte[] blowfishEncrypted = cipher.doFinal(bytes);

			byte[] base64Encrypt=Base64.getEncoder().withoutPadding().encode(blowfishEncrypted);

			String encrypted=new String(base64Encrypt);

			return encrypted;

		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		} catch (NoSuchPaddingException e3) {
			e3.printStackTrace();
		} catch (InvalidKeyException e4) {
			e4.printStackTrace();
		} catch (IllegalBlockSizeException e5) {
			e5.printStackTrace();
		} catch (BadPaddingException e6) {
			e6.printStackTrace();
		}
		return null;

	}

	public static String blowfishDecrypt(String encrypted){
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key, "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] bytes=encrypted.getBytes();

			byte[] base64Decrypt=Base64.getDecoder().decode(bytes);

			byte[] original = cipher.doFinal(base64Decrypt);

			String decrypted=new String(original);

			return decrypted;

		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		} catch (NoSuchPaddingException e3) {
			e3.printStackTrace();
		} catch (InvalidKeyException e4) {
			e4.printStackTrace();
		} catch (IllegalBlockSizeException e5) {
			e5.printStackTrace();
		} catch (BadPaddingException e6) {
			e6.printStackTrace();
		}
		return null;
	}


	public static String desEncrypt(String plainText){

		try {

			byte[] ivDes="asdfghjk".getBytes();
			IvParameterSpec iv = new IvParameterSpec(ivDes);

			SecretKeySpec sKeySpec = new SecretKeySpec(desKey, "DESede");

			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, sKeySpec,iv);

			byte[] bytes=plainText.getBytes();

			byte[] aesEncrypted = cipher.doFinal(bytes);

			byte[] base64Encrypt=Base64.getEncoder().withoutPadding().encode(aesEncrypted);

			String encrypted=new String(base64Encrypt);

			return encrypted;

		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e2) {
			e2.printStackTrace();
		} catch (NoSuchPaddingException e3) {
			e3.printStackTrace();
		} catch (IllegalBlockSizeException e4) {
			e4.printStackTrace();
		} catch (BadPaddingException e5) {
			e5.printStackTrace();
		} catch (InvalidAlgorithmParameterException e6) {
			e6.printStackTrace();
		}
		return null;

	}

	public static String desDecrypt(String encrypted){
		try {

			byte[] ivDes="asdfghjk".getBytes();
			IvParameterSpec iv = new IvParameterSpec(ivDes);

			SecretKeySpec sKeySpec = new SecretKeySpec(desKey, "DESede");

			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, sKeySpec,iv);

			byte[] bytes=encrypted.getBytes();

			byte[] base64Decrypt=Base64.getDecoder().decode(bytes);

			byte[] original = cipher.doFinal(base64Decrypt);

			String decrypted=new String(original);

			return decrypted;

		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e2) {
			e2.printStackTrace();
		} catch (NoSuchPaddingException e3) {
			e3.printStackTrace();
		} catch (IllegalBlockSizeException e4) {
			e4.printStackTrace();
		} catch (BadPaddingException e5) {
			e5.printStackTrace();
		} catch (InvalidAlgorithmParameterException e6) {
			e6.printStackTrace();
		}
		return null;

	}


	public static String base64(String option, String text){

		if(option.equals("encrypt")) {
			byte[] bytes=text.getBytes();
			byte[] encodedString = Base64.getEncoder().withoutPadding().encode(bytes);
			return new String(encodedString);

		}else if(option.equals("decrypt")) {
			byte[] decrypted = Base64.getDecoder().decode(text);
			return new String(decrypted);

		}else {
			return null;
		}
	}
	
}
