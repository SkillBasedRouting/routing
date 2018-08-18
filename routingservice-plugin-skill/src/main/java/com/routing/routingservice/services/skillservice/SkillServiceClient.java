package com.routing.routingservice.services.skillservice;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;

import org.keycloak.authorization.client.AuthzClient;

import com.routing.routingservice.client.model.requirements.RequirementDTO;
import com.routing.routingservice.feature.user.model.UserShape;
import com.routing.routingservice.services.skillservice.exception.SkillServiceException;
import com.routing.routingservice.services.skillservice.exception.UnexpectedSkillServiceException;
import com.routing.routingservice.services.skillservice.model.SkillServiceQuery;
import com.routing.routingservice.services.skillservice.model.SkillServiceQueryResponse;
import com.routing.skillservice.exception.SkillServiceErrorResponse;

/**
 * <b>com.routing.routingservice.services.skillservice.SkillServiceClient</b>
 * <p>
 *   client for skill api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@RequestScoped
public class SkillServiceClient {

	private com.routing.skillservice.client.SkillServiceClient service;

	@PostConstruct
	private void init() {
		//final AuthzClient authzClient = AuthzClient.create();
		//final String jwt = authzClient.obtainAccessToken().getToken();
		//this.service = new com.routing.skillservice.client.SkillServiceClient("http://skillservice:8080", jwt);
		this.service = new com.routing.skillservice.client.SkillServiceClient("http://skillservice:8080");
	}

    /**
     * query users
     * @param requirements
     * @return
     */
	public Set<UserShape> query(final Set<RequirementDTO> requirements) {

		final SkillServiceQuery query = new SkillServiceQuery(requirements);

		try {

			final SkillServiceQueryResponse resp = new SkillServiceQueryResponse(this.service.query(query));

			return resp;

		} catch (WebApplicationException e) {
			throw this.mapToException(e);
		}

	}

    /**
     * get multipliers
     * @param keys set of skill keys
     * @return map with skill keys as key and multipliers as value
     */
	public Map<String, Double> getMultipliers(final Set<String> keys) {

		try {
			return this.service.getMultipliers(keys);
		} catch (WebApplicationException e) {
			throw this.mapToException(e);
		}
	}

	private RuntimeException mapToException(final WebApplicationException e) {

		try {

			final SkillServiceErrorResponse error = e.getResponse().readEntity(SkillServiceErrorResponse.class);

			return new UnexpectedSkillServiceException(error.getReasonCode(), error.getMessage());

		} catch (ProcessingException e2) {
			if (e instanceof NotFoundException) {
				return SkillServiceException.SERVICE_NOT_FOUND;
			} else {
				return SkillServiceException.SERVICE_ERROR;
			}
		}

	}

}
