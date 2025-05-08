<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
table,tr,td,th{
border: solid 1px;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table>
			<tr>
				<th>UID</th>
				<th>NAME</th>
				<th>ADDR</th>
				<th>EMAIL</th>
			</tr>

			<c:forEach var="user" items="${userlist}">
				<tr>
					<td>${user.uid}</td>
					<td>${user.name}</td>
					<td>${user.adress}</td>
					<td>${user.email}</td>
				</tr>
			</c:forEach>

		</table>
		<div><a href="adduser">ADD USER</a></div>
	</center>
</body>
</html>