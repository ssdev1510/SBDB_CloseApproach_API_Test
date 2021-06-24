package stepDefinitions;

/* Sahil Singla / sahil.9singla@gmail.com
 * RestAssured Utility Class to control
 * different methods of APIs
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import SBDB_pojo.Root;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.RestAssuredUtils;
import utils.JavaUtils;

public class SBDB_GET_API_StepDef {

	private static RestAssuredUtils restAssuredUtils;
	private String noToken = null;
	private static Response response;
	private static Logger Log = LogManager.getLogger();

	@Given("User set basePath URL {string}")
	public void user_set_base_path_url(String basePath) throws IOException {
		String baseURI = JavaUtils.getConfigProperty("baseURL") + basePath;
		restAssuredUtils = new RestAssuredUtils(baseURI);
		
		Log.info("Setting up the BaseURI to- " + baseURI);
	}

	@When("User calls {string} operation with https request")
	public void user_calls_operation_with_https_request(String method) {
		response = restAssuredUtils.executeRequestAPI(method, noToken);
		
		Log.info("Performing " + method + " method to check the response payload.");
	}

	@Then("User should get status code {string}")
	public void user_should_get_status_code(String statusCode) {
		int statusCodeInt = Integer.parseInt(statusCode);
		Assert.assertEquals(statusCodeInt, response.getStatusCode());
		
		Log.info("Response Status code is: " + response.getStatusCode());
	}
	
	@Then("Response time should be less than {string} second")
	public void response_time_should_be_less_than_second(String responseExpectedTime) {
		int resTime = Integer.parseInt(responseExpectedTime);
		boolean result = false;
		
		if(resTime >= response.getTimeIn(TimeUnit.SECONDS) ) {
			result = true;  }
		else {
			result = false; }
		
		Assert.assertTrue("Response time is greater than expected time.", result);
		
		Log.info("Response Timne is: " + response.getTimeIn(TimeUnit.SECONDS) + " Seconds");
	}

	@Then("User should see booking Signature Version {string} and Source {string}")
	public void user_should_see_booking_signature_version_and_source(String SigVersion, String SigSource) {
		String signatureVersion = response.getBody().jsonPath().get("signature.version");
		String signatureSource = response.getBody().jsonPath().get("signature.source");
		
		Assert.assertEquals(SigVersion, signatureVersion);
		Assert.assertEquals(SigSource, signatureSource);
		
		Log.info("Response Payload having Signature source: " + signatureSource + " and its version is: " + signatureVersion);
	}
	
	@When("User calls {string} operation with query parameters Min Date {string} Max Date {string} Max Distance {string}")
	public void user_calls_operation_with_query_parameters_max_date_min_date_max_distance(String method, String dateMin, String dateMax, String distMax) {
		HashMap<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("date-min", dateMin);
		queryParam.put("date-max", dateMax);
		queryParam.put("dist-max", distMax);
		
		response = restAssuredUtils.ExecuteAPIWithQueryParm(queryParam, method, noToken);
	}
	
	@Then("User should see count as {string}")
	public void user_should_see_count_as(String count) {
		String responseCount = response.getBody().as(Root.class).getCount();
		//String responseCount = response.getBody().jsonPath().get("count");
		Assert.assertEquals(count, responseCount );
		
		Log.info("Response Payload: " + response.asString());
		Log.info("Total response Count is: " + responseCount);
	}

	@When("User calls {string} operation with query parameters")
	public void user_calls_operation_with_query_parameters(String method, DataTable dataTable) {
		List<Map<String, String>> queryData = dataTable.asMaps(String.class, String.class);
		HashMap<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("des", queryData.get(0).get("des"));
		queryParam.put("date-min", queryData.get(0).get("date-min"));
		queryParam.put("dist-max", queryData.get(0).get("dist-max"));
		
		response = restAssuredUtils.ExecuteAPIWithQueryParm(queryParam, method, noToken);
	}
	
	@When("User calls {string} operation with query parameters with asteroid primary designation")
	public void user_calls_operation_with_query_parameters_with_asteroid_primary_designation(String method, DataTable dataTable) {
		List<Map<String, String>> queryData = dataTable.asMaps(String.class, String.class);
		HashMap<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("des", queryData.get(0).get("des"));
		queryParam.put("date-min", queryData.get(0).get("date-min"));
		queryParam.put("date-min", queryData.get(0).get("date-max"));
		queryParam.put("dist-max", queryData.get(0).get("dist-max"));
		
		response = restAssuredUtils.ExecuteAPIWithQueryParm(queryParam, method, noToken);
	}
	
	@Then("User should see Orbit ID as {string}")
	public void user_should_see_orbit_id_as(String orbirtID) { 
		String orbit_id = response.getBody().as(Root.class).getData().get(0).get(1);
		Assert.assertEquals(orbirtID, orbit_id );
		
		Log.info("Response Payload: " + response.asString());
		Log.info("Orbit Id of asteroid primary designation 433 in year 1900 is- " + orbit_id);
	}
}
