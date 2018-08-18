package com.routing.routingservice.feature.skill.model;

public class IntegerShape extends Shape {

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IntegerShape [base=");
        builder.append(super.toString());
        builder.append("value=");
        builder.append(value);
        builder.append("]");
        return builder.toString();
    }

    public static final class Builder {
        private IntegerShape integerShape;

        private Builder() {
            integerShape = new IntegerShape();
        }

        public static Builder anIntegerShape() {
            return new Builder();
        }

        public Builder withId(String id) {
            integerShape.setId(id);
            return this;
        }

        public Builder withValue(Integer value) {
            integerShape.setValue(value);
            return this;
        }

        public Builder withSkillKey(String skillKey) {
            integerShape.setSkillKey(skillKey);
            return this;
        }

        public IntegerShape build() {
            return integerShape;
        }
    }
}
