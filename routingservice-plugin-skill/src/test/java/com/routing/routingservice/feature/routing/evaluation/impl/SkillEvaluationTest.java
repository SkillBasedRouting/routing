package com.routing.routingservice.feature.routing.evaluation.impl;

import com.routing.routingservice.client.model.requirements.RequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.BooleanSkillRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.IntegerSkillRequirementDTO;
import com.routing.routingservice.feature.routing.context.RoutingContext;
import com.routing.routingservice.feature.routing.evaluation.model.SingleEvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.SkillEvaluationUserResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.strategy.DirectRoutingStrategy;
import com.routing.routingservice.feature.routing.model.strategy.RequirementRoutingStrategy;
import com.routing.routingservice.feature.skill.model.IntegerShape;
import com.routing.routingservice.feature.skill.model.Shape;
import com.routing.routingservice.feature.user.model.UserShape;
import com.routing.routingservice.services.skillservice.SkillServiceClient;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * <b>com.routing.routingservice.feature.routing.evaluation.impl.SkillEvaluationTest</b>
 * <p>
 * tests for {@link SkillEvaluation}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class SkillEvaluationTest {

    @InjectMocks
    private SkillEvaluation skillEvaluation;

    @Mock
    private SkillServiceClient skillServiceClient;

    //@Mock
    //private Event<MonitoringEventDTO> monitoringEvents;

    @Mock
    private RoutingContext routingContext;


    public SkillEvaluationTest() {

        MockitoAnnotations.initMocks(this);

        // doNothing().when(this.monitoringEvents).fire(any());
        when(this.routingContext.getRoutingId()).thenReturn(UUID.randomUUID().toString());
        when(this.skillServiceClient.query(any())).thenReturn(Collections.emptySet());
    }

    private UserShape userShape(final String id) {
        final UserShape userShape = new UserShape();
        userShape.setUserId(id);
        userShape.setShapes(new LinkedHashSet<>());
        userShape.getShapes().add(integerShape());
        return userShape;
    }

    private IntegerShape integerShape() {
        final IntegerShape integerShape = new IntegerShape();
        integerShape.setSkillKey(UUID.randomUUID().toString());
        integerShape.setValue(10);
        return integerShape;
    }

    @Test
    public void should_ConsiderUserAsPossible() {

        final RequirementRoutingStrategy strategy = new RequirementRoutingStrategy(new LinkedHashSet<>());
        final RoutingRequest request = new RoutingRequest(strategy);

        final Set<UserShape> userShapes = new HashSet<>();
        final String user = UUID.randomUUID().toString();
        userShapes.add(this.userShape(user));
        when(this.skillServiceClient.query(any())).thenReturn(userShapes);

        SingleEvaluationResult result = this.skillEvaluation.evaluate(request);

        assertThat(userShapes.size()).isEqualTo(result.getUsers().size());
        assertThat(result.getUsers()).containsKey(user);
    }

    @Test
    public void should_ConsiderMultipleUsersAsPossible() {

        final RequirementRoutingStrategy strategy = new RequirementRoutingStrategy(new LinkedHashSet<>());
        final RoutingRequest request = new RoutingRequest(strategy);

        final Set<UserShape> userShapes = new HashSet<>();
        userShapes.add(this.userShape(UUID.randomUUID().toString()));
        userShapes.add(this.userShape(UUID.randomUUID().toString()));
        userShapes.add(this.userShape(UUID.randomUUID().toString()));
        when(this.skillServiceClient.query(any())).thenReturn(userShapes);

        SingleEvaluationResult result = this.skillEvaluation.evaluate(request);

        assertThat(userShapes.size()).isEqualTo(result.getUsers().size());
    }

    @Test
    public void should_CallSkillService_With_GivenRequirements() {

        final Set<RequirementDTO> requirements = new HashSet<>();
        requirements.add(IntegerSkillRequirementDTO.Builder.anIntegerSkillRequirementDTO().withSkillKey("integerSkill").withValue(10).build());
        requirements.add(BooleanSkillRequirementDTO.Builder.aBooleanSkillRequirementDTO().withSkillKey("booleanSkill").withValue(false).build());
        final RequirementRoutingStrategy strategy = new RequirementRoutingStrategy(requirements);
        final RoutingRequest request = new RoutingRequest(strategy);

        final ArgumentCaptor<Set<RequirementDTO>> requirementsArg = ArgumentCaptor.forClass(Set.class);
        when(this.skillServiceClient.query(requirementsArg.capture())).thenReturn(Collections.emptySet());

        this.skillEvaluation.evaluate(request);

        assertThat(requirementsArg.getValue()).isEqualTo(requirements);
    }

    @Test
    public void should_Throw_IllegalArgumentException_When_InvalidStrategyIsGiven() {

        try {
            final RoutingRequest request = RoutingRequest.Builder.aRoutingRequest().withStrategy(new DirectRoutingStrategy()).build();
            this.skillEvaluation.evaluate(request);
            fail("can call skill evaluation with other strategy");
        } catch (IllegalArgumentException e) {
            // ok
        }

    }

    /*@Test
    public void should_PublishResult() {

        final List<String> users = Arrays.asList("1", "2");

        final Set<UserShape> userShapes = new HashSet<>();
        users.forEach(user -> {
            userShapes.add(UserShape.Builder.anUserShape().withUserId(user).withShapes(Collections.emptySet()).build());
        });
        when(this.skillServiceClient.query(any())).thenReturn(userShapes);

        final ArgumentCaptor<SkillRoutingMonitoringEventDTO> monitoringArg = ArgumentCaptor.forClass(SkillRoutingMonitoringEventDTO.class);
        doNothing().when(this.monitoringEvents).fire(monitoringArg.capture());

        final RoutingRequest request = RoutingRequest.Builder.aRoutingRequest().withStrategy(new RequirementRoutingStrategy(Collections.emptySet())).build();
        this.skillEvaluation.evaluate(request);

        final Set<UserShapesDTO> expected = new HashSet<>();
        users.forEach(user -> {
            expected.add(UserShapesDTO.Builder.anUserShapesDTO().withUser(user).withShapes(new HashMap<>()).build());
        });
        assertThat(monitoringArg.getValue().getResult()).isEqualTo(expected);
    }

    @Test
    public void should_PublishResults_With_IntegerShapes() {

        final IntegerShape integerShape = IntegerShape.Builder.anIntegerShape().withSkillKey("skillA").withValue(10).build();
        final Set<Shape> shapes = new HashSet<>(Arrays.asList(integerShape));

        final String user = "A";

        final Set<UserShape> userShapes = new HashSet<>();
        userShapes.add(UserShape.Builder.anUserShape().withUserId(user).withShapes(shapes).build());
        when(this.skillServiceClient.query(any())).thenReturn(userShapes);

        final ArgumentCaptor<SkillRoutingMonitoringEventDTO> monitoringArg = ArgumentCaptor.forClass(SkillRoutingMonitoringEventDTO.class);
        doNothing().when(this.monitoringEvents).fire(monitoringArg.capture());

        final RoutingRequest request = RoutingRequest.Builder.aRoutingRequest().withStrategy(new RequirementRoutingStrategy(Collections.emptySet())).build();
        this.skillEvaluation.evaluate(request);

        assertThat(monitoringArg.getValue().getResult()).hasSize(1);
        final UserShapesDTO result = monitoringArg.getValue().getResult().iterator().next();
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getShapes()).containsOnlyKeys(integerShape.getSkillKey());
        assertThat(result.getShapes()).containsValue(integerShape.getValue());
    }*/

    @Test
    public void should_ReturnResult_With_Shapes() {

        final IntegerShape integerShape = IntegerShape.Builder.anIntegerShape().withSkillKey("skillA").withValue(10).build();
        final Set<Shape> shapes = new HashSet<>(Arrays.asList(integerShape));

        final String user = "A";

        final Set<UserShape> userShapes = new HashSet<>();
        userShapes.add(UserShape.Builder.anUserShape().withUserId(user).withShapes(shapes).build());
        when(this.skillServiceClient.query(any())).thenReturn(userShapes);

        final RoutingRequest request = RoutingRequest.Builder.aRoutingRequest().withStrategy(new RequirementRoutingStrategy(Collections.emptySet())).build();
        final SingleEvaluationResult result = this.skillEvaluation.evaluate(request);

        assertThat(result.getUsers()).containsOnlyKeys(user);
        final SkillEvaluationUserResult userResult = (SkillEvaluationUserResult) result.getUsers().get(user);
        assertThat(userResult.getShapes()).isEqualTo(shapes);
    }


}