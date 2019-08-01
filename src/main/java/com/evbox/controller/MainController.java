package com.evbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.evbox.entities.SessionSummary;
import com.evbox.entities.Station;
import com.evbox.entities.SubmitChargingStation;
import com.evbox.services.IStationStorageService;

@RestController
@RequestMapping("/evbox")
public class MainController {
	
	@Autowired
	private IStationStorageService stationStorageService;
	
	@RequestMapping(value = "/chargingSessions", method = RequestMethod.POST) 
	public Station createNewChargingSession(@RequestBody SubmitChargingStation newChargingStation) {
		return stationStorageService.addChargingSession(newChargingStation.getStationId());
	}
	
	@RequestMapping(value = "/chargingSessions/{id}", method = RequestMethod.PUT) 
	public Station stopChargingSession(@PathVariable String id) {
		return stationStorageService.stopChargingSession(id);
	}
	
	@RequestMapping("/chargingSessions")
	public List<Station> getListOfStations() {
		return stationStorageService.getAllChargingSessions();
	}
	
	@RequestMapping("/chargingSessions/summary")
	public SessionSummary getSummery() {
		return stationStorageService.getSummary();
	}

}
