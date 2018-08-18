package com.routing.routingservice.feature.routing.evaluation.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * <b>com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult</b>
 * <p>
 *   result of an evaluation
 *   holds multiple results for each user
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class EvaluationResult {

	private Map<String, Set<EvaluationUserResult>> users;

	public EvaluationResult(final Collection<SingleEvaluationResult> results) {
		final Map<String, Set<EvaluationUserResult>> users = new HashMap<>();
		results.forEach(result -> {
			result.getUsers().forEach((user, userResult) -> {
				if (!users.containsKey(user)) {
					users.put(user, new LinkedHashSet<>());
				}
				users.get(user).add(userResult);
			});
		});
		this.users = users;
	}

	public EvaluationResult(Map<String, Set<EvaluationUserResult>> users) {
		this.users = users;
	}

	public Map<String, Set<EvaluationUserResult>> getUsers() {
		return users;
	}

	public void setUsers(Map<String, Set<EvaluationUserResult>> users) {
		this.users = users;
	}

    /**
     * get all evaluation results of an user
     * @param userId id of user
     * @return all evaluation results of user
     */
	public Optional<Set<EvaluationUserResult>> ofUser(final String userId) {
		return Optional.ofNullable(this.users.get(userId));
	}

    /**
     * get all typed evaluation results of an user
     * @param userId id of user
     * @param type type of result
     * @param <E> type of result
     * @return all evaluation results of user and type
     */
	public <E extends EvaluationUserResult> Optional<E> ofUser(final String userId, final Class<E> type) {

		final Optional<Set<EvaluationUserResult>> results = this.ofUser(userId);

		if (!results.isPresent()) {
			throw new RuntimeException("user not present in evaluation results");
		}
		
		return results.get().stream().filter(result -> type.isAssignableFrom(result.getClass()))
				.map(result -> (E) result).findAny();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EvaluationResult [users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}


	public static final class Builder {
		private Map<String, Set<EvaluationUserResult>> users;

		private Builder() {
		}

		public static Builder anEvaluationResult() {
			return new Builder();
		}

		public Builder withUsers(Map<String, Set<EvaluationUserResult>> users) {
			this.users = users;
			return this;
		}

		public EvaluationResult build() {
			EvaluationResult evaluationResult = new EvaluationResult(users);
			return evaluationResult;
		}
	}
}
