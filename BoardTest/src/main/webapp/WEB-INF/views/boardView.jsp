<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
<script>
	// 댓글 목록
	// result = commandList
	// result 값은 결국 return 받은 값
	function commentList(result){
		var output="";
		output += "<table><tr>";
		output += "<th>작성자</th>";
		output += "<th>내용</th>";
		output += "<th>삭제</th></tr>";

		for(var i in result){
			output += "<tr>";
			output += "<td>" + result[i].cwriter + "</td>";
			output += "<td>" + result[i].ccontents + "</td>";
			output += "<td><button onclick='commentDelete(" + result[i].cnum + ")'>삭제</button></td>";
			output += "</tr>";
		}
		output += "</table>";
		$("#commentArea").html(output);
	}

	/* $(document).ready(function(){
		// 페이지가 로딩될 때 실행
		commentList(result);
		}); */ 

	// 페이지 로딩시 댓글 목록 조회
	$(document).ready(function(){
		var cbnum = ${bView.bnum};
		$.ajax({
			type : "POST",
			url : "comment/commentList",
			data : {"cbnum" : cbnum },
			dataType : "json",
			success : function(result){
				commentList(result);
				},
			error: function() {
				alert("댓글 조회 실패");
				}
			});
		});
		
	// 댓글 입력
	$(document).ready(function(){
		
		$("#commentWriteBtn").click(function(){
			var cwriter = $("#cwriter").val();
			var ccontents = $("#ccontents").val();
			var cbnum = ${bView.bnum};

			$.ajax({
				type : "post",
				url : "comment/commentwrite",
				data : {
					"cwriter" : cwriter,
					"ccontents" : ccontents,
					"cbnum" : cbnum
					},
				dataType : "json",
				success : function(result){
					commentList(result);

					/* 댓글 상자 안 기본값을 설정함 */
					$("#cwriter").val("");
					$("#ccontents").val("");
				},
				error : function() {
					alert("댓글 입력 실패");
				}
				});
			});
		});	

	function commentDelete(cnum){
		$.ajax({
			type : "GET",
			url : "comment/commentdelete",
			data : {
					"cnum" : cnum,
					"cbnum" : ${bView.bnum}
				},
			dataType : "json",
			success : function(result){
				commentList(result);
				},
			error : function(){
				alert("댓글 입력 실패");
				}
			});
	}
</script>
</head>
<body>
			<table border="1">
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>비밀번호</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>제목</th>
					<th>내용</th>
					<th>첨부파일</th>
				</tr>
				<tr>
					<td>${bView.bnum}</td>
					<td>${bView.bwriter }</td>
					<td>${bView.bpw }</td>
					<td>${bView.bdate }</td>
					<td>${bView.bhit }</td>
					<td>${bView.btitle }</td>
					<td>${bView.bcontents }</td>
					<td>
						<img src="resources/fileUpload/${bView.bfilename}" style="width:200px; height:200px;" />
						<br/>${bView.bfilename }
					</td>
				</tr>
			</table>
			<br/>
			
			<div>
				작성자 : <input type="text" id="cwriter"><br/>
				댓글 : <textarea rows="5" cols="20" id="ccontents"></textarea>
				<button id="commentWriteBtn">댓글 입력</button>
			</div>
			
			<div id="commentArea">
				<%-- <table>
					<tr>
						<th>작성자</th>
						<th>내용</th>
						<th>삭제</th>
					</tr>
					<c:forEach var="comment" items="${commentList}">
						<tr>
							<th>${comment.cwriter}</th>
							<th>${comment.ccontents}</th>
							<th><button onclick="commentDelete('${comment.cnum}')">삭제</button></th>
						</tr>
					</c:forEach>
				</table> --%>
			</div>
			
			<br/>
			<br/>
			
		<button onclick="location.href='boardModify?bnum=${bView.bnum}&page=${page}'">수정</button>
		<button onclick="location.href='pagingList?page=${page}'">목록</button>
</body>
</html>