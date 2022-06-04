package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	@Test
	public void test1() {
		RestAssured.given()
		.baseUri("https://chercher.tech/sample/api/books.xml")
		.when()
		.get()
		.then()
		.log()
		.body()
		.statusCode(200); 
		
	}
	
	
	@Test
	public void test2() {
		Response response = RestAssured.given()
									.baseUri("https://chercher.tech/sample/api/books.xml")
									.when()
									.get();
		
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		
		System.out.println("All the books are " +books.toString());
	    System.out.println("First book is " +books.get(0).toString());
	    System.out.println("Second book is " +books.get(1).toString());
	   
	    //to retrieve language of books using getattribute()
	    System.out.println("Language of first book is " +books.get(0).getAttribute("lang"));
	    
	    // To retrieve books using for each loop
	    for(String i:books) {
            System.out.println(i);
        }
	    
	    // To retrieve books using for loop
	    for(int index=0; index<books.size();index++)
	    {
	    	System.out.println("book is " +books.get(index).toString());
	    }
	    
	    // to retrieve author of books
	    books = response.then().extract().path("bookstore.book.author"); 
	    // or write it as  NodeChildrenImpl author = response.then().extract().path("bookstore.book.author"); then in the below calls instead of books use author
		
		System.out.println("All the author are " +books.toString());
	    System.out.println("First author is " +books.get(0).toString());
	    System.out.println("Second author is " +books.get(1).toString());
	    
	    
	    //To print prices
	    NodeChildrenImpl prices = response.then().extract().path("bookstore.book.price");
		
		System.out.println("All the prices are " +prices.toString());
		
	    System.out.println("First price:paperback is " +prices.get(0).children().get("paperback"));
	    System.out.println("First price:hardcover is " +prices.get(0).children().get("hardcover"));
	    
	    System.out.println("Second book price is " +prices.get(1).toString());
	}
}
