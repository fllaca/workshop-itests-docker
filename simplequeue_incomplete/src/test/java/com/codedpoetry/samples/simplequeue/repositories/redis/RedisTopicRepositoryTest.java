package com.codedpoetry.samples.simplequeue.repositories.redis;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codedpoetry.samples.simplequeue.IntegrationTest;
import com.codedpoetry.samples.simplequeue.entities.Topic;
import com.codedpoetry.samples.simplequeue.repositories.TopicRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTest.class)	
public class RedisTopicRepositoryTest {
	
	@Autowired
	TopicRepository repository;
	
	@Before
	public void setup() {
		Set<Topic> allTopics = repository.getAllTopics();
		for(Topic topic : allTopics) {
			repository.removeTopic(topic.getName());
		}
	}

	@Test
	public void testGetTopic() throws Exception {
		this.testCreateTopic();
	}

	@Test
	public void testGetAllTopics() throws Exception {
		repository.createTopic("topic1");
		repository.createTopic("topic2");
		
		Set<Topic> allTopics = repository.getAllTopics();
		
		assertEquals(2, allTopics.size());
	}

	@Test
	public void testCreateTopic() throws Exception {
		Topic newTopic = repository.createTopic("test");
		Topic topic = repository.getTopic("test");
		
		assertEquals(newTopic.getName(), topic.getName());
	}

}
