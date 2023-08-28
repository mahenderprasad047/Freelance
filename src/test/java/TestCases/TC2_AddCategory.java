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
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.Reporter;
import GenericUtilities.TestDataProviderForTC;
import Pages.LoginPage;
import Pages.ManageCategoriesPage;
import Pages.HomePage;

public class TC2_AddCategory extends BaseClass
{
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void AddCategory()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC2_AddCategory");
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginEmail"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step1 - User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "Admin User Logged in Successfully");
		
		Assert.assertTrue(home.NavigateToManageCategoriesScreen(),"Unable to navigate to ManageCategories screeen");
		Reporter.LogEvent(driver, "Pass", "Step2", "Step2 - Admin User successfully navigated to ManageCatefories screen");
		
		
		//Add Category
		lst = TestDataProviderForTC.getDataFromExcel("TC2_AddCategory","CourseDetails","ManageCategory");
		ManageCategoriesPage category = new ManageCategoriesPage(driver);
		
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step3", "Adding a new category with name - " + map.get("Category"));
			Assert.assertTrue(category.AddCategory(map.get("Category")),"Step3 - Unable to add a new category with name - " + map.get("Category"));
			Reporter.LogEvent(driver, "Info", "Step3", "New category is successfully added with name - " + map.get("Category"));
		}
		Reporter.LogEvent(driver, "Pass", "Step3", "All new category are added");
		
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC1_CreateUsers");
		
		
		
	}
	
	
}
