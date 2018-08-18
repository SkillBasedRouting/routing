package com.routing.routingservice.feature.strategy.impl.direct;

import com.routing.routingservice.exception.RoutingServiceException;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.RoutingResult;
import com.routing.routingservice.feature.routing.model.strategy.DirectRoutingStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * <b>com.routing.routingservice.feature.strategy.impl.direct.DirectStrategyWorkerTest</b>
 * <p>
 * tests for {@link DirectStrategyWorker}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class DirectStrategyWorkerTest {

    private DirectStrategyWorker worker;

    @Before
    public void init() {
        this.worker = new DirectStrategyWorker();
    }

    @Test
    public void should_SetGroup_With_DirectStrategy_On_Route() {

        final DirectRoutingStrategy strategy = new DirectRoutingStrategy();
        final RoutingRequest request = new RoutingRequest(strategy);

        final String group = UUID.randomUUID().toString();
        strategy.setGroup(group);

        final RoutingResult result = this.worker.route(request);

        assertThat(result.getGroup()).isEqualTo(group);
    }

    @Test
    public void should_SetUser_With_DirectStrategy_On_Route() {

        final DirectRoutingStrategy strategy = new DirectRoutingStrategy();
        final RoutingRequest request = new RoutingRequest(strategy);

        final String user = UUID.randomUUID().toString();
        strategy.setUser(user);

        final RoutingResult result = this.worker.route(request);

        assertThat(result.getUser().getId()).isEqualTo(user);
    }

    @Test
    public void should_SetUserAndGroup_With_DirectStrategy_On_Route() {

        final DirectRoutingStrategy strategy = new DirectRoutingStrategy();
        final RoutingRequest request = new RoutingRequest(strategy);

        final String user = UUID.randomUUID().toString();
        strategy.setUser(user);
        final String group = UUID.randomUUID().toString();
        strategy.setGroup(group);

        final RoutingResult result = this.worker.route(request);

        assertThat(result.getUser().getId()).isEqualTo(user);
        assertThat(result.getGroup()).isEqualTo(group);
    }

}