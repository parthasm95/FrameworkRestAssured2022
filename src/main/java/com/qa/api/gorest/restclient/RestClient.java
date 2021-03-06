package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all the http methods which will call the APIs and having
 * generic methods for getting the response and fetch the values from response
 * 
 * 
 * @author parth
 *
 */

public class RestClient {

	// HTTP Methods : Get, POST, PUT, DELETE
	
	/**
	 * This Method is used to call GET APIs
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramMap
	 * @param log
	 * @return this method returning response from the GET call
	 */
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String,String> token,
			Map<String, String> paramMap, boolean log) {
		if(setBaseURI(baseURI)) {
		RequestSpecification request = createRequest(contentType, token, paramMap, log);
		return getResponse("GET", request, basePath);
		}
		return null;

	}
	/**
	 * This Method is used to call POST APIs
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramMap
	 * @param log
	 * @param obj : Payload
	 * @return this method returning response from the POST call,
	 */
	@Step("Post call with {0}, {1}, {2}, {3}, {4}, {5}, {6}")
	public static Response doPost(String contentType, String baseURI, String basePath, Map<String,String> token,
			Map<String, String> paramMap, boolean log, Object obj) {
		if(setBaseURI(baseURI)) {
		RequestSpecification request = createRequest(contentType, token, paramMap, log);
		addRequestPayLoad(request, obj);
		return getResponse("POST", request, basePath);
		}
		return null;
	}
	
	/**
	 * 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramMap
	 * @param log
	 * @param obj
	 * @return
	 */
	public static Response doPatch(String contentType, String baseURI, String basePath, Map<String,String> token,
			Map<String, String> paramMap, boolean log, Object obj) {
		if(setBaseURI(baseURI)) {
		RequestSpecification request = createRequest(contentType, token, paramMap, log);
		addRequestPayLoad(request, obj);
		return getResponse("PUT", request, basePath);
		}
		return null;
	}
	
	private static void addRequestPayLoad(RequestSpecification request, Object obj) {
		
		if(obj instanceof Map) {
			request.formParams((Map<String,String>)obj);
		}else {
			String jsonPayload = TestUtil.getSerializedJSON(obj);
			request.body(jsonPayload);
		}
	}

	private static boolean setBaseURI(String baseURI) {
		if(baseURI==null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct baseURI");
			return false;
		}
		try {
		RestAssured.baseURI = baseURI;
		return true;
		} catch (Exception e) {
			System.out.println("Some exception got occured while assining the base URI with Rest Assured...");
			return false;
		}
	}

	private static RequestSpecification createRequest(String contentType, Map<String,String> token, Map<String, String> paramsMap,
			boolean log) {

		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (token.size()>0) {
			//request.header("Authorization", "Bearer " + token);
			request.headers(token);
		}

		if (!(paramsMap == null)) {
			request.queryParams(paramsMap);
		}
		
		if(contentType != null) {
			
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			} else if (contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File("C://Users//parth//OneDrive//Pictures//beyourself.jpg"));
			}
		}

		return request;

	}
	
	//experiment code
	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {

		Response response = null;

		switch (httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;

		case "POST":
			response = request.post(basePath);
			break;

		case "PUT":
			response = request.put(basePath);
			break;
			
		case "PATCH":
			response = request.patch(basePath);
			break;

		case "DELETE":
			response = request.delete(basePath);
			break;

		default:
			System.out.println("Please pass correct http method");
			break;
		}

		return response;
	}

	/*
	 * public static Response getResponse(String httpMethod, RequestSpecification
	 * request, String basePath) {
	 * 
	 * return executeAPI(httpMethod, request, basePath);
	 * 
	 * }
	 * 
	 * public static Response executeAPI(String httpMethod, RequestSpecification
	 * request, String basePath) {
	 * 
	 * Response response = null;
	 * 
	 * switch (httpMethod) { case "GET": response = request.get(basePath); break;
	 * 
	 * case "POST": response = request.post(basePath); break;
	 * 
	 * case "PUT": response = request.put(basePath); break;
	 * 
	 * case "DELETE": response = request.delete(basePath); break;
	 * 
	 * default: System.out.println("Please pass correct http method"); break; }
	 * 
	 * return response; }
	 * 
	 */

}
