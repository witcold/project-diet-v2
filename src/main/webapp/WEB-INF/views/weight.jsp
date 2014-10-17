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
				<div id="placeholder" class="center-block" style="min-width:900px;height:200px">
				</div>
				<div id="date" class="btn-group btn-group-justified">
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-xs-1" style="width: 1px;"></th>
							<th><spring:message code="date" /></th>
							<th><spring:message code="weight.weight" /></th>
							<th class="col-xs-1" style="width: 1px;"></th>
						</tr>
					</thead>
					<tbody id="weight-table">
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
						<form:form id="weightForm" action="weight/add" accept-charset="UTF-8" method="post" modelAttribute="weight" onSubmit="return validateDate(event)">
							<div class="form-group">
								<spring:message code="date" var="date"/>
								<div class='input-group date' id='datetimepicker'>
									<form:input path="date" readonly="true" placeholder="${date}" class="form-control"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group input-group">
								<form:input path="weight" type="number" min="1" step="0.001" max="999" class="form-control" required="true"/>
								<span class="input-group-addon">
									<spring:message code="weight.measure"/>
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

		<!-- TODO  # {} is prohibited -->
		<script type="text/template" id="date-template">
			<a href="weight# {{ prevFrom }}/{{ prevTo }}" class="btn btn-default navbar-btn" role="button">&larr;</a>
			<a class="btn btn-link navbar-btn disabled" role="button">{{ now }}</a>
			<a href="weight# {{ nextFrom }}/{{ nextTo }}" class="btn btn-default navbar-btn" role="button">&rarr;</a>
		</script>

		<script type="text/template" id="weight-tr-template">
			<td><a style="cursor: pointer;" onclick="editForm('{{date}}', {{ weight }})"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td>{{ date}}</td>
			<td>{{ weight }}</td>
			<td class="text-right"><a style="cursor: pointer;" onclick="deleteWeight('{{ date }}')" class="text-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/bootstrap-datetimepicker.js"></script>
		<script src="//code.highcharts.com/highcharts.js"></script>
		<script src="resources/js/highcharts-utils.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="resources/js/backbone/weight.js"></script>

		<script type="text/javascript">
			$('#datetimepicker').datetimepicker({
				format: 'YYYY.MM.DD',
				pickTime: false,
				useStrict: true
			});

			var datetimepicker = $('#datetimepicker').data("DateTimePicker");
			var weightform = $('#weightForm');

			$(function plot() {
				var weightChart = plotEmptyChart('#placeholder', {
					tooltip: {
						valueSuffix: ' <spring:message code="weight.measure"/>'
					}
				});
				$.get('weight/raw', function(result) {
					weightChart.addSeries({
						name: '<spring:message code="label.weight"/>',
						data: process(result, 'date', 'weight')
					});
				});
			});

			function deleteWeight(date) {
				if (confirm('<spring:message code="form.confirm"/>'))
					$.post('weight/delete', {'date': date}, function(result) {
						location.reload();
					});
			};

			function editForm(date, weight) {
				weightform.attr('action', 'weight/update');
				datetimepicker.setDate(new Date(date));
				weightform.find('#datetimepicker .input-group-addon').hide();
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

			function validateDate(event) {
				var date = weightform.find('#datetimepicker input').val();
				if (!date) {
					datetimepicker.show(event);
					return false;
				}
			}
		</script>
	</body>
</html>
