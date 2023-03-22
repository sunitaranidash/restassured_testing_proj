package day4;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ParsingJSONResponseData {
//@Test
void testJsonResponse()
{
	given()
	.contentType("ContentType.JSON")
	.when()
	.get("http://localhost:3000/store")
	.then()
	.statusCode(200)
	.header("Content-Type","application/json; charset=utf-8")
	.body("book[4].title",equalTo("The Lord of the rings"))
	.log().all();
	
}
//@Test
void testJsonResponseBodyData()
{
	Response res=given()
			.contentType(ContentType.JSON)
			.when()
			.get("http://localhost:3000/book");
	Assert.assertEquals(res.getStatusCode(),200);
	Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
	String bookname=res.jsonPath().get("book[4].title").toString();
	Assert.assertEquals(bookname,"The Lord of the rings");
}
@Test
void testJsonResponseBody()
{
	
	Response res=given()
			.contentType(ContentType.JSON)
			.when()
			.get("http://localhost:3000/book");
	JSONObject jo=new JSONObject(res.toString());
	boolean status=false;
	for(int i=0;i<jo.getJSONArray("book").length();i++)
	{
		String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		//System.out.println(bookTitle);
		if(bookTitle.equals("The Lord of the rings"))
		{
			status=true;
			break;
		}
		
	}
	Assert.assertEquals(status, true);
	
	
}
}
