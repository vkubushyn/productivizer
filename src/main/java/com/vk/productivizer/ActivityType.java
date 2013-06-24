package com.vk.productivizer;

public enum ActivityType {
	PRODUCTIVE("Productive"), LEISURE("Leisure");
	
	private String displayLabel;
	private ActivityType(String displayLabel) {
		this.displayLabel = displayLabel;
	}
	
	public String getDisplayLabel() {
		return displayLabel;
	}
}