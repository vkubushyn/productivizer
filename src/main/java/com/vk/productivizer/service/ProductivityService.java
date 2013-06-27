package com.vk.productivizer.service;

import java.util.Date;
import java.util.List;

import com.vk.productivizer.zk.ProductivityEntry;

public interface ProductivityService {
	/**
	 * Gets the productivity entries in the requested time period.
	 * @param from
	 *          The date of the earliest entry to get, inclusive.
	 * @param to
	 *          The date of the latest entry to get, exclusive.
	 * @return
	 *          A list of entries, sorted by date.
	 */
	List<ProductivityEntry> getProductivityEntries(Date from, Date to);
	
	/**
	 * Adds the productivity entry to the record,
	 * and recalculates available hours.
	 * 
	 * @param entry
	 *          The entry to get.
	 */
	void addEntry(ProductivityEntry entry);
}