package com.routing.routingservice.feature.routing.exception;

import com.routing.routingservice.exception.RoutingServiceException;

public class RoutingException extends RoutingServiceException {

    public static final RoutingException TASK_REQUIRED = new RoutingException(0, "a task is required");
    private static final long serialVersionUID = 1L;

    public RoutingException(final int reasonCode, final String message) {
        super(reasonCode, message);
    }

}
