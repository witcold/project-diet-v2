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
		<link rel="stylesheet" href="resources/css/bootstrap.css">
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
				Hello, mr. {{ firstName }} {{ lastName }}!
			</h3>
			<h4>
				Your height:
				<small>{{ height }} cm</small>
			</h4>
			<h4>
				Your last weight:
				<small>${lastWeight.weight} kg</small>
				BMI:
				<small>
					${bmi}
				</small>
			</h4>
			<h4>
				Your age:
				<small>{{ age }} year</small>
			</h4>
			<p>Assuming this data, your basal methabolic rate is ${bmr} kcal.</p>
			<p>Your physical activity level set to {{ activityLevel }}, so you need ${bmr*activityLevel} kcal to spend every day.</p>
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
		<script type="text/javascript">
			var weightPath = '#weightPlaceholder';
			var weightValueSuffix = ' <spring:message code="weight.measure"/>';
			var weightChartName = '<spring:message code="label.weight"/>';
			var goalWeightChartName = '<spring:message code="label.goal"/>';

			var diaryPath = '#diaryPlaceholder';
			var diaryValueSuffix = ' <spring:message code="calories.measure"/>';
			var diaryChartName = '<spring:message code="diary.calories.total"/>';
			var goalDiaryChartName = '<spring:message code="label.goal"/>';
		</script>
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="//code.highcharts.com/highcharts.js"></script>
		<script src="resources/js/highcharts-utils.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone-utils.js"></script>
	</body>
</html>
