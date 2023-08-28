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
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ManageCategoriesPage;
import Pages.ManageCousesPage;
import Pages.SignUpPage;

public class TC5_ShopProduct extends BaseClass
{

	public WebDriver driver;

	@BeforeMethod
	public void setupDriver()
	{
		driver = getDriver();
	}
	
	@Test
	public void ShopProduct()
	{
		Reporter.LogEvent(driver, "Info", "", "Start of Execution  - TC5_ShopProduct");
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		String GeneratedRandomValue = null,newUserEmailID,newUserPassword;
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ManageCategoriesPage category = new ManageCategoriesPage(driver);
		ManageCousesPage course = new ManageCousesPage(driver);
		SignUpPage signUp  = new SignUpPage(driver);
		CartPage cart = new CartPage(driver);
	
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginEmail"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step1 - User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step1", "Admin User Logged in Successfully");
		
		Assert.assertTrue(home.NavigateToManageCategoriesScreen(),"Unable to navigate to ManageCategories screeen");
		Reporter.LogEvent(driver, "Pass", "Step2", "Step2 - Admin User successfully navigated to ManageCatefories screen");
		
		
		//Add Category
		lst = TestDataProviderForTC.getDataFromExcel("TC4_ShopProduct","CourseDetails","ManageCategory");
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step3", "Adding a new category with name - " + map.get("Category"));
			Assert.assertTrue(category.AddCategory(map.get("Category")),"Step3 - Unable to add a new category with name - " + map.get("Category"));
			Reporter.LogEvent(driver, "Info", "Step3", "New category is successfully added with name - " + map.get("Category"));
		}
		Reporter.LogEvent(driver, "Pass", "Step3", "All new category are added");
		
		//Navigate back to HomePage
		Assert.assertTrue(home.NavigateToHomeScreen(),"Step4 - Navigate to Home screeen");
		Reporter.LogEvent(driver, "Pass", "Step4", "Successfully navigate to Home screen from ManageCategoty screen");
		
		Assert.assertTrue(home.NavigateToManageCoursesScreen(),"Step5 - Unable to navigate to ManageCourses screeen from home screen");
		Reporter.LogEvent(driver, "Pass", "Step5", "Admin User successfully navigated to ManageCourses screen from home screen");
		
		//Add course
		lst = TestDataProviderForTC.getDataFromExcel("TC4_ShopProduct","CourseDetails","ManageCourse");
		
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step6", "Adding a new course with name - " + map.get("CourseName"));
			Assert.assertTrue(course.AddCourse(map),"Step6 - Unable to add a new course with name - " + map.get("CourseName"));
			Reporter.LogEvent(driver, "Info", "Step6", "New course is successfully added with name - " + map.get("CourseName"));
		}
		
		//LogOut From Application
		Assert.assertTrue(home.LogOutFromApplication(),"Step7 - LogOut from Application");
		Reporter.LogEvent(driver, "Pass", "Step7", "LogOut from Application");
		
		//Create user
		lst = TestDataProviderForTC.getDataFromExcel("TC4_ShopProduct","UserDetails","NewUser");
		List<String> lstRandomValue = new ArrayList<String>();
		for(int i=0;i<lst.size();i++)
		{
			GeneratedRandomValue = "";
			login.ClickOnNewUserLink();
			Assert.assertTrue(driver.getCurrentUrl().contains("signup"),"Unable to navigate to SignUp page");
			Reporter.LogEvent(driver, "Pass", "Step8", "Successfully Navigated to SignUp page");
			
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step8", "Adding a new user with username - "+ map.get("Name"));
			GeneratedRandomValue = signUp.AddNewUser(map);
			lstRandomValue.add(GeneratedRandomValue);
			Assert.assertTrue(signUp.VerifyUserAddedSuccessfully(driver), "Step9 - User not added successfully");
			Reporter.LogEvent(driver, "Pass", "Step8", "User is added with username - "+ map.get("Name"));
		}
		
		TestDataProviderForTC.SetDataToAColumnOfExcel("TC4_ShopProduct","UserDetails","NewUser", lstRandomValue, "RandomValue");
		Reporter.LogEvent(driver, "Pass", "Step9", "Writing data into -UserDetails- sheet for testcase - TC4_ShopProduct");
		
		
		//Login to application as new user - From here only for single user, course
		newUserEmailID = "vmjypgkMS03@gmail.com";	//GeneratedRandomValue + map.get("email");
		newUserPassword = "vmjypgkMahen03";		//GeneratedRandomValue + map.get("Password");
		Reporter.LogEvent(driver, "Info", "Step10", "Login to application as New user");
		Reporter.LogEvent(driver, "Info", "Step10", "New User Login emailId - " + newUserEmailID);
		Reporter.LogEvent(driver, "Info", "Step10", "New User Login Password - " + newUserPassword);
		login.LoginToApplication(newUserEmailID, newUserPassword);
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"Step10 - New User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step10", "New User Logged in Successfully");
		
		//Add the course
		Reporter.LogEvent(driver, "Info", "Step11", "Add course to New user");
		lst = TestDataProviderForTC.getDataFromExcel("TC4_ShopProduct","CourseDetails","ManageCourse");
		map = lst.get(0);
		Assert.assertTrue(cart.AddToCart(map),"Step11 - Unable to add course for new user");
		Reporter.LogEvent(driver, "Pass", "Step11", "Course added for new user Successfully");
		
		//Enroll Now
		String orderID = null;
		List<String> lstOrderId = new ArrayList<String>();
		Reporter.LogEvent(driver, "Info", "Step12", "Enroll for the course to New user");
		orderID = cart.EnrollForTheCourse(map);
		lstOrderId.add(orderID);
		Assert.assertNotNull(orderID,"Step12 - Unable to enroll for the course for new user");
		Reporter.LogEvent(driver, "Pass", "Step12", "Course added for new user Successfully");
		
		TestDataProviderForTC.SetDataToAColumnOfExcel("TC4_ShopProduct","CourseDetails","ManageCourse", lstOrderId, "OrderID");
		Reporter.LogEvent(driver, "Pass", "Step13", "Writing UserID into -UserDetails- sheet for testcase - TC4_ShopProduct");
		
		//LogOut From Application
		Assert.assertTrue(home.LogOutFromApplication(),"Step13_1 - LogOut from Application");
		Reporter.LogEvent(driver, "Pass", "Step13_1", "LogOut from Application");
		
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginEmail"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		
		Assert.assertTrue(home.UserIsAbleToLoginSuccessfully(),"User is NOT logged in successfully");
		Reporter.LogEvent(driver, "Pass", "Step13", "Admin User Logged in Successfully");
		
		//Delete Courses
		Assert.assertTrue(home.NavigateToManageCoursesScreen(),"Step14 - Unable to navigate to ManageCourses screeen from home screen");
		Reporter.LogEvent(driver, "Pass", "Step14", "Admin User successfully navigated to ManageCourses screen from home screen");
		
		Reporter.LogEvent(driver, "Info", "Step15", "Delete newly created Courses");
		lst = TestDataProviderForTC.getDataFromExcel("TC4_ShopProduct","CourseDetails","ManageCourse");
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step15", "Deleting Courses - " + map.get("CourseName"));
			Assert.assertTrue(course.DeleteCourse(map.get("CourseName")),"Step15 - Unable to delete category with name - " + map.get("CourseName"));
			Reporter.LogEvent(driver, "Info", "Step15", "New category is successfully deleted with name - " + map.get("CourseName"));
		}
		Reporter.LogEvent(driver, "Pass", "Step15", "All newly created courses are deleted");
		
		//Delete Categories
		Assert.assertTrue(home.NavigateToHomeScreen(),"Step16 - Navigate to Home screeen");
		Reporter.LogEvent(driver, "Pass", "Step16", "Successfully navigate to Home screen from ManageCategoty screen");
		
		Assert.assertTrue(home.NavigateToManageCategoriesScreen(),"Step5 - Unable to navigate to ManageCategories screeen");
		Reporter.LogEvent(driver, "Pass", "Step17", "Step17 - Admin User successfully navigated to ManageCatefories screen");
		
		Reporter.LogEvent(driver, "Info", "Step18", "Delete newly created categories");
		lst = TestDataProviderForTC.getDataFromExcel("TC4_ShopProduct","CourseDetails","ManageCategory");
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Reporter.LogEvent(driver, "Info", "Step18", "Deletig category - " + map.get("Category"));
			Assert.assertTrue(category.DeleteCategory(map.get("Category")),"Step18 - Unable to delete category with name - " + map.get("Category"));
			Reporter.LogEvent(driver, "Info", "Step18", "New category is successfully deleted with name - " + map.get("Category"));
		}
		Reporter.LogEvent(driver, "Pass", "Step18", "All newly created categories are deleted");
		
		Reporter.LogEvent(driver, "Info", "", "End of Execution  - TC5_ShopProduct");
	}
}
