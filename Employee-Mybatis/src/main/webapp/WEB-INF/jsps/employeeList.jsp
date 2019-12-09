<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
	<style>
		th a:hover 
		{
			text-decoration: none;
			color: white;
		}
		
		th a 
		{
			color: white;
		}
		
		a:hover 
		{
			text-decoration: none;
			color: blue;
		}
		
		tr.even 
		{
			background-color: #AFEEEE;
		}
		
		th 
		{
			background-color: #4CAF50;
			color: white;
		}
		
		td, th 
		{
			font-size: 13px;
			text-align: left;
		}
		
		.modal
		{
			background: rgba(0, 0, 0, 0.5);
			position: absolute;
			float: left;
			left: 50%;
			top: 50%;
			transform: translate(-50%, -50%);
		}
	</style>
</head>
<title>Employees Details</title>
<body class="body">
	<jsp:include page="nav.jsp" />

	<div class="container mt-3">
		<a href="showFormForAdd">
			<button type="button" class="btn btn-success pl-4 pr-4 "
				style="float: right;">
				<spring:message code="lbl.add" text="Add New" />
			</button>
		</a> <a href="exportExcel">
			<button type="button" class="btn btn-success pl-4 pr-4">
				<spring:message code="lbl.export" text="Export" />
			</button>
		</a>
	</div>

	<div class="container table table-responsive-xl mt-2">
		<display:table name="${employeeList}" sort="list" pagesize="10"
			id="table1" class="table table-bordered" keepStatus="true"
			requestURI="" export="true"
			decorator="com.shivsashi.decorator.EmployeeDecorator">

			<c:url var="updateLink" value="/displayUpdateForm">
				<c:param name="employeeId" value="${table1.employeeId}" />
			</c:url>

			<c:url var="deleteLink" value="/displayDeleteForm">
				<c:param name="employeeId" value="${table1.employeeId}" />
			</c:url>

			<display:column title="Actions" media="html">
				<a href="${updateLink}"><img
					src="${pageContext.request.contextPath}/resources/images/edit.png"
					alt="edit" class="image" /></a>
				<a href="${deleteLink}"
					onclick="return confirm('Are you sure you want to delete it?');"><img
					src="${pageContext.request.contextPath}/resources/images/recycle-bin.png"
					alt="delete" class="image" /></a>
			</display:column>

			<display:column property="department" title="Dept." sortable="true"
				group="1" media="xml pdf html csv" />
			<display:setProperty name="export.decorated" value="false" />
			<display:column property="firstName" title="FirstName"
				sortable="true" media="xml html excel pdf csv" />
			<display:column property="lastName" title="LastName"
				media="xml html excel pdf csv" />
			<display:column property="age" title="Age" sortable="true"
				media="xml html pdf csv" />
			<display:column property="gender" title="Gender"
				media="xml html pdf csv" />
			<display:column property="email" title="Email"
				style="font-weight: bold; font-size:13px;"
				media="html excel pdf csv" />
			<display:column property="salary" title="Salary"
				media="excel html pdf" sortable="true" />
			<display:column property="address" title="Address"
				media="excel html pdf csv" />
			<display:column property="state" title="State"
				media="excel html pdf csv" />
			<display:column property="city" title="City"
				media="excel html pdf csv" />
			<display:column title="SkillSet" media="excel html pdf">
				<c:forEach items="${table1.skills}" var="skill" varStatus="loop">
	            		${skill.skillName}${!loop.last ? ',' : ''}
	            	</c:forEach>
			</display:column>
			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:setProperty name="export.excel.filename"
				value="EmployeeDetails.xls" />
			<display:setProperty name="export.pdf.filename"
				value="EmployeeDetails.pdf" />
			<display:setProperty name="export.csv.filename"
				value="EmployeeDetails.csv" />
			<display:setProperty name="export.xml.filename"
				value="EmployeeDetails.xml" />
			<display:setProperty name="export.pdf" value="true" />
			<display:setProperty name="export.excel" value="true" />
			<display:setProperty name="export.csv" value="true" />
			<display:setProperty name="export.xml" value="true" />
		</display:table>
	</div>

	<div class="container">
		<form method="POST" enctype="multipart/form-data"
			action="${pageContext.request.contextPath}/employee/import">
			<input type="file" class="btn btn-success" name="file"> <input
				type="submit" class="btn btn-success pl-4 pr-4" value="Import">
		</form>
	</div>

	<div class="container">
		<a href="exportJSON">
			<button type="button" class="btn btn-success pl-4 pr-4">
				<spring:message code="lbl.json" text="Export JSON" />
			</button>
		</a>
	</div>
	
	<div class="modal" tabindex="-1" role="dialog" id="MyModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div id="employeeDetails"></div>
			</div>
		</div>
	</div>

	<script>
		function showEmpDetails(empid) 
		{
			console.log(empid);
			$.ajax
			({
				url : "employeeInfo",
				type : "POST",
				data : 
				{
					empId : empid
				},
				success : function(data) 
				{
					console.log(data);
					$("#employeeDetails").html(data);
					$("#MyModal").show();
				},
				error : function(record) 
				{
					console.log(record);
					alert("error");
				}
			});
		}
	</script>
</body>
</html>