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
import Pages.ManageCategoriesPage;
import Pages.ManageCousesPage;

public class TC4_DeleteNewCourseAndCategory  extends BaseClass
{

	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void DeleteNewlyCreatedCourseAndCategories()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC4_DeleteNewCourseAndCategory");
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ManageCategoriesPage category = new ManageCategoriesPage(driver);
		ManageCousesPage course = new ManageCousesPage(driver);
		
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginEmail"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "Admin User Logged in Successfully");
		
		//Delete Courses
		Assert.assertTrue(home.NavigateToManageCoursesScreen(),"Step2 - Unable to navigate to ManageCourses screeen from home screen");
		Reporter.LogEvent(driver, "Pass", "Step2", "Admin User successfully navigated to ManageCourses screen from home screen");
		
		Reporter.LogEvent(driver, "Info", "Step3", "Delete newly created Courses");
		lst = TestDataProviderForTC.getDataFromExcel("TC5_DeleteNewCourseAndCategory","CourseDetails","ManageCourse");
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step3", "Deleting Courses - " + map.get("CourseName"));
			Assert.assertTrue(course.DeleteCourse(map.get("CourseName")),"Step3 - Unable to delete Courses with name - " + map.get("CourseName"));
			Reporter.LogEvent(driver, "Info", "Step3", "New Course is successfully deleted with name - " + map.get("CourseName"));
		}
		Reporter.LogEvent(driver, "Pass", "Step3", "All newly created courses are deleted");
		
		//Delete Categories
		Reporter.LogEvent(driver, "Info", "Step4", "Navigate to home screen");
		Assert.assertTrue(home.NavigateToHomeScreen(),"Step4 - Navigate to Home screeen");
		Reporter.LogEvent(driver, "Pass", "Step4", "Successfully navigate to Home screen from ManageCategoty screen");
		
		Assert.assertTrue(home.NavigateToManageCategoriesScreen(),"Step5 - Unable to navigate to ManageCategories screeen");
		Reporter.LogEvent(driver, "Pass", "Step5", "Step2 - Admin User successfully navigated to ManageCatefories screen");
		
		Reporter.LogEvent(driver, "Info", "Step6", "Delete newly created categories");
		lst = TestDataProviderForTC.getDataFromExcel("TC5_DeleteNewCourseAndCategory","CourseDetails","ManageCategory");
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step6", "Deletig category - " + map.get("Category"));
			Assert.assertTrue(category.DeleteCategory(map.get("Category")),"Step6 - Unable to delete category with name - " + map.get("Category"));
			Reporter.LogEvent(driver, "Info", "Step6", "New category is successfully deleted with name - " + map.get("Category"));
		}
		Reporter.LogEvent(driver, "Pass", "Step6", "All newly created categories are deleted");
		
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC4_DeleteNewCourseAndCategory");
	}
	
}
