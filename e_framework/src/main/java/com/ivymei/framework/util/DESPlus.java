package com.ivymei.framework.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;


/**
 * des加密解密工具类
 * @author Administrator
 *
 */
public class DESPlus {
	/**
	 * 默认的加密串是对接一号外卖的  请不要随意改动
	 */
	private final static String STRDEFAULTKEY = "57ne0zb3-pz6z-q9fc-qvzk-4podx7ag3av5";//加密串
	private Cipher encryptCipher = null;
	
	private Cipher decryptCipher = null;

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public DESPlus() throws Exception {
		this(STRDEFAULTKEY);
	}

	@SuppressWarnings("restriction")
	public DESPlus(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	public String encrypt(String strIn)  {
		try {
			return byteArr2HexStr(encrypt(strIn.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "" ; 
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	private Key getKey(byte[] arrBtmp) {
		byte[] b = new byte[8];
		for (int x = 0; x < arrBtmp.length && x < b.length; x++) {
			b[x] = arrBtmp[x];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(b, "DES");
		return key;
	}

	private static DESPlus dESPlus = null;

	public static DESPlus getDESPlus() {
		if (dESPlus == null) {
			try {
				dESPlus = new DESPlus( DESPlus.STRDEFAULTKEY );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dESPlus;
	}
	
	
	public static void main(String[] args) throws Exception {
		DESPlus de = DESPlus.getDESPlus();
		String s = "{openId:\"oI1HjtzNT-Iy51L3U2ZtpAB4ea2M\",\"channelKey\":\"yihaowaimai\"}";
		String s1 = de.encrypt(s);
		System.out.println(s1);
		System.out.println(de.decrypt(s1));
	}
	
}
