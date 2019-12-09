package com.shivsashi.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.shivsashi.entity.Employee;

public class ExcelView extends AbstractExcelView
{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HSSFSheet excelSheet = workbook.createSheet("Employee List");
		setExcelHeader(excelSheet);
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = (List<Employee>) model.get("employees");
		setExcelRows(excelSheet, employeeList);
	}

	/**
	 * @param excelSheet
	 */
	public void setExcelHeader(HSSFSheet excelSheet)
	{
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("First Name");
		excelHeader.createCell(1).setCellValue("Last Name");
		excelHeader.createCell(2).setCellValue("Age");
		excelHeader.createCell(3).setCellValue("Gender");
		excelHeader.createCell(4).setCellValue("Email");
		excelHeader.createCell(5).setCellValue("Salary");
		excelHeader.createCell(6).setCellValue("Department");
		excelHeader.createCell(7).setCellValue("State");
		excelHeader.createCell(8).setCellValue("City");
		excelHeader.createCell(9).setCellValue("Address");
		excelHeader.createCell(10).setCellValue("Skills");
	}

	/**
	 * @param excelSheet
	 * @param employeeList
	 */
	public void setExcelRows(HSSFSheet excelSheet, List<Employee> employeeList)
	{
		int record = 1;
		StringBuilder skillSet;
		StringBuilder sb;
		String skills;
		HSSFRow excelRow;
		for (Employee employee : employeeList)
		{
			excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(employee.getFirstName());
			excelRow.createCell(1).setCellValue(employee.getLastName());
			excelRow.createCell(2).setCellValue(employee.getAge());
			excelRow.createCell(3).setCellValue(employee.getGender());
			excelRow.createCell(4).setCellValue(employee.getEmail());
			excelRow.createCell(5).setCellValue(employee.getSalary());
			excelRow.createCell(6).setCellValue(employee.getDepartment());
			excelRow.createCell(7).setCellValue(employee.getState());
			excelRow.createCell(8).setCellValue(employee.getCity());
			excelRow.createCell(9).setCellValue(employee.getAddress());

			skillSet = new StringBuilder();
			for (int i = 0; i < employee.getSkills().size(); i++)
			{
				skillSet.append(employee.getSkills().get(i).getSkillName());
				skillSet.append(", ");
			}

			sb = new StringBuilder(skillSet);
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			skills = new String(sb);
			excelRow.createCell(10).setCellValue(skills);
		}
	}

}
