<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="boardWriteFile" method="post" name="boardWriteForm" enctype="multipart/form-data">
<table>
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
</table>
</form>
<button onclick="boardWrite()">글등록</button>
<button onclick="location.href='pagingList'">목록</button>
</body>

<script>
function boardWrite(){
	boardWriteForm.submit();
}
</script>
</html>