package com.zhao.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("daoSupport")
public class DaoSupport implements DAO{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object save(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}
	/**
	 * 批量更新
	 * @param str
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public Object batchSave(String str,List list) throws Exception{
		return sqlSessionTemplate.insert(str, list);
	}

	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object update(String str, Object obj) throws Exception {
		return sqlSessionTemplate.update(str, obj);
	}
	/**
	 * 批量更新
	 * @param str
	 * @param list
	 * @throws Exception
	 */
	public void BatchUpdate(String str,List list) throws Exception{
		SqlSessionFactory sqlSessionFactory =sqlSessionTemplate.getSqlSessionFactory();
		//批量执行器
		SqlSession sqlSession =sqlSessionFactory.openSession(ExecutorType.BATCH,false);
		try {
			if(list!=null){
				for(int i=0;i<list.size();i++){
					sqlSession.update(str, list.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} catch (Exception e) {
			sqlSession.close();
		}
	}
	/**
	 * 批量删除
	 * @param str
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public Object batchDelete(String str,List list)throws Exception{
		return sqlSessionTemplate.delete(str, list);
	}

	/**
	 * 删除
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str, Object obj) throws Exception {
		return sqlSessionTemplate.delete(str, obj);
	}

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object finForObject(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str,obj);
	}

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object finForList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}
	public Object finForMap(String sql, Object object, String key) throws Exception {
		return sqlSessionTemplate.selectMap(sql, object, key);
	}

}
