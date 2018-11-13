<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生徒一覧</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/user_list.css">
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

	<!-- 検索フォーム -->
	<h1 class="sub-title"> 生徒一覧 </h1>
	<div class="signMsg">
		<c:if test="${signMsg != null}">
			${signMsg}
		</c:if>
	</div>
	<div class="signup">
		<a href="UserSignUpServlet" class="signup-btn btn btn-secondary btn-lg active" role="button" aria-pressed="true">新規登録</a>
	</div>
	<form class="form bold" method="post" action="UserListServlet">
		<div class="txarea">
			<div class="form-group row">
				<label for="inputLoginId" class="col-sm-2 col-form-label">ログインID</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputLoginId" id="inputLoginId" placeholder="完全一致">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputUserName" class="col-sm-2 col-form-label">生徒氏名</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputUserName" id="inputUserName" placeholder="部分一致">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">学年</legend>
					<div class="col-sm-7">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="inputUGradeNo" id="gridRadios1" value="1">
							<label class="form-check-label" for="gridRadios1"> １年生</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="inputUGradeNo" id="gridRadios2" value="2">
							<label class="form-check-label" for="gridRadios2"> ２年生 </label>
						</div>
						<div class="form-check disabled">
							<input class="form-check-input" type="radio" name="inputUGradeNo" id="gridRadios3" value="3">
							<label class="form-check-label" for="gridRadios3"> ３年生 </label>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputAddress" class="col-sm-2 col-form-label">住所</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputAddress" id="inputAddress" placeholder="部分一致">
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10 search">
				<button type="submit" class="btn btn-primary search-btn">検索</button>
			</div>
		</div>
	</form>

	<!-- ユーザ一覧表 -->
	<div class="search-style">
		${uGradeNo}学年　　
		【検索ワード】ログインID："${loginId}"　　生徒氏名："${userName}"　　住所："${address}"
	</div>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">ログインID</th>
				<th scope="col">学年</th>
				<th scope="col">読み仮名</th>
				<th scope="col">名前</th>
				<th scope="col">性別</th>
				<th scope="col">連絡先</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<th scope="row">${user.loginId}</th>
					<td>中 ${user.grade}</td>
					<td>${user.userNamePhonetic}</td>
					<td>${user.userName}</td>
					<td>${user.sex}</td>
					<td>${user.contactInfo}</td>
					<td>
						<a href="UserDetailServlet?admin=${user.userId}" class="detail button btn btn-secondary btn-lg active" role="button" aria-pressed="true">詳細</a>
						<a href="UserUpdateServlet?userId=${user.userId}" class="update button btn btn-secondary btn-lg active" role="button" aria-pressed="true">更新</a>
						<a href="UserDeleteServlet?userId=${user.userId}" class="delete button btn btn-secondary btn-lg active" role="button" aria-pressed="true">削除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>