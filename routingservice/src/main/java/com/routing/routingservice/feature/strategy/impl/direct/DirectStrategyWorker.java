package com.routing.routingservice.feature.strategy.impl.direct;

import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.RoutingResult;
import com.routing.routingservice.feature.routing.model.strategy.DirectRoutingStrategy;
import com.routing.routingservice.feature.strategy.Strategy;
import com.routing.routingservice.feature.strategy.StrategyWorker;
import com.routing.routingservice.feature.user.model.User;

import javax.enterprise.context.ApplicationScoped;

/**
 * <b>com.routing.routingservice.feature.strategy.impl.direct.DirectStrategyWorker</b>
 * <p>
 * worker to handle all direct strategy requests
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@ApplicationScoped
@Strategy(DirectRoutingStrategy.class)
public class DirectStrategyWorker implements StrategyWorker {

    @Override
    public RoutingResult route(RoutingRequest request) {

        final DirectRoutingStrategy strategy = request.getStrategy(DirectRoutingStrategy.class);

        // set result directly from request
        final RoutingResult result = new RoutingResult();
        result.setUser(new User(strategy.getUser()));
        result.setGroup(strategy.getGroup());
        return result;

    }

}
