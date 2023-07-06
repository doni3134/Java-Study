<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Welcome Page</title>
</head>
<body>
<h1>Welcome Page</h1>
<form action="welcome.jsp" method="get">
  <label for="name">Enter your name:</label>
  <input type="text" id="name" name="name">
  <input type="submit" value="Submit">
</form>
<%
  String name = request.getParameter("name");
  if (name != null && !name.isEmpty()) {
%>
<h2>Welcome, <%= name %>!</h2>
<%
  }
%>
</body>
</html>
