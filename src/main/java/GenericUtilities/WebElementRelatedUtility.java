package GenericUtilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementRelatedUtility 
{

	public static void TypeUsingJavaScript(WebDriver driver, WebElement ele,String value)
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+value +"'",ele);
	}
	
	public static void ClickUsingJavaScript(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",ele);
	}
	
	public static void PerformClick(WebDriver driver, WebElement ele)
	{
		try {
			ele.click();
			Common.WaitForFewSeconds(1);
			System.out.println("normal Click worked");
			return;
		}catch(Exception e)
		{
			System.out.println("Unable to click normally.Trying with Actions class");
		}
		
		try {
			new Actions(driver).scrollToElement(ele).click().build().perform();
			Common.WaitForFewSeconds(1);
			System.out.println("Actions class Click worked");
			return;
		}catch(Exception e)
		{
			System.out.println("Unable to click normally.Trying with JavaScriptExecutor class");
		}
		
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click",ele);
			Common.WaitForFewSeconds(1);
			System.out.println("JavaScriptExecutor Click worked");
			return;
		}catch(Exception e)
		{
			System.out.println("Unable to click the WebElement");
		}
	}
	
	public static void SelectFromDropDown(WebDriver driver, WebElement lstDropDown, String value)
	{
		ScrollToAWebElementUsingJS(driver, lstDropDown);
		Select ele = new Select(lstDropDown);
		ele.selectByVisibleText(value);
	}

	public static void ScrollToAWebElement(WebDriver driver, WebElement ele)
	{
		try {
			new Actions(driver).scrollToElement(ele).build().perform();
		}catch(Exception e)
		{
			System.out.println("Unable to scroll to WebElement");
		}
	}
	
	public static void MoveToAWebElement(WebDriver driver, WebElement ele)
	{
		try {
			new Actions(driver).moveToElement(ele).build().perform();
		}catch(Exception e)
		{
			System.out.println("Unable to move to WebElement");
		}
	}
	
	//Recommended
	public static void ScrollToAWebElementUsingJS(WebDriver driver, WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}
}
