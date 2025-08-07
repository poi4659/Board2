<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판 글 작성</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>

<script>
    $(document).ready(function() {
        $("form").submit(function(event) {
            var title = $("#bdTitle").val().trim();
            var content = $("#bdContent").val().trim();
            var writer = $("#bdWriter").val().trim();

            if (title === "") {
                alert("제목을 입력하세요.");
                $("#bdTitle").focus();
                return false; // 폼 제출 방지
            }

            if (content === "") {
                alert("내용을 입력하세요.");
                $("#bdContent").focus();
                return false; // 폼 제출 방지
            }

            if (writer === "") {
                alert("작성자를 입력하세요.");
                $("#bdWriter").focus();
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
					<h1>게시글 작성</h1>
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
							<h5>글 작성</h5>
						</div>
						<div class="card-body">
							<%-- 폼 데이터를 서버에 POST 방식으로 전송 --%>
							<form method="post" action="./BoardInsert">
								<fieldset>
									<div class="form-group row">
										<label for="bdTitle" class="ml-sm-3 col-form-label"> 제목 </label>
										<div class="ml-sm-3">
											<%-- 각 항목에 대해 name 속성은 서버에서 데이터를 받는 키로 사용->DTO랑 맞추기 --%>
											<input type="text" name="bdTitle" id="bdTitle" class="form-control form-control-sm">
										</div>
									</div>
									<div class="form-group row">
										<label for="bdContent" class="ml-sm-3 col-form-label"> 내용 </label>
										<div class="ml-sm-3">
											<input type="text" name="bdContent" id="bdContent" class="form-control form-control-sm">
										</div>
									</div>
									<div class="form-group row">
										<label for="bdWriter" class="ml-sm-3 col-form-label"> 작성자 </label>
										<div class="ml-sm-3">
											<input type="text" name="bdWriter" id="bdWriter" class="form-control form-control-sm">
										</div>
									</div>
									<div class="form-group">
										<%-- 사용자가 입력한 데이터를 서버로 제출하는 버튼 --%>
										<button type="submit" class="btn btn-secondary" id="insertBtn">등록</button>
										<%-- 폼을 초기화하여 입력된 데이터를 지우는 버튼 --%>
										<button type="reset" class="btn btn-secondary" id="resetBtn">입력 초기화</button>
									</div>
								</fieldset>
							</form>
							<div>
								<%-- 버튼을 클릭하면 게시글 목록 페이지로 리디렉션 --%>
								<a href="./BoardList" class="btn btn-primary btn-block"> 게시글 목록 </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>