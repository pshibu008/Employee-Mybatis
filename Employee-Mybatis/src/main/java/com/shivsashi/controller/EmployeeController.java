package com.shivsashi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shivsashi.dao.EmployeeMapper;
import com.shivsashi.entity.Employee;
import com.shivsashi.entity.SkillSet;
import com.shivsashi.importexcel.ExcelHelper;

@Controller
public class EmployeeController implements ServletContextAware
{
	@Autowired
	EmployeeMapper				employeeMapper;

	private ServletContext		servletContext;

	private static final String	ERRORPAGE		= "employeeError";

	private static final String	ADDEMPLOYEE		= "addEmployee";

	private static final String	EMPLOYEELIST	= "employeeList";

	private static final String	EMPLOYEE		= "employee";

	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	/**
	 * @param model
	 * @param request
	 * @param employee
	 * @param skill
	 * @param gender
	 * @return
	 */
	@RequestMapping("/listOfEmployee")
	public String showListOfEmployees(Model model, HttpServletRequest request, Employee employee, SkillSet skill, String gender)
	{
		if (gender != null)
		{
			List<Employee> emp = employeeMapper.getAllEmployees();
			List<Employee> employeeByGender = new ArrayList<>();
			for (int i = 0; i < emp.size(); i++)
			{
				if (emp.get(i).getGender().equalsIgnoreCase(gender))
				{
					employeeByGender.add(emp.get(i));
				}
			}
			model.addAttribute(EMPLOYEELIST, employeeByGender);
		}
		else
		{
			model.addAttribute(EMPLOYEELIST, employeeMapper.getAllEmployees());
		}

		return EMPLOYEELIST;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/showFormForAdd")
	public String addEmployee(Model model)
	{
		model.addAttribute(EMPLOYEE, new Employee());
		return ADDEMPLOYEE;
	}

	/**
	 * @param employee
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveProcess")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, HttpServletRequest request)
	{
		if (employee.getEmployeeId() == null)
		{
			String[] skills = request.getParameterValues("skill");
			String skillSet = manipulate(skills);
			employee.setSkillSet(skillSet);
			employeeMapper.saveEmployee(employee, skills);
			return "redirect:/listOfEmployee?success=true";
		}
		else
		{
			String[] skills = request.getParameterValues("skill");
			String skillSet = manipulate(skills);
			employee.setSkillSet(skillSet);
			employeeMapper.updateEmployee(employee, skills);
			return "redirect:/listOfEmployee?update=true";
		}
	}

	/**
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping("/displayUpdateForm")
	public String showUpdateForm(@RequestParam("employeeId") int employeeId, Model model)
	{
		Employee findById = employeeMapper.findById(employeeId);
		if (findById != null)
		{
			model.addAttribute(EMPLOYEE, findById);
			return ADDEMPLOYEE;
		}
		else
		{
			return ERRORPAGE;
		}
	}

	/**
	 * @param employeeId
	 * @return
	 */
	@RequestMapping("/displayDeleteForm")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId)
	{
		employeeMapper.deleteEmployee(employeeId);
		return "redirect:/listOfEmployee?delete=true";
	}

	@RequestMapping("/backToHome")
	public String backHome()
	{
		return "redirect:/listOfEmployee";
	}

	/**
	 * @param skills
	 * @return
	 */
	public String manipulate(String[] skills)
	{
		StringBuilder mSkill = new StringBuilder();
		for (String s : skills)
		{
			mSkill.append(s);
			mSkill.append(", ");
		}
		mSkill.deleteCharAt(mSkill.length() - 1);
		mSkill.deleteCharAt(mSkill.length() - 1);
		return new String(mSkill);
	}

	/**
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employeeInfo", method = RequestMethod.POST)
	public String empInfo(@RequestParam("empId") int employeeId, Model model)
	{
		Employee findById = employeeMapper.findById(employeeId);
		model.addAttribute(EMPLOYEE, findById);
		return "employeeModal";
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request)
	{
		return "redirect:/login";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/displayBarGraph")
	public String barGraph(Model model)
	{
		return "employeeChart";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/lineBar")
	public String lineBarChart(Model model)
	{
		List<Employee> emp = employeeMapper.getAllEmployees();
		int cseCount = 0;
		int ecCount = 0;
		int itCount = 0;
		int cseSalary = 0;
		int ecSalary = 0;
		int itSalary = 0;

		for (int j = 0; j < emp.size(); j++)
		{
			if (emp.get(j).getDepartment().equalsIgnoreCase("CSE"))
			{
				cseSalary += emp.get(j).getSalary();
				cseCount++;
			}
			else if (emp.get(j).getDepartment().equalsIgnoreCase("IT"))
			{
				itSalary += emp.get(j).getSalary();
				itCount++;
			}
			else
			{
				ecSalary += emp.get(j).getSalary();
				ecCount++;
			}
		}

		Map<Integer, Integer> map = new HashMap<>();
		map.put(cseCount, cseSalary);
		map.put(ecCount, ecSalary);
		map.put(itCount, itSalary);
		model.addAttribute("map", map);

		model.addAttribute("cse", cseCount);
		model.addAttribute("ec", ecCount);
		model.addAttribute("it", itCount);
		model.addAttribute("cseSalary", cseSalary);
		model.addAttribute("itSalary", itSalary);
		model.addAttribute("ecSalary", ecSalary);

		return "lineBarChart";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/pie")
	public String pieChart(Model model)
	{
		Map<Integer, Integer> map = getAgeKeyValue();
		Set<Map.Entry<Integer, Integer>> hmap = map.entrySet();
		ArrayList<Integer> key = new ArrayList<>();
		ArrayList<Integer> value = new ArrayList<>();
		for (Map.Entry<Integer, Integer> data : hmap)
		{
			key.add(data.getKey());
			value.add(data.getValue());
		}
		model.addAttribute("key", key);
		model.addAttribute("value", value);

		return "pieChart";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/bar")
	public String barChart(Model model)
	{
		List<Employee> emp = employeeMapper.getAllEmployees();
		int countMale = 0;
		int countFemale = 0;
		for (int i = 0; i < emp.size(); i++)
		{
			if (emp.get(i).getGender().equalsIgnoreCase("male"))
			{
				countMale++;
			}
			else
			{
				countFemale++;
			}
		}
		model.addAttribute("male", countMale);
		model.addAttribute("female", countFemale);
		return "barChart";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/line")
	public String lineChart(Model model)
	{
		List<Employee> emp = employeeMapper.getAllEmployees();
		ArrayList<Employee> maleList = new ArrayList<>();
		ArrayList<Employee> femaleList = new ArrayList<>();

		for (int i = 0; i < emp.size(); i++)
		{
			if (emp.get(i).getGender().equalsIgnoreCase("male"))
			{
				maleList.add(emp.get(i));
			}
			else
			{
				femaleList.add(emp.get(i));
			}
		}

		Map<Integer, Integer> map = getAgeKeyValue();
		Set<Map.Entry<Integer, Integer>> hmap = map.entrySet();
		ArrayList<Integer> key = new ArrayList<>();
		for (Map.Entry<Integer, Integer> data : hmap)
		{
			key.add(data.getKey());
		}
		model.addAttribute("key", key);

		ArrayList<Integer> mSal = new ArrayList<>();
		ArrayList<Integer> fSal = new ArrayList<>();
		int mSalary = 0;
		int fSalary = 0;
		for (int j = 0; j < key.size(); j++)
		{
			for (int k = 0; k < maleList.size(); k++)
			{
				if (maleList.get(k).getAge() == key.get(j))
				{
					mSalary += maleList.get(k).getSalary();
				}
			}
			mSal.add(mSalary);
			mSalary = 0;

			for (int k = 0; k < femaleList.size(); k++)
			{
				if (femaleList.get(k).getAge() == key.get(j))
				{
					fSalary += femaleList.get(k).getSalary();
				}
			}
			fSal.add(fSalary);
			fSalary = 0;
		}
		model.addAttribute("maleSalary", mSal);
		model.addAttribute("femaleSalary", fSal);
		return "lineChart";
	}

	@RequestMapping("/exportExcel")
	public ModelAndView generateExcel()
	{
		List<Employee> employeeList = employeeMapper.getAllEmployees();
		return new ModelAndView("excelView", "employees", employeeList);
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public String importExcel(@RequestParam("file") MultipartFile file)
	{
		String fileName = uploadExcelFile(file);
		String excelPath = servletContext.getContextPath() + "/src/main/webapp/resources/excels/" + fileName;
		ExcelHelper excelHelper = new ExcelHelper();
		List<Employee> employeeList = excelHelper.initialize(excelPath);
		String skillSet = "";
		int employeeId = 0;
		String[] skills;
		for (int i = 0; i < employeeList.size(); i++)
		{
			if (employeeList.get(i).getOperation().equalsIgnoreCase("ADD"))
			{
				skillSet = employeeList.get(i).getSkillSet().toUpperCase();
				skills = skillSet.split(", ");
				employeeMapper.saveEmployee(employeeList.get(i), skills);
			}
			else if (employeeList.get(i).getOperation().equalsIgnoreCase("MOD"))
			{
				skillSet = employeeList.get(i).getSkillSet().toUpperCase();
				skills = skillSet.split(", ");
				employeeMapper.updateEmployee(employeeList.get(i), skills);
			}
			else
			{
				employeeId = employeeList.get(i).getEmployeeId();
				employeeMapper.deleteEmployee(employeeId);
			}
		}

		return "redirect:/listOfEmployee";
	}

	/**
	 * @param multipartFile
	 * @return
	 */
	private String uploadExcelFile(MultipartFile multipartFile)
	{
		try
		{
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(servletContext.getContextPath() + "/src/main/webapp/resources/excels/" + multipartFile.getOriginalFilename());
			Files.write(path, bytes);
			return multipartFile.getOriginalFilename();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}

	public Map<Integer, Integer> getAgeKeyValue()
	{
		List<Employee> emp = employeeMapper.getAllEmployees();

		Map<Integer, Integer> map = new HashMap<>();
		int i = 0;
		int oldVal;
		int newVal;
		while (i != emp.size())
		{
			if (!map.containsKey(emp.get(i).getAge()))
			{
				map.put(emp.get(i).getAge(), 1);
			}
			else
			{
				oldVal = map.get(emp.get(i).getAge());
				newVal = oldVal + 1;
				map.put(emp.get(i).getAge(), newVal);
			}
			++i;
		}

		return map;
	}

	@RequestMapping("/exportJSON")
	public @ResponseBody List<Employee> getEmployeeInJSON()
	{
		return employeeMapper.getAllEmployees();
	}
	
}
