package com.routing.routingservice.api.correlation;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.routing.routingservice.feature.routing.context.RoutingContextProducer;

/**
 * <b>com.routing.routingservice.api.correlation.CorrelationFilter</b>
 * <p>
 *   tries to reads a correlation id from all requests
 *   if not found a new will be created
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Provider
public class CorrelationFilter implements ContainerRequestFilter {

	@Inject
	private RoutingContextProducer routingContextProducer;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String routingId = requestContext.getHeaderString("X-CORRELATION_ID");
		if (null == routingId || routingId.isEmpty()) {
			routingId = UUID.randomUUID().toString();
		}

		this.routingContextProducer.setContext(routingId);
	}

}
