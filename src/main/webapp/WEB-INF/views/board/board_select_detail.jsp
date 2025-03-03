<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
</head>
<body>
	<header id="main-header" class="py-2 btn-dark text-white">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h1>게시판</h1>
				</div>
			</div>
		</div>
	</header>
	<section id="dept" class="py-4 mb-4 bg-light"></section>
	<section id="details">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h5>게시글</h5>
						</div>
						<div class="card-body">
							<table class="table table-hover">
								<thead class="thead-light">
									<tr class="text-center">
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>등록일</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr class="text-center">
										<!-- BoardDTO의 bNum, bTitle 등을 출력 -->
										<td>${boardDTO.bdNum}</td>
										<td>${boardDTO.bdTitle}</td>
										<td>${boardDTO.bdWriter}</td>
										<td>
											<fmt:formatDate value="${boardDTO.regDate}" pattern="yyyy-MM-dd" />
										</td>
									</tr>
								</tbody>
							</table>
							<div class="row">
								<div class="col-md-4">
									<%-- 게시글 목록 버튼을 클릭하면, 게시글 목록 페이지로 리디렉션 --%>
									<a href="./BoardList" class="btn btn-primary btn-block"> 게시글 목록 </a>
								</div>
								<div class="col-md-4">
									<%-- 게시글 수정 버튼을 클릭하면, bdNum 값을 URL 파라미터로 전달하면서 게시글 수정 페이지로 리디렉션 --%>
									<a href="./BoardUpdate?bdNum=${boardDTO.bdNum}" class="btn btn-warning btn-block"> 게시글 수정 </a>
								</div>
								<div class="col-md-4">
									<%-- 게시글 삭제 버튼을 클릭하면, bdNum 값을 URL 파라미터로 전달하면서 게시글 삭제 페이지로 이동 --%>
									<a href="./BoardDelete?bdNum=${boardDTO.bdNum}" class="btn btn-danger btn-block"> 게시글 삭제 </a>
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