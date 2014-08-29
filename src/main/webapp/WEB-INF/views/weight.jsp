<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="welcome" /></title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1>Weight</h1>
				<table class="table">
					<thead>
						<tr>
							<th><spring:message code="date" /></th>
							<th><spring:message code="weight.weight" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2014-08-28 12:35</td>
							<td>66.8</td>
						</tr>
						<tr>
							<td>2014-08-29 12:35</td>
							<td>66.6</td>
						</tr>
						<tr>
							<td>2014-08-30 12:35</td>
							<td>66.7</td>
						</tr>
					</tbody>
				</table>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#weightModal"><spring:message code="weight.add" /></button>
				<button type="button" class="btn btn-warning"><spring:message code="weight.edit" /></button>
				<button type="button" class="btn btn-danger"><spring:message code="weight.delete" /></button>
			</div>
		</div>

		<div class="modal fade" id="weightModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Weight</h4>
					</div>
					<div class="modal-body">
						<form:form id="weightForm" accept-charset="UTF-8" method="post" >
							
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</body>
</html>
