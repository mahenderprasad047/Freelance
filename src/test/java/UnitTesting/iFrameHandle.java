package UnitTesting;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import GenericUtilities.Common;
import GenericUtilities.SwitchToUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class iFrameHandle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Start of the execution");
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.rediff.com/");
		driver.manage().window().maximize();
		
		/*
		//Using iFrame as WebElement
		SwitchToUtility.HandleFrame(driver, driver.findElement(By.xpath("//iframe[@name='moneyiframe']")));
		CommonFunctions.WaitForFewSeconds(2);
		driver.findElement(By.xpath("//input[@id='query']")).sendKeys("123456778");
		CommonFunctions.WaitForFewSeconds(2);
		SwitchToUtility.SwitchToBrowserFromFrame(driver);
		CommonFunctions.WaitForFewSeconds(2);
		driver.findElement(By.xpath("//a[@title='Lightning fast free email']")).click();
		*/
		
		/*
		//Using Index
		SwitchToUtility.HandleFrame(driver, 0);
		CommonFunctions.WaitForFewSeconds(2);
		WebElement ele = driver.findElement(By.xpath("//input[@id='query']"));
		CommonFunctions.HighlightWebElement(driver, ele);
		driver.findElement(By.xpath("//input[@id='query']")).sendKeys("123456778");
		CommonFunctions.WaitForFewSeconds(2);
		SwitchToUtility.SwitchToBrowserFromFrame(driver);
		CommonFunctions.WaitForFewSeconds(2);
		driver.findElement(By.xpath("//a[@title='Lightning fast free email']")).click();
		*/
		
		//Using ID or Name
		SwitchToUtility.HandleFrame(driver, "moneyiframe");
		Common.WaitForFewSeconds(2);
		WebElement ele = driver.findElement(By.xpath("//input[@id='query']"));
		Common.HighlightWebElement(driver, ele);
		driver.findElement(By.xpath("//input[@id='query']")).sendKeys("123456778");
		Common.WaitForFewSeconds(2);
		SwitchToUtility.SwitchToBrowserFromFrame(driver);
		Common.WaitForFewSeconds(2);
		driver.findElement(By.xpath("//a[@title='Lightning fast free email']")).click();
		
		
		
	}

}
