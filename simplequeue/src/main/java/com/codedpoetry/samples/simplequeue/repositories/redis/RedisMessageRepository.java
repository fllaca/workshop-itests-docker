package com.codedpoetry.samples.simplequeue.repositories.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.codedpoetry.samples.simplequeue.entities.Message;
import com.codedpoetry.samples.simplequeue.repositories.MessageRepository;

@Repository
public class RedisMessageRepository implements MessageRepository{

	@Autowired
	private StringRedisTemplate template;
	
	@Override
	public Message pop(String topic) {
		
		String message = template.opsForList().rightPop(topic);
		Message result = null;
		if (message != null) {
			result = new Message(message);
		}
		return result;
	}

	@Override
	public Message push(String topic, String message) {
		template.opsForList().leftPush(topic,message);
		return new Message(message);
	}

}
