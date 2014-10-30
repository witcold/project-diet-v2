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
		<img alt="${lang}" src="resources/images/${lang}.png"> (@= i18n['language'] @)<span class="caret"></span>
	</a>
	<ul class="dropdown-menu" role="menu">
		<li><a href="(@= location.pathname @)?lang=ru"><img alt="ru_RU" src="resources/images/ru.png"> ru</a></li>
		<li><a href="(@= location.pathname @)?lang=en"><img alt="en_US" src="resources/images/en.png"> en</a></li>
	</ul>
</script>

<script type="text/template" id="menu-navbar-template">
	<li class="${dashboardActive}"><a href="dashboard">(@= i18n['label.dashboard'] @)</a></li>
	<li class="${weightActive}"><a href="weight">(@= i18n['label.weight'] @)</a></li>
	<li class="${foodActive}"><a href="food">(@= i18n['label.food'] @)</a></li>
	<li class="${diaryActive}"><a href="diary">(@= i18n['label.diary'] @)</a></li>
	<li class="${goalActive}"><a href="goal">(@= i18n['label.goal'] @)</a></li>
</script>

<script type="text/template" id="menu-logout-template">
	(@= i18n['form.log.as'] @) <c:out value="${account.login}"/>. <a href="logout">(@= i18n['form.log.out'] @)</a>
</script>

<script type="text/template" id="menu-signup-template">
	<a href="signup"><button type="button" class="btn btn-primary">(@= i18n['form.sign.up'] @)</button></a>
</script>

<script type="text/template" id="menu-login-template">
	<div class="form-group"><input name="login" type="email" placeholder="(@= i18n['user.login'] @)" maxlength="40" class="form-control" required></div>
	<div class="form-group"><input name="password" type="password" placeholder="(@= i18n['user.password'] @)" class="form-control" required></div>
	<div class="form-group"><button class="btn btn-success">(@= i18n['form.log.in'] @)</button></div>
</script>
