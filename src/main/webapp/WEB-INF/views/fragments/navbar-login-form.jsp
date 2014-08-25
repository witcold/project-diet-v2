<form:form class="navbar-form navbar-right" action="login" method="post" modelAttribute="user">
	<div class="form-group">
		<input type="text" id="login" name="login" class="form-control" placeholder="<fmt:message key="login" />" required>
	</div>
	<div class="form-group">
		<input type="text" id="password" name="password" class="form-control" placeholder="<fmt:message key="password" />" required>
	</div>
	<div class="form-group">
		<form:button class="btn btn-success"><fmt:message key="log_in" /></form:button>
	</div>
</form:form>