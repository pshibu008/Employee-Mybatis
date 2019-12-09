<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-light bg-light justify-content-between">
         <a class="navbar-brand"><spring:message code="lbl.heading" text="Employee Portal"/></a>
         <form class="form-inline">
	         <a href="listOfEmployee" style="text-decoration: none;">
	         <img src="${pageContext.request.contextPath}/resources/images/details.png"
					alt="delete" style="width: 25px; height: 25px; margin-right: 20px;" /></a>
			 <a href="displayBarGraph" style="text-decoration: none;">
			 <img src="${pageContext.request.contextPath}/resources/images/dashboard.png"
					alt="delete" style="width: 25px; height: 25px; margin-right: 20px;" /></a> 
	         <a href="logout" style="text-decoration: none;">
	         <img src="${pageContext.request.contextPath}/resources/images/logout.png"
					alt="delete" style="width: 25px; height: 25px;" /></a>
         </form>
</nav>
