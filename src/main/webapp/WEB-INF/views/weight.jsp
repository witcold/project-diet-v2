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
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">
							<spring:message code="weight.add"/>
						</h4>
					</div>
					<div class="modal-body">
						<form id="weightForm" action="" onSubmit="sendForm(event)">
							<div class="form-group">
								<spring:message code="date" var="date"/>
								<div class='input-group date' id='datetimepicker'>
									<input id="date" name="date" placeholder="${date}" class="form-control" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							<div class="form-group input-group">
								<input id="weight" name="weight" type="number" min="1" step="0.001" max="999" class="form-control" required>
								<span class="input-group-addon"><spring:message code="weight.measure"/></span>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="form.close"/></button>
						<button type="submit" class="btn btn-primary" form="weightForm"><spring:message code="form.save"/></button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/template" id="weight-month-pager-template">
			<a href="weight#(@= prev @)" class="btn btn-default navbar-btn" role="button">&larr;</a>
			<a class="btn btn-link navbar-btn disabled" role="button">(@= now.toLocaleFormat("%m.%Y") @)</a>
			<a href="weight#(@= next @)" class="btn btn-default navbar-btn" role="button">&rarr;</a>
		</script>

		<script type="text/template" id="weight-tr-template">
			<td><a style="cursor: pointer;" onclick="editForm('(@= new Date(date).toLocaleFormat("%Y-%m-%d") @)', (@= weight @))"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td>(@= new Date(date).toLocaleFormat("%d.%m.%Y") @)</td>
			<td>(@= weight @)</td>
			<td class="text-right"><a style="cursor: pointer;" onclick="deleteWeight('(@= new Date(date).toLocaleFormat("%Y.%m.%d") @)')" class="text-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
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
	</body>
</html>
