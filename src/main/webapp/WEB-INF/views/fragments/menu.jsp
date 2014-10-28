<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="lang" value="${pageContext.response.locale.language}"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href=".">Diet</a>
		</div>
		<div id="main-menu" class="navbar-collapse collapse">
		</div>
	</div>
</div>

<script type="text/template" id="menu-language-template">
	<a data-toggle="dropdown" class="btn" role="button" href="#">
		<img alt="${lang}" src="resources/images/${lang}.png">(@= obj['language'] @)<span class="caret"></span>
	</a>
	<ul class="dropdown-menu" role="menu">
		<li><a href="?lang=ru"><img alt="ru_RU" src="resources/images/ru.png"> ru</a></li>
		<li><a href="?lang=en"><img alt="en_US" src="resources/images/en.png"> en</a></li>
	</ul>
</script>

<script type="text/template" id="menu-navbar-template">
	<li class="${dashboardActive}"><a href="dashboard">(@= obj['label.dashboard'] @)</a></li>
	<li class="${weightActive}"><a href="weight">(@= obj['label.weight'] @)</a></li>
	<li class="${foodActive}"><a href="food">(@= obj['label.food'] @)</a></li>
	<li class="${diaryActive}"><a href="diary">(@= obj['label.diary'] @)</a></li>
	<li class="${goalsActive}"><a href="goal">(@= obj['label.goal'] @)</a></li>
</script>

<script type="text/template" id="menu-logout-template">
	(@= obj['form.log.as'] @)<c:out value="${account.login}"/>. <a href="logout">(@= obj['form.log.out'] @)</a>
</script>

<script type="text/template" id="menu-signup-template">
	<a href="signup"><button type="button" class="btn btn-primary">(@= obj['form.sign.up'] @)</button></a>
</script>

<script type="text/template" id="menu-login-template">
	<div class="form-group"><input name="login" type="email" placeholder="(@= obj['user.login'] @)" maxlength="40" class="form-control" required></div>
	<div class="form-group"><input name="password" type="password" placeholder="(@= obj['user.password'] @)" class="form-control" required></div>
	<div class="form-group"><button class="btn btn-success">(@= obj['form.log.in'] @)</button></div>
</script>
