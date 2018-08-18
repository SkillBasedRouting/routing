package com.routing.routingservice.client.model.strategy;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * <b>com.routing.routingservice.client.model.strategy.RoutingStrategyDTO</b>
 * <p>
 *   a strategy gives information how something should be routed
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(name = DirectRoutingStrategyDTO.TYPE, value = DirectRoutingStrategyDTO.class),
		@Type(name = RequirementRoutingStrategyDTO.TYPE, value = RequirementRoutingStrategyDTO.class) })
public abstract class RoutingStrategyDTO {

	public abstract String type();

}
