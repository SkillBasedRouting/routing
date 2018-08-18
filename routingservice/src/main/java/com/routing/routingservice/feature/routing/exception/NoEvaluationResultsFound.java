package com.routing.routingservice.feature.routing.exception;

/**
 * <b>com.routing.routingservice.feature.routing.exception.NoEvaluationResultsFound</b>
 * <p>
 *   exception that is thrown if no evaluation results are found
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class NoEvaluationResultsFound extends RoutingException {

	private static final long serialVersionUID = 1L;

	public NoEvaluationResultsFound() {
		super(0, "no evaluation results found");
	}

}
