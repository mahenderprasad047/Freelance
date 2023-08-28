package UnitTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import GenericUtilities.BrowserUtilities;
import GenericUtilities.TestDataProviderForTC;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;

public class TestDataFromExcel extends TestDataProviderForTC{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> lstCustID = new ArrayList<String>();
		lstCustID.add("54334");
		lstCustID.add("91729");
		lstCustID.add("54968");
		
		

		TestDataProviderForTC.SetDataToAColumnOfExcel("TC1_CreateCustomer","TestData1","NewCustomer", lstCustID, "CustomerID");
		TestDataProviderForTC.SetDataToAColumnOfExcel("TC2_EditCustomer","TestData1","NewCustomer", lstCustID, "CustomerID");
		
		
		
		

	}

}
