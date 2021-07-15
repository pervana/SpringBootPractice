package com.pervanadurdyyeva.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TourAgencyOffer {
	@JsonIgnoreProperties(ignoreUnknown = true)   /*ignore errors during marshalling process */
	public static final String SOURCE = "TravelAgency";
	
	private String code;
	private String name;
	private long duration;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}

}
