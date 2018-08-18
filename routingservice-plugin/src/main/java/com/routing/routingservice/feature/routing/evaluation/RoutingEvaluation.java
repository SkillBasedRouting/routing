package com.routing.routingservice.feature.routing.evaluation;

import com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;

/**
 * <b>com.routing.routingservice.feature.routing.evaluation.RoutingEvaluation</b>
 * <p>
 *   plugin interface for finding all eligible users for a request
 *   needs to be implemented and provided over cdi to be used
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public interface RoutingEvaluation {

    /**
     * evaluate a {@link RoutingRequest}
     * @param request request
     * @return evaluation result
     */
	SingleEvaluationResult evaluate(final RoutingRequest request);

}
