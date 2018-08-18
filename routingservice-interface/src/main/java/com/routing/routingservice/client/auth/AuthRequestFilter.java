package com.routing.routingservice.client.auth;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 * <b>com.routing.routingservice.client.auth.AuthRequestFilter</b>
 * <p>
 *   request interceptor that enriches every request with a jwt auth token
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class AuthRequestFilter implements ClientRequestFilter {

    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String AUTHORIZATION_VALUE_PREFIX = "Bearer ";

    private final String authValue;

	public AuthRequestFilter(final String jwt) {
		authValue = new StringBuilder().append(AUTHORIZATION_VALUE_PREFIX).append(jwt).toString();
	}

	@Override
	public void filter(ClientRequestContext content) throws IOException {
		content.getHeaders().add(AUTHORIZATION_KEY, this.authValue);
	}

}
