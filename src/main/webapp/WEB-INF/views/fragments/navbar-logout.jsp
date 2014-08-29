<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="navbar-form navbar-right navbar-text">
	<spring:message code="log_as"/> ${account.login}.
	<a href="logout">
		<spring:message code="log_out" />
	</a>
</div>