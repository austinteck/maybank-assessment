package com.maybank.maybank.beans;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Austin Teck
 * Utility POJO class to hold ISS location 3rd party call
 *
 */
public class IssResponse {
	
	@JsonProperty("iss_position")
    private IssPosition issPosition;
    private String message;
    private Timestamp timestamp;
    
	public IssPosition getIssPosition() {
		return issPosition;
	}
	public void setIssPosition(IssPosition issPosition) {
		this.issPosition = issPosition;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
    
    
}
