package UnitTesting;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Log;

public class TestNGListenerExamples 
{
	ExtentTest child;
	
	@Test
	public void test1()
	{
		Reporter.log("reporting log from test1",true);
		Assert.assertEquals(false, false,"Verified test1");
		child.info("Info from test1");
		
		
	}
	
	@Test
	public void test2()
	{
		Assert.assertEquals(false, true,"Verified test2");
		child.info("Info from test1");
		child.pass("Pass from test2");
	}
	
	@Test
	public void test3()
	{
		Assert.assertEquals(true, true,"Verified test3-1");
		Assert.assertEquals(true, true,"Verified test3-2");
		child.info("Info from test1");
		child.fail("Fail from test3");
	}
}
