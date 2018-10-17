<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト結果マスタ一覧</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/exam_master.css">
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
	<!-- 検索フォーム -->
	<h1 class="sub-title"> テスト結果マスタ一覧 </h1>
		<div class="error">
			<c:if test="${errMsg != null}">
				${errMsg}
			</c:if>
		</div>
	<form class="form bold" method="post" action="ExamMasterServlet">
		<div class="txarea">
			<div class="form-group row">
				<label for="inputYear" class="col-sm-1 col-form-label">年度</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="inputYear" id="inputYear">
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-8 search">
				<button type="submit" name="search" class="btn btn-primary search-btn" value="search">検索</button>
			</div>
		</div>
	</form>

	<div class="text-center">
		<c:forEach var="i" begin="1" end="3">
			<c:if test="${eGradeNo == i}">
				<a class="term-btn btn btn-secondary btn-lg disabled" href="ExamMasterServlet?eTermNo=${eTermNo}&eGradeNo=${i}&jspFg=${jspFg}">${i}年生</a>
			</c:if>
			<c:if test="${eGradeNo != i}">
				<a class="term-btn btn btn-secondary btn-lg " href="ExamMasterServlet?eTermNo=${eTermNo}&eGradeNo=${i}&jspFg=${jspFg}">${i}年生</a>
			</c:if>
		</c:forEach>
	</div>
	<div class="text-center">
		<c:forEach var="i" begin="1" end="3">
			<c:if test="${eTermNo == i}">
				<a class="term-btn btn btn-secondary btn-lg disabled" href="ExamMasterServlet?eGradeNo=${eGradeNo}&eTermNo=${i}&jspFg=${jspFg}">${i}学期末</a>
			</c:if>
			<c:if test="${eTermNo != i}">
				<a class="term-btn btn btn-secondary btn-lg " href="ExamMasterServlet?eGradeNo=${eGradeNo}&eTermNo=${i}&jspFg=${jspFg}">${i}学期末</a>
			</c:if>
		</c:forEach>
	</div>

	<form method="post" action="ExamMasterServlet">
		<div class="table-box">
			<div class="signup">
				<a class="table-title"> ${year}年度　${eGradeNo}学年　${eTermNo}学期末テスト </a>
				<c:if test="${jspFg == 0}">
					<a href="ExamMasterServlet?jspFg=0&eGradeNo=${eGradeNo}&eTermNo=${eTermNo}" class="signup-btn btn btn-secondary btn-lg active disabled" role="button" aria-pressed="true">閲覧画面</a>
				</c:if>
				<c:if test="${jspFg != 0}">
					<a href="ExamMasterServlet?jspFg=0&eGradeNo=${eGradeNo}&eTermNo=${eTermNo}" class="signup-btn btn btn-secondary btn-lg active" role="button" aria-pressed="true">閲覧画面</a>
				</c:if>
				<c:if test="${jspFg == 1}">
					<a href="ExamMasterServlet?jspFg=1&eGradeNo=${eGradeNo}&eTermNo=${eTermNo}" class="signup-btn btn btn-secondary btn-lg active disabled" role="button" aria-pressed="true">更新画面</a>
				</c:if>
				<c:if test="${jspFg != 1}">
					<a href="ExamMasterServlet?jspFg=1&eGradeNo=${eGradeNo}&eTermNo=${eTermNo}" class="signup-btn btn btn-secondary btn-lg active" role="button" aria-pressed="true">更新画面</a>
				</c:if>
			</div>
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">ログインID</th>
						<th scope="col">読み仮名</th>
						<th scope="col">名前</th>
						<th scope="col">性別</th>
						<th scope="col">国語</th>
						<th scope="col">数学</th>
						<th scope="col">英語</th>
						<th scope="col">理科</th>
						<th scope="col">社会</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="exam" items="${examList}">
						<tr>
							<c:if test="${year == exam.year && eGradeNo == exam.grade && eTermNo == exam.term}">
								<input type="hidden" name="userId${exam.userId}" value="${exam.userId}">
								<th scope="row">${exam.loginId}</th>
								<td>${exam.userNamePhonetic}</td>
								<td>${exam.userName}</td>
								<td>${exam.sex}</td>
								<c:if test="${jspFg == 0}">
									<td>${exam.japanese}</td>
								</c:if>
								<c:if test="${jspFg == 1}">
									<td>
										<input type="text" class="form-control" name="japanese${exam.userId}" value="${exam.japanese}">
									</td>
								</c:if>
								<c:if test="${jspFg == 0}">
									<td>${exam.math}</td>
								</c:if>
								<c:if test="${jspFg == 1}">
									<td>
										<input type="text" class="form-control" name="math${exam.userId}" value="${exam.math}">
									</td>
								</c:if>
								<c:if test="${jspFg == 0}">
									<td>${exam.english}</td>
								</c:if>
								<c:if test="${jspFg == 1}">
									<td>
										<input type="text" class="form-control" name="english${exam.userId}" value="${exam.english}">
									</td>
								</c:if>
								<c:if test="${jspFg == 0}">
									<td>${exam.science}</td>
								</c:if>
								<c:if test="${jspFg == 1}">
									<td>
										<input type="text" class="form-control" name="science${exam.userId}" value="${exam.science}">
									</td>
								</c:if>
								<c:if test="${jspFg == 0}">
									<td>${exam.social}</td>
								</c:if>
								<c:if test="${jspFg == 1}">
									<td>
										<input type="text" class="form-control" name="social${exam.userId}" value="${exam.social}">
									</td>
								</c:if>
								<td><a href="UserDetailServlet?admin=${exam.userId}" class="detail button btn btn-secondary btn-lg active" role="button" aria-pressed="true">詳細</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${jspFg == 1}">
			<div class="form-group row">
				<div class="col-sm-10 search">
					<button type="submit" name="update" class="btn btn-primary search-btn" value="update">更新</button>
				</div>
			</div>
		</c:if>
	</form>
</body>
</html>