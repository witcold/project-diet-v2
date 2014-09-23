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
			<spring:message code="label.dashboard"/>
		</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="label.dashboard"/>
				</h1>
				<h3>
					Hello, mr. ${account.firstName} ${account.lastName}!
				</h3>
				<h4>
					Your height:
					<small>${account.height} cm</small>
				</h4>
				<h4>
					Your last weight:
					<small>${lastWeight.weight} kg</small>
				</h4>
				<fmt:formatDate pattern="dd/MM/yyyy" value='${CURRDATE}' />
				<h4>
					Your age:
					<small>${age} year</small>
				</h4>
				<p>Assuming this data, your basal methabolic rate is ${bmr} kcal.</p>
				<c:set var="dailyCalories" value="${bmr*account.activityLevel}"/>
				<p>Your physical activity level set to ${account.activityLevel}, so you need ${dailyCalories} kcal to spend every day.</p>
				<h2>
					Your current weight loss progress
					<small>lose 5 kg in 3 month</small>
				</h2>
				<div id="weightPlaceholder" class="center-block" style="min-width:600px;height:200px">
				</div>
				<h2>
					Your diary stats
					<small>2340 / ${dailyCalories} for last 7 days</small>
				</h2>
				<div id="diaryPlaceholder" class="center-block" style="min-width:600px;height:200px">
				</div>
			</div>
		</div>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="//code.highcharts.com/highcharts.js"></script>
		<script type="text/javascript">
			$(function plotWeight() {
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
						$('#weightPlaceholder').highcharts({
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

			$(function plotDiary() {
				$.ajax({
					url: 'diary/aggregated',
					data: {'from': '<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd" />'},
					type: 'GET',
					success: function(result) {
						var data = [];
						for (var i = 0; i < result.length; i++) {
							var dateParts = result[i]['date'].split("-");
							var date = new Date(dateParts[0], (dateParts[1] - 1), dateParts[2]);
							data.push([+date, result[i]['calories']]);
						}
						Highcharts.setOptions({
							global: {
								useUTC: false
							}
						});
						$('#diaryPlaceholder').highcharts({
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
								valueSuffix: ' kcal'
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
								name: '<spring:message code="diary.total" />',
								data: data,
							}]
						});
					}
				});
			});
		</script>
	</body>
</html>
