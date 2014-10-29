<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<link rel="stylesheet" href="resources/css/bootstrap.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="modal-dialog">
			</div>
		</div>

		<script type="text/template" id="form-template">
			<form action="login" accept-charset="UTF-8" method="post">
				<h2 class="modal-header">(@= i18n['label.login'] @)</h2>
				<form:errors path="*" element="div" class="alert alert-danger"/>
				<div class="form-group"><input name="login" type="email" placeholder="(@= i18n['user.login'] @)" maxlength="40" class="form-control" required></div>
				<div class="form-group"><input name="password" type="password" placeholder="(@= i18n['user.password'] @)" class="form-control" required></div>
				<div class="form-group"><button type="submit" class="btn btn-success">(@= i18n['form.log.in'] @)</button></div>
			</form>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="messages?${pageContext.response.locale}"></script>
		<script src="resources/js/backbone/login.js"></script>
		<script src="resources/js/backbone/menu.js"></script>
	</body>
</html>
