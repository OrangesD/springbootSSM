package com.zhao.service;

import java.util.List;

import com.zhao.entity.Page;
import com.zhao.entity.PageData;

public interface userService {
	
	/**
	 * 分页查询数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<PageData> getTanMu(Page pd) throws Exception;
	/**
	 * 查询数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<PageData> getTanMu(PageData pd) throws Exception;
}
