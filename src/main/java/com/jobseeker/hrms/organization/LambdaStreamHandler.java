package com.jobseeker.hrms.organization;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class LambdaStreamHandler implements RequestStreamHandler {

	private static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	private final MongoClient mongoClient;

	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(OrganizationHrmsApiApplication.class);
		} catch (ContainerInitializationException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}

	public LambdaStreamHandler() {
		String uriMongo = System.getenv("URL_DB_CONNECTION");
		mongoClient = MongoClients.create(
				MongoClientSettings.builder()
						.applyConnectionString(new ConnectionString(uriMongo))
						.applyToClusterSettings(builder ->
								builder.serverSelectionTimeout(12, TimeUnit.SECONDS))
						.applyToSocketSettings(builder ->
								builder.connectTimeout(10, TimeUnit.SECONDS)
										.readTimeout(15, TimeUnit.SECONDS))
						.build()
		);
	}

	@Override
	public void handleRequest(
			InputStream inputStream,
			OutputStream outputStream,
			Context context
	) throws IOException {
		handler.proxyStream(inputStream, outputStream, context);
	}

}