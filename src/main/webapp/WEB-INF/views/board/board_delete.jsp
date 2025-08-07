<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 삭제</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
<script src="./js/jquery.validate.min.js" type="text/javascript"></script>
<script src="./js/dept_validity.js" type="text/javascript"></script>
</head>
<body>
	<header id="main-header" class="py-2 btn-dark text-white">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h1>게시글 삭제</h1>
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
							<h5>글 삭제</h5>
						</div>
						<div class="card-body">
							<%-- POST 방식으로 데이터를 서버에 전송 --%>
							<form method="post" id="sign_board">
								<fieldset>
									<div class="form-group row">
										<div class="ml-sm-3">
											<%-- value="${param.bnum}": URL에서 bnum 파라미터를 가져옴 --%>
											<input type="hidden" id="bdNum" name="bdNum" value="${param.bdNum}" />

											<%-- 작성자는 수정할 수 없도록 readonly 속성으로 설정 --%>
											<label for="bdWriter" class="ml-sm-3 col-form-label text-right">
												작성자 </label> <input type="text" name="bdWriter" id="bdWriter"
												class="form-control form-control-sm bg-white"
												value="${boardDTO.bdWriter}" readonly
											>
										</div>
									</div>
									<div class="form-group row">
										<label for="bdTitle" class="ml-sm-3 col-form-label"> 제목 </label>
										<div class="ml-sm-3">
											<input type="text" name="bdTitle" id="bdTitle"
												class="form-control form-control-sm bg-white" value="${boardDTO.bdTitle}"
												readonly
											>
										</div>
									</div>
									<div class="form-group row">
										<label for="bdContent" class="ml-sm-3 col-form-label"> 내용 </label>
										<div class="ml-sm-3">
											<input type="text" name="bdContent" id="bdContent"
												class="form-control form-control-sm bg-white" value="${boardDTO.bdContent}"
												readonly
											>
										</div>
									</div>
									<div class="form-group">
										<%-- 사용자가 입력한 데이터를 서버로 제출하는 버튼 --%>
										<button type="submit" class="btn btn-secondary" id="deleteBtn">게시글 삭제</button>
									</div>
								</fieldset>
							</form>
							<div class="row">
								<div class="col-md-12">
									<a href="./BoardList" class="btn btn-primary btn-block"> 게시글 목록 </a>
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