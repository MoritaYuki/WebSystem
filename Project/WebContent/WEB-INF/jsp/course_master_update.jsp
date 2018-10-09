<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座マスタ更新</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/course_master_update.css">
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
	<h1 class="sub-title"> 講座マスタ更新 </h1>
	<form class="form bold" method="get" action="../CourseMaster/course_master.html">
		<div class="txarea">
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">学年</legend>
					<div class="col-sm-7">
						<c:forEach var="i" begin="1" end="3">
							<c:if test="${course.grade != i}" >
								<div class="form-check">
									<input class="form-check-input " type="radio" name="inputGrade" id="gridRadios${i}" value="${i}">
									<label class="form-check-label " for="gridRadios${i}"> ${i}年生</label>
								</div>
							</c:if>
							<c:if test="${course.grade == i}" >
								<div class="form-check">
									<input class="form-check-input " type="radio" name="inputGrade" id="gridRadios${i}" value="${i}" checked>
									<label class="form-check-label " for="gridRadios${i}"> ${i}年生</label>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">期間</legend>
					<div class="col-sm-7">
						<c:forEach var="t" begin="1" end="3">
							<div class="form-check">
								<c:if test="${course.term != t}" >
									<input class="form-check-input " type="radio" name="inputTerm" id="gridRadios${t}" value="${t}">
									<label class="form-check-label " for="gridRadios${t}"> ${t}学期</label>
								</c:if>
								<c:if test="${course.term == t}" >
									<input class="form-check-input " type="radio" name="inputTerm" id="gridRadios${t}" value="${t}" checked>
									<label class="form-check-label " for="gridRadios${t}"> ${t}学期</label>
								</c:if>
								<c:if test="${course.term != t+3}" >
									<input class="form-check-input event" type="radio" name="inputTerm" id="gridRadios${t+3}" value="${t+3}">
									<label class="form-check-label event-lavel" for="gridRadios${t+3}"> ${sCourseList[t-1]}講習</label>
								</c:if>
								<c:if test="${course.term == t+3}" >
									<input class="form-check-input event" type="radio" name="inputTerm" id="gridRadios${t+3}" value="${t+3}" checked>
									<label class="form-check-label event-lavel" for="gridRadios${t+3}"> ${sCourseList[t-1]}講習</label>
								</c:if>
							</div>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputCourseName" class="col-sm-2 col-form-label">講座名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputCourseName" id="inputCourseName" value="${course.courseName}">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputTeacher" class="col-sm-2 col-form-label">担当教員</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputTeacher" id="inputTeacher" value="${course.teacher}">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputCourseDetail" class="col-sm-2 col-form-label">講座詳細</label>
				<div class="col-sm-7">
					<textarea class="form-control" name="inputCourseDetail" id="inputCourseDetail"rows="3">${course.courseDetail}</textarea>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10 search">
				<button type="submit" class="btn btn-primary search-btn">更新</button>
			</div>
		</div>
	</form>
</body>
</html>