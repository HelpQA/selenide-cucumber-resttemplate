package org.selenide.examples.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.qa.rest.HttpResponse;
import com.qa.rest.RestClient;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductAppAPIController {

	RestClient res= new RestClient();
	HttpResponse response = new HttpResponse();
	
	
	private String endPointAPI = "http://localhost:8080/";
	
	private String requestProduct = "{\"name\":\"Mobile\",\"price\":600000.0,\"description\":\"Apple Mobile\"}";  
	
	@Given("User generates endpoint")
	public void userGenratesEndPoint() {
		System.out.println("In Given =====================");
	}

	@When("User hit the (.*) with resourse (.*)$")
	public void apiEndpoint(String apiName, String resource) {
		
		Map<String, String> mapHttpHeaders = new HashMap<String, String>();
		mapHttpHeaders.put("Content-Type", "application/json");
		
		String url = endPointAPI + resource;
		
		if (apiName.toLowerCase().contains("post")) {
			response = res.doPost(url, mapHttpHeaders, requestProduct);

		} else if (apiName.toLowerCase().contains("get")) {			
			response = res.doGet(url, mapHttpHeaders);
		} else if(apiName.toLowerCase().contains("delete")) {
			response = res.doDelete(url, mapHttpHeaders);
		} 
		
		System.out.println(response);
	}
	
	@Then("Validate status code is {int}")
	public void validateStatusCode(int code) {
		assertEquals(response.getStatusCode(), code);
	}
}
