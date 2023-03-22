package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	//@Test
	void testXMLResponse()
	{
	given()
	.when()
	.get("http://restapi.adequateshop.com/api/Traveler?page=1")
	.then()
	.statusCode(200)
	.header("Content-Type","application/xml; charset=utf-8")
	.body("TravelerinformationResponse.page",equalTo("1"))
	.body("TravelerinformationResponse.travelers.Travelerinformation[0].name",equalTo("Developer"));
	}
	//@Test
	void testXMLResponseData()
	{
		Response res=given()
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-type"),"application/xml; charset=utf-8");
		
		String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		String travelerName=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelerName, "Developer");
	}
@Test
void testXMLResponseBody()
{
	Response res=given()
			.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
	XmlPath xmlobj=new XmlPath(res.asString());

	List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
	Assert.assertEquals(travellers.size(),10);
	List<String> traveller_names=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
	boolean status=false;
	for(String travellername:traveller_names)
	{
		//System.out.println(travellername);
		if(travellername.equals("Developer"))
		{
			status=true;
			break;
			
		}
		Assert.assertEquals(status, true);
		
	}
	
	
	
	
}
}
