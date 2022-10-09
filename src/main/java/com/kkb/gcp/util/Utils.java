package com.kkb.gcp.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

public class Utils {
	static String projectId ="kkb481";
    static String topicId="kkb481-topic";

	public static void publishWithErrorHandle(String message)
		      throws IOException, InterruptedException {
		    TopicName topicName = TopicName.of(projectId, topicId);
		    Publisher publisher = null;

		    try {
		      // Create a publisher instance with default settings bound to the topic
		      publisher = Publisher.newBuilder(topicName).build();

		      //List<String> messages = Arrays.asList("first message", "second message");

		        ByteString data = ByteString.copyFromUtf8(message);
		        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

		        // Once published, returns a server-assigned message id (unique within the topic)
		        ApiFuture<String> future = publisher.publish(pubsubMessage);

		        // Add an asynchronous callback to handle success / failure
		        ApiFutures.addCallback(
		            future,
		            new ApiFutureCallback<String>() {

		              @Override
		              public void onFailure(Throwable throwable) {
		                if (throwable instanceof ApiException) {
		                  ApiException apiException = ((ApiException) throwable);
		                  // details on the API exception
		                  System.out.println(apiException.getStatusCode().getCode());
		                  System.out.println(apiException.isRetryable());
		                }
		                System.out.println("Error publishing message : " + message);
		              }

		              @Override
		              public void onSuccess(String messageId) {
		                // Once published, returns server-assigned message ids (unique within the topic)
		                System.out.println("Published message ID: " + messageId);
		              }
		            },
		            MoreExecutors.directExecutor());
		      
		    } finally {
		      if (publisher != null) {
		        // When finished with the publisher, shutdown to free up resources.
		        publisher.shutdown();
		        publisher.awaitTermination(1, TimeUnit.MINUTES);
		      }
		    }
		  }
}
