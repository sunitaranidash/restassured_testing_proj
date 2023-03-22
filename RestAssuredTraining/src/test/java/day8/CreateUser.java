package day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
public class CreateUser {
@Test
void test_createUser(ITestContext context)
{
	Faker faker=new Faker();
	JSONObject data= new JSONObject();
	data.put("name",faker.name().fullName());
	data.put("gender", "Male");
	data.put("email",faker.internet().emailAddress());
	data.put("status","inactive");
	String bearerToken="581449830d3c9e61b38060c876986ff095f783ab4b2b3c911c3353fac5d2e993";
	int id=given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
	System.out.println("Generated id is:"+id);
	context.getSuite().setAttribute("user_id",id);
	
	
}
}
