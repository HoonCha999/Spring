<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 검색창 -->
	<div class="top">
		<form action="boardSearch" method="GET">
			검색조건: <select name="type">
				<option value="1">작성자</option>
				<option value="2">제목</option>
				</select>
				
			검색어: <input type="text" name="keyword"/>
			
		<input type="submit" value="검색">
		</form>
	</div>

	<!-- 게시판 목록 및 페이지 리스트 -->
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>작성자</td>
			<td>글제목</td>
			<td>작성일자</td>
			<td>조회수</td>
			<td>수정하기</td>
			<td>삭제하기</td>
		</tr>
		<c:forEach var="board" items="${paginglist }">
			<tr>
				<td>${board.bnum }</td>
				<td>${board.bwriter }</td>
				<td>${board.btitle }</td>
				<td>${board.bdate }</td>
				<td>${board.bhit }</td>
				<td><a href="boardView?bnum=${board.bnum }&page=${paging.page}">${board.bnum }번 상세</a></td>
				<td><a href="boardDelete?bnum=${board.bnum }&page=${paging.page}">${board.bnum }번 삭제</a></td>
			</tr>

		</c:forEach>
	</table>
	<c:if test="${paging.page <= 1 }">[이전]&nbsp;</c:if>
	<c:if test="${paging.page > 1 }">
		<a href="pagingList?page=${paging.page-1 }">[이전]&nbsp;</a>
	</c:if>

	<c:forEach begin="${paging.startpage }" end="${paging.endpage }"
		var="i" step="1">
		<c:choose>
			<c:when test="${i eq paging.page }">${i }</c:when>
			<c:otherwise>
				<a href="pagingList?page=${i }">${i }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${paging.page >= paging.maxpage }">[다음]</c:if>
	<c:if test="${paging.page < paging.maxpage }">
		<a href="pagingList?page=${paging.page+1 }">[다음]</a>
	</c:if>

</body>
</html>