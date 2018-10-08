<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト結果マスタ一覧</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/exam_master_signup_user.css">
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
				<li class="nav-item active"><a class="nav-link"
					href="UserListServlet">生徒一覧<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item active"><a class="nav-link"
					href="ApplicationListServlet">申込管理</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="ExamMasterServlet">成績マスタ</a></li>
				<li class="nav-item"><a class="nav-link"
					href="CourseMasterServlet" role="button" aria-haspopup="true"
					aria-expanded="false"> 講座マスタ </a></li>
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span
			class="sr-only">(current)</span></a>
	</nav>
	<!-- 新規登録フォーム -->
	<h1 class="sub-title"> テスト結果マスタ新規登録 </h1>
	<form class="form bold" method="post" action="ExamMasterSignUpUserServlet">
		<div class="txarea">
		<div class="signup-explain" >テスト結果を新規登録する生徒を選択してください。</div>
			<div class="form-group row">
				<label for="inputLoginId" class="col-sm-2 col-form-label">ログインID</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputLoginId" id="inputLoginId">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">学年</legend>
					<div class="col-sm-7">
						<c:forEach var="i" begin="1" end="3">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="inputGrade" id="gridRadios${i}" value="${i}">
								<label class="form-check-label" for="gridRadios${i}"> ${i}年生</label>
							</div>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputUserName" class="col-sm-2 col-form-label">生徒氏名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputUserName" id="inputUserName">
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10 search">
				<button type="submit" class="btn btn-primary search-btn">生徒を検索</button>
			</div>
		</div>
	</form>

	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">ログインID</th>
				<th scope="col">学年</th>
				<th scope="col">読み仮名</th>
				<th scope="col">名前</th>
				<th scope="col">性別</th>
				<th scope="col">連絡先</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<th scope="row">${user.loginId}</th>
					<td>中 ${user.grade}</td>
					<td>${user.userNamePhonetic}</td>
					<td>${user.userName}</td>
					<td>${user.sex}</td>
					<td>${user.contactInfo}</td>
					<td>
						<a href="ExamMasterSignUpServlet?userId=${user.userId}" class="button btn btn-secondary btn-lg active" role="button" aria-pressed="true">入力</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>