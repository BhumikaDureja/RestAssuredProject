package restAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest {


	@Test
	public void test1() {
RestAssured.baseURI = "http://localhost:3000/employees";
		
		Map<String,Object> MapObj = new HashMap<String,Object>();
		
		MapObj.put("name", "Alex"); //2.
		MapObj.put("salary", "6000");
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
					   				.accept(ContentType.JSON)
					   				.body(MapObj)
					   				.put("/5");    //3.
		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode(); //Fetching request's status Response status code
		Assert.assertEquals(ResponseCode,200); //4.
		
	}
}

/* Changes to PUT Request class are as following:
	1. Copy Paste same code from Post file - in this case copied code for PostRequestWithMap.java
	2. Change the name of the person as part of Put request
	3. Instead of Post parameter give put parameter
	4. Update the assetion for response code. it should be 200 for put request  */
