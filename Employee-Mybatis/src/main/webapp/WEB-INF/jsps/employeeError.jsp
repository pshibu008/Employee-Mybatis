<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
	  integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
	<style>
		body 
		{
			background-color: white;
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
		}
	</style>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<h4><spring:message code="lbl.msg" text="OOPs!! Not a Valid Id"/></h4>
</body>
</html>