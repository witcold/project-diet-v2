<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<ul class="nav navbar-nav">
	<!-- <li class="active"><a href="./">Home</a></li> -->
	<li class="${dashboardActive}"><a href="dashboard"><spring:message code="dashboard" /></a></li>
	<li class="${weightActive}"><a href="weight"><spring:message code="weight" /></a></li>
</ul>