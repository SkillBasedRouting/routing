package com.routing.routingservice.feature.routing.model;

import com.routing.routingservice.client.model.RoutingResultDTO;
import com.routing.routingservice.feature.user.model.User;

public class RoutingResult {

	private User user;
	private String group;

	public RoutingResultDTO asDTO() {
		final RoutingResultDTO dto = new RoutingResultDTO();
		if (null != this.user) {
			dto.setUser(this.user.dto());
		}
		dto.setGroup(this.group);
		return dto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoutingResult [user=");
		builder.append(user);
		builder.append(", group=");
		builder.append(group);
		builder.append("]");
		return builder.toString();
	}

}
