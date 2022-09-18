package com.maybank.maybank.beans;

/**
 * 
 * @author Austin Teck
 * Utility POJO class to hold ISS location 3rd party call
 *
 */
public class IssPosition {

	private Double latitude;
    private Double longitude;
    
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public String toString() {
		return "\n{ Latitude: " + latitude + ", Longitude: " + longitude + " }";
	}
    
    
}
