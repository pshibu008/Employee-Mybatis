<div class="modal-header">
    <button type="button" class="close" id="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body text-center">
	<h5>${employee.firstName} ${employee.lastName}</h5>
    <label>Age : </label>  ${employee.age} <br>
    <label>Gender : </label>${employee.gender} <br>
    <label>Email : </label>  ${employee.email} <br>
    <label>Salary : </label>  ${employee.salary} <br>
    <label>State : </label>  ${employee.state} <br>
    <label>City : </label>  ${employee.city} <br>
    <label>Department : </label>  ${employee.department} <br>
    <label>Address : </label>  ${employee.address} 
</div> 
<script>
	$('#close').click(function() 
	{
	    location.reload();
	});
</script>
