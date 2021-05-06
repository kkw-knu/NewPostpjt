<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Mem.*"%>
<%@ include file="sessionchk.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title> 
<link rel="stylesheet" type="text/css" href="common.css">
<script>
	function chk(){
		if(frm.password.value != frm.confirmPass.value){
			alert("암호와 확인이 다릅니다.");
			frm.password.focus();
			frm.password.value="";
			return false;
		}
	}
</script>
</head><body>
<%	//조회로 데이터를 보여주고 수정 요청
	MemberDao md = MemberDao.getinstance();
	Member member = md.select(id);
%>
<form action="update.jsp" method="post" name="frm" onsubmit="return chk()">
	<input type="hidden" name="id" value="<%=member.getId() %>">
	<table><caption>회원정보 수정</caption>
		<tr><th>아이디</th><td><%=member.getId() %></td></tr>
		<tr><th>암호</th><td><input type="password" name="password" required="required" autofocus="autofocus"></td></tr>
		<tr><th>암호확인</th><td><input type="password" name="confirmpass" required="required"></td></tr>
		<tr><th>이름</th><td><input type="text" name="name" required="required" value="<%=member.getName() %>"></td></tr>
		<tr><th>주소</th><td><input type="text" name="address" required="required" value="<%=member.getAddress() %>"></td></tr>
		<tr><th>전화번호</th><td><input type="text" name="tel" title="전화형식 3-3,3-4"required="required" pattern="\d{3}-\d{3,4}-\d{4}"
		placeholder="010-1111-1111" value="<%=member.getTel() %>"></td></tr>
		<tr><th>가입일</th><td><%=member.getReg_date() %></td></tr>
		<tr><th colspan="2"><input type="submit" value="확인"></th></tr>
	</table> 
</form> 
</body>
</html>