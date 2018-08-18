package com.routing.routingservice.client.model.requirements.skill;

/**
 * <b>com.routing.routingservice.client.model.requirements.skill.BooleanSkillRequirementDTO</b>
 * <p>
 * represents a skill that shape is either true or false
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class BooleanSkillRequirementDTO extends SkillRequirementDTO {

    public static final String TYPE = "http://routing.com/routingservice/v1/types/requirement/skill/boolean";

    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String type() {
        return BooleanSkillRequirementDTO.TYPE;
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
        BooleanSkillRequirementDTO other = (BooleanSkillRequirementDTO) obj;
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
        builder.append("BooleanRequirementDTO [base=");
        builder.append(super.toString());
        builder.append(", value=");
        builder.append(value);
        builder.append("]");
        return builder.toString();
    }

    public static final class Builder {
        private BooleanSkillRequirementDTO booleanSkillRequirementDTO;

        private Builder() {
            booleanSkillRequirementDTO = new BooleanSkillRequirementDTO();
        }

        public static Builder aBooleanSkillRequirementDTO() {
            return new Builder();
        }

        public Builder withSkillKey(String skillKey) {
            booleanSkillRequirementDTO.setSkillKey(skillKey);
            return this;
        }

        public Builder withValue(Boolean value) {
            booleanSkillRequirementDTO.setValue(value);
            return this;
        }

        public BooleanSkillRequirementDTO build() {
            return booleanSkillRequirementDTO;
        }
    }
}
