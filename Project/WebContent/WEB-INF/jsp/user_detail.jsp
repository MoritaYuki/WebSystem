<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント詳細</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/user_detail.css">
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
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span
			class="sr-only">(current)</span></a>
	</nav>


	<h1 class="sub-title">アカウント情報</h1>
	<div class="info-container">
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4">ログインID</div>
				<div class="col col-lg-5">${userData.loginId}</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4">学年</div>
				<div class="col col-lg-5">中学${userData.grade}年生</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4">生徒氏名</div>
				<div class="col col-lg-5">${userData.userName}</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4">生年月日</div>
				<div class="col col-lg-5">${userData.birthday}</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4">連絡先</div>
				<div class="col col-lg-5">${userData.contactInfo}</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col col-lg-4">住所</div>
				<div class="col col-lg-5">${userData.address}</div>
			</div>
		</div>
	</div>

	<div class="table-container">
		<h3 class="table-name">【受講講座一覧】</h3>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">学年</th>
					<th scope="col">講座名</th>
					<th scope="col">担当教員</th>
					<th scope="col">期間</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="course" items="${appCourseList}">
					<tr>
						<th scope="row">中${course.grade}</th>
						<td>${course.courseName}</td>
						<td>${course.teacher}</td>
						<td><c:if test="${course.term <= 3}">
								${course.term}学期
							</c:if> <c:if test="${course.term > 3}">
								${sCourseList[course.term-4]}
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="table-box">
		<h3 class="table-name">【テスト結果】</h3>
		<c:forEach var="i" begin="1" end="3">
			<p class="table-title">■第${i}学年</p>
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">学年</th>
						<th scope="col">テスト名</th>
						<th scope="col">国語</th>
						<th scope="col">数学</th>
						<th scope="col">英語</th>
						<th scope="col">理科</th>
						<th scope="col">社会</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="exam" items="${examList}">
						<c:if test="${exam.grade == i}">
							<tr>
								<th scope="row">${exam.grade}学年</th>
								<td>${exam.term}学期末</td>
								<td>${exam.japanese}</td>
								<td>${exam.math}</td>
								<td>${exam.english}</td>
								<td>${exam.science}</td>
								<td>${exam.social}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<div class="comment-box">
				<div class="col-sm-10">
					<c:if test="${loginInfo.userId != 1}">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">【担当教員からのコメント】</h5>
								<pre class="card-text">${commentList[i-1]}</pre>
							</div>
						</div>
					</c:if>
					<c:if test="${loginInfo.userId == 1}">
						<form class="form bold" method="post" action="UserDetailServlet">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">【担当教員からのコメント】</h5>
									<textarea class="card-text" name="inputComment">${commentList[i-1]}</textarea>
									<input type="hidden" name="inputUserId" value="${userId}">
									<input type="hidden" name="inputGrade" value="${i}">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-10 search">
									<button type="submit" class="btn btn-primary search-btn">コメントを更新</button>
								</div>
							</div>
						</form>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>