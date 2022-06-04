package restAPI;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
					   				.accept(ContentType.JSON)
					   				.body(" {\r\n"
					   						+ "        \"name\": \"Ron\",\r\n"
					   						+ "        \"salary\": \"15000\"\r\n"
					   						+ "       \r\n"
					   						+ "  }")
					   				.post("/create");
		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode(); //Fetching request's status Response status code
		Assert.assertEquals(ResponseCode,201);
		
		JsonPath jpath  = response.jsonPath(); 
		jpath.get("id");
	//	List <String> id = jpath.get("id"); //to store id in form of sstring if there are multiple ids
        System.out.println("Employee id : "+jpath.get("id"));
        
      
		
		
	}
}
