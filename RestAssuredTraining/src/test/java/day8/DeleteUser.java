package day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	@Test
	void test_deleteUser(ITestContext context)
	{
		String bearerToken="581449830d3c9e61b38060c876986ff095f783ab4b2b3c911c3353fac5d2e993";
		int id=(Integer) context.getSuite().getAttribute("user_id");
		given()
		  .headers("Authorization","Bearer "+bearerToken)
		  .pathParam("id",id)
		.when()
		  .delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
		  .statusCode(204)
		  .log().all();
	}
}
