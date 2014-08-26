<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="navbar-form navbar-right">
	<p style="color: white;">
		<spring:message code="log_as" text="log_as" /> ${account.login}.
		<a href="logout">
			<spring:message code="log_out" text="log_out" />
		</a>
	</p>
</div>