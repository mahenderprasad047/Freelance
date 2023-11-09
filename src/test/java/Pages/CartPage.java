package Pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import GenericUtilities.Common;
import GenericUtilities.Reporter;
import GenericUtilities.WebElementRelatedUtility;

public class CartPage 
{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By btnCart = By.xpath("//button[@Class=\"cartBtn\"]");
	By eleCartItemCount = By.xpath("//button[@Class=\"cartBtn\"]//span");
	By eleTotalPrice = By.xpath("//h3[text()=\"Total Price:\"]//b");
	By btnEnrollNow = By.xpath("//button[text()=\"Enroll Now\"]");
	By edtAddress = By.xpath("//textarea[@id=\"address\"]");
	By edtPhoneNumber = By.xpath("//h3[text()=\"Phone Number\"]/following::input");
	By btnEnrollNowFinal = By.xpath("//button[@class=\"action-btn\" and text()=\"Enroll Now\"]");
	By eleOrderID = By.xpath("//h4[contains(text(),'Your order id is')]");
	By btnCancel = By.xpath("//button[@class=\"btn-close\"]");
	
	
	public boolean AddToCart(Map<String,String> map)
	{
		Common.WaitForFewSeconds(2);
		String courseName = map.get("CourseName");
		WebElement ele = driver.findElement(By.xpath("//h2[contains(text(),\"" + courseName + "\")]//following::button[1]"));
		WebElementRelatedUtility.ClickUsingJavaScript(driver, ele);
		Common.WaitForFewSeconds(1);
		if(driver.findElement(eleCartItemCount).getText().equals("1"))
		{
			Reporter.LogEvent(driver, "Done", "-", "Verified CartItemCount is 1 after adding the course");
		}
		else
		{
			Reporter.LogEvent(driver, "Done", "-", "Unable to verify CartItemCount is 1 after adding the course");
			return true;
		}
		
		driver.findElement(btnCart).click();
		Common.WaitForFewSeconds(2);
		String totalPrice = driver.findElement(eleTotalPrice).getText();
		if(totalPrice.contains(map.get("Price")))
		{
			Reporter.LogEvent(driver, "Done", "-", "Verified TotalPrice after adding the item in cart");
		}
		else
		{
			Reporter.LogEvent(driver, "Done", "-", "Unable to verify TotalPrice is "+ map.get("Price") +" after adding the item in cart");
			return false;
		}
		return true;
	}
	
	public String EnrollForTheCourse(Map<String,String> map)
	{
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scroll(250, 0)");
		
		Common.WaitForFewSeconds(1);
		driver.findElement(btnEnrollNow).click();
		Common.WaitForFewSeconds(1);
		driver.findElement(edtAddress).sendKeys(map.get("Address"));
		driver.findElement(edtPhoneNumber).sendKeys(map.get("PhoneNumber"));
		Common.WaitForFewSeconds(1);
		driver.findElement(btnEnrollNowFinal).click();
		//act.moveToElement(driver.findElement(btnEnrollNowFinal)).click().build().perform();
		//WebElementRelatedUtility.PerformClick(driver, driver.findElement(btnEnrollNowFinal));
		Common.WaitForFewSeconds(1);
		String[] temp = driver.findElement(eleOrderID).getText().split("-");
		Reporter.LogEvent(driver, "Done", "-", "User successfully enrolled for the course. Order Id - " + temp[1]);
		Common.WaitForFewSeconds(1);
		driver.findElement(btnCancel).click();
		
		
		
		return temp[1];
	}
}
