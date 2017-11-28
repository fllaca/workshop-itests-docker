package com.codedpoetry.samples.simplequeue.repositories;

import java.util.Set;

import com.codedpoetry.samples.simplequeue.entities.Topic;

public interface TopicRepository {

	Topic getTopic(String name);
	
	Topic removeTopic(String name);
	
	Topic createTopic(String name);

	Set<Topic> getAllTopics();

}
