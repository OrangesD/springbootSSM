package com.zhao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhao.dao.DaoSupport;
import com.zhao.entity.Page;
import com.zhao.entity.PageData;
import com.zhao.service.userService;

@Service
//添加事务性
@Transactional(readOnly =false)
public class userServiceImpl implements userService{
	
	@Resource(name="daoSupport")
	private DaoSupport dao;

	public List<PageData> getTanMu(Page pd) throws Exception {
		//return user.getTanMu(pd);
		return (List<PageData>) dao.finForList("UserMapper.getTanMulistPage", pd);
	}
	public List<PageData> getTanMu(PageData pd) throws Exception {
		return (List<PageData>) dao.finForList("UserMapper.getTanMu", pd);
	}

}
