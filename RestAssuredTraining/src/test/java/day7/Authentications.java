package day7;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
public class Authentications {
	//@Test
	void testBasicAuthentication()
	{
		given()
		.auth().basic("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
	.statusCode(200)
	.body("authenticated", equalTo(true))
	.log().all();
	}
	//@Test
	void testDigestAuthentication()
	{
		given()
		.auth().digest("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
	.statusCode(200)
	.body("authenticated", equalTo(true))
	.log().all();
	}
	//@Test
	void testPreemptiveAuthentication()
	{
		given()
		.auth().preemptive().basic("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
	.statusCode(200)
	.body("authenticated", equalTo(true))
	.log().all();
	}
	//@Test
	void testBearerTokenAuthentication()
	{
		String bearerToken="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";
		given()
		.headers("Authorization","Bearer "+bearerToken)
		.when()
		.get("https://api.github.com/user/repos")
		.then()
	.statusCode(200)
	
	.log().all();
	}
	//@Test
	void testOAuth2Authentication()
	{
		
		given()
		.auth().oauth2("5059edcbcd7326157af3")
		.when()
		.get("https://api.github.com/users/mojombo")
		.then()
	.statusCode(200)
	
	.log().all();
	}
	@Test
	void testAPIKeyAuthentication() {
		given()
		.queryParam("appid", "5d67a1102fecd13030b97e31652d7dd0")
		.when()
		.get("https://api.openweathermap.org/data/2.5/weather?q=Canada&APPID=appid")
		.then()
		.statusCode(200)
		.log().all();
		
	}
}
