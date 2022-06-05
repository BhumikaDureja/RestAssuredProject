package apiChaining;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	Response response;
	String BaseURI = "http://localhost:3000/employees/" ;
	
	@Test
	public void test1() {
		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("Sam", "2000");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath  = response.jsonPath(); 
		int EmployeeID = jpath.get("id");
        System.out.println("Employee id : "+EmployeeID);
        
        response = PutMethod(EmployeeID, "Reema", "6000");
        Assert.assertEquals(response.getStatusCode(), 200);
        jpath = response.jsonPath();  //already initailised jpath above so no need to initailise again with JsonPath 
        Assert.assertEquals(jpath.get("name"), "Reema"); // or directly give Assert.assertEquals(response.jsonPath().get("name"), "Rick");
        
        
        response = DeleteMethod(EmployeeID);
        Assert.assertEquals(response.getStatusCode(),200);
        String ResponseBody = response.getBody().asString();
        Assert.assertEquals(ResponseBody, "{}");   // or we can directly give Assert.assertEquals(response.getBody().asString(), "{}");
		
        
        response = GetMethod(EmployeeID);
        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertEquals(response.getBody().asString(), "{}"); 
	}
	
	public Response GetMethodAll() {
		
		RestAssured.baseURI = BaseURI ;
		RequestSpecification request = RestAssured.given(); 
		Response response = request.get();  
		
		return response;
	}
	
	public Response PostMethod(String Name, String Salary) {
		
		RestAssured.baseURI = BaseURI ;
		
		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
   				.accept(ContentType.JSON)
   				.body(jobj.toString())
   				.post("/create");
		
		return response;
	}
	
	public Response PutMethod(int EmpID, String Name, String Salary) {
		
		RestAssured.baseURI = BaseURI ;
		
		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response =	request.contentType(ContentType.JSON)
   				.accept(ContentType.JSON)
   				.body(jobj.toString())
   				.put("/" + EmpID);
		
		return response;
	}
	
	public Response DeleteMethod(int EmpID) {
		
		RestAssured.baseURI = BaseURI ;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/" +EmpID);
		
		return response;
	}
	
	public Response GetMethod(int EmpID) {
		
		RestAssured.baseURI = BaseURI ;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/" +EmpID);
		
		return response;
	}
	
	
	

}
