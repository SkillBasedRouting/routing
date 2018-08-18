package com.routing.routingservice.feature.routing.model.strategy;

import com.routing.routingservice.client.model.strategy.DirectRoutingStrategyDTO;
import com.routing.routingservice.client.model.strategy.RoutingStrategyDTO;
import com.routing.routingservice.exception.RoutingServiceException;

public class DirectRoutingStrategy extends RoutingStrategy {

	private String user;
	private String group;

	public DirectRoutingStrategy() {

	}

	public DirectRoutingStrategy(final DirectRoutingStrategyDTO strategyDTO) {
		this.user = strategyDTO.getUser();
		this.group = strategyDTO.getGroup();
	}

	@Override
	public void validate() {
		if (null == user && null == group) {
			throw RoutingServiceException.USERORGROUP_REQUIRED;
		}
	}

	@Override
	public DirectRoutingStrategyDTO asDTO() {
		final DirectRoutingStrategyDTO dto = new DirectRoutingStrategyDTO();
		dto.setUser(this.user);
		dto.setGroup(this.group);
		return dto;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		DirectRoutingStrategy other = (DirectRoutingStrategy) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DirectRoutingStrategy [user=");
		builder.append(user);
		builder.append(", group=");
		builder.append(group);
		builder.append("]");
		return builder.toString();
	}

}
