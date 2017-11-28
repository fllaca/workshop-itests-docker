package com.codedpoetry.samples.simplequeue.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedpoetry.samples.simplequeue.entities.Message;
import com.codedpoetry.samples.simplequeue.entities.Topic;
import com.codedpoetry.samples.simplequeue.repositories.MessageRepository;
import com.codedpoetry.samples.simplequeue.repositories.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	MessageRepository messageRepository;

	@Override
	public Topic getTopic(String name) {
		return topicRepository.getTopic(name);
	}

	@Override
	public Set<Topic> getAllTopics() {
		return topicRepository.getAllTopics();
	}

	@Override
	public Topic createTopic(String name) {
		return topicRepository.createTopic(name);
	}

	@Override
	public Message pop(String topic) {
		return messageRepository.pop(topic);
	}

	@Override
	public Message push(String topic, String message) {
		if (topicRepository.getTopic(topic) == null) {
			topicRepository.createTopic(topic);
		}
		return messageRepository.push(topic, message);
	}
}
