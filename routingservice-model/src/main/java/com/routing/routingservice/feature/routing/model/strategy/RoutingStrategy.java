package com.routing.routingservice.feature.routing.model.strategy;

import com.routing.routingservice.client.model.strategy.DirectRoutingStrategyDTO;
import com.routing.routingservice.client.model.strategy.RequirementRoutingStrategyDTO;
import com.routing.routingservice.client.model.strategy.RoutingStrategyDTO;

public abstract class RoutingStrategy {

	public static RoutingStrategy ofDTO(final RoutingStrategyDTO strategyDTO) {
		if (strategyDTO instanceof DirectRoutingStrategyDTO) {
			return new DirectRoutingStrategy((DirectRoutingStrategyDTO) strategyDTO);
		} else if (strategyDTO instanceof RequirementRoutingStrategyDTO) {
			return new RequirementRoutingStrategy((RequirementRoutingStrategyDTO) strategyDTO);
		} else {
			throw new IllegalArgumentException("unknown strategy type");
		}
	}

	public abstract void validate();
	public abstract RoutingStrategyDTO asDTO();

}
