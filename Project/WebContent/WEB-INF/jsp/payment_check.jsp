<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入金確認</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/payment_check.css">
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
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span class="sr-only">(current)</span></a>
	</nav>

	<h1 class="sub-title"> 入金確認 </h1>
	<div class="table-box">
		<p class="table-title"> 申込内容 </p>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">学年</th>
					<th scope="col">講座名</th>
					<th scope="col">担当教員</th>
					<th scope="col">期間</th>
					<th scope="col">料金</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="applicationDetail" items="${applicationDetailList}">
					<tr>
						<th scope="row">中${applicationDetail.grade}</th>
						<td>${applicationDetail.courseName}</td>
						<td>${applicationDetail.teacher}</td>
						<td>
							<c:if test="${applicationDetail.term <= 3}">
								${applicationDetail.term}学期
							</c:if> <c:if test="${applicationDetail.term > 3}">
								${sCourseList[course.term-4]}
							</c:if></td>
						<td>${applicationDetail.price} 円</td>
					</tr>
				</c:forEach>
				<tr>
					<th scope="row"></th>
					<td></td>
					<td></td>
					<td>合計金額</td>
					<td>${totalPrice} 円</td>
				</tr>
				<tr>
					<th scope="row"></th>
					<td></td>
					<td></td>
					<td>未納額（入金済金額）</td>
					<td>${totalPrice-application.payAmount}（${application.payAmount}） 円</td>
				</tr>
			</tbody>
		</table>
	</div>

	<form class="payment-form" method="post" action="PaymentCheckServlet">
		<div class="row pm">
			<div class="col-sm-5">
				<div class="form-group row">
					<label for="payment" class="col-sm-5 col-form-label">今回の入金金額</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" name= "payment" id="payment" value="${payment}">
					</div>
				</div>
			</div>
			<div class="col-sm-2 vector">⇒</div>
			<div class="col-sm-5">過不足金額： ${totalDef} 円</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10 search">
				<button name="cal" type="submit" class="btn btn-primary search-btn" value="cal">この金額で過不足を算出</button>
				<button name="sign" type="submit" class="btn btn-primary search-btn ok" value="sign">登録</button>
			</div>
		</div>
	</form>
</body>
</html>