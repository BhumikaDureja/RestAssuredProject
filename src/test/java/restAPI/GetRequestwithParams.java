package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestwithParams {
	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees/";
		
		RequestSpecification request = RestAssured.given(); // capturing the baseUrI in request object
		
		Response response = request.param("id", 1).get();   //storing response according to parameter value
		
		String ResponseBody = response.getBody().asString(); //storing the get response as string in responseBody
		
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode(); //Fetching request's status Response status code
		
		Assert.assertEquals(ResponseCode,200);
		
		System.out.println("ResponseStatusCode : "+ResponseCode);
		
		Assert.assertTrue(ResponseBody.contains("Pankaj")); // To verify if Pankaj is in Response Body
		
		JsonPath jpath  = response.jsonPath();  //converting response into  json path object
		List <String> names  = jpath.get("name");  //storing json path name into a list
		
		System.out.println("Employee Name : "+ names.get(0)); //Printing 0th index record or first record in the List
		Assert.assertEquals(names.get(0),"Pankaj"); //verifying if first name retrieved is Pankaj or not
		
		
		String Header = response.getHeader("Content-Type"); //to get the header from the response
		System.out.println(Header);
		
	}
}
