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
		<title>
			<spring:message code="label.weight"/>
		</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="label.weight" />
				</h1>
				<div id="placeholder" class="center-block" style="min-width:600px;height:200px">
				</div>
				<div class="btn-group btn-group-justified">
					<fmt:formatDate value="${prevDate}" var="prevMonth" pattern="yyyy.MM.dd"/>
					<a href="weight?from=${prevMonth}" class="btn btn-default navbar-btn" role="button">
						&larr;
					</a>
					<a class="btn btn-link navbar-btn disabled" role="button">
						<fmt:formatDate value="${currentDate}" pattern="MMMM yyyy"/>
					</a>
					<c:choose>
						<c:when test="${not empty nextDate}">
							<fmt:formatDate value="${nextDate}" var="nextMonth" pattern="yyyy.MM.dd"/>
							<a href="weight?from=${nextMonth}" class="btn btn-default navbar-btn" role="button">
								&rarr;
							</a>
						</c:when>
						<c:otherwise>
							<a href="weight" class="btn btn-default navbar-btn disabled" role="button">
								&rarr;
							</a>
						</c:otherwise>
					</c:choose>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-xs-1" style="width: 1px;">
							</th>
							<th>
								<spring:message code="weight.date" />
							</th>
							<th>
								<spring:message code="weight.weight" />
							</th>
							<th class="col-xs-1" style="width: 1px;">
							</th>
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
								<td>
									<fmt:formatDate value="${weight.date}" pattern="dd MMMM yyyy"/>
								</td>
								<td>
									<c:out value="${weight.weight}"/>
								</td>
								<td class="text-right">
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
					<spring:message code="weight.add"/>
				</button>
			</div>
		</div>

		<div class="modal fade" id="weightModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<spring:message code="weight.add"/>
						</h4>
					</div>
					<div class="modal-body">
						<form:form id="weightForm" action="weight/add" accept-charset="UTF-8" method="post" modelAttribute="weight">
							<div class="form-group">
								<spring:message code="weight.date" var="date"/>
								<div class='input-group date' id='datetimepicker'>
									<form:input readonly="true" path="date" placeholder="${date}" class="form-control"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group input-group">
								<form:input type="number" min="1" step="0.001" max="999" path="weight" class="form-control" required="true"/>
								<span class="input-group-addon">
									<spring:message code="weight.weight.measure"/>
								</span>
							</div>
						</form:form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<spring:message code="form.close"/>
						</button>
						<button type="submit" class="btn btn-primary" form="weightForm">
							<spring:message code="form.save"/>
						</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="//code.jquery.com/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.js"></script>
		<script src="//code.highcharts.com/highcharts.js"></script>

		<script type="text/javascript">
			$('#datetimepicker').datetimepicker({
				format: 'YYYY.MM.DD',
				pickTime: false,
				useStrict: true
			});

			var datetimepicker = $('#datetimepicker').data("DateTimePicker");
			var weightform = $('#weightForm');

			$(function plot() {
				$.ajax({
					url: 'weight/raw',
					data: {'from': '<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd" />'},
					type: 'GET',
					success: function(result) {
						var data = [];
						for (var i = 0; i < result.length; i++) {
							var dateParts = result[i]['date'].split("-");
							var date = new Date(dateParts[0], (dateParts[1] - 1), dateParts[2]);
							data.push([+date, result[i]['weight']]);
						}
						Highcharts.setOptions({
							global: {
								useUTC: false
							}
						});
						$('#placeholder').highcharts({
							chart: {
								backgroundColor: null
							},
							legend: {
								enabled: false
							},
							title: {
								text: null
							},
							subtitle: {
								text: null
							},
							tooltip: {
								valueSuffix: ' kg'
							},
							xAxis: {
								type: 'datetime',
								tickInterval: 24 * 3600 * 1000, // one day
								gridLineWidth: 1,
								dateTimeLabelFormats: {
									day: '%e.%m',
								}
							},
							yAxis: [{
								title: {
										text: null
								}
							}, {
								linkedTo: 0,
								opposite: true,
								title: {
										text: null
								}
							}],
							series: [{
								name: '<spring:message code="weight" />',
								data: data,
							}]
						});
					}
				});
			});

			function deleteWeight(date) {
				if (confirm('<spring:message code="delete.confirm" />'))
					$.ajax({
						url: 'weight/delete',
						data: {'date': date},
						type: 'POST',
						success: function(result) {
							location.reload();
						}
					});
			};

			function editForm(date, weight) {
				weightform.attr('action', 'weight/update');
				datetimepicker.setDate(new Date(date));
				weightform.find('.input-group-addon').hide();
				weightform.find('.date').removeClass('input-group');
				$('#weight').val(weight);
				$('#weightModal').modal('show');
			};

			$('#weightModal').on('hidden.bs.modal', function (e) {
				weightform.trigger('reset');
				weightform.attr('action', 'weight/add');
				weightform.find('.date').addClass('input-group');
				weightform.find('.input-group-addon').show();
			});
		</script>
	</body>
</html>
