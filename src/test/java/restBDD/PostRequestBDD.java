package restBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequestBDD {

	@Test
	public void test1() {
		Map<String,Object> MapObj = new HashMap<String,Object>();
		
		MapObj.put("name", "Tom");
		MapObj.put("salary", "6000");
		
		RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.contentType(ContentType.JSON)
	   				.accept(ContentType.JSON)
	   				.body(MapObj)
					.when()
					.post("/create")
					.then()
					.log()
				//	.all();
					.body()   //either body or status can be put in a request
				//	.status()  // for logging the status code
					.statusCode(201) // for assertion of status code
					.body("name", Matchers.equalTo("Tom"));
	}
}
