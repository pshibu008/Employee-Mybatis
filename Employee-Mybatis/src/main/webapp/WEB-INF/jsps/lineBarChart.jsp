<script>
$(function()
{ 
	var lineBarChart = new CanvasJS.Chart("lineBarChartContainer", 
	{
		animationEnabled: true,
		theme: "light2",
		title:
		{
			text: "Branch wise total Employee Salary",
			fontSize: 20,
			fontFamily: "Trebuchet MS",
			fontWeight: "bold",
			margin: 10
		},
		axisY: 
		{
			title: "Number of Employee",
		    suffix: " K"
		},
		data: 
		[{        
			type: "column",
			dataPoints: 
			[      
				{ y: ${cse}, label: "CSE" },   
				{ y: ${ec},  label: "EC" },
				{ y: ${it},  label: "IT" }
			]
		},
		{        
			type: "line",
			toolTipContent: "{label}: {y}K",        
	        showInLegend: true,
			dataPoints: 
			[      
				{ y: ${cseSalary}/10000, label: "CSE" },                   
				{ y: ${ecSalary}/10000,  label: "EC" },                         
				{ y: ${itSalary}/10000,  label: "IT" }                         
			]
		}]
	});
	lineBarChart.render();
});
</script>
<div class="card shadow p-3 bg-white rounded">
	<div class="card-body">
		<div id="lineBarChartContainer" style="height: 240px; width: 100%;"></div>
	</div>
</div>