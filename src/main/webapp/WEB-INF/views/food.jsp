<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>
			<spring:message code="label.food"/>
		</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="label.food"/>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="food">
							<spring:message code="label.food"/>
						</a>
					</li>
					<li class="active">
						<c:choose>
							<c:when test="${not empty currentCategory}">
								<c:out value="${currentCategory.getName(lang)}"/>
							</c:when>
							<c:otherwise>
								<spring:message code="food.category.all"/>
							</c:otherwise>
						</c:choose>
					</li>
				</ol>
				<div class="categories list-group col-md-2">
				</div>
				<div class="col-md-10">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>
									<spring:message code="food.name"/>
								</th>
								<th>
									<spring:message code="food.calories"/>
								</th>
								<th>
									<spring:message code="food.proteins"/>
								</th>
								<th>
									<spring:message code="food.fats"/>
								</th>
								<th>
									<spring:message code="food.carbohydrates"/>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="food" items="${foodList}">
								<tr>
									<td>
										<c:out value="${food.getName(lang)}"/>
									</td>
									<td>
										<c:out value="${food.calories}"/>
									</td>
									<td>
										<c:out value="${food.proteins}"/>
									</td>
									<td>
										<c:out value="${food.fats}"/>
									</td>
									<td>
										<c:out value="${food.carbohydrates}"/>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="resources/js/backbone/food.js"></script>
	</body>
</html>
