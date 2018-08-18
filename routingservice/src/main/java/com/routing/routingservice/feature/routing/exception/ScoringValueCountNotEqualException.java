package com.routing.routingservice.feature.routing.exception;

/**
 * <b>com.routing.routingservice.feature.routing.exception.ScoringValueCountNotEqualException</b>
 * <p>
 *   exception that is thrown if the amount of scoring values of an user is not equal
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class ScoringValueCountNotEqualException extends RoutingException {

	private static final long serialVersionUID = 1L;

	public ScoringValueCountNotEqualException() {
		super(0, "amount of scoring values is not equal");
	}

}
