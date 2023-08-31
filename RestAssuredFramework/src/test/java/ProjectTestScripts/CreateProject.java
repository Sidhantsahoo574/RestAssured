package ProjectTestScripts;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.CreateProjectWRTPojo;
import genericUtilities.BaseClass;
import genericUtilities.DatabaseUtilities;
import genericUtilities.EndPointLibraries;
import genericUtilities.IConstant;
import genericUtilities.JavaUtilities;
import genericUtilities.RestAssuredLibrary;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CreateProject extends BaseClass {
	
	@Test
	
	public void createProj() throws SQLException
	{
		JavaUtilities j=new JavaUtilities();
		RestAssuredLibrary rLib=new RestAssuredLibrary();
		DatabaseUtilities dLib=new DatabaseUtilities();
	    CreateProjectWRTPojo cp=new  CreateProjectWRTPojo("Sidhanta","tyss"+j.getRandom(),"On going",12);
	    
	    //send the request
	    baseURI=IConstant.urlRMG;
	    
	    Response resp = given().body(cp).contentType(ContentType.JSON)
	 
	    		.when().post(EndPointLibraries.post);
	     //Capture the projectId
	    
	    String expData=rLib.getJsonData(resp, "projectId");
	    System.out.println(expData);
	    
	    //validate the id in database
	    
	    String query="select* from project;";
	    String acData=dLib.readDataFromDBAndValidate(query, 1, expData);
	    
	    Assert.assertEquals(expData, acData);
	    System.out.println("tc pass");
	    
	    resp.then().log().all();
	}

}
