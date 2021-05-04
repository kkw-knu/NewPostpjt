<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="userJoinAction.jsp" method="post">
		ID : <input type="text" name="userID"><br> PW : <input
			type="password" name="userPW"><br> Email : <input
			type="text" name="EmailID"> @ <input type="text"
			name="Emailadress"><br> Gender : <input type="radio"
			id="man" value="man" name="Gender"> <label for="man">남자</label>
		<input type="radio" id="woman" value="woman" name="Gender"> <label
			for="woman">여자</label><br> Birth : <input type="text"
			placeholder="ex 1996/05/28" name="Birth"><br> userName :
		<input type="text" name="userName"><br> <input
			type="submit" value="회원가입">
	</form>
</body>
</html>