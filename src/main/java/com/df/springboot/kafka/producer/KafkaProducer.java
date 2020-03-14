package com.df.springboot.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.df.springboot.kafka.User;

@RestController
@RequestMapping("kafka")
public class KafkaProducer {

//	@Autowired
//	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	KafkaTemplate<String, User> kafkaJsonTemplate;
	
	private static final String TOPIC = "KafkaTopic1";
	
//	@GetMapping("/produce/{message}")
//	public String publishToKafka(@PathVariable("message") final String message) {
//		kafkaTemplate.send(TOPIC, message);
//		return "Published message successfully : " + message;
//	}
	
	@GetMapping("/produceJson/{message}")
	public String publishJsonToKafka(@PathVariable("message") final String name) {
		//kafkaJsonTemplate.send(TOPIC, new User(name, "Developer", 40000F));
		//partitions assigned: [kafkaTopic1-2, kafkaTopic1-1, kafkaTopic1-0, kafkaTopic2-0]
		kafkaJsonTemplate.send(TOPIC, null, "123", new User(name, "Developer", 40000F));
		kafkaJsonTemplate.send(TOPIC, null, "456", new User(name, "Developer", 50000F));
		kafkaJsonTemplate.send(TOPIC, null, "789", new User(name, "Developer", 60000F));
		return "Published JSON message successfully for user : " + name;
	}
}
