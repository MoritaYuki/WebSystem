<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/payment_history.css">
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
				<li class="nav-item active">
					<a class="nav-link" href="../UserDetail/user_detail.html">プロフィール<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="../ExamResult/exam_result.html">成績</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="../CourseList/course_list.html" role="button" aria-haspopup="true" aria-expanded="false"> 講座一覧 </a>
				</li>
			</ul>
		</div>
		<a class="nav-link header-right" href="../Login/login.html">ログアウト<span class="sr-only">(current)</span></a>
	</nav>

	<h1 class="sub-title"> 申込･入金履歴 </h1>

	<div class="table-box">
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">申込日</th>
					<th scope="col">入金</th>
					<th scope="col">必要金額</th>
					<th scope="col">入金金額</th>
					<th scope="col">過不足</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">****年 **月 **日</th>
					<td><span class="check">済</span>（****年 **月 **日）</td>
					<td>\ *****</td>
					<td>\ *****</td>
					<td>\ *****</td>
					<td><a href="../PaymentHistoryDetail/payment_history_detail.html" class="btn btn-primary">詳細</a></td>
				</tr>
				<tr>
					<th scope="row">****年 **月 **日</th>
					<td><span class="check2">未</span></td>
					<td>\ *****</td>
					<td>\ *****</td>
					<td>\ *****</td>
					<td><a href="../PaymentHistoryDetail/payment_history_detail.html" class="btn btn-primary">詳細</a></td>
				</tr>
				<tr>
					<th scope="row"></th>
					<td></td>
					<td></td>
					<td>過不足合計</td>
					<td>\ *****</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>