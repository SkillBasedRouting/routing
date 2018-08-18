package com.routing.routingservice.api.error;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.routing.routingservice.client.exception.RoutingServiceErrorResponse;
import com.routing.routingservice.exception.RoutingServiceException;

/**
 * <b>com.routing.routingservice.api.error.RoutingExceptionMapper</b>
 * <p>
 *   jax-rs exception mapper that handles all exception that occur at api level and returns them as json with a specific http status code to the client
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Provider
public class RoutingExceptionMapper implements ExceptionMapper<RoutingServiceException> {

	@Inject
	private HttpStatusCodeMapper statusCodeMapper;

	/**
	 * map exception to response
	 * @param e occured exception
	 * @return response
	 */
	@Override
	public Response toResponse(RoutingServiceException e) {

		final Integer status = this.statusCodeMapper.getStatus(e);

		final RoutingServiceErrorResponse respEntity = this.map(e);

		return Response.status(status).entity(respEntity).build();
	}

	private RoutingServiceErrorResponse map(final RoutingServiceException e) {
		final RoutingServiceErrorResponse resp = new RoutingServiceErrorResponse();
		resp.setMessage(e.getMessage());
		resp.setReasonCode(e.getReasonCode());
		return resp;
	}

}
