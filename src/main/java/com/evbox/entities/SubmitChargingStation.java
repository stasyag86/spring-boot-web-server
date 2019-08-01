package com.evbox.entities;

public class SubmitChargingStation {
	
	private String stationId;
	
	public SubmitChargingStation() {}

	public SubmitChargingStation(String stationId) {
		this.stationId = stationId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	
}
