package com.routing.routingservice.feature.skill.model;

public abstract class Shape {

	private String id;
	private String skillKey;

	public abstract Object getValue();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSkillKey() {
		return skillKey;
	}

	public void setSkillKey(String skillKey) {
		this.skillKey = skillKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shape [id=");
		builder.append(id);
		builder.append(", skillKey=");
		builder.append(skillKey);
		builder.append("]");
		return builder.toString();
	}

}
