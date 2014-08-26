<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="navbar-form navbar-right">
	<spring:message code="sign_up" var="sign_up"/>
	<a href="signup"><button type="button" class="btn btn-primary">${sign_up}</button></a>
</div>