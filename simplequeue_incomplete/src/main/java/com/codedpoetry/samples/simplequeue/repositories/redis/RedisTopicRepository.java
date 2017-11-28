package com.codedpoetry.samples.simplequeue.repositories.redis;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.codedpoetry.samples.simplequeue.entities.Topic;
import com.codedpoetry.samples.simplequeue.repositories.TopicRepository;

@Repository
public class RedisTopicRepository implements TopicRepository {
	
	@Autowired
	private StringRedisTemplate template;

	@Override
	public Topic getTopic(String name) {
		Set<String> topicNames = template.opsForSet().members("topics");
		
		Topic result = null;
		if (topicNames.contains(name)) {
			result = new Topic(name);
		}
		return result;
	}

	@Override
	public Set<Topic> getAllTopics() {
		return  template.opsForSet()
				.members("topics")
				.stream()
				.map(name -> new Topic(name))
				.collect(Collectors.toSet());
	}

	@Override
	public Topic createTopic(String name) {
		template.opsForSet().add("topics", name);
		return new Topic(name);
	}

	@Override
	public Topic removeTopic(String name) {
		Topic topic = this.getTopic(name);
		if (topic != null) {
			template.opsForSet().remove("topics", name);
		}
		return topic;
	}

}
