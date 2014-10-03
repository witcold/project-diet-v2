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
			</div>
		</div>

		<script type="text/template" id="template">
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
				<small>
					<fmt:formatNumber value="${bmi}" maxFractionDigits="2"/>
				</small>
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
			<div id="weightPlaceholder" class="center-block" style="min-width:900px;height:200px">
			</div>
			<h2>
				Your diary stats
				<small>2340 / ${dailyCalories} kcal for last 7 days</small>
			</h2>
			<div id="diaryPlaceholder" class="center-block" style="min-width:900px;height:200px">
			</div>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="//code.highcharts.com/highcharts.js"></script>
		<script src="resources/js/highcharts-utils.js"></script>
		<script src="resources/js/underscore.js"></script>
		<script src="resources/js/backbone.js"></script>
		<script src="resources/js/backbone-utils.js"></script>

		<script type="text/javascript">
			$(Highcharts.setOptions(globalOptions));

			function plotWeight() {
				var weightChart = plotEmptyChart('#weightPlaceholder', {
					tooltip: {
						valueSuffix: ' <spring:message code="weight.measure"/>'
					}
				});
				$.get('weight/raw', function(result) {
					weightChart.addSeries({
						name: '<spring:message code="label.weight"/>',
						data: process(result, 'date', 'weight')
					});
					$.get('goal/raw', function(result) {
						weightChart.addSeries({
							name: '<spring:message code="label.goal"/>',
							dashStyle: 'dot',
							data: process(result, 'date', 'weight')
						});
					});
				});
			}

			function plotDiary() {
				var diaryChart = plotEmptyChart('#diaryPlaceholder', {
					tooltip: {
						valueSuffix: ' <spring:message code="calories.measure"/>'
					}
				});
				$.get('diary/aggregated', function(result) {
					diaryChart.addSeries({
						name: '<spring:message code="diary.calories.total"/>',
						data: process(result, 'date', 'calories')
					});
					$.get('weight/bmr', function(result) {
						diaryChart.addSeries({
							name: '<spring:message code="label.goal"/>',
							dashStyle: 'dot',
							data: process(result, 'date', 'calories')
						});
					});
				});
			}
		</script>
	</body>
</html>
