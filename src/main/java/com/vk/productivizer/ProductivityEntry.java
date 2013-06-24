package com.vk.productivizer;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class ProductivityEntry {
	private Date from;
	private Date to;
	private ActivityType activityType;
	private String description;
	
	public Date getFrom() {
		return from;
	}
	
	public void setFrom(Date from) {
		this.from = from;
	}
	
	public Date getTo() {
		return to;
	}
	
	public void setTo(Date to) {
		this.to = to;
	}
	
	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDuration() {
		Period period = new Period(new DateTime(from), new DateTime(to));
		return String.format("%d days %d hours %d minutes", period.getDays(), 
				period.getHours(), period.getMinutes());
	}
}