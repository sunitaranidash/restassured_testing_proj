package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	//@Test
	void testCookies()
	{
		given()
		.when()
		.get("https://www.google.com/")
		.then()
		.log().all();
	}
	//value of single cookie
	//@Test
	void getCookieInfo()
	{
		Response res=given()
				.when()
				.get("https://www.google.com/");
		String cookie_value=res.getCookie("AEC");
		System.out.println("values of cookie"+ cookie_value);
	}
//value of all cookie
	@Test
	void getAllCookiesInfo()
	{
		Response res= given()
				.when()
				.get("https://www.google.com/");
				Map<String,String> cookies_values=res.getCookies();
				for(String k:cookies_values.keySet())
				{
					String cookie_value=res.getCookie(k);
					System.out.println(k+"      "+cookie_value);
				}
	}
}
