package com.routing.routingservice.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.routing.routingservice.client.model.RoutingRequestDTO;
import com.routing.routingservice.client.model.RoutingResultDTO;

/**
 * <b>com.routing.routingservice.client.RoutingService</b>
 * <p>
 *   interface that represents the routing api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RoutingService {

	@POST
	@Path("/routing")
	public RoutingResultDTO route(final RoutingRequestDTO request);

}