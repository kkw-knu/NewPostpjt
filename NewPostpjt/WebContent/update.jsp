<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Mem.*"%>
<%@ include file="sessionchk.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="member" class="Mem.Member"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>
<%
	MemberDao md = MemberDao.getinstance();
	int result = md.update(member); 
	if(result == 1){
		out.println("<script>");
		out.println("alert('회원정보가 수정되었습니다.')");
		out.println("location.href='main.jsp'");
		out.println("</script>");
	}else{
		out.println("<script>");
		out.println("alert('회원정보 수정에 실패하였습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
%>
</body>
</html>