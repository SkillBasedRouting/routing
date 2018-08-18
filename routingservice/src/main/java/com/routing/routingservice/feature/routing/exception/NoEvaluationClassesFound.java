package com.routing.routingservice.feature.routing.exception;

/**
 * <b>com.routing.routingservice.feature.routing.exception.NoEvaluationClassesFound</b>
 * <p>
 *   exception that is thrown if no evulation plugin is found
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class NoEvaluationClassesFound extends RoutingException {

	private static final long serialVersionUID = 1L;

	public NoEvaluationClassesFound() {
		super(0, "no evaluation classes found");
	}

}
