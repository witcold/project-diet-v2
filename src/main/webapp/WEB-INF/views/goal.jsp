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
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<spring:message code="goal.add"/>
						</h4>
					</div>
					<div class="modal-body">
						<form:form id="goalForm" action="goal/add" accept-charset="UTF-8" method="post" modelAttribute="goal" onSubmit="return validateDate(event)">
							<div class="form-group">
								<spring:message code="date" var="date"/>
								<div class='input-group date' id='datetimepicker'>
									<form:input readonly="true" path="date" placeholder="${date}" class="form-control"/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group input-group">
								<form:input type="number" min="1" step="0.001" max="999" path="weight" class="form-control" required="true"/>
								<span class="input-group-addon">
									<spring:message code="weight.measure"/>
								</span>
							</div>
						</form:form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<spring:message code="form.close"/>
						</button>
						<button type="submit" class="btn btn-primary" form="goalForm">
							<spring:message code="form.save"/>
						</button>
					</div>
				</div>
			</div>
		</div>

		<script type="text/template" id="goal-tr-template">
			<td><a style="cursor: pointer;" onclick="editForm('(@= new Date(date).toLocaleFormat("%Y-%m-%d") @)', (@= weight @))"><span class="glyphicon glyphicon-pencil"></span></a></td>
			<td>(@= new Date(date).toLocaleFormat("%d.%m.%Y") @)</td>
			<td>(@= weight @)</td>
			<td class="text-right"><a style="cursor: pointer;" onclick="deleteWeight('(@= new Date(date).toLocaleFormat("%Y-%m-%d") @)')" class="text-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="//code.jquery.com/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="resources/js/3rdparty/bootstrap-datetimepicker.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="resources/js/backbone/goal.js"></script>

		<script type="text/javascript">
			$('#datetimepicker').datetimepicker({
				format: 'YYYY.MM.DD',
				pickTime: false,
				useStrict: true
			});

			var datetimepicker = $('#datetimepicker').data("DateTimePicker");
			var goalform = $('#goalForm');

			function deleteGoal(date) {
				if (confirm('<spring:message code="form.confirm"/>'))
					$.ajax({
						url: 'goal/delete',
						data: {'date': date},
						type: 'POST',
						success: function(result) {
							location.reload();
						}
					});
			};

			function editForm(date, weight) {
				goalform.attr('action', 'goal/update');
				datetimepicker.setDate(new Date(date));
				goalform.find('#datetimepicker .input-group-addon').hide();
				goalform.find('.date').removeClass('input-group');
				$('#weight').val(weight);
				$('#goalModal').modal('show');
			};

			$('#goalModal').on('hidden.bs.modal', function (e) {
				goalform.trigger('reset');
				goalform.attr('action', 'goal/add');
				goalform.find('.date').addClass('input-group');
				goalform.find('.input-group-addon').show();
			});

			function validateDate(event) {
				var date = goalform.find('#datetimepicker input').val();
				if (!date) {
					datetimepicker.show(event);
					return false;
				}
			}
		</script>
	</body>
</html>
