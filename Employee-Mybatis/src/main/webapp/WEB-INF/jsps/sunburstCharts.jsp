<div class="card shadow p-3 bg-white rounded">
	<div class="card-body text-center">
		<svg></svg>
	</div>
</div>
<script>

    // JSON data
     var nodeData = ${jsonData}
    	 
    // Variables
    var width = 500;
    var height = 500;
    var radius = Math.min(width, height) / 2;
    var color = d3.scaleOrdinal().range(["Blue", "Green", "Yellow", "#2E86C1", "#138D75 ", "#138D75 ", "#E0E00A", "#E0E00A", "#3498DB", "#3498DB"]);

    // Create primary <g> element
    var g = d3.select('svg')
        .attr('width', width)
        .attr('height', height)
        .append('g')
        .attr('transform', 'translate(' + width / 2 + ',' + height / 2 + ')');    

    // Data strucure
    var partition = d3.partition()
        .size([2 * Math.PI, radius]);

    // Find data root
    var root = d3.hierarchy(nodeData)
        .sum(function (d) 
        { 
            return d.size
        });

    // Size arcs
    partition(root);
    var arc = d3.arc()
        .startAngle(function (d) 
        { 
            return d.x0 
        })
        .endAngle(function (d) 
        { 
            return d.x1 
        })
        .innerRadius(function (d) 
        { 
            return d.y0 
        })
        .outerRadius(function (d) 
        { 
            return d.y1 
        });
    
    var slice = g.selectAll('g')
        .data(root.descendants())
        .enter().append('g').attr("class", "node");
        
    slice.append('path').attr("display", function (d) 
        { 
            return d.depth ? null : "none"; 
        })
        .attr("d", arc)
        .style('stroke', '#fff')
        .style("fill", function (d) 
        { 
            return color((d.children ? d : d.parent).data.name); 
        });
        

	d3.select(self.frameElement).style("height", height + "px");
	
	// define tooltip
	var tooltip = d3.select('body') 
	  .append('div').classed('tooltip', true);   
	tooltip.append('div') 
	  .attr('class', 'label'); 
    
    slice.append("text")
        .attr("transform", function(d) 
        {
            return "translate(" + arc.centroid(d) + ")rotate(" + computeTextRotation(d) + ")"; 
        })
        .attr("dx", "-20")
        .attr("dy", ".5em")
        .attr("font-size","10px")
        .on('mouseover', function(d) 
        {
	      var total = d.parent.value;
	      tooltip.select('.label').html(d.data.name);                              
	      tooltip.style('display', 'block');  
	    })
	    .on('mouseout', function() 
	    {                
	      tooltip.style('display', 'none'); 
	    })
	    .on('mousemove', function(d) 
	    {                 
	      tooltip.style('top', (d3.event.layerY + 10) + 'px'); 
	      tooltip.style('left', (d3.event.layerX + 10) + 'px'); 
	  	})
        .text(function(d) 
        { 
            return d.parent ? d.data.name : "" 
        });
    
    function computeTextRotation(d) 
    {
        var angle = (d.x0 + d.x1) / Math.PI * 90;

        // Avoid upside-down labels
        return (angle < 120 || angle > 270) ? angle : angle + 180;  // labels as rims
        //return (angle < 180) ? angle - 90 : angle + 90;  // labels as spokes
    }
</script>