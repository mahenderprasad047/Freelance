package GenericUtilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwitchToUtility 
{

WebDriver driver;
	
	public SwitchToUtility(WebDriver driver)
	{
		this.driver = driver;
	}
	
	//For Normal Java alert
	public static boolean HandleAlert(WebDriver driver, String alertMessage)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Common.WaitForFewSeconds(2);
		if(alert.getText().contains(alertMessage))
		{
			System.out.println("Log-INFO : Alert message text is verified");
			alert.accept();				
		}
		else
		{
			System.out.println("Log-INFO : Unable to verify alert message text");
			alert.accept();	
			return false;
		}
		
		return true;
		
	}
	
	//For Confirm Alert
	public static boolean HandleAlert(WebDriver driver, String alertMessage, String AcceptOrDismiss)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Common.WaitForFewSeconds(2);
		if(alert.getText().contains(alertMessage))
		{
			System.out.println("Log-INFO : ConfirmAlert message text is verified");			
		}
		else
		{
			System.out.println("Log-INFO : Unable to verify ConfirmAlert message text");
			return false;
		}
		
		Common.WaitForFewSeconds(2);
		if(AcceptOrDismiss.equalsIgnoreCase("Accept"))
		{
			alert.accept();
		}
		else if(AcceptOrDismiss.equalsIgnoreCase("Dismiss"))
		{
			alert.dismiss();
		}
		else
		{
			System.out.println("Log-INFO : Pls confirm/pass Accept or Dismiss ");
			return false;
		}
		
		return true;
		
	}
	
	//For Confirm Alert
	public static boolean HandleAlert(WebDriver driver, String alertMessage, String inputMessage, String AcceptOrDismiss)
		{
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			Common.WaitForFewSeconds(1);
			if(alert.getText().contains(alertMessage))
			{
				System.out.println("Log-INFO : PromptAlert message text is verified");
				alert.sendKeys(inputMessage);
				Common.WaitForFewSeconds(1);			
			}
			else
			{
				System.out.println("Log-INFO : Unable to verify PromptAlert message text");
				return false;
			}
			
			Common.WaitForFewSeconds(1);
			if(AcceptOrDismiss.equalsIgnoreCase("Accept"))
			{
				alert.accept();
				Common.WaitForFewSeconds(1);
			}
			else if(AcceptOrDismiss.equalsIgnoreCase("Dismiss"))
			{
				alert.dismiss();
			}
			else
			{
				System.out.println("Log-INFO : Pls confirm/pass Accept or Dismiss option");
				return false;
			}
			
			return true;
			
		}
	
	//Switch to Windows handle
	public static boolean HandleWindows(WebDriver driver, String basedOn, String valueContains)
	{
		
		//Using window handle ID
		//Using url
		//Using title
		System.out.println("Select based on - " + basedOn + " and value should "+ valueContains);
		
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWinHandles = new ArrayList<String>(windowHandles);
		
		if(basedOn.equalsIgnoreCase("WindowHandleID"))
		{
			for(String str:lstWinHandles)
			{
				if(str.equalsIgnoreCase(valueContains))
				{
					driver.switchTo().window(valueContains);
					return true;
				}
			}
		}
		else if(basedOn.equalsIgnoreCase("Index"))
		{
			int indexValue = Integer.parseInt(valueContains);
			driver.switchTo().window(lstWinHandles.get(indexValue));
		}
		else if(basedOn.equalsIgnoreCase("Title"))
		{
			for(String str:lstWinHandles)
			{
				if(driver.switchTo().window(str).getTitle().contains(valueContains))
				{
					driver.switchTo().window(str);
					return true;
				}
			}
		}
		else if(basedOn.equalsIgnoreCase("URL"))
		{
			for(String str:lstWinHandles)
			{
				if(driver.switchTo().window(str).getCurrentUrl().contains(valueContains))
				{
					driver.switchTo().window(str);
					return true;
				}
			}
		}
		else
		{
			
		}
		return false;
		
	}
	
	
	//Switch to frame based on Name or ID of the frame[
	public static boolean HandleFrame(WebDriver driver,String valueContains)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.name(valueContains))));
		}catch(NoSuchFrameException e)
		{
			System.out.println("Execption : No such frame found");
			return false;
		}
		return true;		
	}
	
	//Switch to frame based on index
	public static boolean HandleFrame(WebDriver driver,int index)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		}catch(NoSuchFrameException e)
		{
			System.out.println("Execption : No such frame found");
			return false;
		}
		return true;		
	}
	
	//Switch to frame based on WebElement 
	public static boolean HandleFrame(WebDriver driver,WebElement ele)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
		}catch(NoSuchFrameException e)
		{
			System.out.println("Execption : No such frame found");
			return false;
		}
		return true;		
	}
	
	public static boolean SwitchToBrowserFromFrame(WebDriver driver)
	{
		
		driver.switchTo().defaultContent();
		return true;
	}
	
	
	
}
