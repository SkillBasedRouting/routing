package com.routing.routingservice.feature.routing.scoring.impl;

import com.routing.routingservice.client.model.requirements.RequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.IntegerSkillRequirementDTO;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationUserResult;
import com.routing.routingservice.feature.routing.evaluation.model.SkillEvaluationUserResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.strategy.RequirementRoutingStrategy;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;
import com.routing.routingservice.services.skillservice.SkillServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * <b>com.routing.routingservice.feature.routing.scoring.impl.SkillScoringTest</b>
 * <p>
 * tests for {@link SkillScoring}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class SkillScoringTest {

    @InjectMocks
    private SkillScoring skillScoring;

    @Mock
    private SkillServiceClient skillServiceClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        when(this.skillServiceClient.getMultipliers(any())).thenReturn(Collections.emptyMap());
    }

    private Map<String, Integer> requirements() {
        final Map<String, Integer> req = new HashMap<>();
        req.put(UUID.randomUUID().toString(), 10);
        req.put(UUID.randomUUID().toString(), 50);
        return req;
    }

    private RoutingRequest getExampleRequest() {
        final Set<RequirementDTO> reqSet = new HashSet<>();
        requirements().forEach((key, value) -> {
            IntegerSkillRequirementDTO integerReq = new IntegerSkillRequirementDTO();
            integerReq.setSkillKey(key);
            integerReq.setValue(value);
            reqSet.add(integerReq);
        });
        final RequirementRoutingStrategy strategy = new RequirementRoutingStrategy(reqSet);

        return new RoutingRequest(strategy);
    }

    private EvaluationResult getExampleEvaluationResult(final String... users) {

        final Map<String, Set<EvaluationUserResult>> completeEvalResult = new HashMap<>();
        Arrays.asList(users).forEach(user -> {
            final Set<EvaluationUserResult> userEvalResult = new HashSet<>();
            userEvalResult.add(new SkillEvaluationUserResult(new HashSet<>()));
            completeEvalResult.put(user, userEvalResult);
        });

        return new EvaluationResult(completeEvalResult);
    }

    @Test
    public void should_ScoreEachUser() {

        final EvaluationResult evaluationResult = this.getExampleEvaluationResult();
        final RoutingRequest request = this.getExampleRequest();

        final ScoringResult result = this.skillScoring.score(evaluationResult, request);

        assertThat(result.getActual().keySet()).isEqualTo(evaluationResult.getUsers().keySet());
    }

    @Test
    public void shoud_MapRequirements_To_Desired() {

        //final ScoringResult result = this.skillScoring.score(result(), request());

        //this.requirements.forEach((key, value) -> {
        //    assertTrue(result.getDesired().contains(value));
        //});
    }

}