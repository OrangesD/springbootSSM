package com.zhao.dao;

public interface DAO {
	
	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object save(String str,Object obj) throws Exception;
	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object update(String str,Object obj) throws Exception;
	/**
	 * 删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str,Object obj) throws Exception;
	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object finForObject(String str,Object obj) throws Exception;
	/**
	 * 查找对象对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object finForList(String str,Object obj) throws Exception;
	/**
	 * 查找对象封装成map
	 * @param sql
	 * @param object
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Object finForMap(String sql,Object object,String key) throws Exception;

}
