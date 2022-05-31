package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {
	
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "aba93e15695fdbb802f1598d4fdc67384453802dd2b264700d0bfb2443184a71";
	
	@Test(priority=1)
	public void getAllUserListAPITest() {
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorizarion", "Bearer "+token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		Assert.assertEquals(response.getStatusCode(), 300);
	}
	
	@Test(priority=2)
	public void getUserListWithQueryParamAPITest() {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "2479");
		params.put("gender", "female");
		
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorizarion", "Bearer "+token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
}