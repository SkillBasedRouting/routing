package com.routing.routingservice.feature.routing.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.routing.routingservice.client.model.RoutingRequestDTO;
import com.routing.routingservice.client.model.RoutingResultDTO;
import com.routing.routingservice.feature.routing.boundary.RoutingBoundary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <b>com.routing.routingservice.feature.routing.resource.RoutingResource</b>
 * <p>
 *   jax-rs endpoint for routing requests
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Path("/routing")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api
public class RoutingResource {

	@Inject
	private RoutingBoundary routingBoundary;

	@POST
	@Path("/")
	@ApiOperation("route to user/group based on input")
	public RoutingResultDTO findBestUser(
			@ApiParam("the routing request, see objects extending RequirementDTO for possible requirements") final RoutingRequestDTO request) {
		return this.routingBoundary.route(request);
	}

}
