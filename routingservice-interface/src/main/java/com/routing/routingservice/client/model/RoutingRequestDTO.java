package com.routing.routingservice.client.model;
import com.routing.routingservice.client.model.strategy.RoutingStrategyDTO;

/**
 * <b>com.routing.routingservice.client.model.RoutingRequestDTO</b>
 * <p>
 *   model that is used as a request to route something
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RoutingRequestDTO {

	private RoutingStrategyDTO strategy;

	public RoutingStrategyDTO getStrategy() {
		return strategy;
	}

	public void setStrategy(RoutingStrategyDTO strategy) {
		this.strategy = strategy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoutingRequestDTO [strategy=");
		builder.append(strategy);
		builder.append("]");
		return builder.toString();
	}

}
