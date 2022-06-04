package restAPI;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees/";
		
		RequestSpecification request = RestAssured.given(); // capturing the baseUrI in request object
		
		Response response = request.delete("/6");   //response is object of Response class, storing response from get request 
		
		String ResponseBody = response.getBody().asString(); //storing the get response as string in responseBody
		
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode(); //Fetching request's status Response status code
		
		Assert.assertEquals(ResponseCode,200);
	
	}

}
