<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>申込・入金履歴</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/application_history.css">
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
				<c:forEach var="application" items="${applicationList}">
					<tr>
						<th scope="row">${application.appDate}</th>
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
						<td>${application.appAmount} 円</td>
						<td>${application.payAmount} 円</td>
						<td>${application.payAmount-application.appAmount} 円</td>
						<td><a href="ApplicationDetailServlet?applicationNo=${application.applicationNo}&payFg=${application.payFg}" class="btn btn-primary">詳細</a></td>
					</tr>
				</c:forEach>
				<tr>
					<th scope="row"></th>
					<td></td>
					<td></td>
					<td>過不足合計</td>
					<td>${totalDef} 円</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>