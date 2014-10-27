<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<form class="navbar-form navbar-right" action="login" accept-charset="UTF-8" method="post">
	<div class="form-group">
		<spring:message code="user.login" var="login"/>
		<input name="login" type="email" placeholder="${login}" maxlength="40" class="form-control" required>
	</div>
	<div class="form-group">
		<spring:message code="user.password" var="password"/>
		<input name="password" type="password" placeholder="${password}" class="form-control" required>
	</div>
	<div class="form-group">
		<spring:message code="form.log.in" var="logIn"/>
		<input type="submit" value="${logIn}" class="btn btn-success">
	</div>
</form>