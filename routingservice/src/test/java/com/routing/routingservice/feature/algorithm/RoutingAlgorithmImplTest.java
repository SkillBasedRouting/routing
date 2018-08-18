package com.routing.routingservice.feature.algorithm;

import com.routing.routingservice.feature.monitoring.control.MonitoringControl;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

/**
 * <b>com.routing.routingservice.feature.algorithm.RoutingAlgorithmImplTest</b>
 * <p>
 * tests for {@link RoutingAlgorithmImpl}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class RoutingAlgorithmImplTest {

    @InjectMocks
    private RoutingAlgorithmImpl routingAlgorithm = new RoutingAlgorithmImpl();

    @Mock
    private MonitoringControl monitoringControl;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);

        doNothing().when(this.monitoringControl).scoreUserMonitoringEvent(any());
    }

    @Test
    public void should_SelectBestUser_On_FindBestUser() {

        final ScoringResult scoringResult = new ScoringResult();
        scoringResult.setDesired(Arrays.asList(10, 10, 10));
        scoringResult.setActual(new HashMap<>());
        scoringResult.getActual().put("1", Arrays.asList(10, 20, 10));
        scoringResult.getActual().put("2", Arrays.asList(10, 30, 10));
        scoringResult.getActual().put("3", Arrays.asList(10, 40, 10));

        final String user = this.routingAlgorithm.findBestUser(scoringResult);

        assertEquals("1", user);
    }

    @Test
    public void should_SelectBestUser_When_CompleteMatchOnRequirements_On_FindBestUser() {

        final ScoringResult scoringResult = new ScoringResult();
        scoringResult.setDesired(Arrays.asList(10, 10, 10));
        scoringResult.setActual(new HashMap<>());
        scoringResult.getActual().put("1", Arrays.asList(10, 20, 10));
        scoringResult.getActual().put("2", Arrays.asList(10, 10, 10));
        scoringResult.getActual().put("3", Arrays.asList(10, 30, 10));

        final String user = this.routingAlgorithm.findBestUser(scoringResult);

        assertEquals("2", user);
    }

}