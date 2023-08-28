package UnitTesting;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class ExtentReportNotWorking {
	
	ExtentReports ext;
	
	@Test
	public void temp1()
	{
		
		ExtentTest test = ext.createTest("Login","Login with Valid user");
		test.info("Informartion-1");
		test.info("Information-2");
		test.info("Information-4");
		test.fail("Failed");
		Reporter.log("Message from reporter");		
		ext.flush();
		
		
		ExtentTest test1 = ext.createTest("Logout","Logout from application");
		test1.info("Informartion-1");
		test1.info("Information-2");
		test1.info("Information-4");
		Assert.assertEquals(false, false, "Asser Matched");
		Reporter.log("Message from reporter");		
		ext.flush();
		
		
		
	}

}
