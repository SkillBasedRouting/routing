package com.routing.routingservice.client.exception;

/**
 * <b>com.routing.routingservice.client.exception.RoutingServiceErrorResponse</b>
 * <p>
 *   model that represents an api error
 *   will be returned as json
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RoutingServiceErrorResponse {

	private Integer reasonCode;
	private String message;

	public Integer getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoutingServiceErrorResponse [reasonCode=");
		builder.append(reasonCode);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
