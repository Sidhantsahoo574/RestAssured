package ProjectTestScripts;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ObjectRepositary.CreateProjectPopupPage;
import ObjectRepositary.HomePage;
import Pojo.CreateProjectWRTPojo;
import genericUtilities.BaseClass;
import genericUtilities.DatabaseUtilities;
import genericUtilities.JavaUtilities;
import genericUtilities.RestAssuredLibrary;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateProject2 extends BaseClass{

	
	HomePage h=new HomePage(driver);
	CreateProjectPopupPage cpp=new CreateProjectPopupPage(driver);
	 JavaUtilities j=new JavaUtilities();
	 RestAssuredLibrary rLib=new RestAssuredLibrary();
	 DatabaseUtilities dLib=new DatabaseUtilities();
	
        
	
	@Test
	public void updateProj() throws SQLException
	{
		
		h.homePage();
		h.getProj().getText();
		String id = h.getId().getText();
		cpp.createProjPage("tyss", "Sidhant", driver);
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
