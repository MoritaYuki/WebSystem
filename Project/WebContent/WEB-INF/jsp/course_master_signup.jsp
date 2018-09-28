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
	<form class="form bold" method="post" action="CourseMasterSignUpServlet">
		<div class="txarea">
			<div class="error">
				<c:if test="${errMsg != null}" >
					${errMsg}
				</c:if>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">学年</legend>
					<div class="col-sm-7">
						<c:forEach var="i" begin="1" end="3">
							<c:if test="${grade != i}" >
								<div class="form-check">
									<input class="form-check-input event" type="radio" name="inputGrade" id="gridRadios${i}" value="${i}">
									<label class="form-check-label event-lavel" for="gridRadios${i}"> ${i}年生</label>
								</div>
							</c:if>
							<c:if test="${grade == i}" >
								<div class="form-check">
									<input class="form-check-input event" type="radio" name="inputGrade" id="gridRadios${i}" value="${i}" checked>
									<label class="form-check-label event-lavel" for="gridRadios${i}"> ${i}年生</label>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputCourseName" class="col-sm-2 col-form-label">講座名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputCourseName" value="courseName">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputTeacher" class="col-sm-2 col-form-label">担当教員</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputTeacher" value="teacher">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">期間</legend>
					<div class="col-sm-7">
						<c:forEach var="g" begin="1" end="3">
						<div class="form-check">
							<c:forEach var="t" begin="1" end="4">
								<c:if test="${t != 4}" >
									<c:if test="${term != (10*g)+t}" >
											<input class="form-check-input event" type="radio" name="inputTerm" id="gridRadios${(10*g)+t}" value="${(10*g)+t}">
											<label class="form-check-label event-lavel" for="gridRadios${(10*g)+t}"> ${g}年生 ${t}学期</label>
									</c:if>
									<c:if test="${term == (10*g)+t}" >
											<input class="form-check-input event" type="radio" name="inputTerm" id="gridRadios${(10*g)+t}" value="${(10*g)+t}" checked>
											<label class="form-check-label event-lavel" for="gridRadios${(10*g)+t}"> ${g}年生 ${t}学期</label>
									</c:if>
								</c:if>
								<c:if test="${t == 4}" >
									<c:if test="${term != (10*g)+t}" >
											<input class="form-check-input event" type="radio" name="inputTerm" id="gridRadios${(10*g)+t}" value="${(10*g)+t}">
											<label class="form-check-label event-lavel" for="gridRadios${(10*g)+t}"> ${sCourseList[g-1]}講習</label>
									</c:if>
									<c:if test="${term == (10*g)+t}" >
											<input class="form-check-input event" type="radio" name="inputTerm" id="gridRadios${(10*g)+t}" value="${(10*g)+t}" checked>
											<label class="form-check-label event-lavel" for="gridRadios${(10*g)+t}"> ${sCourseList[g-1]}講習</label>
									</c:if>
								</c:if>
							</c:forEach>
							</div>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputPrice" class="col-sm-2 col-form-label">料金</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputPrice" name="inputPrice" value="${price}">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputCourseDetail" class="col-sm-2 col-form-label">講座詳細</label>
				<div class="col-sm-7">
					<textarea class="form-control" id="inputCourseDetail" rows="3" name="inputCourseDetail" value="${courseDetail}"></textarea>
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