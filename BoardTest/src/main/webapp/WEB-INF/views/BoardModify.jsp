<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="modifyProcess" method="post" name="modifyProcessForm" enctype="multipart/form-data">
<table>
<tr>
	<td>글번호</td>
	<td><input type="hidden" value="${bView.bnum}" name="bnum"></td>
</tr>
<tr>
	<td>조회수</td>
	<td>${bView.bhit}</td>
</tr>
<tr>
	<td>작성일</td>
	<td>${bView.bdate}</td>
</tr>
<tr>
	<td>작성자</td>
	<td><input type="text" name="bwriter" size="39"/></td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="bpw" size="39"/></td>
</tr>
<tr>
	<td>제목</td>
	<td><input type="text" name="btitle" size="39"/></td>
</tr>
<tr>
	<td>내용</td>
	<td><textarea rows="20" cols="40"name="bcontents"></textarea></td>
</tr>
<tr>
	<td>첨부파일</td>
	<td><input type="file" name="bfile"></td>
</tr>
<tr>
	<td><input type="hidden" value="${page }" name="page"></td>
	<td><input type="submit" value="수정완료"></td>
</tr>

</table>
</form>

<button onclick="location.href='boardList'">목록</button>
</body>

</html>