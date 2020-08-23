package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.Location;

public class TestDataBuild {
	
	public Addplace addPlacePayload(String name, String language , String address) {
		
		
		Addplace p = new Addplace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setName(name);
		p.setWebsite("http://google.com");
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe parkb");
		mylist.add("shopb");
		
		p.setTypes(mylist);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
		
	}
	 
	public String deleteplacepayload(String placeid) {
		
		return "{\r\n \"place_id\":\""+placeid+"\"\r\n}";
	}

}
