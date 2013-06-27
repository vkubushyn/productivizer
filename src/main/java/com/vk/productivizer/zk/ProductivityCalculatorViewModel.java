package com.vk.productivizer.zk;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;

public class ProductivityCalculatorViewModel {
	private static final String DATE_VALIDATION_ERROR = "The \"to\" date must be a later date than the \"from\" date!";
	private static final String VALIDATION_ERROR_TITLE = "Validation Error";
	
	private long currentBalance;
	private ProductivityEntry entry;
	
	public ProductivityEntry getEntry() {
		return entry;
	}
	
	public String getCurrentBalance() {
		Period period = new Period(currentBalance);
		return String.format("%d hours %d minutes", period.getHours(), period.getMinutes());
	}
	
	public ActivityType[] getActivityTypes() {
		return ActivityType.values();
	}
	
	@DependsOn({"entry.to", "entry.from"})
	public String getDuration() {
		return entry.getDuration();
	}
	
	@Init
	public void init() {
		entry = new ProductivityEntry();
		entry.setActivityType(ActivityType.LEISURE);
		DateTime now = new DateTime();
		entry.setTo(now.toDate());
		entry.setFrom(now.minusHours(1).toDate());
	}
	
	@Command("save")
	@NotifyChange({"entry", "currentBalance"})
	public void save() {
		if(validate()) {
			//save to dynamoDb
			
		}
	}
	
	private boolean validate() {
		if(entry.getFrom().after(entry.getTo())) {
			Clients.alert(DATE_VALIDATION_ERROR, VALIDATION_ERROR_TITLE, "ERROR");
			return false;
		}
		
		return true;
	}
}