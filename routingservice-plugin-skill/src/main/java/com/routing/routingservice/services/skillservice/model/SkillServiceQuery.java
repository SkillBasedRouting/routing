package com.routing.routingservice.services.skillservice.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.routing.routingservice.client.model.requirements.RequirementDTO;
import com.routing.routingservice.client.model.requirements.membership.GroupMembershipRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.BooleanSkillRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.EnumSkillRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.IntegerSkillRequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.SkillRequirementDTO;
import com.routing.routingservice.feature.requirement.model.Requirements;
import com.routing.skillservice.dto.query.QueryRequestDTO;
import com.routing.skillservice.dto.shape.request.RequestBooleanShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestEnumShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestIntegerShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestShapeDTO;

/**
 * <b>com.routing.routingservice.services.skillservice.model.SkillServiceQuery</b>
 * <p>
 *   query of skill service for finding all eligible users
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillServiceQuery extends QueryRequestDTO {

	public SkillServiceQuery(final Set<RequirementDTO> requirements) {
		this(new Requirements(requirements));
	}

	public SkillServiceQuery(final Requirements requirements) {
		super();

		setShapes(requirements);
		setGroups(requirements);
	}

	private void setShapes(final Requirements requirements) {
		final Set<RequestShapeDTO> shapes = requirements.get(SkillRequirementDTO.class)
				.map(req -> this.buildShapeDTO(req)).collect(Collectors.toSet());
		super.setShapes(shapes);
	}

	private void setGroups(final Requirements requirements) {
		final Set<String> groups = requirements.get(GroupMembershipRequirementDTO.class).map(req -> req.getGroup())
				.collect(Collectors.toSet());
		super.setGroups(groups);
	}

	private RequestShapeDTO buildShapeDTO(final SkillRequirementDTO requirement) {

		if (null == requirement) {
			return null;
		}

		if (requirement instanceof IntegerSkillRequirementDTO) {
			return this.buildShapeDTO((IntegerSkillRequirementDTO) requirement);
		} else if (requirement instanceof BooleanSkillRequirementDTO) {
			return this.buildShapeDTO((BooleanSkillRequirementDTO) requirement);
		} else if (requirement instanceof EnumSkillRequirementDTO) {
			return this.buildShapeDTO((EnumSkillRequirementDTO) requirement);
		} else {
			throw new IllegalArgumentException("unknown requirement type");
		}

	}

	public RequestIntegerShapeDTO buildShapeDTO(final IntegerSkillRequirementDTO requirement) {

		if (null == requirement) {
			return null;
		}

		final RequestIntegerShapeDTO shapeDTO = new RequestIntegerShapeDTO();
		shapeDTO.setSkillKey(requirement.getSkillKey());
		shapeDTO.setValue(requirement.getValue());

		return shapeDTO;
	}

	public RequestBooleanShapeDTO buildShapeDTO(final BooleanSkillRequirementDTO requirement) {

		if (null == requirement) {
			return null;
		}

		final RequestBooleanShapeDTO shapeDTO = new RequestBooleanShapeDTO();
		shapeDTO.setSkillKey(requirement.getSkillKey());
		shapeDTO.setValue(requirement.getValue());

		return shapeDTO;
	}

	public RequestEnumShapeDTO buildShapeDTO(final EnumSkillRequirementDTO requirement) {

		if (null == requirement) {
			return null;
		}

		final RequestEnumShapeDTO shapeDTO = new RequestEnumShapeDTO();
		shapeDTO.setSkillKey(requirement.getSkillKey());
		shapeDTO.setValue(requirement.getValue());

		return shapeDTO;
	}

}
