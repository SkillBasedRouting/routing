package com.routing.routingservice.feature.routing;

import com.routing.routingservice.exception.RoutingServiceException;

/**
 * <b>com.routing.routingservice.feature.routing.PluginException</b>
 * <p>
 * main exception for all plugins
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class PluginException extends RoutingServiceException {

    public PluginException(final Integer reasonCode, final String message) {
        super(reasonCode, message);
    }

}
