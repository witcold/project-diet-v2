<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
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
							<th class="col-md-1"></th>
							<th><spring:message code="date" /></th>
							<th><spring:message code="weight.weight" /></th>
							<th class="col-md-1"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="weight" items="${weightList}">
							<tr>
								<td>
									<a style="cursor: pointer;" onclick="editForm('${weight.date}', ${weight.weight})">
										<span class="glyphicon glyphicon-pencil"></span>
									</a>
								</td>
								<td><c:out value="${weight.date}"/></td>
								<td><c:out value="${weight.weight}"/></td>
								<td class="text-right">
									<spring:message code="delete.confirm" var="deleteConfirm" />
									<fmt:formatDate value="${weight.date}" var="dateToDelete" pattern="yyyy.MM.dd"/>
									<a style="cursor: pointer;" onclick="deleteWeight('${dateToDelete}')" class="text-danger">
										<span class="glyphicon glyphicon-remove"></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#weightModal">
					<spring:message code="weight.add" />
				</button>
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
						<form:form id="weightForm" action="weight/add" accept-charset="UTF-8" method="post" modelAttribute="weight">
							<div class="form-group">
								<spring:message code="date" var="date"/>
								<div class='input-group date' id='datetimepicker'>
									<form:input readonly="true" path="date" placeholder="${date}" class="form-control" data-date-format="YYYY.MM.DD"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="weight" var="weight"/>
								<form:input type="number" min="0" step="0.001" max="1000" path="weight" placeholder="${weight}" class="form-control" required="true" />
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
					pickTime: false,
					useStrict: true
				});
			});

			function deleteWeight(date) {
				if (confirm('${deleteConfirm}'))
					$.ajax({
						url: 'weight/delete/'.concat(date),
						type: 'DELETE',
						success: function(result) {
							alert('Ok!');
						}
					});
			};

			function editForm(date, weight) {
				$('#weightForm').attr('action', 'weight/update');
				var dp = $('#datetimepicker').data("DateTimePicker");
				dp.setDate(new Date(date));
				dp.find('.input-group-addon').attr('disabled');
				$('#weight').val(weight);
				$('#weightModal').modal('show');
			};

			$('#weightModal').on('hidden.bs.modal', function (e) {
				var form = $(e.currentTarget).find('form');
				form.trigger('reset');
				form.attr('action', 'weight/add');
				form.find('#datetimepicker').data("DateTimePicker").enable();
			});
		</script>
	</body>
</html>
