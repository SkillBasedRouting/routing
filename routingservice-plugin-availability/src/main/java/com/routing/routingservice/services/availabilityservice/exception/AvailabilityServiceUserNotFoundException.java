package com.routing.routingservice.services.availabilityservice.exception;

/**
 * <b>com.routing.routingservice.services.availabilityservice.exception.AvailabilityServiceUserNotFoundException</b>
 * <p>
 *   exception that is thrown when user is not found
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class AvailabilityServiceUserNotFoundException extends AvailabilityServiceException {

	private static final long serialVersionUID = 1L;

	private String user;

	public AvailabilityServiceUserNotFoundException(final String user) {
		super(0, "user " + user + " in availabilityservice not found");
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailabilityServiceUserNotFoundException [user=");
		builder.append(user);
		builder.append(", base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
