package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import GenericUtilities.Common;
import GenericUtilities.GenerateRandomValue;
import GenericUtilities.Reporter;
import GenericUtilities.SwitchToUtility;
import GenericUtilities.WebElementRelatedUtility;

public class ManageCategoriesPage 
{

	WebDriver driver;
	public ManageCategoriesPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By btnAddNewCategory = By.xpath("//button[text()=\"Add New Category \"]");
	By btnDeleteConfirm = By.xpath("//button[text()=\"Delete\"]");
	
	
	public boolean AddCategory(String categoryName)
	{
		String randomString = GenerateRandomValue.GenerateRandomString(4);
		String newCategoryName = categoryName + " " + randomString;
		Common.WaitForFewSeconds(1);
		driver.findElement(btnAddNewCategory).click();
		Common.WaitForFewSeconds(1);
		SwitchToUtility.HandleAlert(driver,"Enter a Category Name",newCategoryName,"Accept");
		Common.WaitForFewSeconds(2);
		Actions act = new Actions(driver);
		
		try {
			act.scrollToElement(driver.findElement(By.xpath("//td[text()=\"" + newCategoryName +"\"]"))).build().perform();
		}catch(Exception e)
		{
			System.out.println("Non-Harm exception occurs while scorlling"+ e.getMessage());
		}
		Common.WaitForFewSeconds(1);
		Reporter.LogEvent(driver, "Done", "-", "New Category is added. CategoryName - " + newCategoryName);
		driver.navigate().refresh();
		return true;
		
	}

	public boolean DeleteCategory(String categoryName)
	{
		WebElement ele;
		Common.WaitForFewSeconds(1);
		try {
			ele = driver.findElement(By.xpath("//table//td[contains(text(),\""+ categoryName + "\")]"));
			Reporter.LogEvent(driver, "Info", "-", "Category found for deletion. categoryName - " + ele.getText());
		}catch(Exception e)
		{
			if(e.getClass().toString().contains("NoSuchElementException"))
			{
				Reporter.LogEvent(driver, "Info", "-", "Category is not available for deltetion. CategoryName - " + categoryName);
				return true;
			}
			else
			{
				Reporter.LogEvent(driver, "Info", "-", "Unable to delete the category.CategoryName - " + categoryName);
				return false;
			}
		}
		
		ele = driver.findElement(By.xpath("//table//td[contains(text(),\"" + categoryName + "\")]//following::button"));
		WebElementRelatedUtility.ClickUsingJavaScript(driver, ele);
		Common.WaitForFewSeconds(1);
		driver.findElement(btnDeleteConfirm).click();
		return true;
	}
	
}
