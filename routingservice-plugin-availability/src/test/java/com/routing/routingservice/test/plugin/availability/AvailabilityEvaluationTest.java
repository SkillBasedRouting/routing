package com.routing.routingservice.test.plugin.availability;

import com.routing.routingservice.feature.routing.context.RoutingContext;
import com.routing.routingservice.feature.routing.evaluation.availability.impl.AvailabilityEvaluation;
import com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult;
import com.routing.routingservice.services.availabilityservice.AvailabilityServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AvailabilityEvaluationTest {

    @InjectMocks
    private AvailabilityEvaluation availabilityEvaluation;

    @Mock
    private RoutingContext routingContext;

    //@Mock
    // private Event<MonitoringEventDTO> monitoringEvents;

    @Mock
    private AvailabilityServiceClient availabilityServiceClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        // doNothing().when(this.monitoringEvents).fire(any());
        when(this.routingContext.getRoutingId()).thenReturn(UUID.randomUUID().toString());
        when(this.availabilityServiceClient.getAvailableUsers()).thenReturn(Collections.emptySet());
    }

    @Test
    public void should_ConsiderAvailableUsers() {

        final Set<String> availableUsers = new HashSet<>(Arrays.asList("1", "2"));
        when(this.availabilityServiceClient.getAvailableUsers()).thenReturn(availableUsers);

        SingleEvaluationResult evaluate = this.availabilityEvaluation.evaluate(null);

        assertThat(evaluate.getUsers().keySet()).isEqualTo(availableUsers);
    }

    @Test
    public void should_CallAvailabilityService() {

        this.availabilityEvaluation.evaluate(null);

        verify(this.availabilityServiceClient, times(1)).getAvailableUsers();
    }

    @Test
    public void should_PublishResult() {

        final String routingId = UUID.randomUUID().toString();
        when(this.routingContext.getRoutingId()).thenReturn(routingId);

        final Set<String> availableUsers = new HashSet<>(Arrays.asList("1", "2"));
        when(this.availabilityServiceClient.getAvailableUsers()).thenReturn(availableUsers);

        this.availabilityEvaluation.evaluate(null);

        //final AvailabilityRoutingMonitoringEventDTO expectedEvent = new AvailabilityRoutingMonitoringEventDTO(routingId, availableUsers);
        //verify(this.monitoringEvents, times(1)).fire(eq(expectedEvent));
    }
}
