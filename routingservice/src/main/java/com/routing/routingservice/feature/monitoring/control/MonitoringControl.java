package com.routing.routingservice.feature.monitoring.control;

import com.routing.routingservice.feature.routing.context.RoutingContext;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.RoutingResult;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

/**
 * <b>com.routing.routingservice.feature.monitoring.control.MonitoringControl</b>
 * <p>
 * handles all monitoring publications
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@ApplicationScoped
public class MonitoringControl {

    //@Inject
    //private Event<MonitoringEventDTO> monitoring;

    //@Inject
    //@DefaultObjectMapper
    //private ObjectMapper objectMapper;

    @Inject
    private RoutingContext routingContext;

    /**
     * publish complete scoring result
     *
     * @param userValueMap
     */
    public void scoreUserMonitoringEvent(final Map<String, Double> userValueMap) {
        //this.monitoring.fire(new ScoreRoutingMonitoringEventDTO(this.routingContext.getRoutingId(), userValueMap
        //		.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), Map.Entry::getValue))));
    }

    /**
     * publish failure of routing
     *
     * @param e
     */
    public void publishFail(final RuntimeException e) {
        //this.monitoring.fire(new FailRoutingMonitoringEventDTO(this.routingContext.getRoutingId()));
    }

    /**
     * publish publishStart of routing
     *
     * @param request
     */
    public void publishStart(final RoutingRequest request) {
		/*try {
			final byte[] strategyArr = this.objectMapper.writerFor(RoutingStrategyDTO.class).writeValueAsBytes(request.getStrategy().asDTO());
			final JsonNode strategy = this.objectMapper.readTree(strategyArr);
			this.monitoring.fire(new StartRoutingMonitoringEventDTO(this.routingContext.getRoutingId(), strategy));
		} catch (IOException e) {
			throw new RuntimeException("can not serialize strategy", e);
		}*/
    }

    /**
     * publish publishSuccess of routing
     *
     * @param result
     */
    public void publishSuccess(final RoutingResult result) {
        final String userId = null != result.getUser() ? result.getUser().getId() : null;
        //this.monitoring
        //		.fire(new EndRoutingMonitoringEventDTO(this.routingContext.getRoutingId(), userId, result.getGroup()));
    }

}
