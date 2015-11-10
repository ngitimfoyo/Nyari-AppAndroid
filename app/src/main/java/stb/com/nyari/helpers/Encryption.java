/*
 * For Encrypt and Decrypt string added by Gus Ari
 */

package stb.com.nyari.helpers;

import android.annotation.SuppressLint;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

// TODO: Auto-generated Javadoc

/**
 * The Class Encryption.
 */
public class Encryption {
	
	/** The charset name. */
	private String charsetName = Constants.UTF8;
	
	/** The algorithm. */
	private String algorithm = Constants.DES;
	
	/** The base64 mode. */
	private int base64Mode = Base64.DEFAULT;

	/**
	 * Gets the charset name.
	 *
	 * @return the charset name
	 */
	public String getCharsetName() {
		return charsetName;
	}

	/**
	 * Sets the charset name.
	 *
	 * @param charsetName the new charset name
	 */
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	/**
	 * Gets the algorithm.
	 *
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * Sets the algorithm.
	 *
	 * @param algorithm the new algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * Gets the base64 mode.
	 *
	 * @return the base64 mode
	 */
	public int getBase64Mode() {
		return base64Mode;
	}

	/**
	 * Sets the base64 mode.
	 *
	 * @param base64Mode the new base64 mode
	 */
	public void setBase64Mode(int base64Mode) {
		this.base64Mode = base64Mode;
	}

	/**
	 * Encrypt.
	 *
	 * @param key the key
	 * @param data the data
	 * @return the string
	 */
	@SuppressLint("TrulyRandom")
	public String encrypt(String key, String data) {
		if (key == null || data == null)
			return null;
		try {
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
			SecretKeyFactory secretKeyFactory = SecretKeyFactory
					.getInstance(algorithm);
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			byte[] dataBytes = data.getBytes(charsetName);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.encodeToString(cipher.doFinal(dataBytes), base64Mode);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Decrypt.
	 *
	 * @param key the key
	 * @param data the data
	 * @return the string
	 */
	public String decrypt(String key, String data) {
		if (key == null || data == null)
			return null;
		try {
			byte[] dataBytes = Base64.decode(data, base64Mode);
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
			SecretKeyFactory secretKeyFactory = SecretKeyFactory
					.getInstance(algorithm);
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
			return new String(dataBytesDecrypted);
		} catch (Exception e) {
			return null;
		}
	}
}
