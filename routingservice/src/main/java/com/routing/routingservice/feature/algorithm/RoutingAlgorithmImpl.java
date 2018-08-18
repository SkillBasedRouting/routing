package com.routing.routingservice.feature.algorithm;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.routing.routingservice.feature.monitoring.control.MonitoringControl;
import com.routing.routingservice.feature.routing.exception.ScoringValueCountNotEqualException;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;

/**
 * <b>com.routing.routingservice.feature.algorithm.RoutingAlgorithmImpl</b>
 * <p>
 *   default implementation of {@link RoutingAlgorithm}
 *
 *   substracts the requirement of the user value and sums the results up
 *   the smallest value will be selected and returned
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class RoutingAlgorithmImpl implements RoutingAlgorithm {

	@Inject
	protected MonitoringControl monitoring;

    /**
     * {@inheritDoc}
     */
	@Override
	public String findBestUser(final ScoringResult result) {

		final Integer[] desiredValues = result.getDesired().stream().toArray(size -> new Integer[size]);

		final Map<String, Double> userValueMap = new HashMap<>();

		// add all users values to map under user as key
		result.getActual().forEach((user, values) -> {
			final Integer[] userValues = values.stream().toArray(size -> new Integer[size]);
			userValueMap.put(user, this.valueUser(desiredValues, userValues));
		});

		// publish result
		this.monitoring.scoreUserMonitoringEvent(userValueMap);

		// find best user and return it
		return this.getBestUser(userValueMap);
	}

    /**
     * value an user values with requirements
     * @param requirements requirements
     * @param userValues user values
     * @return value of user
     */
	private Double valueUser(final Integer[] requirements, final Integer[] userValues) {

		if (requirements.length != userValues.length) {
			throw new ScoringValueCountNotEqualException();
		}

		// substracts the requirement of the user value and sums the results up
		double currentValue = 0;
		for (int x = 0; x < requirements.length; x++) {
			currentValue += Math.abs(requirements[x] - userValues[x]);
		}

		return currentValue;
	}

    /**
     * returns user with lowest diff to requirements
     * @param valueMap values of all users
     * @return user with lowest diff to requirements
     */
	private String getBestUser(final Map<String, Double> valueMap) {

		String currentBestUser = null;
		Double currentMinValue = Double.MAX_VALUE;

		for (Map.Entry<String, Double> entry : valueMap.entrySet()) {
			if (entry.getValue() < currentMinValue) {
				currentMinValue = entry.getValue();
				currentBestUser = entry.getKey();
			}
		}

		return currentBestUser;
	}

}
