<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<link rel="stylesheet" href="resources/css/bootstrap.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1 id="container-label"></h1>
				<div id="placeholder" class="center-block" style="min-width:900px;height:200px">
				</div>
				<div id="date" class="btn-group btn-group-justified">
				</div>
				<table class="table table-hover">
					<thead id="table-header">
					</thead>
					<tbody id="weight-table">
					</tbody>
				</table>
				<button id="add-weight" type="button" class="btn btn-primary" data-toggle="modal" data-target="#weightModal"></button>
			</div>
		</div>

		<div class="modal fade" id="weightModal">
			<div class="modal-dialog">
				<div class="modal-content">
				</div>
			</div>
		</div>

		<script type="text/template" id="weight-month-pager-template">
			<a href="weight#(@= prev @)" class="btn btn-default navbar-btn" role="button">&larr;</a>
			<a class="btn btn-link navbar-btn disabled" role="button">(@= now.toLocaleFormat("%m.%Y") @)</a>
			<a href="weight#(@= next @)" class="btn btn-default navbar-btn" role="button">&rarr;</a>
		</script>

		<script type="text/template" id="table-header-template">
			<tr>
				<th class="col-xs-1" style="width: 1px;"></th>
				<th>(@= i18n['date'] @)</th>
				<th>(@= i18n['weight.weight'] @)</th>
				<th class="col-xs-1" style="width: 1px;"></th>
			</tr>
		</script>

		<script type="text/template" id="weight-tr-template">
			<td id="edit"><a style="cursor: pointer;"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td>(@= new Date(date).toLocaleFormat("%d.%m.%Y") @)</td>
			<td>(@= weight @)</td>
			<td id="delete" class="text-right"><a style="cursor: pointer;" class="text-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
		</script>

		<script type="text/template" id="modal-template">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">(@= i18n['weight.add'] @)</h4>
			</div>
			<div class="modal-body">
				<form id="weightForm" action="" onSubmit="sendForm(event)">
					<div class="form-group">
						<div class='input-group date' id='datetimepicker'>
							<input id="date" name="date" placeholder="(@= i18n['date'] @)" class="form-control" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
					<div class="form-group input-group">
						<input id="weight" name="weight" type="number" min="1" step="0.001" max="999" class="form-control" required>
						<span class="input-group-addon">(@= i18n['weight.measure'] @)</span>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">(@= i18n['form.close'] @)</button>
				<button type="submit" class="btn btn-primary" form="weightForm">(@= i18n['form.save'] @)</button>
			</div>
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
		<script src="messages?${pageContext.response.locale}"></script>
		<script src="resources/js/backbone/weight.js"></script>
		<script src="resources/js/backbone/menu.js"></script>
	</body>
</html>
