package com.routing.routingservice.client.model.requirements;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.routing.routingservice.client.model.requirements.membership.GroupMembershipRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.BooleanSkillRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.EnumSkillRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.IntegerSkillRequirementDTO;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * <b>com.routing.routingservice.client.model.requirements.RequirementDTO</b>
 * <p>
 *   represents a reuirement to an user
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(name = IntegerSkillRequirementDTO.TYPE, value = IntegerSkillRequirementDTO.class),
		@Type(name = BooleanSkillRequirementDTO.TYPE, value = BooleanSkillRequirementDTO.class),
		@Type(name = EnumSkillRequirementDTO.TYPE, value = EnumSkillRequirementDTO.class),
		@Type(name = GroupMembershipRequirementDTO.TYPE, value = GroupMembershipRequirementDTO.class) })
public abstract class RequirementDTO {

	public abstract String type();

}
