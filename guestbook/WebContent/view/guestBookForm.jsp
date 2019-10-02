<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.util.*"
import="guestbook.dao.*"
import="guestbook.model.*"
import="guestbook.service.*"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
   	request.setCharacterEncoding("utf-8");
	List list = (List)request.getAttribute("list");
	
	int totalPage = (int)request.getAttribute("totalPage");
%>
<c:set var="list" value="<%=list %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="write.do" method="post">
		<table WIDTH="500" BORDER="1" CellPadding="0" CellSpacing="0">
			<TR>
				<TD WIDTH="40%" ALIGN="center">이름</TD>
				<TD width="80%" ALIGN="center"><INPUT TYPE="text" SIZE="15"
					MAXLENGTH="10" NAME="name"></TD>
			</TR>
			<TR>
				<TD WIDTH="40%" ALIGN="center">암호</TD>
				<TD WIDTH="60%" ALIGN="center"><INPUT TYPE="password" SIZE="15"
					MAXLENGTH="10" NAME="password"></TD>
			</TR>
			<TR>
				<TD WIDTH="40%" ALIGN="center">메시지</TD>
				<TD WIDTH="60%" ALIGN="center"><TEXTAREA NAME="message" ROWS=3
						COLS=30></TEXTAREA></TD>
			</TR>
			<TR>
				<TD WIDTH="100%" ALIGN="center" COLSPAN="2"><INPUT
					TYPE="submit" VALUE="메시지 남기기"></TD>
			</TR>
			</table>
			<hr> 
<%-- c:if test="${viewData.isEmpty()}"> 등록된 메시지가 없습니다. </c:if>
<c:if test="${!viewData.isEmpty()}">--%>



<br>
<table border="1"> 
<c:forEach var="message" items="${list}">
<tr>
<td>
메시지 번호: ${message.id}  <br /> 
손님 이름:${message.name} <br /> 
메시지: ${message.message} <br /> 
<a href="del.do?messageId=${message.id} &messagepassword=${message.password}">[삭제하기]</a> 

</td>
</tr> 
</c:forEach>
			
</table>

<c:forEach var="pageNum" begin="1" end="<%= totalPage %>">
<a href="list.do?pageNum=${ pageNum }">[${ pageNum }]</a>
</c:forEach>

</form>
</body>
</html>