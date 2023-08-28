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
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ManageCousesPage;

public class TC3_AddCourse extends BaseClass
{
	
	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void AddCourse()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC3_AddCourse");
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginEmail"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "Admin User Logged in Successfully");
		
		Assert.assertTrue(home.NavigateToManageCoursesScreen(),"Unable to navigate to ManageCourses screeen");
		Reporter.LogEvent(driver, "Pass", "Step2", "Admin User successfully navigated to ManageCourses screen");
		
		//Add courser
		lst = TestDataProviderForTC.getDataFromExcel("TC3_AddCourse","CourseDetails","ManageCourse");
		ManageCousesPage course = new ManageCousesPage(driver);
		
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step3", "Adding a new course with name - " + map.get("CourseName"));
			Assert.assertTrue(course.AddCourse(map),"Step3 - Unable to add a new course with name - " + map.get("CourseName"));
			Reporter.LogEvent(driver, "Info", "Step3", "New course is successfully added with name - " + map.get("CourseName"));
		}
		
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC3_AddCourse");
	}
	
}
