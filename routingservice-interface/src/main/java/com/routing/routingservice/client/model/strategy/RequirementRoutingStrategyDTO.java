package com.routing.routingservice.client.model.strategy;

import java.util.Set;

import com.routing.routingservice.client.model.requirements.RequirementDTO;

/**
 * <b>com.routing.routingservice.client.model.strategy.RequirementRoutingStrategyDTO</b>
 * <p>
 *   strategy to find an user by multiple {@link RequirementDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RequirementRoutingStrategyDTO extends RoutingStrategyDTO {

	public static final String TYPE = "http://routing.com/routingservice/v1/types/strategies/requirement";

	private Set<RequirementDTO> requirements;

	@Override
	public String type() {
		return RequirementRoutingStrategyDTO.TYPE;
	}

	public Set<RequirementDTO> getRequirements() {
		return requirements;
	}

	public void setRequirements(Set<RequirementDTO> requirements) {
		this.requirements = requirements;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequirementRoutingStrategyDTO [requirements=");
		builder.append(requirements);
		builder.append("]");
		return builder.toString();
	}

}
