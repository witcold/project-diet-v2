<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="weight" /></title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="weight" /></h1>
				<div class="btn-group btn-group-justified">
					<fmt:formatDate value="${prevDate}" var="prevMonth" pattern="yyyy.MM.dd"/>
					<a href="?from=${prevMonth}" class="btn btn-default navbar-btn" role="button">
						&larr;
					</a>
					<a class="btn btn-link navbar-btn disabled" role="button">
						<fmt:formatDate value="${currentDate}" pattern="MMMM yyyy"/>
					</a>
					<fmt:formatDate value="${nextDate}" var="nextMonth" pattern="yyyy.MM.dd"/>
					<a href="?from=${nextMonth}" class="btn btn-default navbar-btn" role="button">
						&rarr;
					</a>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th><spring:message code="date" /></th>
							<th><spring:message code="weight.weight" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="weight" items="${weightList}">
							<tr onclick="$('#weightModal').modal('show')">
								<td><c:out value="${weight.date}"/></td>
								<td><c:out value="${weight.weight}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#weightModal">
					<spring:message code="weight.add" />
				</button>
<%-- 				<button type="button" class="btn btn-warning"><spring:message code="weight.edit" /></button> --%>
<%-- 				<button type="button" class="btn btn-danger"><spring:message code="weight.delete" /></button> --%>
			</div>
		</div>

		<div class="modal fade" id="weightModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title"><spring:message code="weight.add" /></h4>
					</div>
					<div class="modal-body">
						<form:form id="weightForm" action="addweight" accept-charset="UTF-8" method="post" modelAttribute="weight">
							<div class="form-group">
								<spring:message code="date" var="date"/>
								<div class='input-group date' id='datetimepicker'>
									<form:input path="date" placeholder="${date}" class="form-control"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="weight" var="weight"/>
								<form:input type="number" step="0.001" path="weight" placeholder="${weight}" class="form-control" required="true" />
							</div>
						</form:form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary" form="weightForm">Save changes</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.min.js"></script>

		<script type="text/javascript">
			$(function () {
				$('#datetimepicker').datetimepicker({
					pickTime: false
				});
			});
		</script>
	</body>
</html>
