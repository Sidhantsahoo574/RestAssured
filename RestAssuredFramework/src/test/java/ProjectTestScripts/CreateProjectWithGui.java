package ProjectTestScripts;

import java.sql.SQLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.CreateProjectWRTPojo;
import genericUtilities.BaseClass;
import genericUtilities.DatabaseUtilities;
import genericUtilities.EndPointLibraries;
import genericUtilities.IConstant;
import genericUtilities.JavaUtilities;
import genericUtilities.RestAssuredLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CreateProjectWithGui extends BaseClass {
	

	
	@Test
	
	public void updateProject() throws SQLException
	{
		//Create project with gui
		 JavaUtilities j=new JavaUtilities();
		 RestAssuredLibrary rLib=new RestAssuredLibrary();
		 DatabaseUtilities dLib=new DatabaseUtilities();
		 
	    System.setProperty("webdriver.edge.driver", "./src/main/resources/driver/msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(IConstant.urlRMG);
		driver.findElement(By.id("usernmae")).sendKeys(IConstant.apiUserName);
		driver.findElement(By.id("inputPassword")).sendKeys(IConstant.apiPassword);
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		String proj="tyss"+j.getRandom();
		driver.findElement(By.name("projectName")).sendKeys(proj);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("document.getElementsByName('teamSize').value='10'");
		//driver.findElement(By.name("teamSize")).sendKeys("11");
	     driver.findElement(By.name("createdBy")).sendKeys("Sid");
		WebElement dd = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		Select s=new Select(dd);
		s.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		WebElement ele = driver.findElement(By.xpath("//td[text()='"+proj+"']"));
		System.out.println(ele.getText());
		WebElement pid = driver.findElement(By.xpath("//td[text()='"+proj+"']/preceding-sibling::td"));
	    String id=pid.getText();
		
		//update project with api
		CreateProjectWRTPojo cp=new CreateProjectWRTPojo("Abhi", "tyss07"+j.getRandom(), "completed", 15);
		
		Response resp = given().body(cp).contentType(ContentType.JSON)
		
	    .when().put("http://rmgtestingserver:8084/projects/"+id);
	    String id1 = resp.jsonPath().get("projectId");
	    resp.then().log().all();
		
		//get the project
	    
	    Response rsp = given().pathParam("Id", id1)
		.when().get("http://rmgtestingserver:8084/projects/{Id}");
	    String expData = rsp.jsonPath().get("projectId");
	   // System.out.println(rsp.jsonPath().get("createdBy"));
		
		rsp.then().log().all();
	    
		//verify in the database
	    String query="select* from project;";
	    String acData=dLib.readDataFromDBAndValidate(query, 1, expData);
	    
	    Assert.assertEquals(expData, acData);
	    System.out.println("tc pass");
	    
		
	}

}
