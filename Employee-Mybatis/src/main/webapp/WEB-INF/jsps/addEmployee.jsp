<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
 	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
</head>
<body class="body">
	<jsp:include page="nav.jsp"/>
	<script src="${pageContext.request.contextPath}/resources/jQuery/validate.js"></script>
	<script type="text/javascript">
	$(document).ready(function () 
	{
	    var skill1 = $("#skill").val().split(",");
	    var skill = $.trim(skill1);
	    var $checkboxes = $("input[type=checkbox]");
	    $checkboxes.each(function (idx, element) 
	    {
	         if (skill.indexOf(element.value) != -1) 
	         {
	            element.setAttribute("checked", "checked");
	            $("#skill").val("");
	         }
	         else 
	         {
	            element.removeAttribute("checked");
	         }
	    });
	});
	</script>
	<div class="container text-right mt-5 mb-2">  <!-- backToHome.html -->
		<a href="backToHome"><button type="button" class="btn btn-success pl-4 pr-4"><spring:message code="lbl.back" text="BACK"/></button></a>
	</div>
	<div class="container border border-dark" style="padding:1rem;"> 
		<form:form method="POST" class="form-group" modelAttribute="employee" action="saveProcess">
	    	<form:hidden path="employeeId" value="${employee.employeeId}"/>
	        <form:hidden path="skillSet" value = "${employee.skillSet}" id = "skill"/>
			<div class="row">
				<div class="col-md-6">
					<label for="firstName" class="control-label"><spring:message code="lbl.firstName" text="First Name"/></label>
					<form:input path="firstName" type="text" id="firstName" class="form-control"/>
					<div id="show_error_fname" class="error"></div>
				</div>
				<div class="col-md-6">
					<label for="lastName"><spring:message code="lbl.lastName" text="Last Name"/></label>
					<form:input path="lastName" type="text" id="lastName" class="form-control"/>
				</div>
				<div class="col-md-6">
					<label for="age"><spring:message code="lbl.age" text="Age"/></label>
					<form:input path="age" type="text" id="age" class="form-control"/>
					<div id="show_error_age" class="error"></div>
				</div>
				<div class="col-md-6">
					<label for="gender"><spring:message code="lbl.gender" text="Gender"/></label>
					<div class="mt-1">
						<label><form:radiobutton path="gender" value="Male" id="gender1"/> <spring:message code="lbl.male" text="Male"/></label>
		                <label class="pl-2"><form:radiobutton path="gender" value="Female" id="gender2"/> <spring:message code="lbl.female" text="Female"/></label>
	                    <div id="show_error_gender" class="error"></div>
	                </div>
	        	</div>
				<div class="col-md-6">
					<label for="salary"><spring:message code="lbl.salary" text="Salary"/></label>
					<form:input path="salary" type="text" id="salary" class="form-control"/>
					<div id="show_error_salary" class="error"></div>
				</div>
				<div class="col-md-6">
					<label for="email"><spring:message code="lbl.email" text="Email"/></label>
					<form:input path="email" type="email" id="email" class="form-control"/>
					<div id="show_error_email" class="error"></div>
				</div>
				<div class="col-md-6">
					<label for="email"><spring:message code="lbl.dept" text="Department"/></label>
					<form:select path="department" id="department" class="form-control" style="font-size:13px;">  
		           		<form:option value="IT" label="IT"/>  
		          		<form:option value="CSE" label="CSE"/>  
		         		<form:option value="EC" label="EC"/> 
	          		</form:select> 		
				</div>
				<div class="col-md-6">
					<label for="state"><spring:message code="lbl.state" text="State"/></label>
					<form:select path="state" id="state" class="form-control" style="font-size:13px;"> 
			        <form:option value="Select City" label="Select State"/> 
			        </form:select>
	    	        <div id="show_error_state" class="error"></div>
				</div>
				<div class="col-md-6">
					<label for="city"><spring:message code="lbl.city" text="City"/></label>
					<form:select path="city" id="city" class="form-control" style="font-size:13px;"> 
			        <form:option value="" label="Select City"/> 
			        </form:select>
				</div>
				<div class="col-md-6">
					<label for="skill"><spring:message code="lbl.skill" text="Skill Set"/></label>
					<div class="mt-1">
						<label class="check"><spring:message code="lbl.skill_1" text="JAVA"/>   <input type="checkbox" value="JAVA" id="check" class="sizeCheckbox" name="skill"></label>  
				        <label class="check pl-2"><spring:message code="lbl.skill_2" text="J2EE"/>   <input type="checkbox" value="J2EE" id="check" class="sizeCheckbox" name="skill"></label> 
				        <label class="check pl-2"><spring:message code="lbl.skill_3" text="SQL"/>   <input type="checkbox" value="SQL" id="check" class="sizeCheckbox" name="skill"></label>
				        <label class="check pl-2"><spring:message code="lbl.skill_4" text="SPRING"/>   <input type="checkbox" value="SPRING" id="check" class="sizeCheckbox" name="skill"></label><br>
				        <div id="show_error_skill" class="error"></div>
			        </div>
				</div>
				<div class="col-md-6">
					<label for="email"><spring:message code="lbl.address" text="Address"/></label>
					<form:textarea path="address" rows="3" cols="30" class="form-control" style="font-size:13px;"/>
				</div>
			</div>
				
			<div class="row">
				<div class="col-md-12 col-lg-12 text-center mt-4">
					<button type="reset"  value="clear" class="btn btn-secondary"><spring:message code="lbl.reset" text="Clear"/></button>
					<input type="submit" value="Submit" id="submit" class="btn btn-success">
				</div>
			</div>	
		</form:form>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	 integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	 integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" 
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<script>
		var obj, i, j;
		obj = 
		{
		  "state_city":
		   [
		    {
		      "name":"Karnataka",
		      "cities":["Bangalore", "Vijaypura"]
		    },
		    {
		      "name":"Bihar", 
		      "cities":["Patna", "Gaya"]
		    },
		    {
		      "name":"UttarPradesh", 
		      "cities":["Lucknow", "Agra"] 
		    }
		  ]
		}
		
		$(document).ready(function () 
		{
			var opt="";
			opt += '<option value="">Select state</option>';
			for (k in obj.state_city)
			{
			  opt += '<option value="'+ obj.state_city[k].name+'">'+obj.state_city[k].name+'</option>';
			}
			$('#state').html(opt);
			
			$("#state").on("change", function() 
			{
			    var state = $("#state").val();
			    if(state != "")
			    {
			       for (i in obj.state_city)
			         {
			            if(state == obj.state_city[i].name)
			             {
			                var cities = obj.state_city[i].cities;
			                var opt="";
			                for(j=0; j<2; j++)
			                {
			                    opt += '<option value="'+ cities[j]+'">'+cities[j]+'</option>';
			                }
			                $('#city').html(opt);
			             }
			          }
			     }
			     else
			     {
			           $('#city').html('<option value="">Select city</option>');
			     }
			});
			
			$("#state").val('${employee.state}');
			$("#state").trigger("change");
			$('#city').val('${employee.city}');
		});
	</script>
</body>
</html>

