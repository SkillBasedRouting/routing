package com.routing.routingservice.feature.routing.evaluation.model;

import java.util.Map;

/**
 * <b>com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult</b>
 * <p>
 *   result of an evaluation
 *   holds only one result of each user
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SingleEvaluationResult {

	private Map<String, EvaluationUserResult> users;

	public SingleEvaluationResult(Map<String, EvaluationUserResult> users) {
		this.users = users;
	}

	public Map<String, EvaluationUserResult> getUsers() {
		return users;
	}

	public void setUsers(Map<String, EvaluationUserResult> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("EvaluationResult{");
		sb.append("users=").append(users);
		sb.append('}');
		return sb.toString();
	}
}
