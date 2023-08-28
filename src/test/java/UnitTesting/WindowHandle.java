package UnitTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import GenericUtilities.Common;
import GenericUtilities.SwitchToUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start of the execution");
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		String parentWindowHandle = driver.getWindowHandle();
		driver.findElement(By.xpath("//div[@class='orangehrm-login-footer-sm']//a[1]")).click();
		Common.WaitForFewSeconds(1);
		
		driver.findElement(By.xpath("//div[@class='orangehrm-login-footer-sm']//a[2]")).click();
		Common.WaitForFewSeconds(1);
		
		driver.findElement(By.xpath("//div[@class='orangehrm-login-footer-sm']//a[3]")).click();
		Common.WaitForFewSeconds(1);
		
		driver.findElement(By.xpath("//div[@class='orangehrm-login-footer-sm']//a[4]")).click();
		Common.WaitForFewSeconds(1);
		
		Set<String> s1 = driver.getWindowHandles();
		List<String> l1 = new ArrayList<String>(s1);
		String title = driver.switchTo().window(l1.get(0)).getTitle();
		System.out.println("title -" + title);
		/*
		System.out.println("Current titke " +driver.getTitle());
		SwitchToUtility.HandleWindows(driver, "WindowHandleID", parentWindowHandle);
		System.out.println("1st - " + driver.getTitle());
		CommonFunctions.HardWait(5);
		
		SwitchToUtility.HandleWindows(driver, "Index", "1");
		System.out.println("2nd - " + driver.getTitle());
		CommonFunctions.HardWait(5);
		
		SwitchToUtility.HandleWindows(driver, "Index", "2");
		System.out.println("2nd - " + driver.getTitle());
		CommonFunctions.HardWait(5);
		
		SwitchToUtility.HandleWindows(driver, "Title", "Twitter");
		System.out.println("3rd - " + driver.getTitle());
		CommonFunctions.HardWait(5);
		
		SwitchToUtility.HandleWindows(driver, "URL", "youtube");
		System.out.println("4th - " + driver.getTitle());
		CommonFunctions.HardWait(5);
		
		*/
		

	}

}
