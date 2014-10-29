<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		<link rel="stylesheet" href="resources/css/bootstrap.css">
		<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-slider.css">
		<style type="text/css">
			.input-group-addon.aligned {
				min-width:160px;
				text-align:right;
			}
		</style>
	</head>
	<body>
		<%@ include file="fragments/menu.jsp" %>
		<div class="jumbotron">
			<div class="modal-dialog">
			</div>
		</div>

		<script type="text/template" id="form-template">
			<form action="signup" accept-charset="UTF-8" method="post">
				<h2 class="modal-header">(@= i18n['label.signup'] @)</h2>
				<form:errors path="*" element="div" class="alert alert-danger"/>
				<h4>(@= i18n['signup.account'] @)</h4>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.login'] @)</span>
					<input name="login" type="email" placeholder="(@= i18n['user.login'] @)" maxlength="40" class="form-control" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.password'] @)</span>
					<input name="password" type="password" placeholder="(@= i18n['user.password'] @)" class="form-control" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['signup.password.confirm'] @)</span>
					<input name="passwordConfirm" type="password" placeholder="(@= i18n['signup.password.confirm'] @)" class="form-control" required>
				</div>
				<h4>(@= i18n['signup.personal'] @)</h4>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.name.first'] @)</span>
					<input name="firstName" placeholder="(@= i18n['user.name.first'] @)" maxlength="40" class="form-control" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.name.last'] @)</span>
					<input name="lastName" placeholder="(@= i18n['user.name.last'] @)" maxlength="40" class="form-control" required>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.gender'] @)</span>
					<select name="gender" class="form-control" required>
						<option disabled>(@= i18n['signup.gender'] @)</option>
						<c:forEach var="option" items="${genders}"><option value="${option}" label="(@= i18n['${option.description}'] @)">
						</c:forEach>
					</select>
				</div>
				<div class="form-group input-group" id="datetimepicker">
					<span class="input-group-addon aligned">(@= i18n['user.date.birth'] @)</span>
					<input name="birthDate" placeholder="(@= i18n['signup.birthdate'] @)" class="form-control" readonly>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.country'] @)</span>
					<select name="countryId" class="form-control" required>
						<option disabled>(@= i18n['signup.country'] @)</option>
						<option value="RU" label="RU">
						<option value="GB" label="GB">
						<option value="US" label="US">
					</select>
				</div>
				<h4>(@= i18n['signup.other'] @)</h4>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.weight'] @)</span>
					<input name="weight" type="number" min="1" step="0.001" max="999" class="form-control" required>
					<span class="input-group-addon">(@= i18n['weight.measure'] @)</span>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.height'] @)</span>
					<input name="height" type="number" min="1" max="500" class="form-control" required>
					<span class="input-group-addon">(@= i18n['height.measure'] @)</span>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon aligned">(@= i18n['user.activity'] @)</span>
					<input name="activityLevel" type="number" min="1" step="0.01" max="3" value="1.50" class="form-control" required>
					<span class="input-group-addon"><input id="paeLevel"/></span>
				</div>
				<button type="submit" class="btn btn-success">(@= i18n['form.sign.up'] @)</button>
			</form>
		</script>

		<!-- Placed at the end of the document so the pages load faster -->
		<script src="resources/js/3rdparty/jquery-1.11.1.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
		<script src="resources/js/3rdparty/bootstrap.js"></script>
		<script src="resources/js/3rdparty/bootstrap-datetimepicker.js"></script>
		<script src="resources/js/3rdparty/bootstrap-slider.js"></script>
		<script src="resources/js/3rdparty/underscore.js"></script>
		<script src="resources/js/3rdparty/backbone.js"></script>
		<script src="resources/js/backbone/models.js"></script>
		<script src="messages?${pageContext.response.locale}"></script>
		<script src="resources/js/backbone/signup.js"></script>
		<script src="resources/js/backbone/menu.js"></script>
	</body>
</html>
