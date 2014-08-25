<form:form class="navbar-form navbar-right" action="login" method="post" commandName="user">
	<div class="form-group">
		<form:input path="login" placeholder="Login" class="form-control" required="true" />
	</div>
	<div class="form-group">
		<form:password path="password" placeholder="Password" class="form-control" required="true" />
	</div>
	<div class="form-group">
		<form:button class="btn btn-success">Log in</form:button>
	</div>
</form:form>