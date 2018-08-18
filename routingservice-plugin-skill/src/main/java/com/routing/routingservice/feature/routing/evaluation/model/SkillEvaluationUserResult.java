package com.routing.routingservice.feature.routing.evaluation.model;

import java.util.Set;

import com.routing.routingservice.feature.routing.evaluation.model.EvaluationUserResult;
import com.routing.routingservice.feature.skill.model.Shape;

/**
 * <b>com.routing.routingservice.feature.routing.evaluation.model.SkillEvaluationUserResult</b>
 * <p>
 *   skill model of {@link EvaluationUserResult}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillEvaluationUserResult extends EvaluationUserResult {

	private Set<Shape> shapes;

	public SkillEvaluationUserResult() {
		
	}
	
	public SkillEvaluationUserResult(Set<Shape> shapes) {
		super();
		this.shapes = shapes;
	}

	public Set<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(Set<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SkillEvaluationUserResult [shapes=");
		builder.append(shapes);
		builder.append("]");
		return builder.toString();
	}

}
