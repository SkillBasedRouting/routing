package com.routing.routingservice.client.model.requirements.skill;

import java.util.Set;

/**
 * <b>com.routing.routingservice.client.model.requirements.skill.EnumSkillRequirementDTO</b>
 * <p>
 * represents a skill that shape can be multiple non numeric values
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class EnumSkillRequirementDTO extends SkillRequirementDTO {

    public static final String TYPE = "http://routing.com/routingservice/v1/types/requirement/skill/enum";

    private Set<String> value;

    @Override
    public String type() {
        return EnumSkillRequirementDTO.TYPE;
    }

    public Set<String> getValue() {
        return value;
    }

    public void setValue(Set<String> value) {
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
        EnumSkillRequirementDTO other = (EnumSkillRequirementDTO) obj;
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
        builder.append("EnumSkillRequirementDTO []");
        return builder.toString();
    }

    public static final class Builder {
        private EnumSkillRequirementDTO enumSkillRequirementDTO;

        private Builder() {
            enumSkillRequirementDTO = new EnumSkillRequirementDTO();
        }

        public static Builder anEnumSkillRequirementDTO() {
            return new Builder();
        }

        public Builder withSkillKey(String skillKey) {
            enumSkillRequirementDTO.setSkillKey(skillKey);
            return this;
        }

        public Builder withValue(Set<String> value) {
            enumSkillRequirementDTO.setValue(value);
            return this;
        }

        public EnumSkillRequirementDTO build() {
            return enumSkillRequirementDTO;
        }
    }
}
