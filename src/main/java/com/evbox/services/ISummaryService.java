package com.evbox.services;

import com.evbox.entities.SessionSummary;

public interface ISummaryService {

	public void startCount();
	
	public void stopCount();
	
	public SessionSummary getSummary();
}
