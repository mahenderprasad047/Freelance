package GenericUtilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtilities 
{
	
	ThreadLocal<WebDriver> tl=new ThreadLocal<>();
	
	WebDriver driver;
	
	public WebDriver getDriver()
	{
		return tl.get();
	}
	
	public WebDriver startBrowser(String browserName, String url) throws InterruptedException, MalformedURLException
	{
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			ChromeOptions opt = new ChromeOptions();
			//opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			//opt.addExtensions(new File("./Extensions/AdBlocker.crx"));
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.merge(opt);
			driver = new ChromeDriver(opt);
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			tl.set(driver);
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			EdgeOptions opt = new EdgeOptions();
			//opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			//opt.addExtensions(new File("./Extensions/AdBlocker.crx"));
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("edge");
			cap.merge(opt);
			driver = new EdgeDriver(opt);
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			tl.set(driver);

		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			FirefoxOptions opt = new FirefoxOptions();
			//opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
			//opt.addExtensions(new File("./Extensions/AdBlocker.crx"));
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.merge(opt);
			driver = new FirefoxDriver(opt);
			//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			tl.set(driver);
		}
		else
		{
			System.out.println("Wrong choice");
			return null;
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));	//For Browser to load
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	//For every WebElement
		getDriver().get(url);
		System.out.println("Application launched");
		return driver;		
	}
	
	
	public void CloseBrowser(WebDriver driver)
	{
		tl.get().quit();
		System.out.println("Browser closed");
	}
	
	
}
