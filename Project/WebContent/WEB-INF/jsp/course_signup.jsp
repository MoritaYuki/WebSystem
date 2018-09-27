<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座登録</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/course_signup.css">
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
					<a class="nav-link"href="UserListServlet">生徒一覧<span class="sr-only">(current)</span></a>
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

	<!-- 検索フォーム -->
	<h1 class="sub-title"> 講座登録 </h1>
	<form class="form bold" method="post" action="CourseSignUpServlet">
		<div class="txarea">
			<div class="signMsg">
				<c:if test="${signMsg != null}">
					${signMsg}
				</c:if>
			</div>
			<p class="subsub-title"> 講座検索（検索結果は下記の「選択可能講座」に表示されます） </p>
				<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">期間</legend>
					<div class="col-sm-7">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1" checked>
							<label class="form-check-label" for="gridRadios1"> １学期</label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios1" value="option1">
							<label class="form-check-label event-lavel" for="gridRadios1"> 春期講習</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label" for="gridRadios2"> ２学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios2" value="option2">
							<label class="form-check-label event-lavel" for="gridRadios2"> 夏期講習 </label>
						</div>
						<div class="form-check disabled">
							<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3">
							<label class="form-check-label" for="gridRadios3"> ３学期 </label>
							<input class="form-check-input event" type="radio" name="gridRadios" id="gridRadios3" value="option3">
							<label class="form-check-label event-lavel" for="gridRadios3"> 冬期講習 </label>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label for="inputUserName" class="col-sm-2 col-form-label">講座名</label>
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
			<div class="col-sm-10 search">
				<button type="submit" class="btn btn-primary search-btn">検索</button>
			</div>
		</div>
	</form>

	<!-- 講座の入力フォーム -->
	<form class="form bold table-box" method="post" action="">
		<div class="row ">
			<div class="col-sm-5">
				<p class="subsub-title"> 選択可能講座リスト </p>
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
							<th scope="row">
								<div class="checkbox">
									<label><input type="checkbox"> 国語 </label>
								</div>
							</th>
							<td>佐藤</td>
							<td>**/** ～ **/**</td>
							<td>\ *****</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2 vector-box"> ⇒ </div>
			<div class="col-sm-5">
				<p class="subsub-title"> 登録講座リスト </p>
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
							<td>**/** ～ **/**</td>
							<td>\ *****</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-11 signup">
				<button type="submit" name="export" class="btn btn-primary search-btn list-in">選択した講座を登録講座リストに移す</button>
				<button type="submit" name="sign" class="btn btn-primary search-btn">登録講座リストの内容で登録</button>
			</div>
		</div>
	</form>
</body>
</html>