package day2;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffWaysToCreatePostRequestBody {
	//1)Post body request using HashMap
	//@Test
	void testPostUsingHashMap()
	{
		HashMap data=new HashMap();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		String courseArr[]= {"C","C++"};
		data.put("courses",courseArr);
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
	}
	//2)Post body request using org.json library
	//@Test(priority=1)
	void testPostUsingJsonLibrary()
	{
		JSONObject data=new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		String coursesArr[]= {"C","C++"};
		data.put("courses",coursesArr);
		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
	}
	//3)Post body request using POJO class
	//@Test(priority=1)
	void testPostUsingPOJO()
	{
		Pojo_PostRequest data=new Pojo_PostRequest();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		String coursesArr[]= {"C","C++"};
		data.setCourses(coursesArr);
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
	}
	//4)Post body request using External file
	@Test(priority=1)
	void testPostUsingExternalFile() throws FileNotFoundException
	{
		File f= new File(".\\body.json");
		FileReader fr= new FileReader(f);
		JSONTokener jt= new JSONTokener(fr);
		JSONObject data= new JSONObject(jt);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Scott"))
		.body("location",equalTo("France"))
		.body("phone",equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
		
	}
	@Test(priority=2)
	void testDelete()
	{
		given()
		.when()
		.delete("http://localhost:3000/students/4")
		.then()
		.statusCode(200);
	}

}
