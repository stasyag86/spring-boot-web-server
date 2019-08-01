package com.evbox.services;

import java.util.List;

import com.evbox.entities.SessionSummary;
import com.evbox.entities.Station;

public interface IStationStorageService {

	public Station addChargingSession(String stationId);
	
	public Station stopChargingSession(String stationId);
	
	public List<Station> getAllChargingSessions();
	
	public SessionSummary getSummary();
}
