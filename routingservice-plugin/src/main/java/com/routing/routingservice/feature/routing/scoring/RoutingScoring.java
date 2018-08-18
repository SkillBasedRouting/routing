package com.routing.routingservice.feature.routing.scoring;

import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;

/**
 * <b>com.routing.routingservice.feature.routing.scoring.RoutingScoring</b>
 * <p>
 *   plugin interface for scoring all eligible users
 *   needs to be implemented and provided over cdi to be used
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public interface RoutingScoring {

    /**
     * score all eligible users
     * @param result evaluation results
     * @param request routing request
     * @return scoring result
     */
	ScoringResult score(final EvaluationResult result, final RoutingRequest request);

}
