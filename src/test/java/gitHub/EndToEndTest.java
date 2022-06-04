package gitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	@Test
	public void test1() {
		RestAssured.baseURI = "https://api.github.com/users/BhumikaDureja/repos";
	
		RequestSpecification request = RestAssured.given(); // capturing the baseUrI in request object
		
		Response response = request.get(); 
		
		String ResponseBody = response.getBody().asString(); //storing the get response as string in responseBody
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode(); //Fetching request's status Response status code
		Assert.assertEquals(ResponseCode,200);
	}

	@Test
	public void test2() throws IOException {
		
		RestAssured.baseURI = "https://api.github.com/user/repos";
	
		RequestSpecification request = RestAssured.given();
		
		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));
		
		Response response =	request.auth()
									.oauth2("")  //GitHub token should be given here between double quotes.. removed for security reasons
									.contentType(ContentType.JSON)
					   				.accept(ContentType.JSON)
					   				.body(dataBytes)
					   				.post();
		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode(); //Fetching request's status Response status code
		Assert.assertEquals(ResponseCode,201);
		
		
	}
}
