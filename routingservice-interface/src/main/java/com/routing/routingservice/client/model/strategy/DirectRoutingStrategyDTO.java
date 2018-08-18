package com.routing.routingservice.client.model.strategy;

/**
 * <b>com.routing.routingservice.client.model.strategy.DirectRoutingStrategyDTO</b>
 * <p>
 *   stategy to route something direct to an user and/or a group
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class DirectRoutingStrategyDTO extends RoutingStrategyDTO {

	public static final String TYPE = "http://routing.com/routingservice/v1/types/strategies/direct";

	private String user;
	private String group;

	@Override
	public String type() {
		return DirectRoutingStrategyDTO.TYPE;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
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
		builder.append("DirectRoutingStrategyDTO [user=");
		builder.append(user);
		builder.append(", group=");
		builder.append(group);
		builder.append("]");
		return builder.toString();
	}

}
