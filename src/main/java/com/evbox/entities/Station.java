package com.evbox.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.evbox.status.StatusEnum;

public class Station {
	private UUID id;
	private String stationId;
	private LocalDateTime startedAt;
	private LocalDateTime stoppedAt;
	private StatusEnum status;
	
	

	public Station(String stationId) {
		this.id = UUID.randomUUID();
		this.stationId = stationId;
		this.startedAt = LocalDateTime.now();
		this.status = StatusEnum.IN_PROGRESS;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getStationId() {
		return stationId;
	}
	
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	public LocalDateTime getStartedAt() {
		return startedAt;
	}
	
	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}
	
	public LocalDateTime getStoppedAt() {
		return stoppedAt;
	}
	
	public void setStoppedAt(LocalDateTime stoppedAt) {
		this.stoppedAt = stoppedAt;
	}
	
	public StatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
