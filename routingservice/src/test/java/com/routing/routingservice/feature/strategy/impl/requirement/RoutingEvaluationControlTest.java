package com.routing.routingservice.feature.strategy.impl.requirement;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.routing.routingservice.feature.routing.evaluation.RoutingEvaluation;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationUserResult;
import com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.enterprise.inject.Instance;

public class RoutingEvaluationControlTest {

	@InjectMocks
	private RoutingEvaluationControl routingEvaluationControl;

	@Mock
	private Instance<RoutingEvaluation> evaluations;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		when(this.evaluations.iterator()).thenReturn(new HashSet<RoutingEvaluation>().iterator());
	}

	public void setRoutingEvaluationControl(final String[]... evaluations) {
		final List<RoutingEvaluation> routingEvaluations = new ArrayList<>();
		for (String[] users : evaluations) {
			routingEvaluations.add(new RoutingEvaluation() {
				@Override
				public SingleEvaluationResult evaluate(RoutingRequest request) {
					return new SingleEvaluationResult(Arrays.asList(users).stream()
							.collect(Collectors.toMap(u -> u, u -> new EvaluationUserResult() {
							})));
				}
			});
		}
        when(this.evaluations.iterator()).thenReturn(routingEvaluations.iterator());
	}

	@Test
	public void should_ReturnUsers_On_Evaluate() {

		final String[] users = new String[] { "1", "2", "3" };
		this.setRoutingEvaluationControl(users);

		final EvaluationResult result = this.routingEvaluationControl.evaluate(new RoutingRequest());

		for (String user : users) {
			assertTrue(result.getUsers().containsKey(user));
		}
	}

    @Test
    public void should_ReturnMergedSingleUser() {

        final String[] users1 = new String[] { "1", "2", "3" };
        final String[] users2 = new String[] { "3", "4", "5" };
        this.setRoutingEvaluationControl(users1, users2);

        final EvaluationResult result = this.routingEvaluationControl.evaluate(new RoutingRequest());

        final String[] desiredResult = new String[] { "3" };
        for (String user : desiredResult) {
            assertTrue(result.getUsers().containsKey(user));
        }
    }

    @Test
    public void should_ReturnMergedMultipleUsers() {

        final String[] users1 = new String[] { "1", "2", "3" };
        final String[] users2 = new String[] { "2", "3", "5" };
        this.setRoutingEvaluationControl(users1, users2);

        final EvaluationResult result = this.routingEvaluationControl.evaluate(new RoutingRequest());

        final String[] desiredResult = new String[] { "2", "3" };
        for (String user : desiredResult) {
            assertTrue(result.getUsers().containsKey(user));
        }
    }

}
