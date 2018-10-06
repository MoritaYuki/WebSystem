<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座マスタ</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/course_master.css">
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

	<!-- 検索フォーム -->
	<h1 class="sub-title">講座マスタ一覧</h1>
	<div class="signMsg">
		<c:if test="${signMsg != null}">
			${signMsg}
		</c:if>
	</div>
	<div class="signup">
		<a href="CourseMasterSignUpServlet"
			class="signup-btn btn btn-secondary btn-lg active" role="button"
			aria-pressed="true">新規登録</a>
	</div>
	<form class="form bold" method="post" action="CourseMasterServlet">
		<div class="txarea">
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">学年</legend>
					<div class="col-sm-7">
						<c:forEach var="i" begin="1" end="3">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="inputCGradeNo" id="gridRadios${i}" value="${i}">
								<label class="form-check-label" for="gridRadios${i}"> ${i}年生</label>
							</div>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputCourseName" class="col-sm-2 col-form-label">講座名</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="inputCourseName" id="inputCourseName">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputTeacher" class="col-sm-2 col-form-label">担当教員</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="inputTeacher" id="inputTeacher">
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10 search">
				<button type="submit" class="btn btn-primary search-btn">検索</button>
			</div>
		</div>
	</form>
	<div class="text-center">
		<c:forEach var="i" begin="1" end="3">
			<c:if test="${cTermNo == i}">
				<a class="term-btn btn btn-secondary btn-lg disabled" href="CourseMasterServlet?cTermNo=${i}">${i}学期</a>
			</c:if>
			<c:if test="${cTermNo != i}">
				<a class="term-btn btn btn-secondary btn-lg " href="CourseMasterServlet?cTermNo=${i}">${i}学期</a>
			</c:if>
		</c:forEach>
	</div>
	<div class="text-center">
		<c:forEach var="i" begin="4" end="6">
			<c:if test="${cTermNo == i}">
				<a class="term-btn btn btn-secondary btn-lg disabled" href="CourseMasterServlet?cTermNo=${i}">${sCourseList[i-4]}講習</a>
			</c:if>
			<c:if test="${cTermNo != i}">
				<a class="term-btn btn btn-secondary btn-lg " href="CourseMasterServlet?cTermNo=${i}">${sCourseList[i-4]}講習</a>
			</c:if>
		</c:forEach>
	</div>

	<!-- 講座一覧表 -->
	<div class="search-style">
		${cGradeNo}学年　　
		<c:if test="${cTermNo <= 3}">
			${cTermNo}学期　　
		</c:if>
		<c:if test="${cTermNo > 3}">
			${sCourseList[cTermNo-4]}講習　　
		</c:if>
		【検索ワード】講座名："${courseName}"　　担当教員："${teacher}"
	</div>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">学年</th>
				<th scope="col">講座名</th>
				<th scope="col">担当教員</th>
				<th scope="col">期間</th>
				<th scope="col">料金</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="course" items="${courseList}">
				<tr>
					<c:if test="${cTermNo == course.term}">
						<th scope="row">中${course.grade}</th>
						<td>${course.courseName}</td>
						<td>${course.teacher}</td>
						<td>
							<c:if test="${course.term <= 3}" >
								${course.term}学期
							</c:if>
							<c:if test="${course.term > 3}" >
								${sCourseList[course.term-4]}
							</c:if>
						</td>
						<td>${course.price}円/月</td>
						<td><a href="CourseDetailServlet"
							class="detail button btn btn-secondary btn-lg active"
							role="button" aria-pressed="true">詳細</a> <a
							href="CourseMasterUpdateServlet"
							class="update button btn btn-secondary btn-lg active"
							role="button" aria-pressed="true">更新</a> <a
							href="CourseMasterDeleteServlet"
							class="delete button btn btn-secondary btn-lg active"
							role="button" aria-pressed="true">削除</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="text-center">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#">前</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">後</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>