package com.routing.routingservice.client.model;

import com.routing.routingservice.client.model.user.UserDTO;

/**
 * <b>com.routing.routingservice.client.model.RoutingResultDTO</b>
 * <p>
 *   model that is used as a result of a routing request
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RoutingResultDTO {

	private UserDTO user;
	private String group;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
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
		builder.append("RoutingResultDTO [user=");
		builder.append(user);
		builder.append(", group=");
		builder.append(group);
		builder.append("]");
		return builder.toString();
	}

}
