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
				<li class="nav-item active">
					<a class="nav-link" href="CourseMasterServlet" role="button" aria-haspopup="true" aria-expanded="false"> 講座マスタ </a>
				</li>
			</ul>
		</div>
		<a class="nav-link header-right" href="LogoutServlet">ログアウト<span class="sr-only">(current)</span></a>
	</nav>

	<h1 class="sub-title"> 申込一覧 </h1>

	<div class="signMsg">
		<c:if test="${signMsg != null}">
			${signMsg}
		</c:if>
	</div>

	<form class="form bold" method="post" action="ApplicationListServlet">
		<div class="txarea">
			<div class="form-group row">
				<label for="inputApplicationNo" class="col-sm-2 col-form-label">申込番号</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputApplicationNo" id="inputApplicationNo" placeholder="完全一致">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputBetween" class="col-sm-2 col-form-label">申込日</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="inputStart" id="inputBetween" placeholder="年/月/日">
				</div>
				<div class="between col-sm-1">～</div>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="inputEnd" id="inputBetween" placeholder="年/月/日">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputLoginId" class="col-sm-2 col-form-label">ログインID</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputLoginId" id="inputLoginId" placeholder="完全一致">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputUserName" class="col-sm-2 col-form-label">ユーザ名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputUserName" id="inputUserName" placeholder="部分一致">
				</div>
			</div>
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
		</div>
		<div class="form-group row">
			<div class="col-sm-9 search">
				<button type="submit" class="btn btn-primary search-btn">検索</button>
			</div>
		</div>
	</form>

	<div class="search-style">
		${grade}学年　　申込番号："${applicationNo}"　　申込日："${start}～${end}"<br>
		【検索ワード】ログインID："${loginId}"　　生徒氏名："${userName}"
	</div>

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
					<c:if test="${application.payFg == 1}">
						<td><span class="check1"> ${application.payFgList[1]}</span>(${application.payDate})</td>
					</c:if>
					<c:if test="${application.payFg == 0}">
						<td><span class="check2"> ${application.payFgList[0]} </span></td>
					</c:if>
					<c:if test="${application.payFg == 2}">
						<td><span class="check3"> ${application.payFgList[2]} </span></td>
					</c:if>
					<c:if test="${application.payFg == 3}">
						<td><span class="check3"> ${application.payFgList[3]} </span></td>
					</c:if>
					<td>
						<a href="ApplicationDetailServlet?applicationNo=${application.applicationNo}&payFg=${application.payFg}" class="detail button btn btn-secondary btn-lg active" role="button" aria-pressed="true">詳細</a>
						<a href="PaymentCheckServlet?applicationNo=${application.applicationNo}" class="detail button btn btn-secondary btn-lg active" role="button" aria-pressed="true">入力</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>