package TestCases;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.Reporter;
import GenericUtilities.TestDataProviderForTC;
import Pages.LoginPage;
import Pages.SignUpPage;

public class TC1_CreateUser extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void CreateUsers() throws InterruptedException 
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC1_CreateUsers");
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		int i;
		
		LoginPage login = new LoginPage(driver);
		SignUpPage signUp  = new SignUpPage(driver);
		lst = TestDataProviderForTC.getDataFromExcel("TC1_CreateUser","UserDetails","NewUser");
		List<String> lstRandomValue = new ArrayList<String>();
		String GeneratedRandomValue;
		for(i=0;i<lst.size();i++)
		{
			GeneratedRandomValue = "";
			login.ClickOnNewUserLink();
			Assert.assertTrue(driver.getCurrentUrl().contains("signup"),"Unable to navigate to SignUp page");
			Reporter.LogEvent(driver, "Pass", "Step1", "Successfully Navigated to SignUp page");
			
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step2", "Adding a new user with username - "+ map.get("Name"));
			GeneratedRandomValue = signUp.AddNewUser(map);
			lstRandomValue.add(GeneratedRandomValue);
			Assert.assertTrue(signUp.VerifyUserAddedSuccessfully(driver),"Step2 - User not added successfully");
			Reporter.LogEvent(driver, "Pass", "Step2", "User is added with username - "+ map.get("Name"));
		}
		
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC1_CreateUsers");
		
		TestDataProviderForTC.SetDataToAColumnOfExcel("TC1_CreateUser","UserDetails","NewUser", lstRandomValue, "RandomValue");
		Reporter.LogEvent(driver, "Info", "", "Writing data into -UserDetails- sheet for testcase - TC1_CreateUser");
	}
}
