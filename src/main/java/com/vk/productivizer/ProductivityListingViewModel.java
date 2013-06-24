package com.vk.productivizer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.Init;

public class ProductivityListingViewModel {
	private List<ProductivityEntry> productivityListing;
	
	public List<ProductivityEntry> getProductivityListing() {
		return productivityListing;
	}

	@Init
	public void init() {
		productivityListing = new ArrayList<ProductivityEntry>();
		//TODO: load entries from DynamoDb
		ProductivityEntry entry = new ProductivityEntry();
		entry.setFrom(new Date());
		entry.setTo(new Date());
		entry.setActivityType(ActivityType.LEISURE);
		entry.setDescription("Slacked off!");
		productivityListing.add(entry);
		entry = new ProductivityEntry();
		entry.setFrom(new Date());
		entry.setTo(new Date());
		entry.setActivityType(ActivityType.PRODUCTIVE);
		entry.setDescription("Coded like a madman!");
		productivityListing.add(entry);
	}
}