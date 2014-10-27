<%@ page language="java" contentType="text/javascript; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
var messages = {
	<c:forEach var="key" items="keys">
		<c:out value="key"/>: <spring:message code="key"/>
	</c:forEach>
}