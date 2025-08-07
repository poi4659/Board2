<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
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

<style type="text/css">
/* 페이지 버튼 가로 정렬 */
ul {
	list-style: none;
	padding: 0;
	text-align: center;
	margin: 0 auto; /* 가로로 중앙 정렬 */
}

li {
	display: inline-block; /* 가로로 나열 */
	padding: 10px;
}
</style>
</head>
<body>
	<c:if test="${not empty msg}">
		<script type="text/javascript">
			window.addEventListener('load', function() {
				alert('아이디 또는 비밀번호가 올바르지 않습니다.');
			});
		</script>
	</c:if>


	<header id="main-header" class="py-2 btn-dark text-white">
		<div class="container">
			<div class="row align-items-center justify-content-between">
				<!-- 왼쪽: 게시판 타이틀 -->
				<div class="col-md-auto">
					<h1>게시판</h1>
				</div>

				<!-- 로그인된 상태라면 -->
				<c:if test="${member != null}">
					<div class="col-md-auto ms-auto">
						<!-- 아이디 클릭시 회원 정보 수정할 수 있는 마이페이지로 이동 -->
						<a href="./MemberMypage" class="btn text-white border-0 bg-transparent">${member.mbId}님</a>
						<a href="./MemberLogout" class="btn text-white border-0 bg-transparent">로그아웃</a>
					</div>
				</c:if>

				<!-- 로그인 안된 상태라면 -->
				<c:if test="${member == null}">
					<!-- 오른쪽: 회원가입 / 로그인 버튼 -->
					<div class="col-md-auto ms-auto">
						<a href="./MemberRegister" class="btn text-white border-0 bg-transparent">회원가입</a>
						<a href="./MemberLogin" class="btn text-white border-0 bg-transparent">로그인</a>
					</div>
				</c:if>


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
							<h5>게시글 목록</h5>
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
									<c:forEach var="list" items="${list}">
										<tr class="text-center">
											<!-- BoardDTO의 bNum, bTitle 등을 출력 -->
											<td>${list.bdNum}</td>
											<td>${list.bdTitle}</td>
											<td>${list.bdWriter}</td>
											<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd" /></td>
											<%-- 번호를 URL 파라미터로 넘겨서 상세 페이지로 이동 --%>
											<td><a href="./BoardSelect?bdNum=${list.bdNum}"
												class="btn btn-outline-info"
											> 게시글 상세 보기 </a></td>
										</tr>
									</c:forEach>
									<%--list가 null일 경우에 반환한다.--%>
									<c:if test="${empty list}">
										<tr>
											<td colspan="4">등록된 게시글이 없습니다.</td>
										</tr>
									</c:if>
								</tbody>
							</table>

							<!-- 로그인 된 상태라면 -->
							<c:if test="${member != null}">
								<div>
									<%-- 클릭 시 ./BoardInsert로 이동하여 게시글 내용을 입력할 수 있는 페이지로 리디렉션 --%>
									<a href="./BoardInsert" class="btn btn-success btn-block"> 게시글 작성</a>
								</div>
							</c:if>

							<!-- 로그인 안된 상태라면 -->
							<c:if test="${member == null}">
								<div>
									<%-- 클릭 시 ./MemberLogin로 이동하여 로그인을 할 수 있는 페이지로 리디렉션 --%>
									<a href="./MemberLogin" class="btn btn-success btn-block">게시글 작성</a>
								</div>
							</c:if>

							<!-- 페이징 처리 -->
							<div style="margin-top: 30px;">
								<ul>
									<c:if test="${pageMaker.prev}">
										<li><a
											href="./BoardList${pageMaker.makeQuery(pageMaker.startPage - 1)}"
										>이전</a></li>
									</c:if>

									<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
										var="idx"
									>
										<li><a href="./BoardList${pageMaker.makeQuery(idx)}">${idx}</a></li>
									</c:forEach>

									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<li><a
											href="./BoardList${pageMaker.makeQuery(pageMaker.endPage + 1)}"
										>다음</a></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>

</html>