package com.routing.routingservice.feature.strategy.impl.requirement;

import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.scoring.RoutingScoring;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.enterprise.inject.Instance;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class RoutingScoringControlTest {

    @InjectMocks
    private RoutingScoringControl routingScoringControl;

    @Mock
    private Instance<RoutingScoring> scorings;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);


    }

    private void mockScorings(final ScoringResult... results) {

        final List<RoutingScoring> scorings = new ArrayList<>(results.length);

        for (ScoringResult scoringResult : results) {
            scorings.add(new RoutingScoring() {
                @Override
                public ScoringResult score(EvaluationResult result, RoutingRequest request) {
                    return scoringResult;
                }
            });
        }

        when(this.scorings.iterator()).thenReturn(scorings.iterator());
    }

    @Test
    public void should_ReturnScoringResult_With_MultipleScorings() {

        final Map<String, List<Integer>> actual1 = new HashMap<>();
        actual1.put("A", Arrays.asList(10, 5));
        actual1.put("B", Arrays.asList(5, 10));
        final ScoringResult scoringResult1 = ScoringResult.Builder.aScoringResult().withDesired(Arrays.asList(10, 10)).withActual(actual1).build();
        final Map<String, List<Integer>> actual2 = new HashMap<>();
        actual2.put("A", Arrays.asList(7, 8));
        actual2.put("B", Arrays.asList(8, 7));
        final ScoringResult scoringResult2 = ScoringResult.Builder.aScoringResult().withDesired(Arrays.asList(8, 8)).withActual(actual2).build();

        this.mockScorings(scoringResult1, scoringResult2);

        final ScoringResult totalResult = this.routingScoringControl.score(new EvaluationResult((Map) null),
                new RoutingRequest());

        final Map<String, List<Integer>> expectedActual = new HashMap<>();
        expectedActual.put("A", Arrays.asList(10, 5, 7, 8));
        expectedActual.put("B", Arrays.asList(5, 10, 8, 7));
        final ScoringResult expected = ScoringResult.Builder.aScoringResult().withDesired(Arrays.asList(10, 10, 8, 8)).withActual(expectedActual).build();
        assertThat(totalResult).isEqualTo(expected);
    }

    @Test
    public void should_ReturnScoringResult() {

        final Map<String, List<Integer>> actual = new HashMap<>();
        actual.put("A", Arrays.asList(10, 5));
        actual.put("B", Arrays.asList(5, 10));
        final ScoringResult scoringResult = ScoringResult.Builder.aScoringResult().withDesired(Arrays.asList(10, 10)).withActual(actual).build();
        this.mockScorings(scoringResult);

        final ScoringResult totalResult = this.routingScoringControl.score(new EvaluationResult((Map) null),
                new RoutingRequest());

        assertThat(totalResult).isEqualTo(scoringResult);
    }


}
