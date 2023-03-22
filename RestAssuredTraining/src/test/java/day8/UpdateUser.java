package day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	@Test
	void test_updateUser(ITestContext context)
	{
		Faker faker=new Faker();
		JSONObject data= new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("gender", "Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status","active");
		String bearerToken="581449830d3c9e61b38060c876986ff095f783ab4b2b3c911c3353fac5d2e993";
		int id=(Integer) context.getSuite().getAttribute("user_id");
		
		given()
				.headers("Authorization","Bearer "+bearerToken)
				.contentType("application/json")
				.body(data.toString())
				.pathParam("id",id)
			.when()
				.put("https://gorest.co.in/public/v2/users/{id}")
			.then()
			.statusCode(200)
			.log().all();
		
		
		
		
		
	}
}
