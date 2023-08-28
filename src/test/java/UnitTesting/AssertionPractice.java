package UnitTesting;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AssertionPractice 
{

	@Test
	public void Practice1()
	{
		AssertionPractice obj = new AssertionPractice();
		Reporter.log("Verify URL");
		System.out.println("Returend Value - " + obj.returnTrue());
		Assert.assertEquals(returnTrue(), true, "Verified URl");
		Assert.assertEquals(returnTrue(), true, "Verified URl2");
		Reporter.log("Log1");
		Reporter.log("Log2");
		Reporter.log("Log3");
		Assert.assertTrue(true, "AssertTue - Matched");
	}
	
	public boolean returnTrue()
	{
		return true;
	}
}
