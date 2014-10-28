<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<spring:message code="label.goal"/>
		</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="label.goal"/>
				</h1>
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-xs-1" style="width: 1px;"></th>
							<th><spring:message code="date"/></th>
							<th><spring:message code="goal.weight"/></th>
							<th class="col-xs-1" style="width: 1px;"></th>
						</tr>
					</thead>
					<tbody id="goal-table">
					</tbody>
				</table>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#goalModal">
					<spring:message code="goal.add"/>
				</button>
			</div>
		</div>

		<div class="modal fade" id="goalModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">
							<spring:message code="goal.add"/>
						</h4>
					</div>
					<div class="modal-body">
						<form id="goalForm" action="" onSubmit="sendForm(event)">
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
						<button type="submit" class="btn btn-primary" form="goalForm"><spring:message code="form.save"/></button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/template" id="goal-tr-template">
			<td id="edit"><a style="cursor: pointer;"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td>(@= new Date(date).toLocaleFormat("%d.%m.%Y") @)</td>
			<td>(@= weight @)</td>
			<td id="delete" class="text-right"><a style="cursor: pointer;" class="text-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/bootstrap-datetimepicker.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="messages"></script>
		<script src="resources/js/backbone/goal.js"></script>
		<script src="resources/js/backbone/menu.js"></script>

		<script type="text/javascript">
			function deleteGoal(date) {
				if (confirm('<spring:message code="form.confirm"/>'))
					$.post('goals/delete', {'date': new Date(date).toLocaleFormat("%Y.%m.%d")});
			};
		</script>
	</body>
</html>
