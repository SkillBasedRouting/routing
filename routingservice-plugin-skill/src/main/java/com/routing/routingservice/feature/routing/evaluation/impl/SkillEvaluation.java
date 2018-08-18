package com.routing.routingservice.feature.routing.evaluation.impl;

import com.routing.routingservice.feature.routing.context.RoutingContext;
import com.routing.routingservice.feature.routing.evaluation.RoutingEvaluation;
import com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.SkillEvaluationUserResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.strategy.RequirementRoutingStrategy;
import com.routing.routingservice.feature.user.model.User;
import com.routing.routingservice.feature.user.model.UserShape;
import com.routing.routingservice.services.skillservice.SkillServiceClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <b>com.routing.routingservice.feature.routing.evaluation.impl.SkillEvaluation</b>
 * <p>
 * skill evaluation of a {@link RoutingRequest}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@ApplicationScoped
public class SkillEvaluation implements RoutingEvaluation {

    @Inject
    SkillServiceClient skillService;

    @Inject
    RoutingContext routingContext;

    // @Inject
    // Event<MonitoringEventDTO> monitoringEvents;

    /**
     * find all eligible users by the skills and group memberships of a request
     *
     * @param request request
     * @return all eligible users
     */
    @Override
    public SingleEvaluationResult evaluate(final RoutingRequest request) {

        if (false == request.getStrategy() instanceof RequirementRoutingStrategy) {
            throw new IllegalArgumentException("illegal strategy for skillservice plugin");
        }
        final RequirementRoutingStrategy strategy = (RequirementRoutingStrategy) request.getStrategy();

        final Set<User> possibleUsers = new LinkedHashSet<>();
        final Set<UserShape> shapeMatches = this.skillService.query(strategy.getRequirements());
        shapeMatches.forEach((match) -> {
            final User user = new User(match.getUserId(), match.getShapes());
            possibleUsers.add(user);
        });

        // publish result
        // this.publishSkillEvaluationResult(possibleUsers);

        return new SingleEvaluationResult(possibleUsers.stream().collect(Collectors.toMap(User::getId, u -> {
            return new SkillEvaluationUserResult(u.getShapes());
        })));
    }

    /*private void publishSkillEvaluationResult(final Set<User> possibleUsers) {
        final Set<UserShapesDTO> result = possibleUsers.stream().map(user -> {
            return new UserShapesDTO(user.getId(),
                    user.getShapes().stream().filter(shape -> shape instanceof IntegerShape)
                            .map(shape -> (IntegerShape) shape)
                            .collect(Collectors.toMap(IntegerShape::getSkillKey, IntegerShape::getValue)));
        }).collect(Collectors.toSet());

        this.monitoringEvents.fire(new SkillRoutingMonitoringEventDTO(this.routingContext.getRoutingId(), result));
    }*/

}
