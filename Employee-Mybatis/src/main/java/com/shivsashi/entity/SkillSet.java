package com.shivsashi.entity;

public class SkillSet
{
	private int		skillId;

	private String	skillName;

	private int		empId;

	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(int empId)
	{
		this.empId = empId;
	}

	public int getSkillId()
	{
		return skillId;
	}

	public void setSkillId(int skillId)
	{
		this.skillId = skillId;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public void setSkillname(String skillName)
	{
		this.skillName = skillName;
	}

}
