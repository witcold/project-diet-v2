<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<ul class="nav navbar-nav">
	<li class="${dashboardActive}">
		<a href="dashboard">
			<spring:message code="label.dashboard"/>
		</a>
	</li>
	<li class="${weightActive}">
		<a href="weight">
			<spring:message code="label.weight"/>
		</a>
	</li>
	<li class="${foodActive}">
		<a href="food">
			<spring:message code="label.food"/>
		</a>
	</li>
	<li class="${diaryActive}">
		<a href="diary">
			<spring:message code="label.diary"/>
		</a>
	</li>
	<li class="${goalsActive}">
		<a href="goals">
			<spring:message code="label.goals"/>
		</a>
	</li>
</ul>