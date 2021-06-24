package utils;

/* Sahil Singla / sahil.9singla@gmail.com
 * RestAssured Utility Class to control...
 * ...different methods of APIs
 */
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

/*
 * -RequestBuilder to construct a request specification... 
 * ...which contains method to set cookies, headers, body, authentication, etc.
 */
	RequestSpecBuilder builder = new RequestSpecBuilder();
	private static RequestSpecification requestSpecification, request;
	private static Logger Log = LogManager.getLogger();

/*
 * -Constructor to build requestSpecification using RequestSpecBuilder. 
 * -build() method is used to build the constructor.
 */
	public RestAssuredUtils(String uri) {
		builder.setBaseUri(uri);
	}

/*
 * -Executing API Request such as Get, Post, Put, Delete > Case when there is no parameter and body.
 * 
 * @Param:
 * -requestMethod: Set Method as per request.
 * -token: In case: No token > set token as null. 
 *  Note- Token depends on type of authentication can be adapt accordingly.
 *  
 *  @Return: - The response Payload.
 */
	public Response executeRequestAPI(String requestMethod, String token) {

		if (token != null) {
			builder.addHeader("Authorization", "Bearer" + token);
		} else {
			Log.info("No token requires for this request");
		}

		requestSpecification = builder.build();
		request = RestAssured.given().contentType(ContentType.JSON).spec(requestSpecification);

		if (requestMethod.equalsIgnoreCase("GET")) {
				return request.get();
		}	else if (requestMethod.equalsIgnoreCase("POST")) {
				return request.post();
		}	else if (requestMethod.equalsIgnoreCase("PUT")) {
				return request.put();
		}	else if (requestMethod.equalsIgnoreCase("DELETE")) {
				return request.delete();
		}
			return null;
	}

/*
 * -Execute API with Path Parameters 
 * 
 * @Param:
 * -pathParameter: Set the pathParameter as Map object. 
 * -method: Set Method as per request. 
 * -token: In case: No token > set token as null.
 * 
 * @Return: - The response Payload.
 */
	public Response ExecuteAPIWithPathParm(Map<String, String> pathParameter, String method, String token) {
		builder.addPathParams(pathParameter);
		return executeRequestAPI(method, token);
	}

/*
 * -Execute API with Query Parameters 
 * 
 * @Param:
 * -queryParameter: Set the queryParameter as Map object. 
 * -method: Set Method as per request. 
 * -token: In case: No token >set token as null.
 * 
 * @Return: - The response Payload.
 */
	public Response ExecuteAPIWithQueryParm(Map<String, String> queryParameter, String method, String token) {
		builder.addQueryParams(queryParameter);
		return executeRequestAPI(method, token);
	}

/*
 * -Execute API with Parameters and Body. 
 * 
 * @Param:
 * -pathParameter: Set the pathParameter as Map object and > Set queryParameter value as null. 
 * -queryParameter: Set the queryParameter as Map object and > Set pathParameter value as null.
 * -method: Set Method as per request. 
 * -token: In case: No token > set token as null. 
 * -body: Set body as request object, otherwise if not set as null.
 * 
 * @Return: - The response Payload.
 */
	public Response ExecuteAPIWithBodyAndParams(Map<String, String> queryParameter, Map<String, String> pathParameter,
			String method, String token, Object body) {
		builder.setBody(body);
		if (queryParameter != null) {
			builder.addPathParams(queryParameter);
		} else if (pathParameter != null) {
			builder.addPathParams(pathParameter);
		} else {
			Log.info("Executing operation without Query and Path Parameter");
		}
		return executeRequestAPI(method, token);
	}

/* Token depends on type of authentication. So below method can be adapt accordingly.
 * To get the authentication token i.e oauth2.0, basic to get the token. 
 * -its depend on project to project how do you setup authentication. 
 * -Using this method, you can get the token and can be used while sending request. 
 * 
 * @Param:
 * -method: Set Method as per request. 
 * -body: Set body as request object, otherwise if not set as null.
 * 
 * @Return: Token.
 */
	public String authToken(String method, Object body) {
		builder.setBody(body);
		return executeRequestAPI(method, null).getBody().jsonPath().getString("access-token");
	}
}
