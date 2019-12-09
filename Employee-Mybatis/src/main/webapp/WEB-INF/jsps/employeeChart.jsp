<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<script src="https://d3js.org/d3.v4.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
		crossorigin="anonymous">
	<title>Dashboard</title>
	<style>
		#topButton
		{
			position: fixed;
			bottom: 40px;
			right: 40px;
			font-size: 22px;
			width: 50px;
			height: 50px;
			background: #e74c3c;
			color: white;
			border: none;
			cursor: pointer;
		}
		div.tooltip 
        {
		    position: absolute;
		    width: 200px;
		    height: auto;
		    padding: 10px;
		    display: none;
		    background-color: white;
		    -webkit-border-radius: 10px;
		    -moz-border-radius: 10px;
		    border-radius: 10px;
		    -webkit-box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.4);
		    -moz-box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.4);
		    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.4);
		    pointer-events: none;
		}
            
		div.tooltip p 
		{
		    margin: 0;
		    font-family: Arial;
		    font-size: 16px;
		    line-height: 20px;
		} 
		.image
		{
			width:17px;
			height:17px;
		}
	</style>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<script src="${pageContext.request.contextPath}/resources/jQuery/scrollTop.js"></script>
	<div class="container mt-3">
	
		<div class="row">
			<div class="col-md-3 p-1">
				<div id="totalEmployee"></div>
			</div>
			<div class="col-md-3 p-1">
				<div id="totalDepartment"></div>
			</div>
			<div class="col-md-3 p-1">
				<div id="totalEmployeeByAge"></div>
			</div>
			<div class="col-md-3 p-1">
				<div id="countMaleVsFemale"></div>
			</div>
		</div>
		
		<div class="row" id="sort_one">
			<div class="col-md-6 p-1">
				<div id="barGraph"></div>
			</div>
			<div class="col-md-6 p-1">
				<div id="pieGraph"></div>
			</div>
			<div class="col-md-6 p-1">
				<div id="lineGraph"></div>
			</div>
			<div class="col-md-6 p-1">
				<div id="lineBarGraph"></div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12 p-1">
				<div id="sunburstChart"></div>
			</div>
		</div>
	</div>

	<button id="topButton"><i class="fas fa-arrow-up"></i></button>
	<script>
		$( function() 
		{
			$( "#sort_one" ).sortable();
			$( "#sort_one" ).disableSelection();
		});
    </script>

	<script>
		$.ajax
		({
			url: "countEmployees", 
			async: false, 
		    success: function(data) 
		    { 
		    	console.log(data); 
		    	$("#totalEmployee").append(data);
		    }
		}); 
	
		$.ajax
		({
			url: "countDepartment", 
			async: false, 
		    success: function(data) 
		    { 
		    	console.log(data); 
		    	$("#totalDepartment").append(data);
		    }
		});
		
		$.ajax
		({
			url: "countEmployeeByAge", 
			async: false, 
		    success: function(data) 
		    { 
		    	console.log(data); 
		    	$("#totalEmployeeByAge").append(data);
		    }
		});
		
		$.ajax
		({
			url: "countMaleVsFemale", 
			async: false, 
		    success: function(data) 
		    { 
		    	console.log(data); 
		    	$("#countMaleVsFemale").append(data);
		    }
		});
		
		$.ajax({url: "bar",
			async: false, 
		    success: function(result) 
		    { 
		    	console.log(result);
		    	$("#barGraph").html(result);
		    }
		});
		
		$.ajax({url: "line",
			async: false, 
		    success: function(data) 
		    { 
		    	console.log(data); 
		    	$("#lineGraph").append(data);
		    }
		}); 
		
		$.ajax({url: "lineBar",
			async: false, 
		    success: function(result) 
		    { 
		    	console.log(result);
		    	$("#lineBarGraph").html(result);
		    }
		});
		
		$.ajax
		({
			url: "pie", 
			async: false, 
		    success: function(data) 
		    { 
		    	console.log(data); 
		    	$("#pieGraph").append(data);
		    }
		});
		
		$.ajax
		({
			url: "sunburst", 
			async: false, 
		    success: function(data) 
		    { 
		    	console.log(data); 
		    	$("#sunburstChart").append(data);
		    }
		});
	</script>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous">
    </script>
</body>
</html>