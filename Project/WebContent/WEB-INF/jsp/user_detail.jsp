<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール</title>
</head>
<body>
	<!-- ヘッダー -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand title bold" href="../MyPage/my_page.html">学習管理システム</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
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
		<a class="nav-link header-right" href="../Login/login.html">ログアウト<span class="sr-only">(current)</span></a>
	</nav>

	<h1 class="sub-title"> アカウント情報 </h1>

	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4"> ログインID </div>
			<div class="col col-lg-5"> id0001 </div>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4"> 学年 </div>
			<div class="col col-lg-5"> 中学１年生 </div>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4"> 生徒氏名 </div>
			<div class="col col-lg-5"> 田中　太郎 </div>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4"> 生年月日 </div>
			<div class="col col-lg-5"> 2005年11月2日 </div>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4"> 連絡先 </div>
			<div class="col col-lg-5"> 03-****-**** </div>
		</div>
	</div>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col col-lg-4"> 住所 </div>
			<div class="col col-lg-5"> 東京都墨田区太平2丁目10-11 </div>
		</div>
	</div>

	<div class="table-container">
		<h3 class="table-name"> 受講講座一覧 </h3>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">講座名</th>
					<th scope="col">担当教員</th>
					<th scope="col">期間</th>
					<th scope="col">料金</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">国語</th>
					<td>佐藤</td>
					<td>1学期（4月～7月）</td>
					<td>***** 円/月</td>
				</tr>
				<tr>
					<th scope="row"></th>
					<td></td>
					<td>合計料金</td>
					<td>\ *****</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>