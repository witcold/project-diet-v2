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
			<div class="container">
				<h1 id="container-label"></h1>
				<div class="categories list-group col-md-2">
				</div>
				<div class="col-md-10">
					<table class="table table-hover">
						<thead id="table-header">
						</thead>
						<tbody id="food-table">
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<script type="text/template" id="table-header-template">
			<tr>
				<th>(@= i18n['food.name'] @)</th>
				<th>(@= i18n['food.calories'] @)</th>
				<th>(@= i18n['food.proteins'] @)</th>
				<th>(@= i18n['food.fats'] @)</th>
				<th>(@= i18n['food.carbohydrates'] @)</th>
			</tr>
		</script>

		<script type="text/template" id="food-tr-template">
			<td>(@= name @)</td>
			<td>(@= calories @)</td>
			<td>(@= proteins @)</td>
			<td>(@= fats @)</td>
			<td>(@= carbohydrates @)</td>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="messages?${pageContext.response.locale}"></script>
		<script src="resources/js/backbone/food.js"></script>
		<script src="resources/js/backbone/menu.js"></script>
	</body>
</html>
