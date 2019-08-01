package com.evbox.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.evbox.entities.SessionSummary;

@Service
public class SummaryService implements ISummaryService{
	
	private SessionSummary sessionSummary;
	
	@PostConstruct
	public void init() {
		sessionSummary = new SessionSummary(0,0,0);
	}

	@Override
	public void startCount() {
		synchronized (sessionSummary) {
			sessionSummary.setTotalCount(sessionSummary.getTotalCount() + 1);
			sessionSummary.setStartedCount(sessionSummary.getStartedCount() + 1);
		}
		
	}

	@Override
	public void stopCount() {
		synchronized (sessionSummary) {
			sessionSummary.setStartedCount(sessionSummary.getStartedCount() - 1);
			sessionSummary.setStoppedCount(sessionSummary.getStoppedCount() + 1);
		}
		
	}

	@Override
	public SessionSummary getSummary() {
		return sessionSummary;
	}

	protected void setSessionSummary(SessionSummary sessionSummary) {
		this.sessionSummary = sessionSummary;
	}
	
	

}
