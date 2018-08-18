package com.routing.routingservice.exception;

import javax.ejb.ApplicationException;

/**
 * <b>com.routing.routingservice.exception.RoutingServiceException</b>
 * <p>
 * exception for all routing service errors
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@ApplicationException
public class RoutingServiceException extends RuntimeException {

    public static final RoutingServiceException GENERIC_ERROR = new RoutingServiceException(0, "unexpected error");
    public static final RoutingServiceException USERORGROUP_REQUIRED = new RoutingServiceException(0,
            "in order to route a user or a group is required");
    public static final RoutingServiceException REQUIREMENTS_REQUIRED = new RoutingServiceException(0,
            "in order to route requirements are needed");
    private static final long serialVersionUID = 1L;
    private Integer reasonCode;

    public RoutingServiceException(final Integer reasonCode, final String message) {
        super(message);
        this.reasonCode = reasonCode;
    }

    public Integer getReasonCode() {
        return reasonCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RoutingServiceException [reasonCode=");
        builder.append(reasonCode);
        builder.append(", message=");
        builder.append(super.getMessage());
        builder.append("]");
        return builder.toString();
    }

}
