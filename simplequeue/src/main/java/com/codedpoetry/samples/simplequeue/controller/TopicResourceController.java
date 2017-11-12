package com.codedpoetry.samples.simplequeue.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codedpoetry.samples.simplequeue.entities.Message;
import com.codedpoetry.samples.simplequeue.entities.RestError;
import com.codedpoetry.samples.simplequeue.entities.Topic;
import com.codedpoetry.samples.simplequeue.services.TopicService;

@RestController
@RequestMapping("/topics")
public class TopicResourceController {

	@Autowired
	private TopicService topicService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<Topic>> getTopics() {

		Set<Topic> list = topicService.getAllTopics();

		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity createTopic(@RequestBody Topic topic) {

		Topic newTopic = topicService.createTopic(topic.getName());
		
		return ResponseEntity.ok().body(newTopic);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity getTopic(@PathVariable String name) {
		Topic topic = topicService.getTopic(name);
		if (topic != null) {
			return ResponseEntity.ok().body(topic);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new RestError("Topic Not Found", HttpStatus.NOT_FOUND.toString()));
		}
	}
	
	@RequestMapping(value = "/{name}/pop", method = RequestMethod.GET)
	public ResponseEntity pop(@PathVariable String name) {
		Message message = topicService.pop(name);
		if (message != null) {
			return ResponseEntity.ok().body(message);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new RestError("No Messages Found", HttpStatus.NOT_FOUND.toString()));
		}
	}
	
	@RequestMapping(value = "/{name}/push", method = RequestMethod.PUT)
	public ResponseEntity push(@PathVariable String name, @RequestBody Message message) {
		Message newMessage = topicService.push(name, message.getMessage());
		if (newMessage != null) {
			return ResponseEntity.ok().body(message);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new RestError("No Messages Found", HttpStatus.NOT_FOUND.toString()));
		}
	}
}
