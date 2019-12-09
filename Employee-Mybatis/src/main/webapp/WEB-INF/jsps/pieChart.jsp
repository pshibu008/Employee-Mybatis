<script>
$(function()
{ 
	var pieChart = new CanvasJS.Chart("pieChartContainer", 
    {
    	animationEnabled: true,
       	theme: "light2",
       	title:
       	{
       		text: "Age wise total number of Employees",
       		fontSize: 20,
       		fontFamily: "Trebuchet MS",
       		fontWeight: "bold",
       		margin: 10
       	},
       	data: 
       	[{        
       		type: "pie",
       		dataPoints: 
       		[   
   
       			{ y: ${value[0]},  label: "${key[0]}" },                 
       			{ y: ${value[1]},  label: "${key[1]}" },						
       			{ y: ${value[2]},  label: "${key[2]}" },
       			{ y: ${value[3]},  label: "${key[3]}" },
       			{ y: ${value[4]},  label: "${key[4]}" },
       			{ y: ${value[5]},  label: "${key[5]}" },
       			{ y: ${value[6]},  label: "${key[6]}" },
       			{ y: ${value[7]},  label: "${key[7]}" }
       			
       		]
       	}]
    });
    pieChart.render();
});
</script>
<div class="card shadow p-3 bg-white rounded">
	<div class="card-body">
		<div id="pieChartContainer" style="height: 240px; width: 100%;"></div>
	</div>
</div>