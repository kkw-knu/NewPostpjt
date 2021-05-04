<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="login.*"%> 
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">@import url("common.css");</style>
</head><body>
<% 
	String id = request.getParameter("id"); 
	MemberDao md = MemberDao.getinstance();
	int result = md.confirm(id);
	if(result>0) out.println("이미 존재하는 id입니다.");
	else out.println("사용할수 있는 id입니다.");
%>

</body>
</html> 