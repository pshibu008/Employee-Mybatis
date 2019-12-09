$(document).ready(function()
{
  	 $('#submit').click(function () 
  	 {
  		 var firstName = $('#firstName').val();
  		 var age = $('#age').val();
  		 var salary = $('#salary').val();
  		 var state = $('#state').val();
  		 var check = $(".sizeCheckbox:checked").length;
  		 var email = $('#email').val();
  		 var email_regex = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i
  		 
  		 if(firstName == "")
		   {
			 $('#show_error_fname').html('**FirstName cannot be empty!');
			 $('#firstName').focus();
			 return false;
		   }
		 else if((firstName.length<=2) || (firstName.length>=30))
		   {
			 $('#show_error_fname').html('**FirstName must in between 3 and 30 letters!');
			 $('#firstName').focus();
			 return false;
		   }
		 else if(!firstName.match(/^[a-zA-Z]+(?:-[a-zA-Z]+)*$/))
		   {
			 $('#show_error_fname').html('**FirstName contains character only!');
			 $('#firstName').focus();
			 return false;
		   }
		 else
		   {
			 $('#show_error_fname').html('');
		   }
		if(age == "")
		   {
			 $('#show_error_age').html('**Age cannot be empty!');
			 $('#age').focus();
			 return false;
		   }
		 else if((age<=18) || (age>=99))
		   {
			 $('#show_error_age').html('**Age must contains 2 digits only and must be greater than 18!');
			 $('#age').focus();
			 return false;
		   }
		 else if(!age.match(/^[1-9]\d*$/g))
		   {
			 $('#show_error_age').html('**Age contains numeric value only!');
			 $('#age').focus();
			 return false;
		   }
		 else
		   {
			 $('#show_error_age').html('');
		   }
		if(!($('#gender1').is(':checked')) && !($('#gender2').is(':checked')))
		   {
			 $('#show_error_gender').html('**Please Select your Gender!');
			 return false;
		   }
		 else
		   {
			 $('#show_error_gender').html('');
		   }
		if(salary == "")
		   {
			 $('#show_error_salary').html('**Salary cannot be empty!');
			 $('#salary').focus();
			 return false;
		   }
		 else if((salary<=9999))
		   {
			 $('#show_error_salary').html('**Salary contains minimum 5 digits!');
			 $('#salary').focus();
			 return false;
		   }
		 else if(!salary.match(/^[1-9]\d*$/g))
		   {
			 $('#show_error_salary').html('**Salary contains numeric value only!');
			 $('#salary').focus();
			 return false;
		   }
		 else
		   {
			 $('#show_error_salary').html('');
		   }
		if(email == "")
		   {
			 $('#show_error_email').html('**Email cannot be empty!');
			 return false;
		   }
		else if(!email_regex.test(email))
		   {
			 $('#show_error_email').html('**Please insert a valid email!');
			 return false;
		   }
		 else
		   {
			 $('#show_error_email').html('');
		   }
		if(state === "Select City")
		   {
			 $('#show_error_state').html('**Please Select state!');
			 return false;
		   }
		 else
		   {
			 $('#show_error_state').html('');
		   }
		if(check < 1)
		   {
			 $('#show_error_skill').html('**Please Select skillSet!');
			 return false;
		   }
		 else
		   {
			 $('#show_error_skill').html('');
		   }
     })
});
