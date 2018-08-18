package com.routing.routingservice.api.error;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.resteasy.util.HttpResponseCodes;

import com.routing.routingservice.exception.RoutingServiceException;
import com.routing.routingservice.feature.routing.exception.NoUserFoundException;
import com.routing.routingservice.feature.routing.exception.RoutingException;

/**
 * <b>com.routing.routingservice.api.error.HttpStatusCodeMapper</b>
 * <p>
 *   maps {@link RoutingServiceException} to a http status code
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class HttpStatusCodeMapper {

	private Map<RoutingServiceException, Integer> refCodes;
	private Map<Class<? extends RoutingServiceException>, Integer> typeCodes;

	@PostConstruct
	private void init() {

		this.refCodes = new HashMap<>(10);
		this.typeCodes = new HashMap<>(10);

		// RoutingException

		this.typeCodes.put(NoUserFoundException.class, HttpResponseCodes.SC_NOT_FOUND);
		this.refCodes.put(RoutingServiceException.USERORGROUP_REQUIRED, HttpResponseCodes.SC_BAD_REQUEST);
		this.refCodes.put(RoutingServiceException.REQUIREMENTS_REQUIRED, HttpResponseCodes.SC_BAD_REQUEST);
		this.refCodes.put(RoutingException.TASK_REQUIRED, HttpResponseCodes.SC_BAD_REQUEST);
	}

    /**
     * get http status code of exception, returns 500 if no specific code is set
     * @param e occured exception
     * @return http status code
     */
	public Integer getStatus(final RoutingServiceException e) {

		Integer status = this.refCodes.get(e);
		if (null == status) {
			status = this.typeCodes.get(e.getClass());
		}
		if (null == status) {
			status = HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;
		}

		return status;
	}

}
