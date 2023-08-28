package GenericUtilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Common 
{
	public final static String CurrentDateTimeStamp = GetCurrentDateTimeStamp();
	
	//To wait for sometime - Hard wait
	public static void WaitForFewSeconds(int time)
	{
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	//To Highlight the a Object
	public static void HighlightWebElement(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.background='yellow'", ele);
	}
	
	//Get Current DateITimeStamp
	public static String GetCurrentDateTimeStamp()
	{
		Date CurrentDate = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		return dateFormater.format(CurrentDate);
	}
	
	//Get Current DateITimeStamp - in different time format
	public static String GetCurrentDateTimeStamp(String format)
	{
		Date CurrentDate = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat(format);
		return dateFormater.format(CurrentDate);
	}
	
	
	
}
