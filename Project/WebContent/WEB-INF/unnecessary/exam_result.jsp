<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト結果</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/exam_result.css">
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

	<h1 class="sub-title"> テスト成績 </h1>

	<!-- 管理者ログインのときは表示しない -->
	<c:if test="${loginInfo.userId != 1}">
		<div class="info-btn-box">
			<c:if test="${fg == 0}">
				<a class="info-btn btn btn-secondary btn-lg disabled" href="UserDetailServlet?fg=0">アカウント情報</a>
			</c:if>
			<c:if test="${fg != 0}">
				<a class="info-btn btn btn-secondary btn-lg " href="UserDetailServlet?fg=0">アカウント情報</a>
			</c:if>
			<c:if test="${fg == 1}">
				<a class="exam-btn info-btn btn btn-secondary btn-lg disabled" href="UserDetailServlet?fg=1">成績情報</a>
			</c:if>
			<c:if test="${fg != 1}">
				<a class="exam-btn info-btn btn btn-secondary btn-lg " href="UserDetailServlet?fg=1">成績情報</a>
			</c:if>
		</div>
	</c:if>

	<!-- ユーザ一覧表 -->
	<c:forEach var="i" begin="1" end="3">
			<div class="table-box">
				<p class="table-title"> 第${i}学年 </p>
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
									<p class="card-text">
										<c:forEach var="comment" items="${commentList[i-1]}">
											${comment} <br>
										</c:forEach>
									</p>
								</div>
							</div>
						</c:if>
						<c:if test="${loginInfo.userId == 1}">
							<form class="form bold" method="post" action="UserDetailServlet">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">【担当教員からのコメント】</h5>
										<!-- 表示の開業を作り出すための不自然なインデント -->
										<textarea class="card-text" name="inputComment"><c:forEach var="comment" items="${commentList[i-1]}">${comment}</c:forEach></textarea>
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
			</div>
	</c:forEach>
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