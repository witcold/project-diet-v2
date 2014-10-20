<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>
			<spring:message code="label.diary"/>
		</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" href="resources/css/typeaheadjs.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="label.diary"/>
				</h1>
				<div id="placeholder" class="center-block" style="min-width:900px;height:200px">
				</div>
				<div id="date" class="btn-group btn-group-justified">
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-xs-1" style="width: 1px;"></th>
							<th class="col-xs-2"><spring:message code="diary.timestamp"/></th>
							<th><spring:message code="diary.food"/></th>
							<th class="col-xs-2"><spring:message code="diary.weight"/></th>
							<th class="col-xs-3"><spring:message code="diary.calories"/></th>
							<th class="col-xs-1" style="width: 1px;"></th>
						</tr>
					</thead>
					<tbody id="diary-table">
					</tbody>
				</table>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#diaryModal">
					<spring:message code="diary.add"/>
				</button>
			</div>
		</div>

		<div class="modal fade" id="diaryModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<spring:message code="diary.add" />
						</h4>
					</div>
					<div class="modal-body">
						<form:form id="diaryForm" action="diary/add" accept-charset="UTF-8" method="post" modelAttribute="diary" onSubmit="return validateForm()">
							<div class="form-group">
								<spring:message code="diary.timestamp" var="timestamp"/>
								<div class="input-group date" id="datetimepicker">
									<form:input readonly="true" path="timestamp" placeholder="${timestamp}" class="form-control"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="diary.food" var="food"/>
								<input type="text" id="foodTypeahead" placeholder="${food}" autocomplete="off" class="form-control typeahead" required>
								<form:input id="foodId" type="hidden" path="food.id"/>
							</div>
							<div class="form-group input-group">
								<form:input type="number" min="0.001" step="0.001" max="10" path="weight" class="form-control" required="true"/>
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
						<button type="submit" class="btn btn-primary" form="diaryForm">
							<spring:message code="form.save"/>
						</button>
					</div>
				</div>
			</div>
		</div>

		<%@ include file="templates/diary-day-pager.html" %>

		<script type="text/template" id="diary-tr-template">
			<td><a style="cursor: pointer;" onclick="editForm('(@ formatDate(new Date(timestamp)) @)', (@= food.id @), '(@= food.name @)', (@= weight @))"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td>(@= new Date(timestamp).toLocaleDateString() @)</td>
			<td>(@= food.name @)</td>
			<td>(@= weight @)</td>
			<td>(@= Math.round(food.calories*weight*10) @)</td>
			<td class="text-right"><a style="cursor: pointer;" onclick="deleteDiary((@= food.id @), '(@= timestamp @)')" class="text-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="//code.jquery.com/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="resources/js/3rdparty/bootstrap-datetimepicker.js"></script>
		<script src="//code.highcharts.com/highcharts.js"></script>
		<script src="resources/js/3rdparty/typeahead.bundle.js"></script>
		<script src="resources/js/highcharts-utils.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="resources/js/backbone/diary.js"></script>

		<script type="text/javascript">
			var engine = new Bloodhound({
				name: 'foods',
				local: [],
				remote: 'food/raw?query=%QUERY',
				datumTokenizer: function(d) {
					return Bloodhound.tokenizers.whitespace(d.name);
				},
				queryTokenizer: Bloodhound.tokenizers.whitespace
			});
			engine.initialize();
			String.prototype.capitalize = function() {
				return this.charAt(0).toUpperCase() + this.slice(1);
			}
			$('.typeahead').typeahead(null, {
				displayKey: 'name' + '${lang}'.capitalize(),
				source: engine.ttAdapter()
			});
			$('.typeahead').on('typeahead:selected typeahead:autocompleted', function(e, datum) {
				$('#foodId').val(datum.id);
			});

			$('#datetimepicker').datetimepicker({
				format: 'YYYY.MM.DD HH:mm',
				pickDate: false,
				useStrict: true
			});

			var datetimepicker = $('#datetimepicker').data("DateTimePicker");
			var diaryform = $('#diaryForm');

			$(function() {
				datetimepicker.setDate(new Date('<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>'));
			});

			$(function plot() {
				var diaryChart = plotEmptyChart('#placeholder', {
					tooltip: {
						valueSuffix: ' <spring:message code="calories.measure"/>'
					}
				});
				$.get('diary/aggregated', function(result) {
					diaryChart.addSeries({
						name: '<spring:message code="diary.calories.total"/>',
						data: process(result, 'date', 'calories')
					});
				});
			});

			function deleteDiary(foodId, timestamp) {
				if (confirm('<spring:message code="form.confirm" />'))
					$.post('diary/delete', {'foodId': foodId, 'timestamp': timestamp}, function(result) {
						location.reload();
					});
			};

			function editForm(date, foodId, foodName, weight) {
				diaryform.attr('action', 'diary/update');
				datetimepicker.setDate(new Date(date));
				diaryform.find('#datetimepicker .input-group-addon').hide();
				diaryform.find('.date').removeClass('input-group');
				diaryform.find('#foodTypeahead').val(foodName);
				diaryform.find('#foodId').val(foodId);
				diaryform.find('#weight').val(weight);
				$('#diaryModal').modal('show');
			};

			$('#diaryModal').on('hidden.bs.modal', function (e) {
				diaryform.trigger('reset');
				diaryform.attr('action', 'diary/add');
				diaryform.find('.date').addClass('input-group');
				diaryform.find('.input-group-addon').show();
			});

			function validateForm(event) {
				var food = diaryform.find('#foodId').val();
				if (!parseInt(food)) {
					diaryform.find('#foodTypeahead').focus();
					return false;
				}
			}
		</script>
	</body>
</html>
