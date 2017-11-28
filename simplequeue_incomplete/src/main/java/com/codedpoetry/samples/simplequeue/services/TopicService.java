package com.codedpoetry.samples.simplequeue.services;

import java.util.Set;

import com.codedpoetry.samples.simplequeue.entities.Message;
import com.codedpoetry.samples.simplequeue.entities.Topic;

public interface TopicService {
	
	Topic getTopic(String name);
	
	Topic createTopic(String name);
	
	Set<Topic> getAllTopics();
	
	Message pop(String topic);
	
	Message push(String topic, String message);

}
