package com.routing.routingservice.services.skillservice.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.routing.routingservice.feature.skill.model.BooleanShape;
import com.routing.routingservice.feature.skill.model.EnumShape;
import com.routing.routingservice.feature.skill.model.IntegerShape;
import com.routing.routingservice.feature.skill.model.Shape;
import com.routing.routingservice.feature.user.model.UserShape;
import com.routing.skillservice.dto.shape.BooleanShapeDTO;
import com.routing.skillservice.dto.shape.EnumShapeDTO;
import com.routing.skillservice.dto.shape.IntegerShapeDTO;
import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.user.UserShapeDTO;

/**
 * <b>com.routing.routingservice.services.skillservice.model.SkillServiceQueryResponse</b>
 * <p>
 *   response of a {@link SkillServiceQuery}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillServiceQueryResponse extends HashSet<UserShape> {

	private static final long serialVersionUID = 1L;

	public SkillServiceQueryResponse(final Set<UserShapeDTO> userShapesDTO) {
		super.addAll(userShapesDTO.stream().map(this::toModel).collect(Collectors.toSet()));
	}

	private UserShape toModel(final UserShapeDTO userShapeDTO) {

		if (null == userShapeDTO) {
			return null;
		}

		final UserShape userShape = new UserShape();
		userShape.setUserId(userShapeDTO.getUserId());
		userShape.setShapes(userShapeDTO.getShapes().stream().map(this::toModel).collect(Collectors.toSet()));

		return userShape;
	}

	private Shape toModel(final ShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			return null;
		}

		if (shapeDTO instanceof BooleanShapeDTO) {
			return this.toModel((BooleanShapeDTO) shapeDTO);
		} else if (shapeDTO instanceof IntegerShapeDTO) {
			return this.toModel((IntegerShapeDTO) shapeDTO);
		} else if (shapeDTO instanceof EnumShapeDTO) {
			return this.toModel((EnumShapeDTO) shapeDTO);
		} else {
			throw new RuntimeException("unknown type");
		}

	}

	private BooleanShape toModel(final BooleanShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			return null;
		}

		final BooleanShape shape = new BooleanShape();
		shape.setId(shapeDTO.getId());
		shape.setSkillKey(shapeDTO.getSkillKey());
		shape.setValue(shapeDTO.getValue());

		return shape;
	}

	private IntegerShape toModel(final IntegerShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			return null;
		}

		final IntegerShape shape = new IntegerShape();
		shape.setId(shapeDTO.getId());
		shape.setSkillKey(shapeDTO.getSkillKey());
		shape.setValue(shapeDTO.getValue());

		return shape;
	}

	private EnumShape toModel(final EnumShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			return null;
		}

		final EnumShape shape = new EnumShape();
		shape.setId(shapeDTO.getId());
		shape.setSkillKey(shapeDTO.getSkillKey());
		shape.setValue(shapeDTO.getValue());

		return shape;
	}

}
