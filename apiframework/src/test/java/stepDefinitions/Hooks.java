package stepDefinitions;

import java.io.IOException;
import io.cucumber.java.Before;
import io.cucumber.plugin.event.StepDefinition;

public class Hooks {
	
	@Before("@DeletePlace") 
	public void beforeScenario() throws IOException{
				
		Stepdefinition m = new Stepdefinition();
		if(Stepdefinition.place_id ==null)
		{
		m.add_place_payload_with("Monday", "Tuesday", "Wednesday");
		m.call_with_method("addplaceAPI", "POST");
		m.verify_place_id_maps_to_using("Monday", "getplaceAPI");
		
		}
		
		
		
	}

}
