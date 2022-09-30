<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">


<title>Insert title here</title>
</head>
<body>
	<h1>Student Debate Registration</h1>
	<hr>
	<form action="/Anuj_kumar_yadav_Spring_MVCLabSolution/student/search" class="form-inline">

			<!-- Add a button -->
			<a href="showFormForAdd"
				class="btn btn-primary btn-sm mb-3"> Add Student </a> 
			
			<!-- Add search field for Name -->
			<input
				type="search" 
				name="name" placeholder="Name"
				class="form-control-sm ml-5 mr-2 mb-3" /> 
			
			<!-- Add search field for Department -->
			<input type="search"
				name="department" placeholder="Department" class="form-control-sm mr-2 mb-3" />
			
			<!-- Add search field for Country -->
			<input type="search"
				name="country" placeholder="Country" class="form-control-sm mr-2 mb-3" />

			<!-- Add search button -->
			<button type="submit" class="btn btn-success btn-sm mb-3">Search</button>

		</form>
		
		
		<table class="table table-bordered table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Department</th>
					<th>Country</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${students}" var="tempStudent">
					<tr>
						<td><c:out value="${tempStudent.id}" /></td>
						<td><c:out value="${tempStudent.name}" /></td>
						<td><c:out value="${tempStudent.department}" /></td>
						<td><c:out value="${tempStudent.country}" /></td>
						<td>
							<!-- Add "update" button/link --> <a
							href="showFormForUpdate?studentId=${tempStudent.id}"
							class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a href="delete?studentId=${tempStudent.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
</body>
</html>