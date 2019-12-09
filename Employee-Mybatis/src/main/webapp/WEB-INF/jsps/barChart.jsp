<script>
$(function ()
{ 
       var barChart = new CanvasJS.Chart("barChartContainer", 
        {
        	animationEnabled: true,
        	theme: "light2",
        	title:
        	{
        		text: "Gender wise Employees Length",
        		fontSize: 20,
        		fontFamily: "Trebuchet MS",
        	    fontWeight: "bold",
        		margin: 10
        	},
        	axisY :{
        		title: "Number of Employees"
        	},
        	data: [{        
        		type: "column",  // column pie funnel
        		click: onClick,
        		dataPoints: [          									
        			{ y: ${male}, label: "MALE" },                   
        			{ y: ${female},  label: "FEMALE" }	 				
        			] 
        		  }]
        });
        				  
       barChart.render(); 
        
       function onClick(e) 
       {
    	   $.ajax({
    	        url: 'listOfEmployee',
    	        data: { 'gender' : e.dataPoint.label },
    	        cache: false,
    	        success: function (data) 
    	        {
    	        	document.open();
    	            document.write(data);
    	            document.close();
    	        },
    	        error: function (xhr, ajaxOptions, thrownError) 
    	        {
    	            
    	        }
    	    });
       }
       
       var chartType = document.getElementById('chartType');
       chartType.addEventListener( "change",  function()
    	{
         	for(var i = 0; i < barChart.options.data.length; i++)
         	{
       	  		barChart.options.data[i].type = chartType.options[chartType.selectedIndex].value;
         	}
         barChart.render();
       });
});

</script>
<div class="card shadow p-1 bg-white rounded">
	<div class="card-body">
		<select id="chartType" name="chartType" style="width:80px; margin-left:85%;">
		  <option value="column">Column</option>
		  <option value="line">Line</option>
		  <option value="bar">Bar</option>
		  <option value="pie">Pie</option>
		  <option value="doughnut">Doughnut</option>
		</select>
		<div id="barChartContainer" style="height: 240px; width: 100%;"></div>
	</div>
</div>
