<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>仮申込一覧</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/application_cart.css">
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

	<h1 class="sub-title"> 仮申込講座一覧 </h1>

	<div class="error">
		<c:if test="${errMsg != null}">
			${errMsg}
		</c:if>
	</div>

	<div class="table-box">
		<p class="table-title"> 仮申込内容 </p>
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
				<c:forEach var="course" items="${cartList}">
					<tr>
						<th scope="row">中${course.grade}</th>
						<td>${course.courseName}</td>
						<td>${course.teacher}</td>
						<td>
							<c:if test="${course.term <= 3}" >
								${course.term}学期
							</c:if>
							<c:if test="${course.term > 3}" >
								${sCourseList[course.term-4]}
							</c:if>
						</td>
						<td>${course.price} 円/月</td>
					</tr>
				</c:forEach>
				<tr>
					<th scope="row"></th>
					<td></td>
					<td></td>
					<td>合計金額</td>
					<td>${totalPrice} 円/月</td>
				</tr>
			</tbody>
		</table>
		<div class="btn-box row justify-content-end">
			<div class="col-4">
				<a class="list-in search-btn btn btn-primary" href="CourseListServlet">仮申込講座を追加</a>
			</div>
			<div class="col-4">
				<form method="post" action="ApplicationCartServlet">
					<button class="search-btn btn btn-primary">仮申込内容で正式に申込</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>