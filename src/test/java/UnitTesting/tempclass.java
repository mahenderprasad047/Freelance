package UnitTesting;

import org.openqa.selenium.chrome.ChromeDriver;

public class tempclass {

	public static void main(String[] args) throws InterruptedException 
	{
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		Thread.sleep(4000);
		System.out.println("No of Handles - " + driver.getWindowHandles().size());
		
		
		
	}

}
