<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>

</head>
<body>
	<header id="main-header" class="py-2 btn-dark text-white">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h1>마이페이지</h1>
				</div>
			</div>
		</div>
	</header>
	<section id="actions" class="py-4 mb-4 bg-light"></section>
	<section id="details">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h5>마이페이지</h5>
						</div>
						<div class="card-body">
							<fieldset>
								<div class="form-group row">
									<label for="mbId" class="ml-sm-3 col-form-label"> 아이디 </label>
									<div class="ml-sm-3">
										<%-- 각 항목에 대해 name 속성은 서버에서 데이터를 받는 키로 사용, DTO랑 맞추기, 사용자가 수정할 수 없게 readonly 추가함  --%>
										<input type="text" name="mbId" id="mbId"
											class="form-control form-control-sm" value="${member.mbId}" readonly
										>
									</div>
								</div>
								<div class="form-group row">
									<label for="mbName" class="ml-sm-3 col-form-label"> 성명 </label>
									<div class="ml-sm-3">
										<%-- 각 항목에 대해 name 속성은 서버에서 데이터를 받는 키로 사용, DTO랑 맞추기, 사용자가 수정할 수 없게 readonly 추가함  --%>
										<input type="text" name="mbName" id="mbName"
											class="form-control form-control-sm" value="${member.mbName}"
											readonly
										>
									</div>
								</div>

							</fieldset>
							<div class="row">
								<div class="col-md-4">
									<%-- 버튼을 클릭하면 회원정보 수정 페이지로 리디렉션 --%>
									<a href="./MemberUpdate" class="btn btn-primary btn-block"> 회원정보 수정
							 		</a>
								</div>
								<div class="col-md-4">
									<a href="./BoardList" class="btn btn-warning btn-block"> 게시글 목록 </a>
								</div>
								<div class="col-md-4">
									<%-- 버튼을 클릭하면 회원탈퇴 페이지로 리디렉션 --%>
									<a href="./MemberDelete" class="btn btn-danger btn-block"> 회원탈퇴 </a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>