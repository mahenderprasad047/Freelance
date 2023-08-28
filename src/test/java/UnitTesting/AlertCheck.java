package UnitTesting;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import GenericUtilities.Common;
import GenericUtilities.SwitchToUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertCheck 
{
	@Test
	public void CHeckJavaALert()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		
		/*
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		SwitchToUtility.HandleAlert(driver, "I am a JS Alert");
		CommonFunctions.HardWait(3);
		
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		SwitchToUtility.HandleConfirmAlert(driver, "I am a JS Confirm", "Accept");
		CommonFunctions.HardWait(3);
		
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		SwitchToUtility.HandleConfirmAlert(driver, "I am a JS Confirm", "Dismiss");
		CommonFunctions.HardWait(3);
		
		*/
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		SwitchToUtility.HandleAlert(driver, "I am a JS prompt", "Mahender1", "Accept");
		Common.WaitForFewSeconds(5);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		SwitchToUtility.HandleAlert(driver, "I am a JS prompt", "Mahender2", "dismiss");
		Common.WaitForFewSeconds(5);
		
		
		String data = "Hello";
		int data1 = 2;
		
		
		
	}

}
