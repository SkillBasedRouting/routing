package com.routing.routingservice.feature.routing.model;

import com.routing.routingservice.client.model.RoutingRequestDTO;
import com.routing.routingservice.client.model.strategy.RoutingStrategyDTO;
import com.routing.routingservice.feature.routing.model.strategy.RoutingStrategy;

public class RoutingRequest {

    private RoutingStrategy strategy;

    public RoutingRequest() {

    }

    public RoutingRequest(final RoutingRequestDTO requestDTO) {
        this(requestDTO.getStrategy());
    }

    public RoutingRequest(final RoutingStrategyDTO strategyDTO) {
        this(RoutingStrategy.ofDTO(strategyDTO));
    }

    public RoutingRequest(final RoutingStrategy strategy) {
        this.strategy = strategy;
    }

    public void validate() {
        this.strategy.validate();
    }

    public RoutingStrategy getStrategy() {
        return strategy;
    }

    public <E extends RoutingStrategy> E getStrategy(Class<E> type) {
        if (this.strategy.getClass().isAssignableFrom(type)) {
            return (E) strategy;
        } else {
            throw new IllegalArgumentException("strategy is not instance of requested type");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((strategy == null) ? 0 : strategy.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RoutingRequest other = (RoutingRequest) obj;
        if (strategy == null) {
            if (other.strategy != null)
                return false;
        } else if (!strategy.equals(other.strategy))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RoutingRequest [strategy=");
        builder.append(strategy);
        builder.append("]");
        return builder.toString();
    }


    public static final class Builder {
        private RoutingStrategy strategy;

        private Builder() {
        }

        public static Builder aRoutingRequest() {
            return new Builder();
        }

        public Builder withStrategy(RoutingStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public RoutingRequest build() {
            return new RoutingRequest(strategy);
        }
    }
}
