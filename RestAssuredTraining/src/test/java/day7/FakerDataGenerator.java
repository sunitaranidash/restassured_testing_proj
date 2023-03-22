package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
@Test
void testGenerateDummyData()
{
	Faker faker=new Faker();
	
	String fullname=faker.name().fullName();
	String firstname=faker.name().firstName();
	 String lastname=faker.name().lastName();
	String username=faker.name().username();
	String password=faker.internet().password();
	String phoneno=faker.phoneNumber().cellPhone();
	String email=faker.internet().safeEmailAddress();
	System.out.println("Full Name:"+firstname);
	
	System.out.println("First Name:"+firstname);
	System.out.println("Last Name:"+lastname);
	System.out.println("UserName:"+username);
	System.out.println("Password:"+password);
	System.out.println("Phone Number:"+phoneno);
	System.out.println("Email:"+email);
	
}
}
