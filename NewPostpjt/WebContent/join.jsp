<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="login.*"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<%
	request.setCharacterEncoding("utf-8");
%>
<!-- Member member = new Member() -->
<jsp:useBean id="member" class="login.Member"></jsp:useBean> 
<!-- id, password, name을 member객체에 setter method를 통하여 저장 -->
<jsp:setProperty property="*" name="member"/>
<%
	MemberDao md = MemberDao.getinstance();
	int result = md.insert(member);
	if(result>0){
		out.println("<script>");
		out.println("alert('회원가입이 완료되었습니다.')");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}else{
		out.println("<script>");
		out.println("alert('회원가입에 실패하였습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
%>
</body>
</html>