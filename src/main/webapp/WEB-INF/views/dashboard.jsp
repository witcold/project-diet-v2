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
				<div class="personal">
				</div>
				<div class="weight">
				</div>
				<div class="calories">
				</div>
				<h2>
					Your current goal progress
				</h2>
				<div id="weightPlaceholder" class="center-block" style="min-width:900px;height:200px">
				</div>
				<h2>
					Your diary stats
				</h2>
				<div id="diaryPlaceholder" class="center-block" style="min-width:900px;height:200px">
				</div>
			</div>
		</div>

		<script type="text/template" id="personal-template">
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
				Your age:
				<small>{{ age }} year</small>
			</h4>
		</script>
		<script type="text/template" id="weight-template">
			<h4>
				Your last weight:
				<small>{{ weight }} kg</small>
				BMI:
				<small>
					{{ bmi.toFixed(2) }}
				</small>
			</h4>
		</script>
		<script type="text/template" id="calories-template">
			<p>Assuming this data, your basal methabolic rate is {{ Math.round(calories / activityLevel) }} kcal.</p>
			<p>Your physical activity level set to {{ activityLevel }}, so you need {{ calories }} kcal to spend every day.</p>
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
