package com.goeuro.vo;

public class CityDetails {

	private String _id;
	private String name;
	private String type;
	private double longitude;
	private double latitude;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public String toString() {
	    StringBuilder result = new StringBuilder();
	    result.append(this.getClass().getName() + " Object {");
	    result.append(" _id: " + _id + ",");
	    result.append(" name: " + name + ",");
	    result.append(" type: " + type + "," );
	    result.append(" latitude: " + latitude + ",");
	    result.append(" longitude: " + longitude);
	    result.append("}");

	    return result.toString();
	  }
}
