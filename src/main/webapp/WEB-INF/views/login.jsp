<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>
			<spring:message code="form.log.in"/>
		</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="modal-dialog">
				<form action="login" accept-charset="UTF-8" method="post">
					<h2 class="modal-header">
						<spring:message code="label.login"/>
					</h2>
					<form:errors path="*" element="div" class="alert alert-danger"/>
					<div class="form-group">
						<spring:message code="user.login" var="login"/>
						<input name="login" type="email" placeholder="${login}" maxlength="40" class="form-control" required>
					</div>
					<div class="form-group">
						<spring:message code="user.password" var="password"/>
						<input name="password" type="password" placeholder="${password}" class="form-control" required>
					</div>
					<div class="form-group">
						<spring:message code="form.log.in" var="logIn"/>
						<input type="submit" value="${logIn}" class="btn btn-success">
					</div>
				</form>
			</div>
		</div>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="//code.jquery.com/jquery-1.11.1.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
