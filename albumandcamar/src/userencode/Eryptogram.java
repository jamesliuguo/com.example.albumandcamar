/* *
 * Copyright 2002-2012 InsightCN(缃戣█淇℃伅绉戞妧鍜ㄨ鏈夐檺鍏徃)<br>
 *
 * ClassName   (绫诲悕): Eryptogram<br>
 * Function    (鍔熻兘): GMO鍚堜綔鍔犲瘑鍏叡绫�br>
 * Detail      (璇︾粏): 涓昏鐢ㄤ簬鍜孏MO鍚堜綔鐨勫姞瀵嗚В瀵嗘柟娉曠被<br>
 * Version     (鐗堟湰): V2.0<br>
 * Date        (鏃ユ湡): 2012-2-15 涓嬪崍1:45:57 <br>
 * Author      (浣滆�): 闄堣吘 <br>
 * Description (璇存槑): 
 * 瀵嗛挜:绠楁硶浜х敓鐨勫姞瀵嗙瀛�  <br/>
 * 瀵嗙爜:娌℃湁浠�箞濂借В閲婄殑锛岀櫥闄嗚鍧涜繕瑕佸憿 <br/>  
 * 绠楁硶:鍙互涓婂崌涓烘暟瀛︾悊璁�<br/>       
 * 涓句釜渚嬪瓙锛屼綘鐨勫瘑鐮佹槸123,浣嗘槸鏄庢枃鍙戦�瀹规槗琚汉鐩楀彇 <br/>    
 * 浣犵敤鏌愪釜绠楁硶鍔犲瘑锛屽瘑閽ユ槸锛�3csif253a6jt342fd621<br/>     
 * 浼犺緭鐨勪究鏄姞瀵嗚繃鐨勬暟鎹紝姣斿:fasetqrlkerqoiruq53qrqewlkjrlfasdfa41瑙ｅ瘑涔熼渶瑕佸瘑閽�<br/>    
 * 鍔犲瘑瀵嗛挜鍜岃В瀵嗗瘑閽ュ彲浠ョ浉鍚岋紙瀵圭О鍔犲瘑绠楁硶),涔熷彲浠ヤ笉鐩稿悓(闈炲绉扮畻娉�<br/>  
 *
 */
package userencode;

import java.security.MessageDigest;



import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import android.util.Log;

/* * 
 * @ClassName   (绫诲悕): Eryptogram 
 * @Description (璇存槑)锛氫富瑕佺敤浜庡拰GMO鍚堜綔鐨勫姞瀵嗚В瀵嗘柟娉曠被 
 * @author      (浣滆�)锛氶檲鑵�
 * @date        (鏃堕棿)锛�012-2-15 涓嬪崍1:45:57  
 */
public class Eryptogram {
	private static final String TAG = "Eryptogram";

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:55:38
	 * @Title (鏂规硶): byte2hex
	 * @Description(鎻忚堪): 瀛楄妭鐮佽浆鎹㈡垚16杩涘埗瀛楃涓�
	 * @param (鍙傛暟)锛欯param b杈撳叆瑕佽浆鎹㈢殑瀛楄妭鐮�
	 * @param (鍙傛暟)锛欯return 璁惧畾鏂囦欢
	 * @return String 杩斿洖杞崲鍚庣殑16杩涘埗瀛楃涓�
	 * @throws
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			if (n < b.length - 1) {
				hs = hs + ":";
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:57:26
	 * @Title (鏂规硶): byte2Str
	 * @Description(鎻忚堪): 浜岃鍒惰浆瀛楃涓�
	 * @param (鍙傛暟)锛欯param b
	 * @param (鍙傛暟)锛欯return 璁惧畾鏂囦欢
	 * @return String 杩斿洖绫诲瀷
	 * @throws
	 */
	private static String byte2Str(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			if (n < (b.length - 1)) {
				hs = hs + "";
			}
		}
		return hs;
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:57:57
	 * @Title (鏂规硶): encryptData
	 * @Description(鎻忚堪): 灏嗘寚瀹氱殑鏁版嵁鏍规嵁鎻愪緵鐨勫瘑閽ヨ繘琛屽姞瀵�
	 * @param (鍙傛暟)锛欯param input 闇�鍔犲瘑鐨勬暟鎹�
	 * @param (鍙傛暟)锛欯param key 瀵嗛挜
	 * @param (鍙傛暟)锛欯param algorithm 鍔犲瘑绠楁硶,鍙敤 DES,DESede,Blowfish
	 * @param (鍙傛暟)锛欯return 鍔犲瘑鍚庣殑鏁版嵁
	 * @param (鍙傛暟)锛欯throws Exception 璁惧畾鏂囦欢
	 * @return String 鍔犲瘑鍚庣殑瀛楃涓�
	 * @throws
	 */
	public static String encryptData(String input, String key, String algorithm)
			throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key.getBytes(),
				algorithm);
		if (debug) {
			Log.i(TAG, "$$鍔犲瘑鍓嶇殑瀛楃涓�" + input);
			Log.i(TAG, "$$鍔犲瘑鍓嶇殑浜岃繘涓�" + byte2hex(input.getBytes()));
		}
		Cipher c1 = Cipher.getInstance(algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(input.getBytes());
		if (debug) {
			Log.i(TAG, "$$鍔犲瘑鍚庣殑瀛楃涓�" + byte2Str(cipherByte));
		}
		return byte2Str(cipherByte);
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-8-15 涓嬪崍4:16:39
	 * @Title (鏂规硶): getCipher
	 * @Description(鎻忚堪): 鑾峰彇Cipher
	 * @param (鍙傛暟)锛欯param mode
	 * @param (鍙傛暟)锛欯param key
	 * @param (鍙傛暟)锛欯param iv
	 * @param (鍙傛暟)锛欯return
	 * @param (鍙傛暟)锛欯throws Exception 璁惧畾鏂囦欢
	 * @return Cipher 杩斿洖绫诲瀷
	 * @throws
	 */
	private static Cipher getCipher(final int mode, final String key,
			final String iv) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
		Cipher cipher = null;

		if (null != iv) {
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(mode, skeySpec, new IvParameterSpec(INIT_IV.getBytes()));
		} else {
			cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(mode, skeySpec);
		}
		return cipher;
	}

	public static void main(String[] args) {
		String testEmail = "test@insightcn.com";
		try {// 杩囩▼婕旂ず锛屾櫘閫氱殑鍔犺В瀵嗘搷浣�
			Eryptogram etg = new Eryptogram();
			Eryptogram.debug = true;
			byte[] key = etg.getSecretKey();
			String aa = "0123456789涓枃娴嬭瘯EnglishTest!%$#@!~~";
			byte[] en = etg.encryptData(aa.getBytes(), key);
			/*********************************************************/
			/* 涓轰簡灏嗗姞瀵嗘暟鎹啓鍏ユ枃浠朵絾涓嶅嚭鐜伴敊璇紝鐩墠鐨勬柟娉曟槸杞负int绫诲瀷杩涜瀛樺偍 */
			/*********************************************************/
			int[] intTemp = new int[en.length / 4];
			for (int i = 0; i < intTemp.length; i++) {
				byte[] byteTemp = new byte[4];
				byteTemp[0] = en[i * 4 + 0];
				byteTemp[1] = en[i * 4 + 1];
				byteTemp[2] = en[i * 4 + 2];
				byteTemp[3] = en[i * 4 + 3];
				intTemp[i] = etg.byteToint(byteTemp);
				// Log.i(TAG, "intTemp["+i+"]="+intTemp[i]);
			}

			/*********************************************************/
			/* 寰呮彁鍙栨枃浠跺唴瀹瑰悗锛屽皢int杞寲涓篵yte鍗冲彲杩涜瑙ｅ瘑 */
			/*********************************************************/
			byte[] decodeTemp = new byte[intTemp.length * 4];
			for (int i = 0; i < intTemp.length; i++) {
				byte[] byteTemp = etg.intTobyte(intTemp[i]);
				decodeTemp[i * 4 + 0] = byteTemp[0];
				decodeTemp[i * 4 + 1] = byteTemp[1];
				decodeTemp[i * 4 + 2] = byteTemp[2];
				decodeTemp[i * 4 + 3] = byteTemp[3];
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {// MD5闈炲绉板姞瀵�
			Eryptogram etg = new Eryptogram();
			Log.i(TAG, "瀵光�01234567鈥欒繘琛孧D5鍔犲瘑鍚庣殑缁撴灉:" + etg.encrypt("01234567"));

			Log.i(TAG, "testEmail:" + etg.encrypt(testEmail));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Fields Algorithm : 瀹氫箟 鍔犲瘑绠楁硶,鍙敤 DES,DESede,Blowfish
	 */
	public String Algorithm = "DES";

	public static final String ALGORITHM = "";

	public static final String INIT_IV = "";

	public static final String KEY_ALGORITHM = "Blowfish";

	/**
	 * @Fields debug : 璋冭瘯鏃朵娇鐢�
	 */
	public static boolean debug = false;

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-8-15 涓嬪崍4:00:51
	 * @Title (鏂规硶): decryptOnBlowfish
	 * @Description(鎻忚堪): 瑙ｅ瘑鐨勬柟娉�
	 * @param (鍙傛暟)锛欯param key
	 * @param (鍙傛暟)锛欯param input
	 * @param (鍙傛暟)锛欯return
	 * @param (鍙傛暟)锛欯throws Exception 璁惧畾鏂囦欢
	 * @return String 杩斿洖绫诲瀷
	 * @throws
	 */
	public static String decryptOnBlowfish(final String key, final String input)
			throws Exception {
		byte[] encrypted = Hex.decodeHex(input.toCharArray());
		Cipher cipher = getCipher(Cipher.DECRYPT_MODE, key, null);
		return new String(cipher.doFinal(encrypted));
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:57:01
	 * @Title (鏂规硶): byteToint
	 * @Description(鎻忚堪): byte[]绫诲瀷杞崲涓篿nt绫诲瀷
	 * @param (鍙傛暟)锛欯param input 寰呰浆鎹㈢殑byte鍊�
	 * @param (鍙傛暟)锛欯return 璁惧畾鏂囦欢
	 * @return int 杩斿洖绫诲瀷
	 * @throws
	 */
	public int byteToint(byte[] input) {
		int output = 0;
		if (input.length != 4) {
			Log.i(TAG, "byte绫诲瀷杞崲涓篿nt绫诲瀷鏃跺彂鐢熼敊璇紒");
			System.exit(-1);
		}
		output = (input[0] & 0xff) | ((input[1] << 8) & 0xff00)
				| ((input[2] << 24) >>> 8) | (input[3] << 24);
		return output;
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:53:08
	 * @Title (鏂规硶): decryptData
	 * @Description(鎻忚堪): 灏嗙粰瀹氱殑宸插姞瀵嗙殑鏁版嵁閫氳繃鎸囧畾鐨勫瘑閽ヨ繘琛岃В瀵�
	 * @param (鍙傛暟)锛欯param input 寰呰В瀵嗙殑鏁版嵁
	 * @param (鍙傛暟)锛欯param key 瀵嗛挜
	 * @param (鍙傛暟)锛欯return 瑙ｅ瘑鍚庣殑鏁版嵁
	 * @param (鍙傛暟)锛欯throws Exception 璁惧畾鏂囦欢
	 * @return byte[] 杩斿洖绫诲瀷
	 * @throws
	 */
	public byte[] decryptData(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug) {
			Log.i(TAG, "瑙ｅ瘑鍓嶇殑淇℃伅:" + byte2hex(input));
		}
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(input);
		if (debug) {
			Log.i(TAG, "瑙ｅ瘑鍚庣殑浜岃繘涓�" + byte2hex(clearByte));
			Log.i(TAG, "瑙ｅ瘑鍚庣殑瀛楃涓�" + (new String(clearByte)));
		}
		return clearByte;
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:59:02
	 * @Title (鏂规硶): decryptData
	 * @Description(鎻忚堪): 灏嗙粰瀹氱殑宸插姞瀵嗙殑鏁版嵁閫氳繃鎸囧畾鐨勫瘑閽ヨ繘琛岃В瀵�
	 * @param (鍙傛暟)锛欯param input
	 * @param (鍙傛暟)锛欯param key 鍙敤 DES,DESede,Blowfish
	 * @param (鍙傛暟)锛欯param algorithm
	 * @param (鍙傛暟)锛欯return 瑙ｅ瘑鍚庣殑鏁版嵁
	 * @param (鍙傛暟)锛欯throws Exception 璁惧畾鏂囦欢
	 * @return String 杩斿洖绫诲瀷
	 * @throws
	 */
	public String decryptData(String input, String key, String algorithm)
			throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key.getBytes(),
				algorithm);
		if (debug) {
			Log.i(TAG, "$$瑙ｅ瘑鍓嶇殑淇℃伅:" + input);
		}
		Cipher c1 = Cipher.getInstance(algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(input.getBytes());
		if (debug) {
			Log.i(TAG, "$$瑙ｅ瘑鍚庣殑浜岃繘涓�" + byte2hex(clearByte));
			Log.i(TAG, "$$瑙ｅ瘑鍚庣殑瀛楃涓�" + byte2Str(clearByte));
		}
		return new String(clearByte);
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:54:56
	 * @Title (鏂规硶): encrypt
	 * @Description(鎻忚堪): 瀵瑰瘑鐮佽繘琛孧D5鍔犲瘑(闈炲绉板姞瀵嗭紝涓嶈兘瑙ｅ瘑锛岄�鐢ㄤ簬瀵嗙爜鍖归厤)
	 * @param (鍙傛暟)锛欯param pwd
	 * @param (鍙傛暟)锛欯return
	 * @param (鍙傛暟)锛欯throws NoSuchAlgorithmException 璁惧畾鏂囦欢
	 * @return String 杩斿洖绫诲瀷
	 * @throws
	 */
	public String encrypt(String pwd) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pwd.getBytes());
		byte[] result = md.digest();
		return byte2Str(result);
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:52:15
	 * @Title (鏂规硶): encryptData
	 * @Description(鎻忚堪): 灏嗘寚瀹氱殑鏁版嵁鏍规嵁鎻愪緵鐨勫瘑閽ヨ繘琛屽姞瀵�
	 * @param (鍙傛暟)锛欯param input 闇�鍔犲瘑鐨勬暟鎹�
	 * @param (鍙傛暟)锛欯param key 瀵嗛挜
	 * @param (鍙傛暟)锛欯return
	 * @param (鍙傛暟)锛欯throws Exception 璁惧畾鏂囦欢
	 * @return byte[] 鍔犲瘑鍚庣殑鏁版嵁
	 * @throws
	 */
	public byte[] encryptData(byte[] input, byte[] key) throws Exception {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		if (debug) {
			Log.i(TAG, "鍔犲瘑鍓嶇殑瀛楃涓�" + new String(input));
			Log.i(TAG, "鍔犲瘑鍓嶇殑浜岃繘涓�" + byte2hex(input));
		}
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(input);
		if (debug) {
			Log.i(TAG, "鍔犲瘑鍚庣殑瀛楃涓�" + byte2Str(cipherByte));
		}
		Log.i(TAG, "鍔犲瘑鍚庣殑浜岃繘涓�" + byte2hex(cipherByte));
		return cipherByte;
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:50:00
	 * @Title (鏂规硶): getSecretKey
	 * @Description(鎻忚堪): 鐢熸垚瀵嗛挜,闅忔満鐢熸垚锛岀洰鍓嶅彧浣滅敤浜庢紨绀�
	 * @param (鍙傛暟)锛欯return
	 * @param (鍙傛暟)锛欯throws Exception 璁惧畾鏂囦欢
	 * @return byte[] 杩斿洖鐢熸垚鐨勫瘑閽�
	 * @throws
	 */
	public byte[] getSecretKey() {
		KeyGenerator keygen;
		SecretKey deskey = null;
		try {
			keygen = KeyGenerator.getInstance(Algorithm);
			deskey = keygen.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (debug) {
			Log.i(TAG, "鐢熸垚瀵嗛挜:" + byte2hex(deskey.getEncoded()));
		}
		return deskey.getEncoded();
	}

	/**
	 * @author (浣滆�)锛氶檲鑵�2012-2-15 涓嬪崍1:56:19
	 * @Title (鏂规硶): intTobyte
	 * @Description(鎻忚堪): int绫诲瀷杞崲涓篵yte[]绫诲瀷
	 * @param (鍙傛暟)锛欯param input 寰呰浆鎹㈢殑int鍊�
	 * @param (鍙傛暟)锛欯return 璁惧畾鏂囦欢
	 * @return byte[] 杩斿洖鍊艰浆鎹㈠悗鐨刡ety鏁扮粍
	 * @throws
	 */
	public byte[] intTobyte(int input) {
		byte[] output = new byte[4];
		output[0] = (byte) (input & 0xff);
		output[1] = (byte) ((input >> 8) & 0xff);
		output[2] = (byte) ((input >> 16) & 0xff);
		output[3] = (byte) (input >>> 24);
		return output;
	}
}
