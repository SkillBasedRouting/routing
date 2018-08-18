package com.routing.routingservice.feature.strategy.impl.requirement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.routing.routingservice.feature.routing.evaluation.RoutingEvaluation;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult;
import com.routing.routingservice.feature.routing.exception.NoEvaluationClassesFound;
import com.routing.routingservice.feature.routing.model.RoutingRequest;

/**
 * <b>com.routing.routingservice.feature.strategy.impl.requirement.RoutingEvaluationControl</b>
 * <p>
 *   evaluate which users are eligable for a routing request
 *   uses all evaluation plugins that are found in classpath. write your own by implementing {@link RoutingEvaluation}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class RoutingEvaluationControl {

	@Inject
	private Instance<RoutingEvaluation> evaluations;

	/**
	 * evaluate which users are eligable for a routing request
	 * @param request routing request
	 * @return result of evaluation
	 */
	public EvaluationResult evaluate(final RoutingRequest request) {

		List<SingleEvaluationResult> results = this.evaluateAll(request);

		if (0 == results.size()) {
			throw new NoEvaluationClassesFound();
		}

		results = this.filterUsers(results);

		return new EvaluationResult(results);
	}

	private List<SingleEvaluationResult> evaluateAll(final RoutingRequest request) {
		final List<SingleEvaluationResult> results = new ArrayList<>();
		for (RoutingEvaluation evaluation : this.evaluations) {
			results.add(evaluation.evaluate(request));
		}
		return results;
	}

    /**
     * filter users that do not occur in every evaluation result
     * @param results evaluation results
     * @return filtered results
     */
	private List<SingleEvaluationResult> filterUsers(final List<SingleEvaluationResult> results) {

		final Set<String> users = results.get(0).getUsers().keySet();

		results.forEach(singleResult -> {
			users.retainAll(singleResult.getUsers().keySet());
		});

		results.forEach(singleResult -> {
			singleResult.getUsers().entrySet().removeIf(entry -> {
				return !users.contains(entry.getKey());
			});
		});

		return results;
	}

}
