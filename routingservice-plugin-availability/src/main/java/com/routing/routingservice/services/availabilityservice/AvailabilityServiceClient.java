package com.routing.routingservice.services.availabilityservice;

import com.routing.availabilityservice.dto.user.UserDTO;
import com.routing.availabilityservice.exception.AvailabilityServiceErrorResponse;
import com.routing.routingservice.services.availabilityservice.exception.AvailabilityServiceException;
import com.routing.routingservice.services.availabilityservice.exception.UnexpectedAvailabilityServiceException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <b>com.routing.routingservice.services.availabilityservice.AvailabilityServiceClient</b>
 * <p>
 * client for availability api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@RequestScoped
public class AvailabilityServiceClient {

    private com.routing.availabilityservice.client.AvailabilityServiceClient service;

    @PostConstruct
    private void init() {
        //final AuthzClient authzClient = AuthzClient.create();
        //final String jwt = authzClient.obtainAccessToken().getToken();
        //this.service = new com.routing.availabilityservice.client.AvailabilityServiceClient(
        //		"http://availabilityservice:8080", jwt);
        try {
            this.service = new com.routing.availabilityservice.client.AvailabilityServiceClient(
                    "http://availabilityservice:8080");
        } catch (ProcessingException e) {
            throw AvailabilityServiceException.SERVICE_NOT_FOUND;
        }
    }

    /**
     * get all available users
     *
     * @return set of all available users
     */
    public Set<String> getAvailableUsers() {
        try {
            final Set<UserDTO> availableUsers = this.service.getAvailableUsers();
            return availableUsers.stream().map(UserDTO::getId).collect(Collectors.toSet());
        } catch (WebApplicationException | ProcessingException e) {
            throw this.mapToException(e);
        }
    }

    private RuntimeException mapToException(final RuntimeException e) {

        try {

            if (e instanceof WebApplicationException) {
                final AvailabilityServiceErrorResponse error = ((WebApplicationException)e).getResponse()
                        .readEntity(AvailabilityServiceErrorResponse.class);
                return new UnexpectedAvailabilityServiceException(error.getReasonCode(), error.getMessage());
            } else if (e instanceof ProcessingException && e.getCause() instanceof UnknownHostException) {
                return AvailabilityServiceException.SERVICE_NOT_FOUND;
            } else {
                throw e;
            }

        } catch (ProcessingException e2) {
            if (e instanceof NotFoundException) {
                return AvailabilityServiceException.SERVICE_NOT_FOUND;
            } else {
                return AvailabilityServiceException.SERVICE_ERROR;
            }
        }

    }
}