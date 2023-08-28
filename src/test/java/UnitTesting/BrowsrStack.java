package UnitTesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowsrStack {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("Chrome");
		WebDriver driver = new RemoteWebDriver(new URL("http:mahenderprasad_cK7pDh:LUZTtvBqszQjQ7mBs3cg@"),cap);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get("https://www.google.com/");
		System.out.println("Title - "+driver.getTitle());
		driver.quit();
		

	}

}
