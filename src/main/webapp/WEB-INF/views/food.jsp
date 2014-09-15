<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="food" /></title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="food" /></h1>
				<ol class="breadcrumb">
					<li><a href="food"><spring:message code="food" /></a></li>
					<li class="active">
						<c:choose>
							<c:when test="${not empty currentCategory}">
								<c:out value="${currentCategory.name}"></c:out>
							</c:when>
							<c:otherwise>
								<spring:message code="food.category.all" />
							</c:otherwise>
						</c:choose>
					</li>
				</ol>
				<div class="list-group col-md-2">
					<c:forEach var="category" items="${categoryList}">
						<a href="food?category=${category.id}" class="list-group-item">
							<c:out value="${category.name}"></c:out>
						</a>
					</c:forEach>
				</div>
				<div class="col-md-10">
					<table class="table table-hover">
						<thead>
							<tr>
								<th><spring:message code="food.name" /></th>
								<th><spring:message code="food.calories" /></th>
								<th><spring:message code="food.proteins" /></th>
								<th><spring:message code="food.fats" /></th>
								<th><spring:message code="food.carbohydrates" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="food" items="${foodList}">
								<tr>
									<td><c:out value="${food.name}"></c:out></td>
									<td><c:out value="${food.calories}"></c:out></td>
									<td><c:out value="${food.proteins}"></c:out></td>
									<td><c:out value="${food.fats}"></c:out></td>
									<td><c:out value="${food.carbohydrates}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
