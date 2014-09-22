<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="navbar-form navbar-right navbar-text">
	<spring:message code="form.log.as"/>
	<c:out value="${account.login}"/>.
	<a href="logout">
		<spring:message code="form.log.out"/>
	</a>
</div>