<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座詳細</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/course_detail.css">
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
					<li class="nav-item active">
						<a class="nav-link" href="CourseMasterServlet" role="button" aria-haspopup="true" aria-expanded="false"> 講座マスタ </a>
					</li>
				</c:if>
				<c:if test="${loginInfo.userId != 1}">
					<li class="nav-item active">
						<a class="nav-link" href="UserDetailServlet">プロフィール<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="CourseListServlet" role="button" aria-haspopup="true" aria-expanded="false"> 講座一覧 </a>
					</li>
				</c:if>
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span class="sr-only">(current)</span></a>
	</nav>

	<h1 class="sub-title"> 講座詳細 </h1>

	<div class="info-container">
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 学年 </div>
				<div class="col col-lg-5"> 中${course.grade} </div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 期間 </div>
				<div class="col col-lg-5">
					<c:if test="${course.term <= 3}">
						${course.term}学期
					</c:if>
					<c:if test="${course.term > 3}">
						${sCourseList[course.term-4]}
					</c:if>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 講座名 </div>
				<div class="col col-lg-5"> ${course.courseName} </div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 担当教員 </div>
				<div class="col col-lg-5"> ${course.teacher} </div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 料金 </div>
				<div class="col col-lg-5"> ${course.price} 円</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 講座詳細 </div>
				<div class="col col-lg-5"> ${course.courseDetail} </div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 作成日 </div>
				<div class="col col-lg-5"> ${course.createDate} </div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4"> 更新日 </div>
				<div class="col col-lg-5"> ${course.updateDate} </div>
			</div>
		</div>
	</div>
</body>
</html>