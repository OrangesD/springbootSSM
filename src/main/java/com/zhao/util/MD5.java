package com.zhao.util;

import java.security.MessageDigest;
/**
 * 工具类md5加密
 * @author OrangesD
 *
 */
public class MD5 {
	/**
	 * md5加密，通过加密本会被破解
	 * @param str
	 * @return
	 */
	public static String md5Str(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				int i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
	/**
	 * 进行多次md5加密
	 * @param str
	 * @return
	 */
	public static String md5(String str){
		//多次加密
		String md=md5Str(md5Str(str)+"网络公司");
		return md;
	}

	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}
}