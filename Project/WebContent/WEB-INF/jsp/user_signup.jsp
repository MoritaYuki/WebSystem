<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント新規登録</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/user_signup.css">
</head>
<body>
	<!-- ヘッダー -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand title bold" href="ManagementMenuServlet">学習管理システム</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link"href="UserListServlet">生徒一覧<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="ApplicationListServlet">申込管理</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="ExamMasterServlet">成績マスタ</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="CourseMasterServlet" role="button" aria-haspopup="true" aria-expanded="false"> 講座マスタ </a>
				</li>
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span class="sr-only">(current)</span></a>
	</nav>
	<!-- 検索フォーム -->
	<h1 class="sub-title"> アカウント新規登録 </h1>
	<form class="form bold" method="post" action="UserSignUpServlet">
		<div class="txarea">
			<div class="error">
				<c:if test="${errMsg != null}" >
					${errMsg}
				</c:if>
			</div>
			<div class="form-group row">
				<label for="inputLoginId" class="col-sm-3 col-form-label">ログインID</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputLoginId" id="inputLoginId" value="${loginId}">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-3 col-form-label">パスワード</label>
				<div class="col-sm-7">
					<input type="password" class="form-control" name="inputPassword" id="inputPassword">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPasswordRe" class="col-sm-3 col-form-label">パスワード（確認）</label>
				<div class="col-sm-7">
					<input type="password" class="form-control" name="inputPasswordRe" id="inputPasswordRe">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-3 pt-0">学年</legend>
					<div class="col-sm-7">
						<c:forEach var="i" begin="1" end="3">
							<c:if test="${uGradeNo != i}" >
								<div class="form-check">
									<input class="form-check-input" type="radio" name="inputUGradeNo" id="gridRadios${i}" value="${i}">
									<label class="form-check-label" for="gridRadios${i}"> ${i}年生</label>
								</div>
							</c:if>
							<c:if test="${uGradeNo == i}" >
								<div class="form-check">
									<input class="form-check-input" type="radio" name="inputUGradeNo" id="gridRadios${i}" value="${i}" checked>
									<label class="form-check-label" for="gridRadios${i}"> ${i}年生</label>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputUserNamePhonetic" class="col-sm-3 col-form-label">読み仮名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputUserNamePhonetic" id="inputUserNamePhonetic" value="${userNamePhonetic}">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputUserName" class="col-sm-3 col-form-label">生徒氏名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputUserName" id="inputUserName" value="${userName}">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-3 pt-0">性別</legend>
					<div class="col-sm-7">
						<c:forEach var="s" items="${sexlist}">
							<c:if test="${s != sex}" >
								<div class="form-check">
									<input class="form-check-input" type="radio" name="inputSex" id="gridRadios" value="${s}" >
									<label class="form-check-label" for="gridRadios">${s}</label>
								</div>
							</c:if>
							<c:if test="${s == sex}" >
								<div class="form-check">
									<input class="form-check-input" type="radio" name="inputSex" id="gridRadios" value="${s}" checked>
									<label class="form-check-label" for="gridRadios1">${s}</label>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputBirthday" class="col-sm-3 col-form-label">生年月日</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputBirthday" id="inputBirthday" placeholder="年 / 月 / 日" value="${birthday}" >
				</div>
			</div>
			<div class="form-group row">
				<label for="inputContactInfo" class="col-sm-3 col-form-label">連絡先</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputContactInfo" id="inputContactInfo" value="${contactInfo}">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputAddress" class="col-sm-3 col-form-label">住所</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputAddress" id="inputAddress" value="${address}">
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10 search">
				<button type="submit" class="btn btn-primary search-btn">登録</button>
			</div>
		</div>
	</form>
</body>
</html>