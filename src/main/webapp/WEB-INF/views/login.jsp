<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="log_in" /></title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu-light.jsp" %>
		<div class="container">
			<div class="jumbotron">
				<form:form action="login" accept-charset="UTF-8" method="post" modelAttribute="user">
					<h2 class="form-signin-heading"><spring:message code="login.label.login"/></h2>
					<form:errors path="*" element="div" class="alert alert-danger" />
					<div class="form-group">
						<spring:message code="login" var="login"/>
						<form:input path="login" placeholder="${login}" maxlength="40" class="form-control" required="true" autofocus="true" />
					</div>
					<div class="form-group">
						<spring:message code="password" var="password"/>
						<form:password path="password" placeholder="${password}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<form:button class="btn btn-success"><spring:message code="log_in" /></form:button>
					</div>
				</form:form>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
