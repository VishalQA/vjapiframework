package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.*;

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

public class Stepdefinition {
	
	RequestSpecification xresponse;
	ResponseSpecification responsespec;
	Response yresponse;
	
	@Given("Add place payload")
	public void add_place_payload() {



		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		Addplace p = new Addplace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setName("Frontline house");
		p.setWebsite("http://google.com");
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		
		p.setTypes(mylist);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		

		 xresponse = given()
		.spec(req)
		.body(p);
		
		
		
	}


	

	@When("call {string} with POST method")
	public void call_with_post_method(String string) {
		
	            yresponse = xresponse.when()
				.post("/maps/api/place/add/json")
				.then()
				.spec(responsespec)
				.extract()
				.response();


		
	}
	@Then("API succesfull with status code {int}")
	public void api_succesfull_with_status_code(Integer int1) {
	   
		assertEquals(yresponse.getStatusCode(),200);
		
		
	}
	@Then("{string} is {string}")
	public void is(String Keyvalue, String Expectedvalue) {
	   
		String respa = yresponse.asString();
		JsonPath js = new JsonPath(respa);
		assertEquals(js.get(Keyvalue).toString(), Expectedvalue);
	}



}
