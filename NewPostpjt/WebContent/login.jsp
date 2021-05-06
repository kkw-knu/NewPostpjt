<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Mem.*"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	MemberDao md = MemberDao.getinstance();
	int result = md.logincheck(id, password);
	if(result > 0 ){
		session.setAttribute("id", id);
		response.sendRedirect("main.jsp");
	}else if(result == 0){
		out.println("<script>");
		out.println("alert('패스워드가 다릅니다.')");
		out.println("history.back()");
		out.println("</script>");
	}else{
		out.println("<script>");
		out.println("alert('없는 아이디입니다..')");
		out.println("history.back()");
		out.println("</script>");
	} 
%>
</body>
</html> 