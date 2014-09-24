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
					BMI:
					<small>${bmi}</small>
				</h4>
				<fmt:formatDate pattern="dd/MM/yyyy" value='${CURRDATE}' />
				<h4>
					Your age:
					<small>${age} year</small>
				</h4>
				<p>Assuming this data, your basal methabolic rate is ${bmr} kcal.</p>
				<fmt:formatNumber var="dailyCalories" value="${bmr*account.activityLevel}" maxFractionDigits="0" groupingUsed=""/>
				<p>Your physical activity level set to ${account.activityLevel}, so you need ${dailyCalories} kcal to spend every day.</p>
				<h2>
					Your current goal progress
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
		<script src="resources/js/highcharts-options.js"></script>

		<script type="text/javascript">
			$(Highcharts.setOptions(globalOptions));

			$(function plotWeight() {
				$.ajax({
					url: 'weight/raw',
					data: {'from': '<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>'},
					type: 'GET',
					success: function(result) {
						var data = [];
						for (var i = 0; i < result.length; i++) {
							var dateParts = result[i]['date'].split("-");
							var date = new Date(dateParts[0], (dateParts[1] - 1), dateParts[2]);
							data.push([+date, result[i]['weight']]);
						}
						var options = $.extend(true, defaultOptions);
						options.tooltip = {
								valueSuffix: ' <spring:message code="weight.measure"/>'
						};
						options.series = [{
							name: '<spring:message code="label.weight"/>',
							data: data
						}];
						$('#weightPlaceholder').highcharts(options);
					}
				});
			});

			$(function plotDiary() {
				$.ajax({
					url: 'diary/aggregated',
					data: {'from': '<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>'},
					type: 'GET',
					success: function(result) {
						var data = [];
						for (var i = 0; i < result.length; i++) {
							var dateParts = result[i]['date'].split("-");
							var date = new Date(dateParts[0], (dateParts[1] - 1), dateParts[2]);
							data.push([+date, result[i]['calories']]);
						}
						var options = $.extend(true, defaultOptions);
						options.tooltip = {
							valueSuffix: ' <spring:message code="calories.measure"/>'
						};
						options.series = [{
							name: '<spring:message code="diary.calories.total"/>',
							data: data,
						}];
						$('#diaryPlaceholder').highcharts(options);
					}
				});
			});
		</script>
	</body>
</html>
