package com.codedpoetry.samples.simplequeue.repositories;

import com.codedpoetry.samples.simplequeue.entities.Message;

public interface MessageRepository {
	
	Message pop(String topic);
	
	Message push(String topic, String message);

}
