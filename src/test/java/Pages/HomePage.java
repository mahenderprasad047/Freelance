package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.Common;
import GenericUtilities.Reporter;
import GenericUtilities.SwitchToUtility;

public class HomePage 
{

	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By eleWelcomeMessage = By.xpath("//h4[contains(text(),'Welcome')]");
	By btnManage = By.xpath("//span[text()=\"Manage\"]");
	By btnManageCategories = By.xpath("//a[@href=\"/category/manage\"]");
	//By eleMenuDropDown = By.xpath("//*[local-name()='svg' and contains(@xmlns,'/svg') and @viewBox=\"0 0 24 24\"]");
	By eleMenuDropDown = By.xpath("//img[@alt='menu']");
	By btnHome = By.xpath("//div[text()=\"Home\"]");
	By btnSignOut = By.xpath("//button[text()=\"Sign out\"]");
	By btnCart = By.xpath("//button[@Class=\"cartBtn\"]");
	
	
	public boolean UserIsAbleToLoginSuccessfully()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(eleWelcomeMessage));
			Reporter.LogEvent(driver, "Done", "Step1", "Verified Welcome message after successfull login to application");
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step1", "Unable to Verify Welcome message after successfull login to application");
			return false;
		}
	}

	public boolean NavigateToManageCategoriesScreen()
	{
		int handleIndex = driver.getWindowHandles().size();
		System.out.println("No of Handles - "+ handleIndex);
		
		Actions act = new Actions(driver);
		Common.WaitForFewSeconds(1);
		act.click(driver.findElement(btnManage)).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		SwitchToUtility.HandleWindows(driver,"Index",String.valueOf(handleIndex));
	
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.urlContains("category/manage"));
			Common.WaitForFewSeconds(1);
			Reporter.LogEvent(driver, "Done", "-", "Successfully Navigate to ManageCategories screen");
			act.moveToElement(driver.findElement(btnCart)).build().perform();	//to close the manage dropdown
			Common.WaitForFewSeconds(1);
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "-", "Unable to navigate to ManageCategories screen");
			return false;
		}
	}
	
	public boolean NavigateToManageCoursesScreen()
	{
		Actions act = new Actions(driver);
		Common.WaitForFewSeconds(1);
		act.click(driver.findElement(btnManage)).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		act.moveToElement(driver.findElement(btnCart)).build().perform();	//to close the manage dropdown
		
		if(driver.getCurrentUrl().contains("course/manage"))
		{
			Reporter.LogEvent(driver, "Done", "Step2", "Successfully Navigate to ManageCouses screen");
			return true;
		}
		else
		{
			Reporter.LogEvent(driver, "Fail", "Step2", "Unable to navigate to ManageCourses screen");
			return false;
		}
	}

	public boolean NavigateToHomeScreen()
	{
		Common.WaitForFewSeconds(2);
		driver.findElement(eleMenuDropDown).click();
		Common.WaitForFewSeconds(1);
		driver.findElement(btnHome).click();
		return true;
	}
	
	
	public boolean LogOutFromApplication()
	{
		Common.WaitForFewSeconds(2);
		driver.findElement(eleMenuDropDown).click();
		Common.WaitForFewSeconds(1);
		driver.findElement(btnSignOut).click();
		//WebElementRelatedUtility.ClickUsingJavaScript(driver, driver.findElement(btnSignOut));
		return true;
	}
	
	
	
	
}
