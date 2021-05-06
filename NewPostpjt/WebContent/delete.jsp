<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Mem.*"%>
<%@ include file="sessionchk.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<%
	MemberDao md = MemberDao.getinstance();
	int result = md.delete(id);
	if(result >0){
		session.invalidate();
		out.println("<script>");
		out.println("alert('회원탈퇴에 성공하였습니다.')");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}else{
		out.println("<script>");
		out.println("alert('회원탈퇴에 실패하였습니다..')");
		out.println("history.back()");
		out.println("</script>");
	}
%>
</body>
</html>