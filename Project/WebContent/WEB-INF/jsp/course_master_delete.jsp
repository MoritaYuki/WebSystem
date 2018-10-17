<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座マスタ削除</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/course_master_delete.css">
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
				<li class="nav-item active"><a class="nav-link"
					href="CourseMasterServlet" role="button" aria-haspopup="true"
					aria-expanded="false"> 講座マスタ </a></li>
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span
			class="sr-only">(current)</span></a>
	</nav>

	<h1 class="sub-title"> 講座マスタ削除 </h1>
	<div class="delete-msg">
		<p class="info-msg">
			${course.grade}年生　
			<c:if test="${course.term <= 3}">
				${course.term}学期　
			</c:if>
			<c:if test="${course.term > 3}">
				${sCourseList[course.term-4]}　
			</c:if>
			${course.courseName}
		</p>
		<p class="info-msg"> 担当教員：${course.teacher} </p>
		<p class="tale-msg"> の講座マスタを本当に削除してもよろしいですか？ </p>
	</div>
	<div class="btn-box">
		<a class="btn btn-primary no" href="CourseMasterServlet" role="button">キャンセル</a>
		<a class="btn btn-primary yes" href="CourseMasterDeleteServlet?courseId=${courseId}&delFg=1" role="button">OK</a>
	</div>
</body>
</html>