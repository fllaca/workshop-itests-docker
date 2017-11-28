package com.codedpoetry.samples.simplequeue.entities;

public class Message {
	
	private String message;


	protected Message() {
	}

	public Message(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
