package com.codedpoetry.samples.simplequeue.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.codedpoetry.samples.simplequeue.entities.Message;
import com.codedpoetry.samples.simplequeue.entities.Topic;
import com.codedpoetry.samples.simplequeue.repositories.MessageRepository;
import com.codedpoetry.samples.simplequeue.repositories.TopicRepository;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceImplTest {
	@Mock
	private MessageRepository messageRepository;
	@Mock
	private TopicRepository topicRepository;
	@InjectMocks
	private TopicServiceImpl topicServiceImpl;
	
	Topic fakeTopic1 = new Topic("fake-topic1");
	Topic fakeTopic2 = new Topic("fake-topic2");
	
	Set<Topic> fakeTopicList = new HashSet<>(Arrays.asList(fakeTopic1, fakeTopic2));

	@Test
	public void testGetTopic() throws Exception {
		when(topicRepository.getTopic("fake-topic1")).thenReturn(fakeTopic1);
		
		Topic topic = topicServiceImpl.getTopic("fake-topic1");
		
		assertEquals(fakeTopic1, topic);
	}

	@Test
	public void testGetAllTopics() throws Exception {
		when(topicRepository.getAllTopics()).thenReturn(fakeTopicList);
		
		Set<Topic> allTopics = topicServiceImpl.getAllTopics();
		
		assertEquals(2, allTopics.size());
	}

	@Test
	public void testCreateTopic() throws Exception {
		
		topicServiceImpl.createTopic("fake-topic");
		verify(topicRepository, times(1)).createTopic(eq("fake-topic"));
	}

	@Test
	public void testPop() throws Exception {
		when(messageRepository.pop("fake-topic")).thenReturn(new Message("fake-message"));
		
		Message message = topicServiceImpl.pop("fake-topic");
		verify(messageRepository, times(1)).pop(eq("fake-topic"));
		assertEquals("fake-message", message.getMessage());
	}

	@Test
	public void testPush() throws Exception {
		when(messageRepository.push("fake-topic", "fake-message")).thenReturn(new Message("fake-message"));
		Message message = topicServiceImpl.push("fake-topic", "fake-message");
		verify(messageRepository, times(1)).push(eq("fake-topic"), eq("fake-message"));
		assertEquals("fake-message", message.getMessage());
	}

}
