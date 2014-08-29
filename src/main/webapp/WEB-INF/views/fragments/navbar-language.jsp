<div class="dropdown navbar-form navbar-right">
	<a data-toggle="dropdown" class="btn" role="button" href="#">
		<img alt="${pageContext.response.locale}" src="resources/images/${pageContext.response.locale}.png">
		${pageContext.response.locale}
		<span class="caret"></span>
	</a>
	<ul class="dropdown-menu" role="menu">
		<li><a href="?lang=ru"><img alt="ru_RU" src="resources/images/ru.png"> ru</a></li>
		<li><a href="?lang=en"><img alt="en_US" src="resources/images/en.png"> en</a></li>
	</ul>
</div>