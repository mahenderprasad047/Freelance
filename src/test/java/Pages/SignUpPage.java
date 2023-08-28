package Pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.Common;
import GenericUtilities.GenerateRandomValue;
import GenericUtilities.Reporter;
import GenericUtilities.WebElementRelatedUtility;

public class SignUpPage 
{
	WebDriver driver;
	public SignUpPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By edtName = By.xpath("//input[@id=\"name\"]");
	By edtEmail = By.xpath("//input[@id=\"email\"]");
	By edtPassword = By.xpath("//input[@id=\"password\"]");
	By chkWebDevelopment = By.xpath("//label[text()=\"WEB DEVELOPMENT\"]/..//input");
	By chkAppDevelopment = By.xpath("//label[text()=\"App Development\"]/..//input");
	By chkAutomationTesting = By.xpath("//label[text()=\"Automation Testing\"]/..//input");
	By chkPerformanceTesting = By.xpath("//label[text()=\"Performance Testing\"]/..//input");
	By chkCypress = By.xpath("//label[text()=\"Cypress\"]/..//input");
	By chkWindowTesting = By.xpath("//label[text()=\"Window Testing\"]/..//input");
	By chkUIAutomation = By.xpath("//label[text()=\"UI Automation\"]/..//input");
	By chkInternationCricket = By.xpath("//label[text()=\"InternationCricket\"]/..//input");
	By rdgMale = By.xpath("//input[@value=\"Male\"]");
	By rdgFemale = By.xpath("//input[@value=\"Female\"]");
	By lstState = By.xpath("//select[@id=\"state\"]");
	By lstHobbies = By.xpath("//select[@id=\"hobbies\"]");
	By btnSignUp = By.xpath("//button[text()=\"Sign up\"]");
	By eleSignUpSuccessfulMessage = By.xpath("//div[text()=\"Signup successfully, Please login!\"]");
			
	
	public String AddNewUser(Map<String,String> map)
	{
		Common.WaitForFewSeconds(2);
		String randomName = GenerateRandomValue.GenerateRandomString(7);
		driver.findElement(edtName).sendKeys(randomName + " " + map.get("Name"));
		driver.findElement(edtEmail).sendKeys(randomName + map.get("email"));
		driver.findElement(edtPassword).sendKeys(randomName + map.get("Password"));
		
		String[] sTemp = map.get("Interest").split(",");
		for(String str : sTemp)
		{
			if(str.equalsIgnoreCase("WEB DEVELOPMENT"))
				driver.findElement(chkWebDevelopment).click();
			else if(str.equalsIgnoreCase("App Development"))
				driver.findElement(chkAppDevelopment).click();
			else if(str.equalsIgnoreCase("Automation Testing"))
				driver.findElement(chkAutomationTesting).click();
			else if(str.equalsIgnoreCase("Performance Testing"))
				driver.findElement(chkPerformanceTesting).click();
			else if(str.equalsIgnoreCase("Cypress"))
				driver.findElement(chkCypress).click();
			else if(str.equalsIgnoreCase("Window Testing"))
				driver.findElement(chkWindowTesting).click();
			else if(str.equalsIgnoreCase("UI Automation"))
				driver.findElement(chkUIAutomation).click();
			else if(str.equalsIgnoreCase("InternationCricket"))
				driver.findElement(chkInternationCricket).click();
			else
				driver.findElement(By.xpath("//label[contains(text(),\""+ str + "\")]/..//input")).click();
		}
		
		if(map.get("Interest").equals("Male"))
			WebElementRelatedUtility.PerformClick(driver, driver.findElement(rdgMale));
		else
			WebElementRelatedUtility.PerformClick(driver, driver.findElement(rdgFemale));
		
		WebElementRelatedUtility.SelectFromDropDown(driver, driver.findElement(lstState), map.get("State"));
		WebElementRelatedUtility.SelectFromDropDown(driver, driver.findElement(lstHobbies), map.get("Hobbies"));
		Common.WaitForFewSeconds(2);
		Reporter.LogEvent(driver, "Done", "-", "User details entered for user - "+ map.get("Name"));
		//WebElementRelatedUtility.PerformClick(driver, driver.findElement(btnSignUp));
		WebElementRelatedUtility.ClickUsingJavaScript(driver, driver.findElement(btnSignUp));
		Common.WaitForFewSeconds(1);
		
		
		return randomName;
	}
	
	public boolean VerifyUserAddedSuccessfully(WebDriver driver)
	{
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(eleSignUpSuccessfulMessage)));
			Reporter.LogEvent(driver, "Done", "Step2", "Verified message - Signup successfully, Please login!");
			return true;
		}catch(Exception e)
		{
			Reporter.LogEvent(driver, "Fail", "Step2", "Unable to Verify message - Signup successfully, Please login!");
			return false;
		}
		
	}
}
