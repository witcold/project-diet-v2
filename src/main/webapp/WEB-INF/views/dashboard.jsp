<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<link rel="stylesheet" href="resources/css/bootstrap.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<div class="personal"></div>
				<div class="weight"></div>
				<div class="calories"></div>
				<h2>Your current goal progress</h2>
				<div id="weightPlaceholder" class="center-block" style="min-width:900px;height:200px"></div>
				<h2>Your diary stats</h2>
				<div id="diaryPlaceholder" class="center-block" style="min-width:900px;height:200px"></div>
			</div>
		</div>

		<script type="text/template" id="personal-template">
			<h1>(@= i18n['label.dashboard'] @)</h1>
			<h3>Hello, mr. (@= firstName @) (@= lastName @)!</h3>
			<h4>Your height: <small>(@= height @) cm</small></h4>
			<h4>Your age: <small>(@= age @) year</small></h4>
		</script>

		<script type="text/template" id="weight-template">
			<h4>Your last weight: <small>(@= weight @) kg</small>
				BMI: <small>(@= bmi.toFixed(2) @)</small>
			</h4>
		</script>

		<script type="text/template" id="calories-template">
			<p>Assuming this data, your basal methabolic rate is (@= Math.round(calories / activityLevel) @) kcal.</p>
			<p>Your physical activity level set to (@= activityLevel @), so you need (@= calories @) kcal to spend every day.</p>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="messages?${pageContext.response.locale}"></script>
		<script type="text/javascript">
			var weightPath = '#weightPlaceholder';
			var weightValueSuffix = ' ' + messages.i18n['weight.measure'];
			var weightChartName = messages.i18n['label.weight'];
			var goalWeightChartName = messages.i18n['label.goal'];

			var diaryPath = '#diaryPlaceholder';
			var diaryValueSuffix = ' ' + messages.i18n['calories.measure'];
			var diaryChartName = messages.i18n['diary.calories.total'];
			var goalDiaryChartName = messages.i18n['label.goal'];
		</script>
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="//code.highcharts.com/highcharts.js"></script>
		<script src="resources/js/highcharts-utils.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="resources/js/backbone/dashboard.js"></script>
		<script src="resources/js/backbone/menu.js"></script>
	</body>
</html>
