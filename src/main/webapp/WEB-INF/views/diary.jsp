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
				<script type="text/template" id="diary-total-calories-template"> <spring:message code="diary.calories.total"/>: (@= total @) <spring:message code="calories.measure"/></script>
				<blockquote class="lead"></blockquote>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#diaryModal"><spring:message code="diary.add"/></button>
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
						<form id="diaryForm" action="" onSubmit="sendForm(event)">
							<div class="form-group">
								<spring:message code="diary.timestamp" var="timestamp"/>
								<div class="input-group date" id="datetimepicker">
									<input id="timestamp" name="timestamp" placeholder="${timestamp}" class="form-control" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="diary.food" var="food"/>
								<input id="foodTypeahead" placeholder="${food}" autocomplete="off" class="form-control typeahead" required>
								<input id="foodId" name="food.id" type="hidden">
							</div>
							<div class="form-group input-group">
								<input id="weight" name="weight" type="number" min="0.001" step="0.001" max="10" class="form-control" required>
								<span class="input-group-addon"><spring:message code="weight.measure"/></span>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="form.close"/></button>
						<button type="submit" class="btn btn-primary" form="diaryForm"><spring:message code="form.save"/></button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/template" id="diary-day-pager-template">
			<a href="diary#(@= prev @)" class="btn btn-default navbar-btn" role="button">&larr;</a>
			<a class="btn btn-link navbar-btn disabled" role="button">(@= now.toLocaleString().slice(0,10) @)</a>
			<a href="diary#(@= next @)" class="btn btn-default navbar-btn" role="button">&rarr;</a>
		</script>

		<script type="text/template" id="diary-tr-template">
			<td id="edit"><a style="cursor: pointer;"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td>(@= new Date(timestamp).toLocaleFormat("%d.%m.%Y %H:%M") @)</td>
			<td>(@= food.name @)</td>
			<td>(@= weight @)</td>
			<td>(@= Math.round(food.calories*weight*10) @)</td>
			<td id="delete" class="text-right"><a style="cursor: pointer;" class="text-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
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

			if (!String.prototype.capitalize) {
				String.prototype.capitalize = function () {
					return this.charAt(0).toUpperCase() + this.slice(1);
				}
			};

			var lastDatum;
			$('.typeahead').typeahead(null, {
				displayKey: 'name' + '${lang}'.capitalize(),
				source: engine.ttAdapter()
			}).on('typeahead:selected typeahead:autocompleted', function(e, datum) {
				$('#foodId').val(datum.id);
				lastDatum = datum;
			});

			function deleteDiary(foodId, timestamp) {
				if (confirm('<spring:message code="form.confirm" />'))
					$.post('diaries/delete', {'food.id': foodId, 'timestamp': new Date(timestamp).toLocaleFormat("%Y.%m.%d %H:%M")});
			};

			$(function plot() {
				var diaryChart = plotEmptyChart('#placeholder', {
					tooltip: {
						valueSuffix: ' <spring:message code="calories.measure"/>'
					}
				});
				$.get('diaries/aggregated', function(result) {
					diaryChart.addSeries({
						name: '<spring:message code="diary.calories.total"/>',
						data: process(result, 'date', 'calories')
					});
				});
			});
		</script>
	</body>
</html>
