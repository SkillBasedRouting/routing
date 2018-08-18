package com.routing.routingservice.feature.routing.context;

public class RoutingContext {

	private String routingId;

	public RoutingContext() {

	}

	public RoutingContext(String routingId) {
		super();
		this.routingId = routingId;
	}

	public String getRoutingId() {
		return routingId;
	}

	public void setRoutingId(String routingId) {
		this.routingId = routingId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoutingContext [routingId=");
		builder.append(routingId);
		builder.append("]");
		return builder.toString();
	}

}
