package com.routing.routingservice.feature.routing.exception;

import com.routing.routingservice.feature.routing.model.RoutingRequest;

/**
 * <b>com.routing.routingservice.feature.routing.exception.NoUserFoundException</b>
 * <p>
 *   exception that is thrown if no user is found
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class NoUserFoundException extends RoutingException {

	private static final long serialVersionUID = 1L;

	private RoutingRequest request;

	public NoUserFoundException(final RoutingRequest request) {
		super(0, "no suitable user found");
		this.request = request;
	}

	public RoutingRequest getRoutingRequest() {
		return this.request;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NoUserFoundException [request=");
		builder.append(request);
		builder.append(", base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
