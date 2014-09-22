<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form class="navbar-form navbar-right" action="login" accept-charset="UTF-8" method="post" modelAttribute="user">
	<div class="form-group">
		<spring:message code="user.login" var="login"/>
		<form:input type="email" path="login" placeholder="${login}" maxlength="40" class="form-control" required="true"/>
	</div>
	<div class="form-group">
		<spring:message code="user.password" var="password"/>
		<form:password path="password" placeholder="${password}" class="form-control" required="true"/>
	</div>
	<div class="form-group">
		<form:button class="btn btn-success">
			<spring:message code="form.log.in"/>
		</form:button>
	</div>
</form:form>