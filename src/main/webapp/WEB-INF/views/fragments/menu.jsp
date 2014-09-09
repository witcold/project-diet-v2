<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href=".">Diet</a>
		</div>
		<div class="navbar-collapse collapse">
			<%@ include file="navbar-language.jsp" %>
			<c:choose>
				<c:when test="${not empty account.login}">
					<%@ include file="navbar-menu.jsp" %>
					<%@ include file="navbar-logout.jsp" %>
				</c:when>
				<c:otherwise>
					<%@ include file="navbar-signup.jsp" %>
					<%@ include file="navbar-login-form.jsp" %>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>