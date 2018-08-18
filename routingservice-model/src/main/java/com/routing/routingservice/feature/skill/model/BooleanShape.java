package com.routing.routingservice.feature.skill.model;

public class BooleanShape extends Shape {

	private Boolean value;

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BooleanShape [base=");
		builder.append(super.toString());
		builder.append("value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
