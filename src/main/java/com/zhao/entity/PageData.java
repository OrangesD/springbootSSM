package com.zhao.entity;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;

/**
 * 万能类
 * @author wuzhao
 *
 */
@SuppressWarnings("unchecked")
public class PageData extends HashMap implements Map {
	private static final long serialVersionUID = 1L;
	Map map = null;
	HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map propertiest = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = propertiest.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public PageData() {
		map = new HashMap();
	}

	public PageData(Map m) {
		map = m;
	}
	/**
	 * 通过key获取value类型为Object
	 */
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arn = (Object[]) map.get(key);
			obj = request == null ? arn : (request.getParameter((String) key) == null ? arn : arn[0]);
		} else {
			obj = map.get(key);
		}
		return obj;

	}
	/**
	 * 通过key获取value类型为String
	 */
	public String getString(Object key) {
		if (null == key) {
			return "";
		}
		return String.valueOf(get(key));
	}
	/**
	 * 通过key获取value类型为Integer
	 */
	public Integer getInt(Object key) {
		Integer inte = 0;
		try {
			inte = Integer.parseInt(getString(key));
		} catch (NumberFormatException e) {
		}
		return inte;
	}

	/**
	 * 判断是否包含指定键
	 * 
	 * @param key
	 * @return
	 */
	public boolean customContainsKey(Object key) {
		if (map.containsKey(key) && !getString(key).equals("")) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * 
	 */
	public Object put(Object key, Object value) {
		if (value instanceof ClobProxyImpl) { // 读取oracle Clob类型数据
			try {
				ClobProxyImpl cpi = (ClobProxyImpl) value;
				Reader is = cpi.getCharacterStream(); // 获取流
				BufferedReader br = new BufferedReader(is);
				String str = br.readLine();
				StringBuffer sb = new StringBuffer();
				while (str != null) { // 循环读取数据拼接到字符串
					sb.append(str);
					sb.append("\n");
					str = br.readLine();
				}
				value = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}

	public FileItemIterator getFileMap() {
		if (request == null) {
			return null;
		}
		if (ServletFileUpload.isMultipartContent(request)) {
			ServletFileUpload upload = new ServletFileUpload();
			try {
				FileItemIterator fileItemIterator = upload.getItemIterator(request);
				return fileItemIterator;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
