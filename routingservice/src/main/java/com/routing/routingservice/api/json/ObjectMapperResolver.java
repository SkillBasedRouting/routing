package com.routing.routingservice.api.json;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * <b>com.routing.routingservice.api.json.ObjectMapperResolver</b>
 * <p>
 *   add custom jackson objectmapper to jaxrs that enables java time (e.g. {@link java.time.LocalDateTime}) de-/serialization
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {

	private ObjectMapper objectMapper;

	@Override
	public ObjectMapper getContext(Class<?> arg0) {

		if (null == this.objectMapper) {
			this.objectMapper = new ObjectMapper();
			this.objectMapper.registerModule(new JavaTimeModule());
		}

		return this.objectMapper;
	}

}
