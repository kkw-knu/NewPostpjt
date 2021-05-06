<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Mem.*"%>
<%@ include file="sessionchk.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<% //session 정보 삭제
	session.invalidate();
	out.println("<script>");
	out.println("alert('로그아웃 되었습니다.')");
	out.println("location.href='loginForm.jsp'");
	out.println("</script>");
%> 
</body>
</html> 