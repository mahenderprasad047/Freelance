package UnitTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class restAssured 
{
	@Test
	public void VerifyAppIsUp()
	{
		Response resp = RestAssured.get("https://freelance-learn-automation.vercel.app/login");
		Assert.assertEquals(resp.statusCode(), 200);
		System.out.println("Response status code verified");
	}
}
