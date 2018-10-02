<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座一覧</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/course_list.css">
</head>
<body>
	<!-- ヘッダー -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<c:if test="${loginInfo.userId == 1}">
			<a class="navbar-brand title bold" href="ManagementMenuServlet">学習管理システム</a>
		</c:if>
		<c:if test="${loginInfo.userId != 1}">
			<a class="navbar-brand title bold" href="MyPageServlet">学習管理システム</a>
		</c:if>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<!-- アカウントが管理者と一般でヘッダーの表示を変更 -->
				<c:if test="${loginInfo.userId == 1}">
					<li class="nav-item active">
						<a class="nav-link" href="UserListServlet">生徒一覧<span class="sr-only">(current)</span></a>
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
				</c:if>
				<c:if test="${loginInfo.userId != 1}">
					<li class="nav-item active">
						<a class="nav-link" href="UserDetailServlet">プロフィール<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="ExamResultServlet">成績</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="CourseListServlet" role="button" aria-haspopup="true" aria-expanded="false"> 講座一覧 </a>
					</li>
				</c:if>
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span class="sr-only">(current)</span></a>
	</nav>

	<!-- 検索フォーム -->
	<h1 class="sub-title"> 講座一覧 </h1>
	<form class="form bold" method="post" action="CourseListServlet">
		<div class="txarea">
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
				<a class="term-btn btn btn-secondary btn-lg disabled" href="CourseListServlet?cTermNo=${i}">${i}学期</a>
			</c:if>
			<c:if test="${cTermNo != i}">
				<a class="term-btn btn btn-secondary btn-lg " href="CourseListServlet?cTermNo=${i}">${i}学期</a>
			</c:if>
		</c:forEach>
	</div>
	<div class="text-center">
		<c:forEach var="i" begin="4" end="6">
			<c:if test="${cTermNo == i}">
				<a class="term-btn btn btn-secondary btn-lg disabled" href="CourseListServlet?cTermNo=${i}">${sCourseList[i-4]}講習</a>
			</c:if>
			<c:if test="${cTermNo != i}">
				<a class="term-btn btn btn-secondary btn-lg " href="CourseListServlet?cTermNo=${i}">${sCourseList[i-4]}講習</a>
			</c:if>
		</c:forEach>
	</div>

	<div class="search-style">
		${grade}学年　　
		<c:if test="${cTermNo <= 3}">
			${cTermNo}学期　　
		</c:if>
		<c:if test="${cTermNo > 3}">
			${sCourseList[cTermNo-4]}講習　　
		</c:if>
		【検索ワード】講座名："${courseName}"　　担当教員："${teacher}"
	</div>

	<!-- 講座一覧 -->
	<div class="container">
		<div class="row">
			<div class="col-sm">
				<c:forEach var="course" items="${courseList}">
					<c:if test="${cTermNo == course.term}">
						<div class="card" style="width: 15rem;">
							<img class="card-img-top" src="/StudentManagement/img/s00023.jpg"
								alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title">
									中${course.grade}　
									<c:if test="${course.term <= 3}">
										${course.term}学期
									</c:if>
									<c:if test="${course.term > 3}">
										${sCourseList[course.term-4]}
									</c:if>
								</h5>
								<h5>${course.courseName}</h5>
								<p class="card-text">${course.teacher}</p>
								<a href="CourseDetailServlet?courseId=${course.courseId}" class="btn btn-primary">詳細</a>
								<a href="ApplicationCartServlet?courseId=${course.courseId}" class="btn btn-primary">申込</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
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