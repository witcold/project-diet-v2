<%@ page session="true" pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<link rel="stylesheet" href="resources/css/bootstrap.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container"></div>
		</div>

		<script type="text/template" id="title-template">(@= i18n['label.welcome'] @)</script>

		<script type="text/template" id="container-template"><h1>(@= i18n['label.welcome'] @)</h1><p>(@= i18n['label.login'] @).</p></script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="messages"></script>
		<script src="resources/js/backbone/home.js"></script>
		<script src="resources/js/backbone/menu.js"></script>
	</body>
</html>
