<script>
$(function()
{ 
	var lineChart = new CanvasJS.Chart("lineChartContainer", 
	{
		theme:"light2",
		animationEnabled: true,
		title:
		{
			text: "Number of Employees w.r.t gender, age and salary",
			fontSize: 20,
			fontFamily: "Trebuchet MS",
			fontWeight: "bold",
			margin: 10
		},
		axisY :
		{
			includeZero: false,
			title: "Salary"
		},
		toolTip: 
		{
			shared: "true"
		},
		data: 
		[{
			type: "line",
			visible: true,
			showInLegend: true,
			name: "Male",
			dataPoints: 
			[
				{ label: "${key[0]}", y: ${maleSalary[0]} },
				{ label: "${key[1]}", y: ${maleSalary[1]} },
				{ label: "${key[2]}", y: ${maleSalary[2]} },
				{ label: "${key[3]}", y: ${maleSalary[3]} },
				{ label: "${key[5]}", y: ${maleSalary[5]} },
				{ label: "${key[6]}", y: ${maleSalary[6]} },
				{ label: "${key[4]}", y: ${maleSalary[4]} }
			]
		},
		{
			type: "line", 
			showInLegend: true,
			visible: true,
			name: "Female",
			dataPoints: 
			[
				{ label: "${key[0]}", y: ${femaleSalary[0]} },
				{ label: "${key[1]}", y: ${femaleSalary[1]} },
				{ label: "${key[2]}", y: ${femaleSalary[2]} },
				{ label: "${key[3]}", y: ${femaleSalary[3]} },
				{ label: "${key[5]}", y: ${femaleSalary[5]} },
				{ label: "${key[6]}", y: ${femaleSalary[6]} },
				{ label: "${key[4]}", y: ${femaleSalary[4]} }
			]
		}]
	});
	lineChart.render();
});

</script>
<div class="card shadow p-3 bg-white rounded">
	<div class="card-body">
		<div id="lineChartContainer" style="height: 240px; width: 100%;"></div>
	</div>
</div>
