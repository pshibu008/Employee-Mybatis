package com.shivsashi.decorator;

import org.displaytag.decorator.TableDecorator;

import com.shivsashi.entity.Employee;

public class EmployeeDecorator extends TableDecorator
{
	/**
	 * @return
	 */
	public String getFirstName()
	{
		Employee employee = (Employee) getCurrentRowObject();

		return "<a href=\"#\" onclick=\"showEmpDetails(" + employee.getEmployeeId() + ")\" style=\" text-decoration:none; color: black;\">" + employee.getFirstName() + "</a>";
	}
}
