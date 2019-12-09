<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<title>index</title>
	<style>
		body
		{
			padding-top: 25vh;
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
		}
		form
		{
			background: white;
		}
		.form-container
		{
			border-radius: 10px;
			padding: 30px;
			box-shadow: 0px 0px 10px 0px;
		}
		.alert 
		{
			font-size:13px;
			font-style: italic;
			color: red;
		}
		#button
		{
			margin-left:20%;
		}
		input[type=text], input[type=password]
		{
			border-color: black;
		}
		.error
		{
			text-align: center;
		}	
	</style>
</head>
<body class="body">
	<script>
		$(document).ready(function()
		{
		  	 $('#submit').click(function () 
		  	 {
		  		 var username = $('#username').val();
		  		 var password = $('#password').val();
		  		 
		  		 if(username == "")
				   {
					 $('#username').css("border-color", "red");
					 return false;
				   }
				 else
				   {
					 $('#username').css("border-color", "black");
					 $('#show_error_main').html('');
				   }
				 if(password == "")
				   {
					 $('#password').css("border-color", "red");
					 return false;
				   }
				 else
				   {
					 $('#password').css("border-color", "black");
					 $('#show_error_main').html('');
				   }
				 if(username != "admin" || password != "admin")
				   {
					 $('#show_error_main').html('**Invalid UserName or Password..');
					 return false;
				   }
				 else
				   {
					 $('#show_error_main').html('');
				   }
			})
			$('#button').click(function()
			{
				$('#show_error_main').html('');
				$('#username').css("border-color", "black");
				$('#password').css("border-color", "black");
			})
		});
	</script>
	<section class="container-fluid">
		<section class="row justify-content-center">
	    	<section class="col-12 col-sm-6 col-md-3">
	       		<form class="form-container" action="listOfEmployee" method="POST">
	          		<h4 class="text-center">Login</h4>
	          		<div class="error"><span class="alert" id="show_error_main"></span></div>
	          		<div class="form-group">
	             		<label>User Name</label>
	             		<input type="text" class="form-control" id="username" name="user">
	          		</div>  
	           		<div class="form-group">
	             		<label> Password </label>
	             		<input type="password" class="form-control" id="password">
	          		</div>
	          		<div>
	              		<input class="btn btn-outline-secondary" type="reset" id="button" value="Clear">
	              		<button type="submit" class="btn btn-primary" id="submit">Submit</button>
	          		</div>  
	       		</form>
	    	</section>
	 	</section>
     </section>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
