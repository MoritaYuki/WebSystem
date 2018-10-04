<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>申込一覧</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/application_list.css">
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
				<li class="nav-item">
					<a class="nav-link" href="CourseMasterServlet" role="button" aria-haspopup="true" aria-expanded="false"> 講座マスタ </a>
				</li>
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span class="sr-only">(current)</span></a>
	</nav>

	<h1 class="sub-title"> 申込一覧 </h1>
	<form class="form bold" method="post" action="">
		<div class="txarea">
			<div class="form-group row">
				<label for="inputLoginId" class="col-sm-2 col-form-label">申込番号</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputLoginId">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputBetween" class="col-sm-2 col-form-label">申込日</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="inputBetween">
				</div>
				<div class="between col-sm-1">～</div>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="inputBetween">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputLoginId" class="col-sm-2 col-form-label">ログインID</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputLoginId">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputUserName" class="col-sm-2 col-form-label">ユーザ名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="inputUserName">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">学年</legend>
					<div class="col-sm-7">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1" checked>
							<label class="form-check-label" for="gridRadios1"> １年生</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label" for="gridRadios2"> ２年生 </label>
						</div>
						<div class="form-check disabled">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3">
							<label class="form-check-label" for="gridRadios3"> ３年生 </label>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
		<div class="form-group row">
			<div class="col-sm-9 search">
				<button type="submit" class="btn btn-primary search-btn">検索</button>
			</div>
		</div>
	</form>

	<!-- ユーザ一覧表 -->
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">申込日</th>
				<th scope="col">申込番号</th>
				<th scope="col">ログインID</th>
				<th scope="col">学年</th>
				<th scope="col">名前</th>
				<th scope="col">申込金額</th>
				<th scope="col">入金状況</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="application" items="${applicationList}">
				<tr>
					<td>${application.appDate}</td>
					<th scope="col">${application.applicationNo}</th>
					<th scope="row">${application.loginId}</th>
					<td>中${application.grade}</td>
					<td>${application.userName}</td>
					<td>${application.appAmount} 円</td>
					<c:if test="${application.payFg == true}">
						<td><span class="check1"> ${payFgList[1]}(${payDate}) </span></td>
					</c:if>
					<c:if test="${application.payFg == false}">
						<td><span class="check2"> ${payFgList[0]} </span></td>
					</c:if>
					<td>
						<a href="ApplicationDetailServlet?applicationNo=${application.applicationNo}&payFg=${application.payFg}" class="detail button btn btn-secondary btn-lg active" role="button" aria-pressed="true">詳細</a>
						<a href="PaymentCheckServlet?applicationNo=${application.applicationNo}" class="detail button btn btn-secondary btn-lg active" role="button" aria-pressed="true">入力</a>
					</td>
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