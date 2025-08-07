<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>

<script>
    $(document).ready(function() {
        $("form").submit(function(event) {
            var mbId = $("#mbId").val().trim();
            var mbPw = $("#mbPw").val().trim();

            if (mbId === "") {
                alert("아이디를 입력하세요.");
                $("#mbId").focus();
                return false; // 폼 제출 방지
            }

            if (mbPw === "") {
                alert("비밀번호를 입력하세요.");
                $("#mbPw").focus();
                return false; // 폼 제출 방지
            }

        });
    });
</script>


</head>
<body>
	<header id="main-header" class="py-2 btn-dark text-white">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h1>로그인</h1>
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
							<h5>로그인</h5>
						</div>
						<div class="card-body">
							<%-- 폼 데이터를 서버에 POST 방식으로 전송 --%>
							<form method="post" action="./MemberLogin">
								<fieldset>
									<div class="form-group row">
										<label for="mbId" class="ml-sm-3 col-form-label"> 아이디 </label>
										<div class="ml-sm-3">
											<%-- 각 항목에 대해 name 속성은 서버에서 데이터를 받는 키로 사용->DTO랑 맞추기 --%>
											<input type="text" name="mbId" id="mbId" class="form-control form-control-sm">
										</div>
									</div>
									<div class="form-group row">
										<label for="mbPw" class="ml-sm-3 col-form-label"> 패스워드 </label>
										<div class="ml-sm-3">
											<input type="password" name="mbPw" id="mbPw" class="form-control form-control-sm">
										</div>
									</div>

									<div class="form-group">
										<%-- 사용자가 입력한 데이터를 서버로 제출하는 버튼 --%>
										<button type="submit" class="btn btn-secondary" id="loginBtn">로그인</button>
									</div>
								</fieldset>
							</form>
							<div>
								<%-- 버튼을 클릭하면 게시글 목록 페이지로 리디렉션 --%>
								<a href="./BoardList" class="btn btn-primary btn-block" id="boardListBtn"> 게시글 목록 </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>