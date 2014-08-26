<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form class="navbar-form navbar-right" action="login" method="post" modelAttribute="user">
	<div class="form-group">
		<spring:message code="login" text="login" var="login"/>
		<form:input path="login" placeholder="${login}" class="form-control" required="true" />
	</div>
	<div class="form-group">
		<spring:message code="password" text="password" var="password"/>
		<form:password path="password" placeholder="${password}" class="form-control" required="true" />
	</div>
	<div class="form-group">
		<spring:message code="log_in" text="log_in" var="log_in"/>
		<form:button class="btn btn-success">${log_in}</form:button>
	</div>
</form:form>