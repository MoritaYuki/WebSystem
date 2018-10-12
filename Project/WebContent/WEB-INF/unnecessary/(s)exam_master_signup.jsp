<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト結果マスタ新規登録</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/exam_master_signup.css">
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

	<div class="container">
		<div class="info-box row justify-content-md-center">
			<div class="col col-lg-2"> ログインID </div>
			<div class="col col-lg-5"> ${user.loginId} </div>
		</div>
		<div class="info-box row justify-content-md-center">
			<div class="col col-lg-2"> 学年 </div>
			<div class="col col-lg-5"> ${user.grade} 年生</div>
		</div>
		<div class="info-box row justify-content-md-center">
			<div class="col col-lg-2"> 生徒氏名 </div>
			<div class="col col-lg-5"> ${user.userName} </div>
		</div>
	</div>

	<form class="form bold" method="post" action="ExamMasterSignUpServlet">
		<div class="txarea">
			<div class="form-group row">
				<label for="inputYear" class="col-sm-2 col-form-label">年度</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputYear" id="inputYear">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">テスト名</legend>
					<div class="col-sm-7">
						<c:forEach var="i" begin="1" end="3">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="inputTerm" id="gridRadios${i}" value="${i}">
								<label class="form-check-label" for="gridRadios${i}"> ${i}学期末</label>
							</div>
						</c:forEach>
					</div>
				</div>
			</fieldset>
		<div class="form-group form-row">
			<label for="inputscore" class="col-sm-2 col-form-label">試験得点</label>
				<div class="col-md-1">
					<label for="inputJapanese">国語</label>
					<input type="text" class="form-control" name="inputJapanese" id="inputJapanese">
				</div>
				<div class="col-md-1 padd">
					<label for="inputMath">数学</label>
					<input type="text" class="form-control" name="inputMath" id="inputMath">
				</div>
				<div class="col-md-1 padd">
					<label for="inputEnglish">英語</label>
					<input type="text" class="form-control" name="inputEnglish" id="inputEnglish">
				</div>
				<div class="col-md-1 padd">
					<label for="inputScience">理科</label>
					<input type="text" class="form-control" name="inputScience" id="inputScience">
				</div>
				<div class="col-md-1 padd">
					<label for="inputSocial">社会</label>
					<input type="text" class="form-control" name="inputSocial" id="inputSocial">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputComment" class="col-sm-2 col-form-label">講師からのコメント</label>
				<div class="col-sm-7">
					<textarea class="form-control" id="inputComment" rows="3" name="inputComment">${comment}</textarea>
				</div>
			</div>
		</div>
		<input type="hidden" name="inputUserId" value="${user.userId}">
		<input type="hidden" name="inputGrade" value="${user.grade}">
		<div class="form-group row">
			<div class="col-sm-10 search">
				<button type="submit" class="btn btn-primary search-btn">登録</button>
			</div>
		</div>
	</form>
</body>
</html>