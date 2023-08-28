package GenericUtilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Reporter {

	public static void LogEvent(WebDriver driver, String eventStatus,String stepName,String stepDetails)
	{
		String timeStamp = Common.GetCurrentDateTimeStamp("HH_mm_ss");
		if(eventStatus.equalsIgnoreCase("Pass"))
		{
			System.out.println("Log-PASS : "+ stepName + "-" + stepDetails + " ScreenshotFileName - " + timeStamp);
			CaptureScreenshotInFileFormat(driver,timeStamp);
		}
		else if(eventStatus.equalsIgnoreCase("Fail"))
		{
			System.out.println("Log-FAIL : "+ stepName + "-" + stepDetails + " ScreenshotFileName - " + timeStamp);
			CaptureScreenshotInFileFormat(driver,timeStamp);
		}
		else if(eventStatus.equalsIgnoreCase("Done"))
		{
			System.out.println("Log-DONE : "+ stepName + "-" + stepDetails + " ScreenshotFileName - " + timeStamp);
			CaptureScreenshotInFileFormat(driver,timeStamp);
		}
		else if(eventStatus.equalsIgnoreCase("Info"))
		{
			System.out.println("Log-INFO : "+ stepName + "-" + stepDetails);
			
		}
	}

	public static String CaptureScreenshotInBase64(WebDriver driver)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		return ts.getScreenshotAs(OutputType.BASE64);
		
	}
	
	public static void CaptureScreenshotInFileFormat(WebDriver driver,String fileName)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Reports/" + Common.CurrentDateTimeStamp + "/" + fileName +".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			System.out.println("Could not capture the screenshot. Exception - " + e.getMessage());
		}
	}
}
