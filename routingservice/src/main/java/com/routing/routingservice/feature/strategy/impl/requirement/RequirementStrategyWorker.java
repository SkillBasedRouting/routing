package com.routing.routingservice.feature.strategy.impl.requirement;

import com.routing.routingservice.feature.algorithm.RoutingAlgorithm;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.exception.NoUserFoundException;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.RoutingResult;
import com.routing.routingservice.feature.routing.model.strategy.RequirementRoutingStrategy;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;
import com.routing.routingservice.feature.strategy.Strategy;
import com.routing.routingservice.feature.strategy.StrategyWorker;
import com.routing.routingservice.feature.user.model.UserBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * <b>com.routing.routingservice.feature.strategy.impl.requirement.RequirementStrategyWorker</b>
 * <p>
 * TODO: comment
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@ApplicationScoped
@Strategy(RequirementRoutingStrategy.class)
public class RequirementStrategyWorker implements StrategyWorker {

    @Inject
    private RoutingScoringControl routingScoringControl;

    @Inject
    private RoutingEvaluationControl routingEvaluationControl;

    @Inject
    private RoutingAlgorithm routingAlgorithm;

    @Override
    public RoutingResult route(RoutingRequest request) {

        // get all users that could be possibly selected
        final EvaluationResult evaluationResult = this.routingEvaluationControl.evaluate(request);

        // if no user found -> throw exception
        if (0 == evaluationResult.getUsers().size()) {
            throw new NoUserFoundException(request);
        }

        // score each user
        final ScoringResult scoringResult = this.routingScoringControl.score(evaluationResult, request);

        // find best user
        final String userId = this.routingAlgorithm.findBestUser(scoringResult);

        final UserBuilder userBuilder = new UserBuilder(userId).withEvaluationResult(evaluationResult);
        return userBuilder.buildRoutingResult();
    }


}
