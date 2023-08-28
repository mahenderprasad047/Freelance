package Pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import GenericUtilities.Common;
import GenericUtilities.GenerateRandomValue;
import GenericUtilities.Reporter;
import GenericUtilities.WebElementRelatedUtility;

public class ManageCousesPage {

	WebDriver driver;
	public ManageCousesPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By btnAddNewCourse = By.xpath("//button[text()=\"Add New Course \"]");
	By btnThumnail = By.xpath("//input[@id=\"thumbnail\"]");
	
	By edtCourseName = By.xpath("//input[@id=\"name\"]");
	By edtCourseDescription = By.xpath("//textarea[@id=\"description\"]");
	By edtInstructor = By.xpath("//input[@id=\"instructorNameId\"]");
	By edtPrice = By.xpath("//input[@id=\"price\"]");
	By edtStartsFrom = By.xpath("//input[@name=\"startDate\"]");
	By edtEndsOn = By.xpath("//input[@name=\"endDate\"]");
	By eleSelectCategory = By.xpath("//div[text()=\"Select Category\"]");
	By btnSave = By.xpath("//button[text()=\"Save\"]");
	
	By eleCurrentMonthYear = By.xpath("//div[contains(@class,\"current-month\")]");
	
	public boolean AddCourse(Map<String,String> map)
	{
		String randomString = GenerateRandomValue.GenerateRandomString(4);
		String courseName = map.get("CourseName") + " "+ randomString;
		Common.WaitForFewSeconds(2);
		driver.findElement(btnAddNewCourse).click();
		Common.WaitForFewSeconds(1);
		WebElement ChooseFile = driver.findElement(btnThumnail);
		String filePath = System.getProperty("user.dir") + "\\TestData\\Thumbnail\\"+ map.get("ThumbnailFile")+".JPG";
		ChooseFile.sendKeys(filePath);
		
		driver.findElement(edtCourseName).sendKeys(courseName);
		driver.findElement(edtCourseDescription).sendKeys(map.get("CourseDescription"));
		driver.findElement(edtInstructor).sendKeys(map.get("Instructor"));
		driver.findElement(edtPrice).sendKeys(map.get("Price"));
		selectDate(driver.findElement(edtStartsFrom),map.get("StartsFrom"));
		Common.WaitForFewSeconds(1);
		selectDate(driver.findElement(edtEndsOn),map.get("EndsOn"));
		Common.WaitForFewSeconds(1);
		driver.findElement(eleSelectCategory).click();
		Common.WaitForFewSeconds(1);
		driver.findElement(By.xpath("//button[contains(text(),\""+ map.get("Category") + "\")]")).click();
		Common.WaitForFewSeconds(1);
		Reporter.LogEvent(driver, "Done", "-", "Before adding a course with course Name - " + courseName);
		Common.WaitForFewSeconds(1);
		driver.findElement(btnSave).click();
		Actions act = new Actions(driver);
		
		try {
			act.scrollToElement(driver.findElement(By.xpath("//td[text()=\"" + courseName +"\"]"))).build().perform();
		}catch(Exception e)
		{
			System.out.println("Non-Harm exception occurs while scorlling"+ e.getMessage());
		}
		Common.WaitForFewSeconds(3);
		Reporter.LogEvent(driver, "Done", "-", "New Category is added. CategoryName - " + courseName);
		driver.navigate().refresh();
		
		Reporter.LogEvent(driver, "Done", "-", "After adding a course with course Name - " + courseName);
		Common.WaitForFewSeconds(1);
		
		
		return true;
	}
	
	public void selectDate(WebElement ele, String dateToBeSelected)
	{
		String[] temp1 = dateToBeSelected.split("/");
		String date,month,year;
		
		if(Integer.parseInt(temp1[1]) < 10)
		{
			date =temp1[1].substring(1, 2);
		}
		else
		{
			date = temp1[1];
		}
		
		 String months[] = {"January", "February", "March", "April",
                 "May", "June", "July", "August", "September",
                 "October", "November", "December"};
		 month = months[Integer.parseInt(temp1[0])-1];
		
		 year = temp1[2];
		 System.out.println("Date to be selected - " + date + "/" + month +"/"+year);
		 
		 ele.click();
		 
		 String CurrentMonthYear= driver.findElement(eleCurrentMonthYear).getText();
		 String temp2[] = CurrentMonthYear.split(" ");
		 if(temp2[1].equalsIgnoreCase(year))
		 {
			 if(temp2[0].equalsIgnoreCase(month))
			 {
				 driver.findElement(By.xpath("//div[text()=\""+ date + "\"]")).click();
			 }
			 else
			 {
				 driver.findElement(By.xpath("//div[text()=\""+ date + "\"]")).click();	//Need to Update this section
			 }
		 }
		 else
		 {
			 driver.findElement(By.xpath("//div[text()=\""+ date + "\"]")).click();	//Need to Update this section 
		 }

	}
	
	public boolean DeleteCourse(String courseName)
	{
		WebElement ele;
		Common.WaitForFewSeconds(1);
		try {
			ele = driver.findElement(By.xpath("//table//td[contains(text(),\""+ courseName + "\")]"));
			Reporter.LogEvent(driver, "Info", "-", "Course found for deletion. courseName - " + ele.getText());
		}catch(Exception e)
		{
			if(e.getClass().toString().contains("NoSuchElementException"))
			{
				Reporter.LogEvent(driver, "Info", "-", "Course is not available for deltetion. courseName - " + courseName);
				return true;
			}
			else
			{
				Reporter.LogEvent(driver, "Info", "-", "Unable to delete the course.courseName - " + courseName);
				return false;
			}
			
		}
		
		ele = driver.findElement(By.xpath("//table//td[contains(text(),\""+ courseName + "\")]//following::button"));
		WebElementRelatedUtility.ClickUsingJavaScript(driver, ele);
		Common.WaitForFewSeconds(1);
		
		
		return true;
	}
}
