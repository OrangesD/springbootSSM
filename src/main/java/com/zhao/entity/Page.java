package com.zhao.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhao.util.SystemConst;

/**
 * 分页
 */
public class Page {
	private int showCount = 10;//每页显示记录数
	private int limit = 10;//每页显示记录数   datatable使用
	private int totalPage;//总页数
	private int totalResult;//总记录数
	private int currentPage;//当前页
	private int currentResult;//当前记录起始索引
	private int offset;//
	private boolean entityOrField;//true:需要分页的地方，传入的参数是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有page属性
	private String pageStr;//最终页面显示的底部翻页导航，详情见：getPageStr();
	private String pageTableStr;//
	private int pageNo;//记录数据起始位置--用于动态sql分页
	private int pageSize;//记录每页显示条数--用于动态sql分页
	private PageData pd = new PageData();//

	protected Logger logger = LoggerFactory.getLogger(Page.class);

	public Page() {
		try {
			this.showCount = Integer.parseInt(SystemConst.PAGE);
			this.limit = Integer.parseInt(SystemConst.PAGE);
		} catch (NumberFormatException e) {
			this.showCount = 15;
			this.limit = 15;
			this.logger.info(e.getMessage());
		}
	}

	public int getTotalPage() {
		if (this.totalResult % this.limit == 0)
			this.totalPage = (this.totalResult / this.limit);
		else
			this.totalPage = (this.totalResult / this.limit + 1);
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return this.totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		PageData p = getPd();
		if ((p.getString("currentPage") != null) && (!"".equals(p.getString("currentPage")))) {
			this.currentPage = new Integer(p.getString("currentPage")).intValue();
		}
		if (this.currentPage <= 0)
			this.currentPage = 1;
		if (this.currentPage > getTotalPage())
			this.currentPage = getTotalPage();
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 后台生产分页
	 */
	public String getPageTableStr() {
		StringBuilder sb = new StringBuilder();
		if (this.totalResult > 0) {
			sb.append("<div  class=\"col-md-4\"><p class=\"pagination-p c-primary\">从 " + (this.currentResult + 1)
					+ " 到 " + this.currentPage * this.limit + " /共 " + this.totalResult + " 条</p></div>");
			sb.append("<div  class=\"col-md-8 text-r\">");
			sb.append("<div class=\"laypage\">");
			sb.append(
					"<select name=\"table-sort_length\" aria-controls=\"table-sort\" class=\"form-control\" onchange=\"changeCount(this.value)\" >");
			sb.append("<option value=\"10\">10</option><option value=\"25\">25</option>");
			sb.append("<option value=\"50\">50</option>");
			sb.append("<option value=\"100\">100</option></select>");
			sb.append("<nav>");
			sb.append("<ul class=\"pagination\">");

			if (this.currentPage == 1) {
				sb.append("\t<li><a>首页</a></li>\n");
				sb.append("\t<li><a>上一页</a></li>\n");
			} else {
				sb.append("\t<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">首页</a></li>\n");
				sb.append("\t<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + (this.currentPage - 1)
						+ ")\">上一页</a></li>\n");
			}
			int showTag = 5;
			int startTag = 1;
			if (this.currentPage > showTag) {
				startTag = this.currentPage - 1;
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; (i <= this.totalPage) && (i <= endTag); i++) {
				if (this.currentPage == i)
					sb.append("<li class=\"active\"><a>" + i + "</a></li>\n");
				else
					sb.append(
							"\t<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + i + ")\">" + i + "</a></li>\n");
			}
			if (this.currentPage == this.totalPage) {
				sb.append("\t<li><a>下一页</a></li>\n");
				sb.append("\t<li><a>尾页</a></li>\n");
			} else {
				sb.append("\t<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + (this.currentPage + 1)
						+ ")\">下一页</a></li>\n");
				sb.append("\t<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + this.totalPage
						+ ")\">尾页</a></li>\n");
			}
			sb.append("\t</li>\n");

			sb.append("</ul>");
			sb.append("</nav>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");

			sb.append("<script type=\"text/javascript\">\n");

			sb.append("function nextPage(page){");
			sb.append("\tif(true && document.forms[0]){\n");
			sb.append("\t\tvar url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("\t\tif(url.indexOf('?')>-1){url += \"&"
					+ (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("\t\telse{url += \"?" + (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("\t\turl = url + page + \"&" + (this.entityOrField ? "limit" : "page.limit") + "=" + this.limit
					+ "\";\n");
			sb.append("\t\tdocument.forms[0].action = url;\n");
			sb.append("\t\tdocument.forms[0].submit();\n");
			sb.append("\t}else{\n");
			sb.append("\t\tvar url = document.location+'';\n");
			sb.append("\t\tif(url.indexOf('?')>-1){\n");
			sb.append("\t\t\tif(url.indexOf('currentPage')>-1){\n");
			sb.append("\t\t\t\tvar reg = /currentPage=\\d*/g;\n");
			sb.append("\t\t\t\turl = url.replace(reg,'currentPage=');\n");
			sb.append("\t\t\t}else{\n");
			sb.append("\t\t\t\turl += \"&" + (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
			sb.append("\t\t\t}\n");
			sb.append("\t\t}else{url += \"?" + (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("\t\turl = url + page + \"&" + (this.entityOrField ? "limit" : "page.limit") + "=" + this.limit
					+ "\";\n");
			sb.append("\t\tdocument.location = url;\n");
			sb.append("\t}\n");
			sb.append("}\n");

			sb.append("function changeCount(value){");
			sb.append("\tif(true && document.forms[0]){\n");
			sb.append("\t\tvar url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("\t\tif(url.indexOf('?')>-1){url += \"&"
					+ (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("\t\telse{url += \"?" + (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("\t\turl = url + \"1&" + (this.entityOrField ? "limit" : "page.limit") + "=\"+value;\n");
			sb.append("\t\tdocument.forms[0].action = url;\n");
			sb.append("\t\tdocument.forms[0].submit();\n");
			sb.append("\t}else{\n");
			sb.append("\t\tvar url = document.location+'';\n");
			sb.append("\t\tif(url.indexOf('?')>-1){\n");
			sb.append("\t\t\tif(url.indexOf('currentPage')>-1){\n");
			sb.append("\t\t\t\tvar reg = /currentPage=\\d*/g;\n");
			sb.append("\t\t\t\turl = url.replace(reg,'currentPage=');\n");
			sb.append("\t\t\t}else{\n");
			sb.append("\t\t\t\turl += \"1&" + (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
			sb.append("\t\t\t}\n");
			sb.append("\t\t}else{url += \"?" + (this.entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("\t\turl = url + \"&" + (this.entityOrField ? "limit" : "page.limit") + "=\"+value;\n");
			sb.append("\t\tdocument.location = url;\n");
			sb.append("\t}\n");
			sb.append("}\n");

			sb.append("</script>\n");
		}
		this.pageTableStr = sb.toString();
		return this.pageTableStr;
	}

	public void setPageTableStr(String pageTableStr) {
		this.pageTableStr = pageTableStr;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return this.showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		this.currentResult = ((getCurrentPage() - 1) * getLimit());
		if (this.currentResult < 0)
			this.currentResult = 0;
		return this.currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return this.entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public PageData getPd() {
		return this.pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

	public int getOffset() {
		return this.offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return this.limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}