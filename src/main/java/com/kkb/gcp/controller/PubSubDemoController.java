package com.kkb.gcp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.kkb.gcp.model.JobState;
import com.kkb.gcp.model.Params;
import com.kkb.gcp.model.User;
import com.kkb.gcp.repository.JobStateRepository;
import com.kkb.gcp.repository.ParamsRepository;
import com.kkb.gcp.repository.UserRepository;
import com.kkb.gcp.util.Utils;

@RestController
public class PubSubDemoController {
	
	String message;
	
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    JobStateRepository jobStateRepository;

    @Autowired
    ParamsRepository paramsRepository;
    
	
	@GetMapping("getMessage")
	public String getMessage() {
		return "Message from GCP "+message;
	}
	
	@Bean
	public PubSubInboundChannelAdapter messageAdapter (@Qualifier("inputChannel") MessageChannel inputChannel, PubSubTemplate template) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(template, "kkb481-topic-sub");
		adapter.setOutputChannel(inputChannel);
		return adapter;
	}
	
	@Bean
	MessageChannel inputChannel() {
		return new DirectChannel();
	}
	
	@ServiceActivator(inputChannel = "inputChannel")
	public void receiveMessage(String paylod) {
		System.out.println("Received message:   "+paylod);
		this.message = paylod;
	}
	
	
	@PostMapping("/api/account/history")
	public ResponseEntity<JobState> setMessage(@RequestBody Params params) throws IOException, InterruptedException {
		String paramsString = params.toString();
		System.out.println("get params from request: "+paramsString);
		Params paramsEntity = paramsRepository.save(params);
		JobState jobState = new JobState();
		jobState.setState("QUEUED");
		jobState.setCreated_ts(Timestamp.from(Instant.now()));
		jobState.setUpdated_ts(Timestamp.from(Instant.now()));
		jobState.setParamsObject(paramsEntity);
		JobState jobStateEntity = jobStateRepository.save(jobState);
		Utils.publishWithErrorHandle(jobStateEntity.toString());
		return new ResponseEntity<JobState>(jobState,HttpStatus.CREATED);
	}
	

	
	
}
