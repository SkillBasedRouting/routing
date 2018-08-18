package com.routing.routingservice.feature.routing.evaluation.availability.impl;

import com.routing.routingservice.feature.routing.context.RoutingContext;
import com.routing.routingservice.feature.routing.evaluation.RoutingEvaluation;
import com.routing.routingservice.feature.routing.evaluation.availability.model.AvailabilityEvaluationUserResult;
import com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.services.availabilityservice.AvailabilityServiceClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <b>com.routing.routingservice.feature.routing.evaluation.availability.impl.AvailabilityEvaluation</b>
 * <p>
 * evaluation of availability
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@ApplicationScoped
public class AvailabilityEvaluation implements RoutingEvaluation {

    @Inject
    private AvailabilityServiceClient availabilityService;

    //@Inject
    //private Event<MonitoringEventDTO> monitoringEvent;

    @Inject
    private RoutingContext routingContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public SingleEvaluationResult evaluate(final RoutingRequest request) {

        final Set<String> availableUsers = this.availabilityService.getAvailableUsers();

        // publish result
        //this.monitoringEvent.fire(new AvailabilityRoutingMonitoringEventDTO(this.routingContext.getRoutingId(), availableUsers));

        return new SingleEvaluationResult(
                availableUsers.stream().collect(Collectors.toMap(u -> u, u -> new AvailabilityEvaluationUserResult())));
    }

}
