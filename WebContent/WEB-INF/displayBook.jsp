<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Book</title>
<style>
	table, th, td{
		border:1px solid blue;
		border-collapse:collapse;
	}

</style>
</head>
<body>
<jsp:include page="header.html"></jsp:include>
<br><br><br>
	<table>
		<tr>
			<th>Book Code</th>
			<th>Book Title</th>
			<th>Book Author</th>
			<th>Book Prize</th>
			<th>Action</th>
		</tr> 
		<c:forEach items="${requestScope.list}" var="data">
			<tr>
				<td>${data.bookCode}</td>
				<td>${data.bookTitle}</td>
				<td>${data.bookAuthor}</td>
				<td>${data.bookPrice}</td>
				<td>
					<a href="UpdateBookServlet?code=${data.bookCode}">Update</a>|
					<a href="DeleteServlet?code=${data.bookCode}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
		
</body>
</html>