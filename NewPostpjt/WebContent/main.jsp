<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Mem.*"%>
<%@ include file="sessionchk.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="common.css">
<script> 
	function del(){
		var chk = confirm("정말로 탈퇴하시겠습니까");
		if(chk) location.href="delete.jsp";
		else alert("삭제가 취소되었습니다.");
	}
	function chk(){ 
		var id = "<%=id%>"; //자바스크립트에서 자바변수를 쓸때 따옴표로 처리해야 사용가능
		if(id != 'master'){ //문자비교할때 java는 equals(), javascript는 ==로 비교한다.
			alert("리스트를 볼 권한이 없습니다");
			return;
		}else location.href="list.jsp";
	}
</script></head><body>
<table><caption>회원관리</caption> 
	<tr><th><button onclick="location.href='updateForm.jsp'">회원 수정</button></th></tr>
	<tr><th><button onclick="del()">회원탈퇴</button></th></tr>
	<tr><th><button onclick="chk()">회원목록</button></th></tr>
	<tr><th><button onclick="location.href='logout.jsp'">로그 아웃</button></th></tr>
</table>
</body>
</html>