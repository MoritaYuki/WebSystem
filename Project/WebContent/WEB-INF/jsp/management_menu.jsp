<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理メニュー</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="/StudentManagement/css/management_menu.css">
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

	<h1 class="sub-title"> 管理メニュー </h1>
	<h3 class="welcome-msg">
		ようこそ ${loginInfo.userName} さん<br>
		メニューからやりたいことを選択してください
	</h3>

	<div class="contents">
		<ul>
			<li>
				<img class="menu-img" width="210px" height="290px" src="/StudentManagement/img/295963ce0d6bdf3.jpg">
				<a class="menu" href="UserListServlet">生徒一覧</a>
			</li>
			<li>
				<img class="menu-img" width="210px" height="290px" src="/StudentManagement/img/295963ce0d6bdf3.jpg">
				<a class="menu" href="ApplicationListServlet">申込管理</a>
			</li>
			<li>
				<img class="menu-img" width="210px" height="290px" src="/StudentManagement/img/295963ce0d6bdf3.jpg">
				<a class="menu" href="ExamMasterServlet">成績マスタ</a>
			</li>
			<li>
				<img class="menu-img" width="210px" height="290px" src="/StudentManagement/img/295963ce0d6bdf3.jpg">
				<a class="menu" href="CourseMasterServlet">講座マスタ</a>
			</li>
		</ul>
	</div>
</body>
</html>