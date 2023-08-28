package BusinessUtilities;

import org.testng.annotations.DataProvider;

import GenericUtilities.ExcelUtilities;

public class DataProvidersForTCs 
{

	@DataProvider(name="login")
	public static Object[][] getTestData()
	{
		return ExcelUtilities.getDataFromExcel("Login");
	}
	
}
