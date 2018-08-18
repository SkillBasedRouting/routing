package com.routing.routingservice.feature.skill.model;

import java.util.Set;

public class EnumShape extends Shape {

	private Set<String> value;

	public Set<String> getValue() {
		return value;
	}

	public void setValue(Set<String> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnumShape []");
		return builder.toString();
	}

}
