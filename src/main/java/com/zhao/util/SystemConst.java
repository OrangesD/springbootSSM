package com.zhao.util;

import java.util.Properties;

public class SystemConst {
	private static Properties pros = RequestUtil.getSystemPprVue();

	public static final String PAGE = pros.getProperty("PAGE");

	public static String getSecretKey(String ak) {
		return pros.getProperty(ak);
	}
}