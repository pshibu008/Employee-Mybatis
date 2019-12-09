package com.shivsashi.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivsashi.dao.EmployeeMapper;
import com.shivsashi.entity.Employee;
import com.shivsashi.jsonvo.Child;
import com.shivsashi.jsonvo.Child1;
import com.shivsashi.jsonvo.Child2;
import com.shivsashi.jsonvo.RootVO;

@Controller
public class StatisticController
{
	@Autowired
	EmployeeMapper					employeeMapper;

	private static DecimalFormat	decimalformat2	= new DecimalFormat("#.#");

	RootVO							rootVO;

	ObjectMapper					mapper;

	private static final Logger		LOGGER			= LoggerFactory.getLogger(StatisticController.class);

	private static String			children		= "children";

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/countEmployees")
	public String totalEmployee(Model model)
	{
		List<Employee> employee = employeeMapper.getAllEmployees();
		model.addAttribute("totalEmployeeCount", employee.size());
		return "totalEmployee";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/countDepartment")
	public String totalDepartment(Model model)
	{
		List<Employee> employee = employeeMapper.getAllEmployees();
		Map<String, Integer> map = new HashMap<>();
		int i = 0;
		int oldVal;
		int newVal;
		while (i != employee.size())
		{
			if (!map.containsKey(employee.get(i).getDepartment()))
			{
				map.put(employee.get(i).getDepartment(), 1);
			}
			else
			{
				oldVal = map.get(employee.get(i).getDepartment());
				newVal = oldVal + 1;
				map.put(employee.get(i).getDepartment(), newVal);
			}
			++i;
		}

		Set<Map.Entry<String, Integer>> hmap = map.entrySet();
		ArrayList<String> countDepartment = new ArrayList<>();
		for (Map.Entry<String, Integer> data : hmap)
		{
			countDepartment.add(data.getKey());
		}

		model.addAttribute("totalDepartmentCount", countDepartment.size());
		return "totalDepartment";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/countEmployeeByAge")
	public String totalEmployeeByAge(Model model)
	{
		List<Employee> employee = employeeMapper.getAllEmployees();
		int countEmployeeByAge = 0;
		for (int i = 0; i < employee.size(); i++)
		{
			if (employee.get(i).getAge() > 50)
			{
				countEmployeeByAge++;
			}
		}
		model.addAttribute("totalEmployeeByAgeCount", countEmployeeByAge);
		return "totalEmployeeByAge";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/countMaleVsFemale")
	public String maleVsFemalePercentage(Model model)
	{
		List<Employee> employee = employeeMapper.getAllEmployees();
		float maleCount = 0;
		float femaleCount = 0;
		for (int i = 0; i < employee.size(); i++)
		{
			if (employee.get(i).getGender().equalsIgnoreCase("Male"))
			{
				maleCount++;
			}
			else
			{
				femaleCount++;
			}
		}

		double malePercent = (maleCount / employee.size()) * 100;
		double femalePercent = (femaleCount / employee.size()) * 100;

		model.addAttribute("malePercent", decimalformat2.format(malePercent));
		model.addAttribute("femalePercent", decimalformat2.format(femalePercent));

		return "maleVsFemalePercentage";
	}

	public void getSunburstChartData()
	{
		List<Employee> employee = employeeMapper.getAllEmployees();
		Map<String, Integer> stateMap = new HashMap<>();
		int i = 0;
		int oldVal;
		int newVal;
		while (i != employee.size())
		{
			if (!stateMap.containsKey(employee.get(i).getState()))
			{
				stateMap.put(employee.get(i).getState(), 1);
			}
			else
			{
				oldVal = stateMap.get(employee.get(i).getState());
				newVal = oldVal + 1;
				stateMap.put(employee.get(i).getState(), newVal);
			}
			++i;
		}

		Set<Map.Entry<String, Integer>> hmap = stateMap.entrySet();
		ArrayList<String> stateName = new ArrayList<>();
		ArrayList<Integer> stateCount = new ArrayList<>();
		for (Map.Entry<String, Integer> data : hmap)
		{
			stateName.add(data.getKey());
			stateCount.add(data.getValue());
		}

		getCity(stateName, stateCount);
	}

	/**
	 * @param stateName
	 * @param stateCount
	 */
	private void getCity(ArrayList<String> stateName, ArrayList<Integer> stateCount)
	{
		List<Employee> employee = employeeMapper.getAllEmployees();
		Map<String, Integer> cityMap;
		int oldVal;
		int newVal;
		Set<Map.Entry<String, Integer>> hmap;
		ArrayList<String> cityName = new ArrayList<>();
		ArrayList<Integer> cityCount = new ArrayList<>();
		for (int i = 0; i < stateName.size(); i++)
		{
			cityMap = new HashMap<>();
			for (int j = 0; j < employee.size(); j++)
			{
				if (employee.get(j).getState().equals(stateName.get(i)))
				{
					if (!cityMap.containsKey(employee.get(j).getCity()))
					{
						cityMap.put(employee.get(j).getCity(), 1);
					}
					else
					{
						oldVal = cityMap.get(employee.get(j).getCity());
						newVal = oldVal + 1;
						cityMap.put(employee.get(j).getCity(), newVal);
					}
				}
			}

			hmap = cityMap.entrySet();
			for (Map.Entry<String, Integer> data : hmap)
			{
				cityName.add(data.getKey());
				cityCount.add(data.getValue());
			}
		}
		getDepartmentWise(stateName, stateCount, cityName, cityCount);
	}

	/**
	 * @param stateName
	 * @param stateCount
	 * @param cityName
	 * @param cityCount
	 */
	private void getDepartmentWise(ArrayList<String> stateName, ArrayList<Integer> stateCount, ArrayList<String> cityName, ArrayList<Integer> cityCount)
	{
		List<Employee> employee = employeeMapper.getAllEmployees();
		Map<String, Integer> departmentMap = null;
		int oldVal;
		int newVal;
		Set<Map.Entry<String, Integer>> hmap;
		ArrayList<String> departmentName = new ArrayList<>();
		ArrayList<Integer> departmentCount = new ArrayList<>();
		for (int i = 0; i < cityName.size(); i++)
		{
			departmentMap = new HashMap<>();
			for (int j = 0; j < employee.size(); j++)
			{
				if (employee.get(j).getCity().equals(cityName.get(i)))
				{
					if (!departmentMap.containsKey(employee.get(j).getDepartment()))
					{
						departmentMap.put(employee.get(j).getDepartment(), 1);
					}
					else
					{
						oldVal = departmentMap.get(employee.get(j).getDepartment());
						newVal = oldVal + 1;
						departmentMap.put(employee.get(j).getDepartment(), newVal);
					}
				}
			}
			hmap = departmentMap.entrySet();
			for (Map.Entry<String, Integer> data : hmap)
			{
				departmentName.add(data.getKey());
				departmentCount.add(data.getValue());
			}
		}
		createJSON(stateName, stateCount, cityName, cityCount, departmentName, departmentCount);
	}

	/**
	 * @param stateName
	 * @param stateCount
	 * @param cityName
	 * @param cityCount
	 * @param departmentName
	 * @param departmentCount
	 * @return
	 */
	private void createJSON(ArrayList<String> stateName, ArrayList<Integer> stateCount, ArrayList<String> cityName, ArrayList<Integer> cityCount, ArrayList<String> departmentName,
			ArrayList<Integer> departmentCount)
	{
		mapper = new ObjectMapper();
		rootVO = new RootVO();
		rootVO.setName("State");
		int cityIndex = 0;
		int deptIndex = 0;
		int citySize = cityName.size() / stateName.size();
		int deptSize = stateName.size();
		Child child;
		ArrayList<Child> listChild = new ArrayList<>();
		Child1 child1;
		ArrayList<Child1> listChild1 = null;
		Child2 child2;
		ArrayList<Child2> listChild2 = null;
		String state = "";
		String city = "";
		String department = "";

		for (int i = 0; i < stateName.size(); i++)
		{
			child = new Child();
			state = stateName.get(i) + "," + stateCount.get(i);
			child.setName(state);
			listChild1 = new ArrayList<>();

			for (int j = 0; j < citySize; j++)
			{
				child1 = new Child1();
				city = cityName.get(cityIndex) + "," + cityCount.get(cityIndex++);
				child1.setName(city);
				listChild2 = new ArrayList<>();

				for (int k = 0; k < deptSize; k++)
				{
					child2 = new Child2();
					department = departmentName.get(deptIndex) + "," + departmentCount.get(deptIndex);
					child2.setName(department);
					child2.setSize(departmentCount.get(deptIndex++));
					listChild2.add(child2);
				}
				child1.setChild2(listChild2);
				listChild1.add(child1);
			}
			child.setChild1(listChild1);

			listChild.add(child);
		}

		rootVO.setChild(listChild);
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/sunburst")
	public String sunburstChart(Model model)
	{
		getSunburstChartData();
		try
		{
			String jsonData = mapper.writeValueAsString(rootVO);
			String jsonObject = jsonData.replace("child", children).replace("children1", children).replace("children2", children);
			model.addAttribute("jsonData", jsonObject);
		}
		catch (JsonProcessingException e)
		{
			LOGGER.error("Exception", e);
		}

		return "sunburstCharts";
	}
}
