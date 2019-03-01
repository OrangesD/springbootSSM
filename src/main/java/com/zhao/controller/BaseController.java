package com.zhao.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zhao.entity.PageData;


public class BaseController implements Serializable{
    private final static Logger logger = LoggerFactory.getLogger(BaseController .class);
	private static final long serialVersionUID = 1L;
	
	public PageData getPageDate(){
		logger.info("URL:"+getRequest().getRequestURL());
		PageData pageDate = new PageData(this.getRequest());
		return pageDate;
	}
	/**
	 * 获取request对象
	 * @return
	 */
	public HttpServletRequest getRequest(){
		HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	public PageData getPageDate(HttpServletRequest requeset){
		return new PageData(requeset);
	}
	/**
	 * 得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	/**
	 * 控制层日志--开始
	 * @param logger
	 * @param functionDescription 功能描述
	 * @param className 类名
	 * @param parameter 日志参数
	 */
	public static void logBefore(Logger logger , String functionDescription,Class className,String parameter){
		logger.info("");
		logger.info(">>>>>>>>>>>>>>>>>>>"+functionDescription+">>>>>>>>>>>>>>>>>>>");
		logger.info("日志描述:"+functionDescription);
		logger.info("访问路径:"+className.getResource("").getPath()+className.getName());
		logger.info("日志参数:"+parameter);
	}
	/**
	 * 控制层日志--结束
	 * @param logger
	 */
	public static void logAfter(Logger logger){
		logger.info(">>>>>>>>>>>>>>>>>>>结束>>>>>>>>>>>>>>>>>>>");
		logger.info("");
	}
	/**
	 * 获取服务访问路径
	 * @return
	 */
	public String getBasePath(){
		HttpServletRequest request= this.getRequest();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+":"+request.getServletPath()+"/";
	}
}
