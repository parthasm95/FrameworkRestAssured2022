package com.qa.api.gorest.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "aba93e15695fdbb802f1598d4fdc67384453802dd2b264700d0bfb2443184a71";
	
	@DataProvider
	public Object[][] getUserData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object[][] userData = ExcelUtil.getTestData("userdata");
		return userData; 
	}
	
	@Test(dataProvider = "getUserData")
	public void createUserAPIPOSTTest(String name, String email, String gender, String status) {
		User user = new User(name, email, gender, status);
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorizarion", "Bearer "+token);
		Response responce = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(responce.getStatusCode());
		System.out.println(responce.prettyPrint());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	//My Test code
	@Test
	public void createUserAPIPOSTTest1() {
		User user = new User("partha",TestUtil.generateRandomEmail(),"male","active");
		Response responce = RestClient.doPost("JSON", baseURI, basePath, token, null, true, user);
		System.out.println(responce.getStatusCode());
		System.out.println(responce.prettyPrint());
	}
	
	//alternate method
	 @Test
	public void createUserAPIPOSTTest() {
		Random randomNumGenerator = new Random();
		User user = new User("partha","test" + randomNumGenerator.nextInt(1000) +"@gmail.com","male","active");
		Response responce = RestClient.doPost("JSON", baseURI, basePath, token, null, true, user);
		System.out.println(responce.getStatusCode());
		System.out.println(responce.prettyPrint());
	}
	
	*/

}
