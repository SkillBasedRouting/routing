package com.routing.routingservice.client.model.requirements.skill;

import com.routing.routingservice.client.model.requirements.RequirementDTO;

/**
 * <b>com.routing.routingservice.client.model.requirements.skill.SkillRequirementDTO</b>
 * <p>
 *   represents a skill
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public abstract class SkillRequirementDTO extends RequirementDTO {

	private String skillKey;

	public abstract Object getValue();

	public String getSkillKey() {
		return skillKey;
	}

	public void setSkillKey(String skillKey) {
		this.skillKey = skillKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequirementDTO [skillKey=");
		builder.append(skillKey);
		builder.append("]");
		return builder.toString();
	}

}
