<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Sign up</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu-light.jsp" %>
		<div class="container">
			<div class="jumbotron">
				<h2>Registering new user</h2>
				<form:form action="signup" method="post" modelAttribute="user">
					<div class="form-group">
						<form:errors path="*" element="div" class="alert alert-danger" />
					</div>
					<div class="form-group">
						<form:input  path="login" placeholder="Login" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<form:password path="password" placeholder="Password" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<form:password path="passwordConfirm" placeholder="Confirm password" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<form:input path="firstName" placeholder="First name" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<form:input path="lastName" placeholder="Last name" class="form-control" required="true" />
					</div>
					<button type="submit" class="btn btn-success">Sign up</button>
				</form:form>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
