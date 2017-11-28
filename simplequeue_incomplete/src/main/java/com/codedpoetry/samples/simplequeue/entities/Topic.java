package com.codedpoetry.samples.simplequeue.entities;

public class Topic {
	
	private String name;


	protected Topic() {
	}

	public Topic(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
