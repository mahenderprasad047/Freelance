package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import GenericUtilities.Common;

public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By lnkNewUser = By.xpath("//a[contains(text(),'Signup')]");
	By edtEmail = By.xpath("//input[@id=\"email1\"]");
	By edtPassword = By.xpath("//input[@id=\"password1\"]");
	By btnSignIn = By.xpath("//button[@type=\"submit\"]");
	
	public WebDriver ClickOnNewUserLink()
	{
		Common.WaitForFewSeconds(1);
		driver.findElement(lnkNewUser).click();
		Common.WaitForFewSeconds(1);
		return driver;
	}
	
	public WebDriver LoginToApplication(String email, String password)
	{
		driver.findElement(edtEmail).sendKeys(email);
		driver.findElement(edtPassword).sendKeys(password);
		Common.WaitForFewSeconds(1);
		driver.findElement(btnSignIn).click();
		//WebElementRelatedUtility.ClickUsingJavaScript(driver, driver.findElement(btnSignIn));
		return driver;
	}
	
	
	
	
	

}
