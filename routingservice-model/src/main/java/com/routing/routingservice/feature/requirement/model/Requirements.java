package com.routing.routingservice.feature.requirement.model;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.routing.routingservice.client.model.requirements.RequirementDTO;

/**
 * <b>com.routing.routingservice.feature.requirement.model.Requirements</b>
 * <p>
 *   holds all requirements
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class Requirements {

	private Set<RequirementDTO> values;

	public Requirements(Set<RequirementDTO> requirements) {
		super();
		if (null == requirements) {
			throw new NullPointerException();
		}

		this.values = requirements;
	}

    /**
     * get any requirement of type
     * @param type type of requirement
     * @param <E> type of requirement
     * @return any requirement of type
     */
	public <E extends RequirementDTO> Optional<E> getAny(Class<E> type) {
		return this.get(type).findAny();
	}

    /**
     * get all requirements
     * @return set of all requirements
     */
	public Set<RequirementDTO> get() {
		return values;
	}

    /**
     * get stream of all requirements of type
     * @param type type of requirement
     * @param <E> type of requirement
     * @return stream of all requirements of type
     */
	public <E extends RequirementDTO> Stream<E> get(Class<E> type) {
		return values.stream()
				.filter(req -> type.isAssignableFrom(req.getClass()))
				.map(req -> (E) req);
	}

    /**
     * check if type of requirement exists
     * @param clazz type of requirement
     * @return
     */
	public boolean containsRequirementOfType(final Class<? extends RequirementDTO> clazz) {
		for (RequirementDTO requirement : this.values) {
			if (clazz.isInstance(requirement)) {
				return true;
			}
		}
		return false;
	}

    /**
     * count all requirements of type
     * @param clazz type of requirement
     * @return count of all requirements of type
     */
	public int countRequirementsOfType(final Class<? extends RequirementDTO> clazz) {
		int counter = 0;
		for (RequirementDTO requirement : this.values) {
			if (clazz.isInstance(requirement)) {
				counter++;
			}
		}
		return counter;
	}

    /**
     * check if requirements are only of one type
     * @param clazz type of requirement
     * @return
     */
	public boolean isTypeOnlyRequirement(final Class<? extends RequirementDTO> clazz) {
		return this.values.size() == this.countRequirementsOfType(clazz);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Requirements [values=");
		builder.append(values);
		builder.append("]");
		return builder.toString();
	}

}
