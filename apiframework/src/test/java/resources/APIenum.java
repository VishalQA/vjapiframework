package resources;

public enum APIenum {
	
	addplaceAPI("/maps/api/place/add/json"),
	getplaceAPI("/maps/api/place/get/json"),
	deleteplaceAPI("/maps/api/place/delete/json");
	
	private String enumresource;

	
	APIenum(String enumresource){
		this.enumresource = enumresource;
	}
	
	public String getresource() {
	
		return enumresource;
	}
}
