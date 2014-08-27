<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="navbar-form navbar-right" style="color: #777; padding-top: 7px">
	<spring:message code="log_as"/> ${account.login}.
	<a href="logout">
		<spring:message code="log_out" />
	</a>
</div>