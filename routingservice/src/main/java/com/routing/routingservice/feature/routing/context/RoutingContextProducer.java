package com.routing.routingservice.feature.routing.context;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

/**
 * <b>com.routing.routingservice.feature.routing.context.RoutingContextProducer</b>
 * <p>
 *   produces the {@link RoutingContext} for each http request over cdi
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@RequestScoped
public class RoutingContextProducer {

	private RoutingContext context;

	public void setContext(final String routingId) {
		this.context = new RoutingContext(routingId);
	}

	@Produces
	@RequestScoped
	public RoutingContext getContext() {
		return this.context;
	}

}
