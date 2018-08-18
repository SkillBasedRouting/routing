package com.routing.routingservice.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.routing.routingservice.client.auth.AuthRequestFilter;
import com.routing.routingservice.client.model.RoutingRequestDTO;
import com.routing.routingservice.client.model.RoutingResultDTO;

/**
 * <b>com.routing.routingservice.client.RoutingServiceClient</b>
 * <p>
 *   java client for routing api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RoutingServiceClient {

	private RoutingService service;

	public RoutingServiceClient(final String url) {
		this(url, null);
	}

	public RoutingServiceClient(final String url, final String jwt) {
		final ResteasyClient client = new ResteasyClientBuilder().build();
		if (null != jwt) {
			client.register(new AuthRequestFilter(jwt));
		}
		final ResteasyWebTarget target = client.target(url);
		this.service = target.proxy(RoutingService.class);
	}

	public RoutingResultDTO route(final RoutingRequestDTO request) {
		return this.service.route(request);
	}

}
