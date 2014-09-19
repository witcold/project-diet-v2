<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="sign_up" /></title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
	</head>
	<body>
		<%@ include file="fragments/menu-light.jsp" %>
		<div class="container">
			<div class="jumbotron">
				<form:form action="signup" accept-charset="UTF-8" method="post" modelAttribute="user">
					<h2 class="form-signin-heading"><spring:message code="signup.label.register" /></h2>
					<div class="form-group">
						<form:errors path="*" element="div" class="alert alert-danger" />
					</div>
					<div class="form-group">
						<spring:message code="login" var="login"/>
						<form:input type="email" path="login" placeholder="${login}" maxlength="40" class="form-control" required="true"/>
					</div>
					<div class="form-group">
						<spring:message code="password" var="password"/>
						<form:password path="password" placeholder="${password}" class="form-control" required="true"/>
					</div>
					<div class="form-group">
						<spring:message code="signup.password.confirm" var="signup_password_confirm"/>
						<form:password path="passwordConfirm" placeholder="${signup_password_confirm}" class="form-control" required="true"/>
					</div>
					<div class="form-group">
						<spring:message code="signup.name.first" var="signup_name_first"/>
						<form:input path="firstName" placeholder="${signup_name_first}" maxlength="40" class="form-control" required="true"/>
					</div>
					<div class="form-group">
						<spring:message code="signup.name.last" var="signup_name_last"/>
						<form:input path="lastName" placeholder="${signup_name_last}" maxlength="40" class="form-control" required="true"/>
					</div>
					<div class="form-group">
						<form:select path="gender" class="form-control" required="true">
							<form:option value="" disabled="true"><spring:message code="signup.gender"/></form:option>
							<form:options items="${gender}" itemLabel="description"/>
						</form:select>
					</div>
					<div class="form-group">
						<spring:message code="date" var="date"/>
						<div class='input-group date' id='datetimepicker'>
							<form:input path="birthDate" readonly="true" placeholder="${date}" class="form-control"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="form-group">
						<spring:message code="signup.country" var="signup_country"/>
						<form:input path="countryId" placeholder="${signup_country}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<form:input path="height" type="number" min="1" max="500" placeholder="${signup_height}" class="form-control" required="true" />
					</div>
					<div class="form-group">
						<form:input path="activityLevel" type="number" min="1" step="0.01" max="5" placeholder="${signup_activity_level}" class="form-control" required="true" />
					</div>
					<button type="submit" class="btn btn-success"><spring:message code="sign_up" /></button>
				</form:form>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="//code.jquery.com/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="resources/js/bootstrap-datetimepicker.js"></script>

		<script type="text/javascript">
			$('#datetimepicker').datetimepicker({
				format: 'YYYY.MM.DD',
				pickTime: false,
				useStrict: true
			});
		</script>
	</body>
</html>
