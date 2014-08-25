<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.dataart.spring.i18n.text"/>
<html lang="${language}">
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
				<h2><fmt:message key="signup.label.register" /></h2>
				<form:form action="signup" method="post" modelAttribute="user">
					<div class="form-group">
						<form:errors path="*" element="div" class="alert alert-danger" />
					</div>
					<div class="form-group">
						<input type="text" id="login" name="login" class="form-control" placeholder="<fmt:message key="login" />" required>
					</div>
					<div class="form-group">
						<input type="password" id="password" name="password" class="form-control" placeholder="<fmt:message key="password" />" required>
					</div>
					<div class="form-group">
						<input type="password" id="passwordConfirm" name="passwordConfirm" class="form-control" placeholder="<fmt:message key="signup.password.confirm" />" required>
					</div>
					<div class="form-group">
						<input type="text" id="firstName" name="firstName" class="form-control" placeholder="<fmt:message key="signup.name.first" />" required>
					</div>
					<div class="form-group">
						<input type="text" id="lastName" name="lastName" class="form-control" placeholder="<fmt:message key="signup.name.last" />" required>
					</div>
					<button type="submit" class="btn btn-success"><fmt:message key="sign_up" /></button>
				</form:form>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
