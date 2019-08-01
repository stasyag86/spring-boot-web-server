package com.evbox.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evbox.entities.SessionSummary;
import com.evbox.entities.Station;
import com.evbox.status.StatusEnum;

@Service
public class StationStorageService implements IStationStorageService{
	
	@Autowired
	private ISummaryService summaryService;
	
	private Map<String, Station> sessionStorage;
	
	@PostConstruct
	public void init() {
		sessionStorage = new ConcurrentHashMap<String, Station>();
	}
	
	@Override
	public Station addChargingSession(String stationId) {
		Station station = null;
		if (!StringUtils.isEmpty(stationId)) {
			//check it not exist
			if (!sessionStorage.containsKey(stationId)) {
				station = new Station(stationId);
				sessionStorage.put(stationId, station);
				summaryService.startCount();
			}	
		}
		
		return station;
	}
	
	@Override
	public Station stopChargingSession(String stationId) {
		Station station = null;
		if (sessionStorage.containsKey(stationId)) {
			station = sessionStorage.get(stationId);
			//check its not stopped already
			if (station.getStatus() != StatusEnum.FINISHED) {
				station.setStoppedAt(LocalDateTime.now());
				station.setStatus(StatusEnum.FINISHED);
				summaryService.stopCount();
			}
			
		}
		return station;
	}
	
	@Override
	public List<Station> getAllChargingSessions() {
		List<Station> stations = new ArrayList<Station>();
		for (String stationId : sessionStorage.keySet()) {
			stations.add(sessionStorage.get(stationId));
		}
		return stations;
	}
	
	@Override
	public SessionSummary getSummary() {
		return summaryService.getSummary();
	}

	protected void setSummaryService(ISummaryService summaryService) {
		this.summaryService = summaryService;
	}

	protected void setSessionStorage(Map<String, Station> sessionStorage) {
		this.sessionStorage = sessionStorage;
	}
	
	
	
	

}
