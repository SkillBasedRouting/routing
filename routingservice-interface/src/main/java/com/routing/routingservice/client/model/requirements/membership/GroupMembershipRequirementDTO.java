package com.routing.routingservice.client.model.requirements.membership;

import com.routing.routingservice.client.model.requirements.RequirementDTO;

/**
 * <b>com.routing.routingservice.client.model.requirements.membership.GroupMembershipRequirementDTO</b>
 * <p>
 * represents a membership in a group
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class GroupMembershipRequirementDTO extends RequirementDTO {

    public static final String TYPE = "http://routing.com/routingservice/v1/types/requirement/groupmembership";

    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String type() {
        return GroupMembershipRequirementDTO.TYPE;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((group == null) ? 0 : group.hashCode());
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
        GroupMembershipRequirementDTO other = (GroupMembershipRequirementDTO) obj;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GroupRequirement [group=");
        builder.append(group);
        builder.append("]");
        return builder.toString();
    }

    public static final class Builder {
        private GroupMembershipRequirementDTO groupMembershipRequirementDTO;

        private Builder() {
            groupMembershipRequirementDTO = new GroupMembershipRequirementDTO();
        }

        public static Builder aGroupMembershipRequirementDTO() {
            return new Builder();
        }

        public Builder withGroup(String group) {
            groupMembershipRequirementDTO.setGroup(group);
            return this;
        }

        public GroupMembershipRequirementDTO build() {
            return groupMembershipRequirementDTO;
        }
    }
}
