package com.example.config;

import com.example.awsKeys.AWSKeys;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class DynamoDBConfiguration {

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(
			buildAmazonDynamoDB()
		);
	}

	private AmazonDynamoDB buildAmazonDynamoDB() {
		return AmazonDynamoDBClientBuilder
			.standard()
			.withEndpointConfiguration(
				new AwsClientBuilder.EndpointConfiguration(
					"dynamodb.us-east-1.amazonaws.com",
					"us-east-1"
				)
			)
			.withCredentials(
				new AWSStaticCredentialsProvider(
					new BasicAWSCredentials(
						AWSKeys.awsAccessKey,
						AWSKeys.awsSecretKey
					)
				)
			)
			.build();
	}
}
