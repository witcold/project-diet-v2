<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="container">
		</div>

		<script type="text/template" id="title-template">(@= i18n['error'] @)</script>

		<script type="text/template" id="container-template">
			<div class="jumbotron">
				<h2>(@= i18n['error'] @)</h2>
				<p><c:out value="${message}"/></p>
			</div>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="messages?${pageContext.response.locale}"></script>
		<script src="resources/js/backbone/error.js"></script>
		<script src="resources/js/backbone/menu.js"></script>
	</body>
</html>
