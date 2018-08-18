package com.routing.routingservice.feature.strategy.impl.requirement;

import com.routing.routingservice.client.model.requirements.RequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.IntegerSkillRequirementDTO;
import com.routing.routingservice.feature.algorithm.RoutingAlgorithm;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationUserResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.RoutingResult;
import com.routing.routingservice.feature.routing.model.strategy.RequirementRoutingStrategy;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <b>com.routing.routingservice.feature.strategy.impl.requirement.RequirementStrategyWorkerTest</b>
 * <p>
 * tests for {@link RequirementStrategyWorker}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class RequirementStrategyWorkerTest {

    @InjectMocks
    private RequirementStrategyWorker requirementStrategyWorker;

    @Mock
    private RoutingScoringControl routingScoringControl;

    @Mock
    private RoutingEvaluationControl routingEvaluationControl;

    @Mock
    private RoutingAlgorithm routingAlgorithm;

    private RoutingRequest buildExampleRequest() {
        final Set<RequirementDTO> requirements = new HashSet<>(1);
        requirements.add(new IntegerSkillRequirementDTO());
        final RequirementRoutingStrategy strategy = new RequirementRoutingStrategy(requirements);
        return new RoutingRequest(strategy);
    }

    private void mockEvaluationResult(final String user) {
        final Map<String, Set<EvaluationUserResult>> evaluationResults = new HashMap<>();
        evaluationResults.put(user, Collections.emptySet());
        when(this.routingEvaluationControl.evaluate(any())).thenReturn(new EvaluationResult(evaluationResults));
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        final String user = UUID.randomUUID().toString();
        when(this.routingAlgorithm.findBestUser(any())).thenReturn(user);
        when(this.routingScoringControl.score(any(), any())).thenReturn(new ScoringResult());
        this.mockEvaluationResult(user);
    }

    @Test
    public void should_ReturnUserFromAlgorithmResult() {

        final RoutingRequest request = this.buildExampleRequest();

        final String user = UUID.randomUUID().toString();
        when(this.routingAlgorithm.findBestUser(any())).thenReturn(user);
        this.mockEvaluationResult(user);

        final RoutingResult result = this.requirementStrategyWorker.route(request);

        assertEquals(user, result.getUser().getId());
    }

    @Test
    public void should_CallAlgorithm_With_ResultsOfScoring() {

        final RoutingRequest request = this.buildExampleRequest();

        final List<Integer> desired = Arrays.asList(1, 2, 3);
        final Map<String, List<Integer>> actual = new HashMap<>();
        final ScoringResult scoringResult = new ScoringResult(desired, actual);
        when(this.routingScoringControl.score(any(), any())).thenReturn(scoringResult);

        final RoutingResult result = this.requirementStrategyWorker.route(request);

        verify(this.routingAlgorithm, times(1)).findBestUser(eq(scoringResult));
    }

    @Test
    public void should_CallEvaluation_With_Request() {

        final RoutingRequest request = this.buildExampleRequest();

        this.requirementStrategyWorker.route(request);

        verify(this.routingEvaluationControl, times(1)).evaluate(eq(request));
    }

    @Test
    public void should_CallScoring_With_RequestAndEvaluationResult() {

        final RoutingRequest request = this.buildExampleRequest();

        final String user = UUID.randomUUID().toString();
        final Map<String, Set<EvaluationUserResult>> evaluationResults = new HashMap<>();
        evaluationResults.put(user, Collections.emptySet());
        final EvaluationResult evaluationResult = new EvaluationResult(evaluationResults);
        when(this.routingEvaluationControl.evaluate(any())).thenReturn(evaluationResult);

        when(this.routingAlgorithm.findBestUser(any())).thenReturn(user);

        this.requirementStrategyWorker.route(request);

        verify(this.routingScoringControl, times(1)).score(eq(evaluationResult), eq(request));
    }

}