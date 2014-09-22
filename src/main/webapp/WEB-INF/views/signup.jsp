<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>
			<spring:message code="form.sign.up"/>
		</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
		<style type="text/css">
			.input-group-addon.aligned {
				min-width:160px;
				text-align:right;
			}
		</style>
	</head>
	<body>
		<%@ include file="fragments/menu-light.jsp" %>
		<div class="container">
			<div class="jumbotron">
				<form:form action="signup" accept-charset="UTF-8" method="post" modelAttribute="user">
					<h2 class="form-signin-heading">
						<spring:message code="label.signup"/>
					</h2>
					<div class="form-group">
						<form:errors path="*" element="div" class="alert alert-danger" />
					</div>
					<div class="form-group input-group">
						<spring:message code="user.login" var="login"/>
						<span class="input-group-addon aligned">
							<c:out value="${login}"/>
						</span>
						<form:input type="email" path="login" placeholder="${login}" maxlength="40" class="form-control" required="true"/>
					</div>
					<div class="form-group input-group">
						<spring:message code="user.password" var="password"/>
						<span class="input-group-addon aligned">
							<c:out value="${password}"/>
						</span>
						<form:password path="password" placeholder="${password}" class="form-control" required="true"/>
					</div>
					<div class="form-group input-group">
						<spring:message code="signup.password.confirm" var="passwordConfirm"/>
						<span class="input-group-addon aligned">
							<c:out value="${password}"/>
						</span>
						<form:password path="passwordConfirm" placeholder="${passwordConfirm}" class="form-control" required="true"/>
					</div>
					<div class="form-group input-group">
						<spring:message code="user.name.first" var="firstname"/>
						<span class="input-group-addon aligned">
							<c:out value="${firstname}"/>
						</span>
						<form:input path="firstName" placeholder="${firstname}" maxlength="40" class="form-control" required="true"/>
					</div>
					<div class="form-group input-group">
						<spring:message code="user.name.last" var="lastname"/>
						<span class="input-group-addon aligned">
							<c:out value="${lastname}"/>
						</span>
						<form:input path="lastName" placeholder="${lastname}" maxlength="40" class="form-control" required="true"/>
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon aligned">
							<spring:message code="user.gender"/>
						</span>
						<form:select path="gender" class="form-control" required="true">
							<form:option value="" disabled="true"><spring:message code="signup.gender"/></form:option>
							<c:forEach var="option" items="${genders}">
								<spring:message code="${option.description}" var="label"/>
								<form:option value="${option}" label="${label}"/>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-group input-group" id="datetimepicker">
						<spring:message code="signup.birthdate" var="birthdate"/>
						<span class="input-group-addon aligned">
							<spring:message code="user.date.birth"/>
						</span>
						<form:input path="birthDate" readonly="true" placeholder="${birthdate}" class="form-control"/>
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
					<div class="form-group input-group">
						<spring:message code="signup.country" var="country"/>
						<span class="input-group-addon aligned">
							<spring:message code="user.country"/>
						</span>
						<form:input path="countryId" placeholder="${country}" class="form-control" required="true" />
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon aligned">
							<spring:message code="user.height"/>
						</span>
						<form:input path="height" type="number" min="1" max="500" class="form-control" required="true" />
						
						<span class="input-group-addon">
							<spring:message code="signup.height.measure"/>
						</span>
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon aligned">
							<spring:message code="user.activity"/>
						</span>
						<form:input path="activityLevel" type="number" min="1" step="0.01" max="3" value="1.50" class="form-control" required="true" />
					</div>
					<button type="submit" class="btn btn-success">
						<spring:message code="form.sign.up"/>
					</button>
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
