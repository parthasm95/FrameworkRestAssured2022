package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.UpdateUser;
import com.qa.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class UpdateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public/v2/users/25";
	String token = "aba93e15695fdbb802f1598d4fdc67384453802dd2b264700d0bfb2443184a71";
	
	@Test
	public void updateUserAPIPUTTest(){
		UpdateUser user = new UpdateUser("Allasani Peddana", "newemail99@hmail.com", "active");
		Map<String,String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorizarion", "Bearer "+token);
		
		Response responce = RestClient.doPatch("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(responce.getStatusCode());
		System.out.println(responce.prettyPrint());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
