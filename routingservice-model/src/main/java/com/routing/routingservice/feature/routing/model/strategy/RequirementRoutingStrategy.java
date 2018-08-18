package com.routing.routingservice.feature.routing.model.strategy;

import java.util.Set;

import com.routing.routingservice.client.model.requirements.RequirementDTO;
import com.routing.routingservice.client.model.strategy.RequirementRoutingStrategyDTO;
import com.routing.routingservice.client.model.strategy.RoutingStrategyDTO;
import com.routing.routingservice.exception.RoutingServiceException;

public class RequirementRoutingStrategy extends RoutingStrategy {

	private Set<RequirementDTO> requirements;

	public RequirementRoutingStrategy(final Set<RequirementDTO> requirements) {
		this.requirements = requirements;
	}

	public RequirementRoutingStrategy(final RequirementRoutingStrategyDTO strategyDTO) {
		this.requirements = strategyDTO.getRequirements();
	}

	@Override
	public void validate() {

		if (null == this.requirements || this.requirements.isEmpty()) {
			throw RoutingServiceException.REQUIREMENTS_REQUIRED;
		}

	}

	@Override
	public RequirementRoutingStrategyDTO asDTO() {
		final RequirementRoutingStrategyDTO dto = new RequirementRoutingStrategyDTO();
		dto.setRequirements(this.requirements);
		return dto;
	}

	public Set<RequirementDTO> getRequirements() {
		return requirements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requirements == null) ? 0 : requirements.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequirementRoutingStrategy other = (RequirementRoutingStrategy) obj;
		if (requirements == null) {
			if (other.requirements != null)
				return false;
		} else if (!requirements.equals(other.requirements))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequirementRoutingStrategy [requirements=");
		builder.append(requirements);
		builder.append("]");
		return builder.toString();
	}

}
