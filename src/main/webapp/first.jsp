<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String bath =request.getContextPath();
	String basePath = request.getScheme() +"://" +request.getServerName()+":"+request.getServerPort()+bath+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath %>static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/JsonHandler.js"></script>
</head>
<body>
<form  id="c" action="javascript:void(0)" method="POST"> 
1.<input type="text" id='a' value=''>
2.<input type="text" id='b' value=''>
3.<input type="submit"   value='提交'>

4.<input type="button" value='重置'>


</form>
</body>
<script type="text/javascript">
$(function(){
	$("input[type=submit]").click(function(){
		
		var aa=$("#a").val();
		var aaa=$("#b").val();
		var a= $("#c").serializeObject();
		alert(JSON.stringify(a));
	})
	
})
	

</script>
</html>