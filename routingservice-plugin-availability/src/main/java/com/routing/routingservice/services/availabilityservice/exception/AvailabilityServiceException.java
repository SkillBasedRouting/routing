package com.routing.routingservice.services.availabilityservice.exception;

import com.routing.routingservice.feature.routing.PluginException;

/**
 * <b>com.routing.routingservice.services.availabilityservice.exception.AvailabilityServiceException</b>
 * <p>
 * exception for all availability service errors
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class AvailabilityServiceException extends PluginException {

    public static final AvailabilityServiceException SERVICE_NOT_FOUND = new AvailabilityServiceException(0,
            "availabilityservice not found");
    public static final AvailabilityServiceException SERVICE_ERROR = new AvailabilityServiceException(0,
            "availabilityservice error");
    private static final long serialVersionUID = 1L;

    public AvailabilityServiceException(final Integer reasonCode, final String message) {
        super(reasonCode, message);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AvailabilityServiceException [base=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
