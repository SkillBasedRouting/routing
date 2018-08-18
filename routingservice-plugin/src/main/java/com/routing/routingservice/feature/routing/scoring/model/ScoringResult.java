package com.routing.routingservice.feature.routing.scoring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>com.routing.routingservice.feature.routing.scoring.model.ScoringResult</b>
 * <p>
 * result of a scoring
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class ScoringResult {

    private List<Integer> desired;
    private Map<String, List<Integer>> actual;

    public ScoringResult() {
        this.desired = new ArrayList<>();
        this.actual = new HashMap<>();
    }

    public ScoringResult(List<Integer> desired, Map<String, List<Integer>> actual) {
        super();
        this.desired = desired;
        this.actual = actual;
    }

    /**
     * merge scoringResult into this
     *
     * @param scoringResult scoringResult to merge into
     * @return merged scoringResult
     */
    public ScoringResult merge(final ScoringResult scoringResult) {
        this.desired.addAll(scoringResult.desired);
        scoringResult.getActual().forEach((user, values) -> {
            if (!this.getActual().containsKey(user)) {
                this.getActual().put(user, new ArrayList<>());
            }
            this.getActual().get(user).addAll(values);
        });
        return this;
    }

    public List<Integer> getDesired() {
        return desired;
    }

    public void setDesired(List<Integer> desired) {
        this.desired = desired;
    }

    public Map<String, List<Integer>> getActual() {
        return actual;
    }

    public void setActual(Map<String, List<Integer>> actual) {
        this.actual = actual;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actual == null) ? 0 : actual.hashCode());
        result = prime * result + ((desired == null) ? 0 : desired.hashCode());
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
        ScoringResult other = (ScoringResult) obj;
        if (actual == null) {
            if (other.actual != null)
                return false;
        } else if (!actual.equals(other.actual))
            return false;
        if (desired == null) {
            if (other.desired != null)
                return false;
        } else if (!desired.equals(other.desired))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ScoringResult [desired=");
        builder.append(desired);
        builder.append(", actual=");
        builder.append(actual);
        builder.append("]");
        return builder.toString();
    }

    public static final class Builder {
        private ScoringResult scoringResult;

        private Builder() {
            scoringResult = new ScoringResult();
        }

        public static Builder aScoringResult() {
            return new Builder();
        }

        public Builder withDesired(List<Integer> desired) {
            scoringResult.setDesired(desired);
            return this;
        }

        public Builder withActual(Map<String, List<Integer>> actual) {
            scoringResult.setActual(actual);
            return this;
        }

        public ScoringResult build() {
            return scoringResult;
        }
    }
}
