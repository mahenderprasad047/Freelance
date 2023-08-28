package UnitTesting;

import org.testng.annotations.Test;

import GenericUtilities.ExcelUtilities;

public class ExcelRead {

	@Test
	public void fun1()
	{
		Object[][] arr = new Object[1][1];
		arr = ExcelUtilities.getDataFromExcel("Login");
		System.out.println("Output _ " + arr[0][1]);
	}
}
