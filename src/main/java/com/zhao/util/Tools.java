package com.zhao.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具
 * 
 * @author OrangesD
 *
 */
public class Tools {

	/**
	 * 随机生成6位数验证码
	 * 
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;
	}

	/**
	 * 检验字符串不为空("",null,"null")
	 * 
	 * @param s
	 * @return
	 */
	public static boolean notEmpty(String s) {
		return (s != null) && (!"".equals(s)) && (!"null".equals(s));
	}

	/**
	 * 检验字符串为空("",null,"null")
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return (s == null) || ("".equals(s)) || ("null".equals(s));
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的字符串分隔符(,)转换成字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();

				return new Date();
			}
		}
		return null;
	}

	/**
	 * 按照参数format格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 把时间根据时,分,秒 转换成时间段
	 * 
	 * @param StrDate
	 * @return
	 */
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date now = new Date();
			Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / 86400000L;
			long hour = times / 3600000L - day * 24L;
			long min = times / 60000L - day * 24L * 60L - hour * 60L;
			long sec = times / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;

			StringBuffer sb = new StringBuffer();

			if (hour > 0L)
				sb.append(hour + "小时前");
			else if (min > 0L)
				sb.append(min + "分钟前");
			else {
				sb.append(sec + "秒前");
			}

			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultTimes;
	}

	/**
	 * 写txt里的单行内容
	 * 
	 * @param fileP
	 *            文件路径
	 * @param content
	 *            写入的内容
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../";
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1)
			filePath = File.separator + filePath;
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 检测KEY是否正确
	 * 
	 * @param paraname
	 *            传入参数
	 * @param FKEY
	 *            接收的KEY
	 * @return 为空则返回true，否则返回false
	 */
	public static boolean checkKey(String paraname, String FKEY) {
		paraname = paraname == null ? "" : paraname;
		return MD5.md5(paraname + DateUtil.getDays() + ",fh,").equals(FKEY);
	}

	/**
	 * 读取txt里的单行内容
	 * 
	 * @param fileP
	 *            文件路径
	 * @return
	 */
	public static String readTxtFile(String fileP) {
		try {
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../";
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if ((file.isFile()) && (file.exists())) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				if ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}

	/**
	 * 判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean b = false;
		if ((str != null) && (!"".equals(str)) && (str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$"))) {
			return true;
		}
		return b;
	}

	/**
	 * 秒转换成时分秒格式
	 * 
	 * @param time
	 */
	public static String timeDifference(int t_seconds) {
		if (t_seconds <= 0) {
			return "";
		}
		int seconds = 0;
		int minutes = 0;
		int hours = 0;
		seconds = t_seconds % 60;
		t_seconds = t_seconds - seconds;
		if (t_seconds > 0) {
			int total_minutes = t_seconds / 60;
			minutes = total_minutes % 60;
			total_minutes = total_minutes - minutes;
			if (total_minutes > 0) {
				hours = total_minutes / 60;
			}
		}
		if (hours < 0) {
			hours = 0;
		}
		if (minutes < 0) {
			minutes = 0;
		}
		if (seconds < 0) {
			seconds = 0;
		}
		return hours + "时" + minutes + "分" + seconds + "秒";
	}

	public static void main(String[] args) {
		System.out.println(getRandomNum());
		System.out.println(timeDifference(70));
	}
}