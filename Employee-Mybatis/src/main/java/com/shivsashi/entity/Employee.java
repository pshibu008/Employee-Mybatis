package com.shivsashi.entity;

import java.util.ArrayList;
import java.util.List;

public class Employee
{
	private Integer			employeeId;

	private String			firstName;

	private String			lastName;

	private int				age;

	private String			gender;

	private int				salary;

	private String			department;

	private String			state;

	private String			city;

	private String			skillSet;

	private String			address;

	private String			email;

	private List<SkillSet>	skills	= new ArrayList<>();

	private String			operation;

	public Integer getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId)
	{
		this.employeeId = employeeId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public int getSalary()
	{
		return salary;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getSkillSet()
	{
		return skillSet;
	}

	public void setSkillSet(String skillSet)
	{
		this.skillSet = skillSet;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<SkillSet> getSkills()
	{
		return skills;
	}

	public void setSkills(List<SkillSet> skills)
	{
		this.skills = skills;
	}

	public String getOperation()
	{
		return operation;
	}

	public void setOperation(String operation)
	{
		this.operation = operation;
	}

}
