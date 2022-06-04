package restBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequestBDD {
	
	@Test
	public void test1() {
		RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.when()
					.get("/1")
					.then()
					.log()
				//	.all();
					.body()   //either body or status can be put in a request
				//	.status()  // for logging the status code
					.statusCode(200); // for assertion of status code
	}

	@Test()
	public void test2() {
		RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.queryParam("id", "1")  // wee can give query param instead of giving in get("/1")
					.when()
					.get()
					.then()
					.log()
					.body()
					.statusCode(200)
					.body("[0].name", Matchers.equalTo("Pankaj")); // 0 is given for first record in the list of records being return, so to match with first record
	}
	
	@Test()
	public void test3() {
		Response response = RestAssured.given()
										.baseUri("http://localhost:3000/employees")
										.queryParam("id", "1")  
										.when()
										.get();
		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		JsonPath jpath  = response.jsonPath();  
		List <String> names  = jpath.get("name");  
		
		System.out.println("Employee Name : "+ names.get(0)); 
		Assert.assertEquals(names.get(0),"Pankaj"); 
		
		String Header = response.getHeader("Content-Type"); 
		System.out.println(Header);
	}
}
