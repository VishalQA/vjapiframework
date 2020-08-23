package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Addplace;
import pojo.Location;
import resources.APIenum;
import resources.TestDataBuild;
import resources.Utils;

public class Stepdefinition extends Utils{
	
	RequestSpecification xresponse;
	ResponseSpecification responsespec;
	Response yresponse;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	
//		responsespec = new ResponseSpecBuilder().expectStatusCode(200)
//				.expectContentType(ContentType.JSON).build();
		 xresponse = given()
		.spec(requestSpecification())
		.body(data.addPlacePayload(name,language,address));
	}

	@When("call {string} with {string} method")
	public void call_with_method(String enumresource, String method) {
		
		APIenum enumvalue = APIenum.valueOf(enumresource);
		System.out.println(enumvalue.getresource());
		
		responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if (method.equalsIgnoreCase("Post")) {
			  yresponse = xresponse.when()
					  .post(enumvalue.getresource());
		}
		else if (method.equalsIgnoreCase("Get")) {
			yresponse = xresponse.when()
					  .get(enumvalue.getresource());
		}
		
//	            yresponse = xresponse.when()
//				.post(enumvalue.getresource())
//				.then()
//				.spec(responsespec)
//				.extract()
//				.response();
		
	}
	@Then("API succesfull with status code {int}")
	public void api_succesfull_with_status_code(Integer int1) {
	   
		assertEquals(yresponse.getStatusCode(),200);
		
		
	}
	@Then("{string} is {string}")
	public void is(String Keyvalue, String Expectedvalue) {
	   
//		String respa = yresponse.asString();
//		JsonPath js = new JsonPath(respa);
//		assertEquals(js.get(Keyvalue).toString(), Expectedvalue);
		
		assertEquals(getJsonPath(yresponse,Keyvalue) ,Expectedvalue );
	}


	@Then("verify place_id maps to {string} using {string}")
	public void verify_place_id_maps_to_using(String expectedname, String resource) throws IOException {
	   
		place_id = getJsonPath(yresponse ,"place_id");
		xresponse = given()
			.spec(requestSpecification()).queryParam("place_id",place_id);
		call_with_method(resource , "GET");
		String actualname = getJsonPath(yresponse ,"name");
		assertEquals(actualname, expectedname);
		System.out.println("Actual name is ===>" +actualname);
		System.out.println("Expected name is ====> "  +expectedname);
		
				
	}
	
	@Given("Delete place payload with")
	public void delete_place_payload_with() throws IOException {
	  
		
		xresponse =given()
		.spec(requestSpecification())
		.body(data.deleteplacepayload(place_id));
	}


}
