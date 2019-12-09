package com.shivsashi.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shivsashi.entity.Employee;
import com.shivsashi.entity.SkillSet;
import com.shivsashi.util.MyBatisUtil;

@Repository
public class EmployeeMapper
{
	private static final Logger	LOGGER		= LoggerFactory.getLogger(EmployeeMapper.class);

	private static final String	EXCEPTION	= "Exception";

	/**
	 * @param employee
	 * @param skills
	 */
	public void saveEmployee(Employee employee, String[] skills)
	{
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();)
		{
			session.insert("insertEmployee", employee);
			employee = session.selectOne("getId");
			SkillSet skillSet = new SkillSet();
			for (int i = 0; i < skills.length; i++)
			{
				skillSet.setEmpId(employee.getEmployeeId());
				skillSet.setSkillname(skills[i]);
				session.insert("insertSkill", skillSet);
			}
			session.commit();
		}
		catch (Exception e)
		{
			LOGGER.error(EXCEPTION, e);
		}

	}

	/**
	 * @param employee
	 * @param skills
	 */
	public void updateEmployee(Employee employee, String[] skills)
	{
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();)
		{
			session.update("updateEmployee", employee);
			session.delete("deleteSkill", employee.getEmployeeId());
			SkillSet skillSet;
			for (int i = 0; i < skills.length; i++)
			{
				skillSet = new SkillSet();
				skillSet.setEmpId(employee.getEmployeeId());
				skillSet.setSkillname(skills[i]);
				session.insert("insertSkill", skillSet);
			}
			session.commit();
		}
		catch (Exception e)
		{
			LOGGER.error(EXCEPTION, e);
		}

	}

	/**
	 * @param employeeId
	 */
	public void deleteEmployee(int employeeId)
	{
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();)
		{
			session.delete("deleteSkill", employeeId);
			session.delete("deleteEmployee", employeeId);
			session.commit();
		}
		catch (Exception e)
		{
			LOGGER.error(EXCEPTION, e);
		}
	}

	/**
	 * @return
	 */
	public List<Employee> getAllEmployees()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<Employee> employeesList = session.selectList("getAllEmployees");
			session.commit();
			return employeesList;
		}
		finally
		{
			session.close();
		}
	}

	/**
	 * @param employeeId
	 * @return
	 */
	public Employee findById(int employeeId)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			Employee employee = (Employee) session.selectOne("findById", employeeId);
			session.commit();
			return employee;
		}
		finally
		{
			session.close();
		}
	}

}
