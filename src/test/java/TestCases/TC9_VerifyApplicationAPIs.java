package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtilities.Reporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC9_VerifyApplicationAPIs 
{
	
	@Test
	public void VerifyAppIsUpAndRunning()
	{
		
		System.out.println("Start of Execution  - TC9_VerifyApplicationAPIs");
		
		System.out.println("Step1 - Verify response code");
		Response resp = RestAssured.get("https://freelance-learn-automation.vercel.app/login");
		Assert.assertEquals(resp.statusCode(), 200,"Step1 - Unable to Verify reponse code");
		System.out.println("Step1 - Response status code verified");
		
		System.out.println("Start of Execution  - TC9_VerifyApplicationAPIs");
	}
}
