package restAPI;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithFile {

	@Test
	public void test1() throws IOException {
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		
		
		RequestSpecification request = RestAssured.given();
		
		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));
		
		Response response =	request.contentType(ContentType.JSON)
					   				.accept(ContentType.JSON)
					   				.body(dataBytes)
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
