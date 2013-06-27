package com.vk.productivizer.util;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.common.collect.Lists;

@Component("configurationUtil")
public class ConfigurationUtil implements ResourceLoaderAware {
	private static final String S3_PROPERTIES_BUCKET_PROPERTY = "PARAM1";
	private static final String S3_PROPERTIES_FILENAME = "config.properties";
	private static final String AWS_SECRET_KEY_PROPERTY = "AWS_SECRET_KEY";
	private static final String AWS_ACCESS_KEY_ID_PROPERTY = "AWS_ACCESS_KEY_ID";
	private static final String DEFAULT_CONFIG_LOCATION = "classpath:default-config.properties";
	
	private ResourceLoader resourceLoader;
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	public Properties[] getApplicationProperties(boolean loadS3Bucket) throws IOException {
		List<Properties> applicationProperties = Lists.newArrayList();
		
		//first load the default configs
		Resource defaultConfig = resourceLoader.getResource(DEFAULT_CONFIG_LOCATION);
		Properties defaultProps = new Properties();
		defaultProps.load(defaultConfig.getInputStream());
		applicationProperties.add(defaultProps);
		
		if(loadS3Bucket) {
			applicationProperties.add(loadS3BucketProperties());
		}
		
		return applicationProperties.toArray(new Properties[]{});
	}
	
	private Properties loadS3BucketProperties() throws IOException {
		//obtain properties from S3
		AWSCredentials awsCreds = new BasicAWSCredentials(
				System.getProperty(AWS_ACCESS_KEY_ID_PROPERTY), System.getProperty(AWS_SECRET_KEY_PROPERTY));
		AmazonS3 s3 = new AmazonS3Client(new StaticCredentialsProvider(awsCreds));
		S3Object s3Properties = s3.getObject(new GetObjectRequest(System.getProperty(S3_PROPERTIES_BUCKET_PROPERTY),
				S3_PROPERTIES_FILENAME));
		
		//load the props into the app
		Properties appConfig = new Properties();
		appConfig.load(s3Properties.getObjectContent());
		
		return appConfig;
	}
}