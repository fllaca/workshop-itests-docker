package com.codedpoetry.samples.simplequeue.entities;

public class Message {
	
	private String message;


	protected Message() {
	}

	public Message(String name) {
		this.message = name;
	}

	@Override
	public String toString() {
		return message;
	}

	public String getName() {
		return message;
	}

	public void setMessage(String name) {
		this.message = name;
	}

}
