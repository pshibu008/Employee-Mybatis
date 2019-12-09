package com.shivsashi.importexcel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shivsashi.entity.Employee;

public class ExcelHelper
{
	private static final Logger	LOGGER					= LoggerFactory.getLogger(ExcelHelper.class);

	private static final String	EXCEPTION				= "Exception";

	private static final String	NAMEREGEX				= "[a-zA-Z ,]+";

	private static final String	CITYVALIDATIONMESSAGE	= "Entered city is not according to the State";

	List<Employee>				employeeList			= new ArrayList<>();

	Workbook					workBook				= null;

	/**
	 * @param workBookName
	 * @return
	 */
	public List<Employee> initialize(String workBookName)
	{
		Properties properties = new Properties();
		try (InputStream inputStream = ExcelHelper.class.getResourceAsStream("/config.properties");)
		{
			properties.load(inputStream);
		}
		catch (IOException e1)
		{
			LOGGER.error(EXCEPTION, e1);
		}
		String filePath = properties.getProperty("filePath");

		try (FileInputStream fileInputStream = new FileInputStream(filePath);)
		{
			LOGGER.info(workBookName);
			workBook = WorkbookFactory.create(fileInputStream);
			Sheet sheet = workBook.getSheetAt(0);
			int noOfRows = sheet.getLastRowNum();
			int oldCellCount = 0;
			int newCellCount = 0;
			int id = 0;
			String operation = null;
			String firstName = null;
			String lastName = null;
			int age = 0;
			String gender = null;
			int salary = 0;
			String department = null;
			String state = null;
			String city = null;
			String address = null;
			String email = null;
			String skills = null;
			Employee employee = null;
			for (int j = 1; j <= noOfRows; j++)
			{
				employee = new Employee();
				oldCellCount = sheet.getRow(j).getLastCellNum();
				newCellCount = validateCell(sheet, j);
				if (oldCellCount == newCellCount)
				{
					operation = sheet.getRow(j).getCell(0).getStringCellValue();
					if (operation.equalsIgnoreCase("ADD") || operation.equalsIgnoreCase("MOD"))
					{
						if (sheet.getRow(j).getCell(1) == null)
						{
							id = 0;
						}
						else
						{
							id = (int) sheet.getRow(j).getCell(1).getNumericCellValue();
						}
						firstName = sheet.getRow(j).getCell(2).getStringCellValue();
						lastName = sheet.getRow(j).getCell(3).getStringCellValue();
						age = (int) sheet.getRow(j).getCell(4).getNumericCellValue();
						gender = sheet.getRow(j).getCell(5).getStringCellValue();
						salary = (int) sheet.getRow(j).getCell(6).getNumericCellValue();
						department = sheet.getRow(j).getCell(7).getStringCellValue();
						state = sheet.getRow(j).getCell(8).getStringCellValue();
						city = sheet.getRow(j).getCell(9).getStringCellValue();
						if (sheet.getRow(j).getCell(10) == null)
						{
							address = "";
						}
						else
						{
							address = sheet.getRow(j).getCell(10).getStringCellValue();
						}
						email = sheet.getRow(j).getCell(11).getStringCellValue();
						skills = sheet.getRow(j).getCell(12).getStringCellValue();

						employee.setOperation(operation);
						employee.setEmployeeId(id);
						employee.setFirstName(firstName);
						employee.setLastName(lastName);
						employee.setAge(age);
						employee.setGender(gender);
						employee.setSalary(salary);
						employee.setDepartment(department.toUpperCase());
						employee.setState(state);
						employee.setCity(city);
						employee.setAddress(address);
						employee.setEmail(email);
						employee.setSkillSet(skills);

						employeeList.add(employee);
					}
					else
					{
						id = (int) sheet.getRow(j).getCell(1).getNumericCellValue();
						employee.setOperation(operation);
						employee.setEmployeeId(id);
						employeeList.add(employee);
					}
				}
			}
		}
		catch (EncryptedDocumentException | IOException e)
		{
			LOGGER.error(EXCEPTION, e);
		}
		try (FileOutputStream out = new FileOutputStream(filePath);)
		{
			workBook.write(out);
		}
		catch (Exception e)
		{
			LOGGER.error(EXCEPTION, e);
		}

		return employeeList;
	}

	/**
	 * @param sheet
	 * @param row
	 * @return
	 */
	private int validateCell(Sheet sheet, int row)
	{
		int totalColumnNumber = sheet.getRow(row).getLastCellNum();
		if (sheet.getRow(row).getCell(2) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("FirstName cannot be empty.");
			cell.setCellValue("FirstName cannot be empty.");
		}
		else if (sheet.getRow(row).getCell(2).getCellType() != CellType.STRING)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("FirstName must contains Characters only.");
			cell.setCellValue("FirstName must be contains Characters only.");
		}
		else if (sheet.getRow(row).getCell(2).getStringCellValue().matches(NAMEREGEX)
				&& (sheet.getRow(row).getCell(2).getStringCellValue().length() < 3 || sheet.getRow(row).getCell(2).getStringCellValue().length() > 10))
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("First Name should be of length 3 and 10 only.");
			cell.setCellValue("First Name should be of length 3 and 10 only.");
		}

		if (sheet.getRow(row).getCell(3) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("LastName cannot be empty.");
			cell.setCellValue("LastName cannot be empty.");
		}
		else if (sheet.getRow(row).getCell(3).getCellType() != CellType.STRING)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("LastName must contains Characters only.");
			cell.setCellValue("LastName must be contains Characters only.");
		}
		else if (sheet.getRow(row).getCell(3).getStringCellValue().matches(NAMEREGEX)
				&& (sheet.getRow(row).getCell(3).getStringCellValue().length() < 3 || sheet.getRow(row).getCell(3).getStringCellValue().length() > 10))
		{

			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Last Name should be of length 3 and 10 only.");
			cell.setCellValue("Last Name should be of length 3 and 10 only.");
		}

		if (sheet.getRow(row).getCell(4) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Age cannot be empty.");
			cell.setCellValue("Age cannot be empty.");
		}
		else if (sheet.getRow(row).getCell(4).getCellType() != CellType.NUMERIC)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Age must contains Numeric value only.");
			cell.setCellValue("Age must contains Numeric value only.");
		}
		else if (sheet.getRow(row).getCell(4).getCellType() == CellType.NUMERIC
				&& ((int) sheet.getRow(row).getCell(4).getNumericCellValue() < 17 || (int) sheet.getRow(row).getCell(4).getNumericCellValue() > 99))
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Age must be of two digit only and greater than 17");
			cell.setCellValue("Age must be of two digit only and greater than 17");
		}

		if (sheet.getRow(row).getCell(6) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Salary cannot be empty.");
			cell.setCellValue("Salary cannot be empty.");
		}
		else if (sheet.getRow(row).getCell(6).getCellType() != CellType.NUMERIC)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Salary must contains Numeric value only.");
			cell.setCellValue("Salary must contains Numeric value only.");
		}
		else if (sheet.getRow(row).getCell(6).getCellType() == CellType.NUMERIC && (int) sheet.getRow(row).getCell(6).getNumericCellValue() < 9999)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Salary must be greater than 4 digit");
			cell.setCellValue("Salary must be greater than 4 digit");
		}

		if (sheet.getRow(row).getCell(7) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Department cannot be empty.");
			cell.setCellValue("Department cannot be empty.");
		}
		else if (sheet.getRow(row).getCell(7).getCellType() != CellType.STRING)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Department must contains Characters only.");
			cell.setCellValue("Department must be contains Characters only.");
		}
		else if (sheet.getRow(row).getCell(7).getStringCellValue().matches(NAMEREGEX))
		{
			String[] deptArr = { "CSE", "IT", "EC" };
			Cell cell = null;
			if (!Arrays.asList(deptArr).contains(sheet.getRow(row).getCell(7).getStringCellValue().toUpperCase()))
			{
				cell = sheet.getRow(row).createCell(totalColumnNumber++);
				LOGGER.info("Department should be in [CSE,EC,IT] only");
				cell.setCellValue("Department should be in [CSE,EC,IT] only.");
			}
		}

		if (sheet.getRow(row).getCell(8) == null || sheet.getRow(row).getCell(9) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("State or City cannot be empty.");
			cell.setCellValue("State or City cannot be empty.");
		}
		else if (sheet.getRow(row).getCell(8).getCellType() != CellType.STRING || sheet.getRow(row).getCell(9).getCellType() != CellType.STRING)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("State and City must contains Characters only.");
			cell.setCellValue("State and City must be contains Characters only.");
		}
		else if (sheet.getRow(row).getCell(8).getStringCellValue().matches(NAMEREGEX) || sheet.getRow(row).getCell(9).getStringCellValue().matches(NAMEREGEX))
		{
			String[] stateArr = { "Karnataka", "Bihar", "UttarPradesh" };
			String[] biharArr = { "Patna", "Gaya" };
			String[] upArr = { "Lucknow", "Agra" };
			String[] karArr = { "Bangalore", "Vijaypura" };

			String state = sheet.getRow(row).getCell(8).getStringCellValue();
			String city = sheet.getRow(row).getCell(9).getStringCellValue();
			Cell cell = null;
			if (!Arrays.asList(stateArr).contains(state))
			{
				cell = sheet.getRow(row).createCell(totalColumnNumber++);
				LOGGER.info("State must be in [Karnataka, Bihar, UttarPradesh]");
				cell.setCellValue("State must be in [Karnataka, Bihar, UttarPradesh]");
			}
			else if (state.equalsIgnoreCase("Karnataka"))
			{
				if (!Arrays.asList(karArr).contains(city))
				{
					cell = sheet.getRow(row).createCell(totalColumnNumber++);
					LOGGER.info(CITYVALIDATIONMESSAGE);
					cell.setCellValue(CITYVALIDATIONMESSAGE);
				}
			}
			else if (state.equalsIgnoreCase("Bihar"))
			{
				if (!Arrays.asList(biharArr).contains(city))
				{
					cell = sheet.getRow(row).createCell(totalColumnNumber++);
					LOGGER.info(CITYVALIDATIONMESSAGE);
					cell.setCellValue(CITYVALIDATIONMESSAGE);
				}
			}
			else if (state.equalsIgnoreCase("UttarPradesh") && !Arrays.asList(upArr).contains(city))
			{
				cell = sheet.getRow(row).createCell(totalColumnNumber++);
				LOGGER.info(CITYVALIDATIONMESSAGE);
				cell.setCellValue(CITYVALIDATIONMESSAGE);
			}

		}

		String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(emailRegex);
		if (sheet.getRow(row).getCell(11) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Email cannot be empty.");
			cell.setCellValue("Email cannot be empty.");
		}
		else if (!pattern.matcher(sheet.getRow(row).getCell(11).getStringCellValue()).matches())
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Not a valid Email.");
			cell.setCellValue("Not a valid Email.");
		}

		if (sheet.getRow(row).getCell(12) == null)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Skill cannot be empty.");
			cell.setCellValue("Skill cannot be empty.");
		}
		else if (sheet.getRow(row).getCell(12).getCellType() != CellType.STRING)
		{
			Cell cell = sheet.getRow(row).createCell(totalColumnNumber++);
			LOGGER.info("Skill must contains Characters only.");
			cell.setCellValue("Skill must be contains Characters only.");
		}
		else if (sheet.getRow(row).getCell(12).getStringCellValue().matches(NAMEREGEX))
		{
			String[] skillArr = { "JAVA", "SQL", "J2EE", "SPRING" };
			String[] skills = sheet.getRow(row).getCell(12).getStringCellValue().toUpperCase().split(", ");

			Cell cell = null;
			for (int i = 0; i < skills.length; i++)
			{
				if (!Arrays.asList(skillArr).contains(skills[i]))
				{
					cell = sheet.getRow(row).createCell(totalColumnNumber++);
					LOGGER.info("Skill is in [JAVA,SQL,J2EE,SPRING]");
					cell.setCellValue("Skill is in [JAVA,SQL,J2EE,SPRING]");
				}
			}

		}
		return totalColumnNumber;
	}
}
