<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="sign_up" /></title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu-light.jsp" %>
		<div class="container">
			<div class="jumbotron">
				<form:form action="signup" accept-charset="UTF-8" method="post" modelAttribute="user">
					<h2 class="form-signin-heading"><spring:message code="signup.label.register" /></h2>
					<div class="form-group">
						<form:errors path="*" element="div" class="alert alert-danger" />
					</div>
					<div class="form-group">
						<spring:message code="login" var="login"/>
						<form:input path="login" placeholder="${login}"  maxlength="40" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="password" var="password"/>
						<form:password path="password" placeholder="${password}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="signup.password.confirm" var="signup_password_confirm"/>
						<form:password path="passwordConfirm" placeholder="${signup_password_confirm}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="signup.name.first" var="signup_name_first"/>
						<form:input path="firstName" placeholder="${signup_name_first}" maxlength="20" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<spring:message code="signup.name.last" var="signup_name_last"/>
						<form:input path="lastName" placeholder="${signup_name_last}" maxlength="20" class="form-control" required="true" />
					</div>
					<button type="submit" class="btn btn-success"><spring:message code="sign_up" /></button>
				</form:form>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
