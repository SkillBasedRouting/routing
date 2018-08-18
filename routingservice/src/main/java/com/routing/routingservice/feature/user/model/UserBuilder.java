package com.routing.routingservice.feature.user.model;

import java.util.Optional;

import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.SkillEvaluationUserResult;
import com.routing.routingservice.feature.routing.model.RoutingResult;

/**
 * <b>com.routing.routingservice.feature.user.model.UserBuilder</b>
 * <p>
 *   user builder
 *
 *   TODO: move to {@link User}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserBuilder {

	private User user;

	public UserBuilder(final String id) {
		this.user = new User();
		this.user.setId(id);
	}

	public UserBuilder withId(final String id) {
		this.user.setId(id);
		return this;
	}

	public UserBuilder withEvaluationResult(final EvaluationResult evaluationResult) {
		final String userId = user.getId();
		final Optional<SkillEvaluationUserResult> result = evaluationResult.ofUser(userId,
				SkillEvaluationUserResult.class);
		if (result.isPresent()) {
			user.setShapes(result.get().getShapes());
		}
		return this;
	}

	public User build() {
		return this.user;
	}

	public RoutingResult buildRoutingResult() {
        final RoutingResult result = new RoutingResult();
        result.setUser(this.build());
        return result;
	}

}
