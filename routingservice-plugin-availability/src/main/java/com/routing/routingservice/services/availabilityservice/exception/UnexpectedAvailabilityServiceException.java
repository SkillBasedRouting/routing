package com.routing.routingservice.services.availabilityservice.exception;

/**
 * <b>com.routing.routingservice.services.availabilityservice.exception.UnexpectedAvailabilityServiceException</b>
 * <p>
 *   exception that is thrown if availability returns an unexpected error
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UnexpectedAvailabilityServiceException extends AvailabilityServiceException {

	private static final long serialVersionUID = 1L;

	private Integer responseCode;
	private String responseMessage;

	public UnexpectedAvailabilityServiceException(final Integer responseCode, final String responseMessage) {
		super(0, "availabilityservice error: " + responseMessage);
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnexpectedAvailabilityServiceException [responseCode=");
		builder.append(responseCode);
		builder.append(", responseMessage=");
		builder.append(responseMessage);
		builder.append(", base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
