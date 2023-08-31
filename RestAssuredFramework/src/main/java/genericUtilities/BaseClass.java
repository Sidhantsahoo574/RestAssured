package genericUtilities;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ObjectRepositary.CreateProjectPopupPage;
import ObjectRepositary.HomePage;
import ObjectRepositary.LoginPage;

public class BaseClass {

	
	public static WebDriver driver;
	public JavaUtilities j=new JavaUtilities();
	public RestAssuredLibrary rLib=new RestAssuredLibrary();
	public DatabaseUtilities dLib=new DatabaseUtilities();
	WebActionUtility wLib=new WebActionUtility();
	LoginPage l;
	
	
	@BeforeSuite
	
	public void bsConfig() throws SQLException
	{
		dLib.connectToDB();
	}
	@AfterSuite
	public void asConfig() throws SQLException
	{
		dLib.closeDB();
	}
	
	@BeforeClass
	public void openBrowser()
	{
		driver=new EdgeDriver();
		wLib.waitForElementInDOM(driver);	
	}
	@BeforeMethod
	public void loginToTheApp()
	{
		new LoginPage(driver);
		driver.get("http://rmgtestingserver:8084/");
		l.loginPage();	
	}
	@AfterMethod
	public void logoutToTheApp()
	{
		System.out.println("logout");
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}
	
}
