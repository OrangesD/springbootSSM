package com.zhao.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestUtil {
	/**
	 * 读取配置文件system.properties
	 * @return
	 */
	public static Properties getSystemPprVue() {
		InputStream inputStream = RequestUtil.class.getClassLoader().getResourceAsStream("system.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * 去掉html标签
	 * 
	 * @param htmlStr
	 * @return
	 */
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";// 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";// 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>";// 定义html的标签正则表达式

	public static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);// 过滤script标签
		htmlStr = m_script.replaceAll("");

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);// 过滤style标签
		htmlStr = m_style.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);// 过滤html标签
		htmlStr = m_html.replaceAll("");

		htmlStr = htmlStr.replaceAll("&nbsp;", " ");// 过滤空格标签

		return htmlStr.trim();
	}
	/**
	 * 获取服务器ip
	 * @return
	 */
	public static String GetServerIp() {
		String ipAddrStr = "";
		InetAddress addr = null;
		try {
	
			addr = InetAddress.getLocalHost();
			byte[] ipaddr = addr.getAddress();

			for (int i = 0; i < ipaddr.length; i++) {
				if (i > 0) {
					ipAddrStr = ipAddrStr + ".";
				}
				ipAddrStr = ipAddrStr + (ipaddr[i] & 0xFF);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ipAddrStr;
	}
	/**
	 * 读取配置文件
	 * @param Properties 配置文件名称 比如:system.properties
	 * @return Properties
	 */
	public static Properties getProperties(String Properties) {
		InputStream inputStream = RequestUtil.class.getClassLoader().getResourceAsStream(Properties);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
}