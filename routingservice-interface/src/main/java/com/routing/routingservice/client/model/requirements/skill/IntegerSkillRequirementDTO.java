package com.routing.routingservice.client.model.requirements.skill;

/**
 * <b>com.routing.routingservice.client.model.requirements.skill.IntegerSkillRequirementDTO</b>
 * <p>
 * represents a skill that shape is numeric
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class IntegerSkillRequirementDTO extends SkillRequirementDTO {

    public static final String TYPE = "http://routing.com/routingservice/v1/types/requirement/skill/integer";

    private Integer value;

    @Override
    public String type() {
        return IntegerSkillRequirementDTO.TYPE;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        IntegerSkillRequirementDTO other = (IntegerSkillRequirementDTO) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IntegerRequirementDTO [base=");
        builder.append(super.toString());
        builder.append(", value=");
        builder.append(value);
        builder.append("]");
        return builder.toString();
    }

    public static final class Builder {
        private IntegerSkillRequirementDTO integerSkillRequirementDTO;

        private Builder() {
            integerSkillRequirementDTO = new IntegerSkillRequirementDTO();
        }

        public static Builder anIntegerSkillRequirementDTO() {
            return new Builder();
        }

        public Builder withSkillKey(String skillKey) {
            integerSkillRequirementDTO.setSkillKey(skillKey);
            return this;
        }

        public Builder withValue(Integer value) {
            integerSkillRequirementDTO.setValue(value);
            return this;
        }

        public IntegerSkillRequirementDTO build() {
            return integerSkillRequirementDTO;
        }
    }
}
