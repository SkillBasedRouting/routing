package com.routing.routingservice.feature.strategy.impl.requirement;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.scoring.RoutingScoring;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;

/**
 * <b>com.routing.routingservice.feature.strategy.impl.requirement.RoutingScoringControl</b>
 * <p>
 *   score all users that are eligable
 *   uses all scoring plugins found in classpath, write your own by implementing {@link RoutingScoring}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class RoutingScoringControl {

	@Inject
	private Instance<RoutingScoring> scorings;

    /**
     * score all users that are eligable
     * @param evaluationResult evaluation result
     * @param request routing request
     * @return scoring result
     */
	public ScoringResult score(final EvaluationResult evaluationResult, final RoutingRequest request) {

		final List<ScoringResult> results = new ArrayList<>();
		for (RoutingScoring scoring : this.scorings) {
			final ScoringResult result = scoring.score(evaluationResult, request);
			results.add(result);
		}

		final ScoringResult globalScoring = new ScoringResult();
		results.forEach((result) -> {
			globalScoring.merge(result);
		});

		return globalScoring;
	}

}
