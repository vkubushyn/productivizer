package com.vk.productivizer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.vk.productivizer.zk.ProductivityEntry;

@Service
public class ProductivityServiceImpl implements ProductivityService {

	private AmazonDynamoDBClient dynamoDbClient;
	
	@Autowired
	public void setDynamoDbClient(AmazonDynamoDBClient dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
	}

	public List<ProductivityEntry> getProductivityEntries(Date from, Date to) {
		
		return null;
	}

	public void addEntry(ProductivityEntry entry) {
		
	}
	
}