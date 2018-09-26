<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/login.css">
</head>
<body>
	<div class="login_menu">
		<h1 class="title">学習管理システム</h1>
	</div>
	<div class="container">
		<div class="login-container">
		<div class="error">
		<c:if test="${errMsg != null}">
				${errMsg}
		</c:if>
		</div>
			<div id="output"></div>
			<div class="avatar"></div>
			<div class="form-box">
				<form action="LoginServlet" method="post">
					<input name="loginId" type="text" placeholder="login id">
					<input name="password" type="password" placeholder="password">
					<button class="btn btn-info btn-block login" type="submit">Login</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>