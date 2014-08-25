<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='<spring:message code="language" text="language" />'>
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
				<form:form action="signup" method="post" modelAttribute="user">
					<spring:message code="signup.label.register" text="signup.label.register" var="signup_label_register"/>
					<h2 class="form-signin-heading">${signup_label_register}</h2>
					<div class="form-group">
						<form:errors path="*" element="div" class="alert alert-danger" />
					</div>
					<div class="form-group">
						<spring:message code="login" text="login" var="login"/>
						<form:input path="login" placeholder="${login}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="password" text="password" var="password"/>
						<form:password path="password" placeholder="${password}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="signup.password.confirm" text="signup.password.confirm" var="signup_password_confirm"/>
						<form:password path="passwordConfirm" placeholder="${signup_password_confirm}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="signup.name.first" text="signup.name.first" var="signup_name_first"/>
						<form:input path="firstName" placeholder="${signup_name_first}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="signup.name.last" text="signup.name.last" var="signup_name_last"/>
						<form:input path="lastName" placeholder="${signup_name_last}" class="form-control" required="true" />
					</div>
					<spring:message code="sign_up" text="sign_up" var="sign_up"/>
					<button type="submit" class="btn btn-success">${sign_up}</button>
				</form:form>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
