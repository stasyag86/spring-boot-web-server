package com.evbox.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.evbox.entities.SessionSummary;
import com.evbox.entities.Station;
import com.evbox.status.StatusEnum;


public class StationStorageServiceTest {
	
	private StationStorageService stationStorageService;
	private SummaryService summaryService;
	
	@Before
	public void setup() {
		Map<String, Station> sessionStorage = new ConcurrentHashMap<String, Station>();
		stationStorageService = new StationStorageService();
		summaryService = new SummaryService();
		SessionSummary sessionSummary = new SessionSummary(0,0,0);
		summaryService.setSessionSummary(sessionSummary);
		stationStorageService.setSummaryService(summaryService);
		stationStorageService.setSessionStorage(sessionStorage);
	}
	
	@Test
	public void testAddChargingSession() throws InterruptedException {
		String stationId = "ABC-1234";
		Station station = stationStorageService.addChargingSession(stationId);
		Assert.assertEquals(StatusEnum.IN_PROGRESS, station.getStatus());
		Assert.assertEquals(stationId, station.getStationId());
		
		Assert.assertNotNull(station.getStartedAt());
		Assert.assertNull(station.getStoppedAt());
	}
	
	@Test
	public void testStopChargingSession() throws InterruptedException {
		String stationId = "EFG-1234";
		stationStorageService.addChargingSession(stationId);
		Thread.sleep(2000);
		Station station = stationStorageService.stopChargingSession(stationId);
		Assert.assertEquals(StatusEnum.FINISHED, station.getStatus());
		Assert.assertEquals(stationId, station.getStationId());
		
		long startAtTime = Timestamp.valueOf(station.getStartedAt()).getTime();
		long stopAtTime = Timestamp.valueOf(station.getStoppedAt()).getTime();
		Assert.assertEquals(true, stopAtTime > startAtTime);
	}
	
	@Test
	public void testGetAllChargingSessions() {
		String firstStationId = "AAA-1234";
		String secondStationId = "BBB-1234";
		String thirdStationId = "CCC-1234";
		
		stationStorageService.addChargingSession(firstStationId);
		stationStorageService.addChargingSession(secondStationId);
		stationStorageService.addChargingSession(thirdStationId);
		stationStorageService.stopChargingSession(firstStationId);
		
		List<Station> allChargingSessions = stationStorageService.getAllChargingSessions();
		Assert.assertEquals(3, allChargingSessions.size());
	}
	
	@Test
	public void testGetSummary() {
		String firstStationId = "DDD-1234";
		String secondStationId = "EEE-1234";
		String thirdStationId = "FFF-1234";
		
		stationStorageService.addChargingSession(firstStationId);
		stationStorageService.addChargingSession(secondStationId);
		stationStorageService.addChargingSession(thirdStationId);
		stationStorageService.stopChargingSession(firstStationId);
		
		SessionSummary summary = stationStorageService.getSummary();
		Assert.assertEquals(3, summary.getTotalCount());
		Assert.assertEquals(2, summary.getStartedCount());
		Assert.assertEquals(1, summary.getStoppedCount());
	}
	
	@Test
	public void testAddingTheSameStation() {
		String firstStationId = "GGG-1234";
		String secondStationId = "HHH-1234";
		String thirdStationId = "GGG-1234";
		
		stationStorageService.addChargingSession(firstStationId);
		stationStorageService.addChargingSession(secondStationId);
		stationStorageService.addChargingSession(thirdStationId);
		
		List<Station> allChargingSessions = stationStorageService.getAllChargingSessions();
		Assert.assertEquals(2, allChargingSessions.size());
		
		SessionSummary summary = stationStorageService.getSummary();
		Assert.assertEquals(2, summary.getTotalCount());
		Assert.assertEquals(2, summary.getStartedCount());
	}
	
	@Test
	public void testStoppingTheSameStation() {
		String firstStationId = "GGG-1234";
		String secondStationId = "HHH-1234";
		String thirdStationId = "GGG-1234";
		
		stationStorageService.addChargingSession(firstStationId);
		stationStorageService.addChargingSession(secondStationId);
		
		stationStorageService.stopChargingSession(firstStationId);
		stationStorageService.stopChargingSession(thirdStationId);
		
		List<Station> allChargingSessions = stationStorageService.getAllChargingSessions();
		Assert.assertEquals(2, allChargingSessions.size());
		
		SessionSummary summary = stationStorageService.getSummary();
		Assert.assertEquals(2, summary.getTotalCount());
		Assert.assertEquals(1, summary.getStartedCount());
		Assert.assertEquals(1, summary.getStoppedCount());
	}

}
