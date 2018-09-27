<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座マスタ新規登録</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/course_master_signup.css">
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
	<h1 class="sub-title"> 講座マスタ新規登録 </h1>
	<form class="form bold" method="get" action="../CourseMaster/course_master.html">
		<div class="txarea">
			<div class="form-group row">
				<label for="inputGrade" class="col-sm-2 col-form-label">学年</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputGrade">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputCourseName" class="col-sm-2 col-form-label">講座名</label>
				<div class="col-sm-7">
					<input type="password" class="form-control" id="inputCourseName">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputTeacher" class="col-sm-2 col-form-label">担当教員</label>
				<div class="col-sm-7">
					<input type="password" class="form-control" id="inputTeacher">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">期間</legend>
					<div class="col-sm-7">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1" checked>
							<label class="form-check-label" for="gridRadios1">１年 １学期</label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios1" value="option1">
							<label class="form-check-label event-lavel" for="gridRadios1"> ２年 １学期</label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios1" value="option1">
							<label class="form-check-label event-lavel" for="gridRadios1">３年 １学期</label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios1" value="option1">
							<label class="form-check-label event-lavel" for="gridRadios1"> 春期講習</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label" for="gridRadios2">１年 ２学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label event-lavel" for="gridRadios2"> ２年 ２学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label event-lavel" for="gridRadios2"> ３年 ２学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label event-lavel" for="gridRadios2"> 夏期講習 </label>
						</div>
						<div class="form-check disabled">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3">
							<label class="form-check-label" for="gridRadios3"> １年 ３学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label event-lavel" for="gridRadios2"> ２年 ３学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label event-lavel" for="gridRadios2"> ３年 ３学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label event-lavel" for="gridRadios2"> 冬期講習 </label>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputPrice" class="col-sm-2 col-form-label">料金</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputPrice">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputCourseDetail" class="col-sm-2 col-form-label">講座詳細</label>
				<div class="col-sm-7">
					<textarea class="form-control" id="inputCourseDetail"rows="3"></textarea>
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