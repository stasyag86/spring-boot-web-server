package com.evbox.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.evbox.entities.SessionSummary;

public class SummaryServiceTest {

	private SummaryService summaryService;
	
	@Before
	public void setup() {
		summaryService = new SummaryService();
		SessionSummary sessionSummary = new SessionSummary(0,0,0);
		summaryService.setSessionSummary(sessionSummary);
	}
	
	@Test
	public void testStartCount() {
		summaryService.startCount();
		summaryService.startCount();
		summaryService.startCount();
		
		SessionSummary summary = summaryService.getSummary();
		
		Assert.assertEquals(3, summary.getTotalCount());
		Assert.assertEquals(3, summary.getStartedCount());
	}
	
	@Test
	public void testStopCount() {
		summaryService.startCount();
		summaryService.startCount();
		summaryService.startCount();
		summaryService.stopCount();
		summaryService.stopCount();
		
		SessionSummary summary = summaryService.getSummary();
		
		Assert.assertEquals(3, summary.getTotalCount());
		Assert.assertEquals(1, summary.getStartedCount());
		Assert.assertEquals(2, summary.getStoppedCount());
	}
}
