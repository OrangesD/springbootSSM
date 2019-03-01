package com.zhao.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zhao.entity.Page;
import com.zhao.entity.PageData;
import com.zhao.service.userService;

@Controller
public class TestController extends BaseController{
	  private final static Logger logger = LoggerFactory.getLogger(TestController .class);
	@Autowired
	private userService us;
	
	@RequestMapping(value="tst")
	@ResponseBody
	public List tests(Page page) throws Exception{
		PageData pDate= this.getPageDate();
		pDate.put("offset", 0);
		pDate.put("limit", 10);
		pDate.put("currentPage", 1);
		
		page.setPd(pDate);
		logBefore(logger, "xxxxxxx", this.getClass(), JSON.toJSONString(pDate));
		List<PageData> list=us.getTanMu(page);
		List<PageData> lists=us.getTanMu(pDate);
		System.err.println(page.getTotalResult());
		logAfter(logger);
		return list;
	}
	/**
	 * springboot视图解析器
	 * @return
	 */
	@RequestMapping(value="/view")
	public String test1(){
		System.err.println("11111111111111111");
		return "viewHtml";
	}

}
