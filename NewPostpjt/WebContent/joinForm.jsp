<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function chk() {
		if(!frm.id.value){ //id입력란에 아무것도 입력이 안됐을 시
			alert('아이디를 입력하세요');
			frm.id.focus();
			return false;
		}else{//jquery명령어
			 //id라는 변수안에 현재 페이지의 id값을 넣고 post방식으로 confirm.jsp에 보낸다. 그후 결과를 받아서 data에 저장하고
			 //그 값을 id가 err인 곳에 html형식으로 넣는다.
			$.post("confirm.jsp", "id="+frm.id.value, function(data) {
				$('#err').html(data);
			});
		}
	}
	function chk2(){
		if(frm.password.value != frm.confirmpass.value){
			alert("암호와 암호확인이 다릅니다.");
			frm.password.focus();
			frm.password.value = "";
			return false;
		}
	}
</script>
</head><body>
<!-- onsubmit = "return chk2()"는 submit을 눌렀을 때 chk2를 실행하고 에러가없으면 action에 있는 join.jsp실행
만약 에러가 있으면 action을 하지말라는 말임 -->
<form action="join.jsp" method="post" name="frm" onsubmit="return chk2()">
	<table><caption>회원가입</caption>
		<tr><th>아이디</th><td><input type="text" name="id" required="required" autofocus="autofocus">
		<input type="button" value="중복체크" onclick="chk()"><div id="err"></div></td></tr>
		<tr><th>암호</th><td><input type="password" name="password" required="required"></td></tr>
		<tr><th>암호확인</th><td><input type="password" name="confirmpass" required="required"></td></tr>
		<tr><th>이름</th><td><input type="text" name="name" required="required"></td></tr>
		<tr><th>주소</th><td><input type="text" name="address" required="required"></td></tr>
		<!-- title="전화형식" 에러가 발생하면 보여줄 메세지에 추가 -->
		<!-- pattern="\d{3}-\d{3,4}-\d{4}" 숫자3-숫자3또는4-숫자4 -->
		<!-- placeholder="010-1111-1111" 초기화면에 보여주고 데이터 입력하면 사라진다. -->
		<tr><th>전화번호</th><td><input type="text" name="tel" title="전화형식 3-3,3-4"required="required" pattern="\d{3}-\d{3,4}-\d{4}" placeholder="010-1111-1111"></td></tr>
		<tr><th colspan="2"><input type="submit" value="확인"></th></tr>
	</table>
</form>  
</body>
</html>