package com.routing.routingservice.feature.routing.boundary;

import com.routing.routingservice.client.model.RoutingRequestDTO;
import com.routing.routingservice.client.model.RoutingResultDTO;
import com.routing.routingservice.exception.ExceptionLogInterceptor;
import com.routing.routingservice.feature.monitoring.control.MonitoringControl;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.RoutingResult;
import com.routing.routingservice.feature.strategy.Strategy;
import com.routing.routingservice.feature.strategy.StrategyWorker;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 * <b>com.routing.routingservice.feature.routing.boundary.RoutingBoundary</b>
 * <p>
 * boundary for all routing use cases, starts new transaction
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors({ExceptionLogInterceptor.class})
public class RoutingBoundary {

    @Any
    @Inject
    private Instance<StrategyWorker> strategyWorkers;

    @Inject
    private MonitoringControl monitoringControl;

    /**
     * route a task with given strategy
     *
     * @param requestDTO the request to route
     * @return result of routing
     */
    public RoutingResultDTO route(final RoutingRequestDTO requestDTO) {

        final RoutingRequest request = new RoutingRequest(requestDTO);

        // validate request
        request.validate();

        // publish start of monitoring
        this.monitoringControl.publishStart(request);

        final Instance<StrategyWorker> workerInstance = this.strategyWorkers.select(new Strategy.StrategyLiteral(request.getStrategy().getClass()));
        StrategyWorker worker = null;
        try {
            worker = workerInstance.get();

            final RoutingResult result = worker.route(request);

            // publish success of routing
            this.monitoringControl.publishSuccess(result);

            return result.asDTO();

        } catch (RuntimeException e) {

            // publish fail of routing
            this.monitoringControl.publishFail(e);

            throw e;

        } finally {
            if (null != worker) {
                workerInstance.destroy(worker);
            }
        }

    }

}
