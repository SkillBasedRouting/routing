package com.routing.routingservice.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * <b>com.routing.routingservice.api.ApiActivator</b>
 * <p>
 *   JAX-RS activator for rest api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationPath("/api/v1")
public class ApiActivator extends Application {

	public ApiActivator() {
		super();

		// swagger config
		final BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("RoutingService");
		beanConfig.setVersion("1.0.0");
		beanConfig.setHost("routing.schwenkschuster.com");
		beanConfig.setBasePath("routingservice/api/v1");
		beanConfig.setScan(true);
		beanConfig.setResourcePackage("com.routing.routingservice");
		beanConfig.setContact("Arndt Schwenkschuster (arndt@schwenkschuster.de)");

	}

}
