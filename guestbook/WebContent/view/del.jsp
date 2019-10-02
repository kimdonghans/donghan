<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("utf-8");
String msgId = request.getParameter("messageId");
String msgPwd = request.getParameter("messagepassword");
%>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
<form action="realDel.do" method="post">
메시지를 삭제하시려면 암호를 입력하세요:<br />
암호: <input type="password" name="password" /> <br />
  <input type="submit" value="메시지 삭제하기" />
  <input type="hidden" name="password" value=<%=msgPwd %> />
  <input type="hidden" name="id" value=<%=msgId %> />
 </form>
</body>
</html>