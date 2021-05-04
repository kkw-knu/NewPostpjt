<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");//모두 한글처리 하겠다는 뜻 그러나 나는 filter를 썻기때문에 필요없다.
	String userID = null;
	String userPW = null;
	if(request.getParameter("userID") != ""){
		userID = (String)request.getParameter("userID");
	}
	if(request.getParameter("userPW") != ""){
		userPW = (String)request.getParameter("userPW");
	}
	if(userID == null || userPW == null){//입력안된 사항이 있으면 나오는 구문
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('모두 입력해주세요')");
		script.println("history.back()");
		script.println("</script>");
		return;
	}else{
		//로그인 성공시
		UserDAO dao = new UserDAO(); //객체를 생성하여 데이터베이스와 연동
		int result = dao.join(userID, userPW);
		if(result == 1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입성공')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
	}
	
%>