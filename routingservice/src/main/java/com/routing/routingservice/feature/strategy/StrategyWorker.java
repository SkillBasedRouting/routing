package com.routing.routingservice.feature.strategy;

import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.RoutingResult;
import com.routing.routingservice.feature.routing.model.strategy.RoutingStrategy;

/**
 * <b>com.routing.routingservice.feature.strategy.StrategyWorker</b>
 * <p>
 * TODO: comment
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public interface StrategyWorker {

    RoutingResult route(final RoutingRequest request);

}
