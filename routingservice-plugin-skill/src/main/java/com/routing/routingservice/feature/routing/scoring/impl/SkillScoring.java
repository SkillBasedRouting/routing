package com.routing.routingservice.feature.routing.scoring.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.routing.routingservice.client.model.requirements.RequirementDTO;
import com.routing.routingservice.client.model.requirements.skill.IntegerSkillRequirementDTO;
import com.routing.routingservice.feature.routing.evaluation.model.EvaluationResult;
import com.routing.routingservice.feature.routing.evaluation.model.SkillEvaluationUserResult;
import com.routing.routingservice.feature.routing.model.RoutingRequest;
import com.routing.routingservice.feature.routing.model.strategy.RequirementRoutingStrategy;
import com.routing.routingservice.feature.routing.scoring.RoutingScoring;
import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;
import com.routing.routingservice.feature.skill.model.IntegerShape;
import com.routing.routingservice.services.skillservice.SkillServiceClient;

/**
 * <b>com.routing.routingservice.feature.routing.scoring.impl.SkillScoring</b>
 * <p>
 *   skill scoring of all eligible users
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class SkillScoring implements RoutingScoring {

    private static final double DEFAULT_MULTIPLIER = 1D;

    @Inject
	protected SkillServiceClient skillService;

    /**
     * score all eligible users by their skills
     * @param result evaluation results
     * @param request routing request
     * @return skill scoring
     */
	@Override
	public ScoringResult score(final EvaluationResult result, final RoutingRequest request) {

		if (false == request.getStrategy() instanceof RequirementRoutingStrategy) {
			throw new IllegalArgumentException("illegal strategy for skillservice plugin");
		}
		final RequirementRoutingStrategy strategy = (RequirementRoutingStrategy) request.getStrategy();

		// get all multipliers from skill api
		final Map<String, Double> multipliers = this.fetchMultipliers(strategy.getRequirements());

		// find desired values
		final List<Integer> desired = this.scoreRequirements(strategy.getRequirements(), multipliers);
		final Map<String, List<Integer>> actual = new HashMap<>();

		// score each user
		result.getUsers().forEach((user, results) -> {
			final Optional<SkillEvaluationUserResult> skillResult = results.stream()
					.filter(r -> r instanceof SkillEvaluationUserResult).map(r -> (SkillEvaluationUserResult) r)
					.findAny();
			if (skillResult.isPresent()) {
				actual.put(user, this.scoreUser(skillResult.get(), multipliers));
			} else {
				System.out.println("no skill result for user " + user);
				throw new RuntimeException("no skill result for user" + user);
			}
		});

		return new ScoringResult(desired, actual);
	}

    /**
     * fetch multipliers from skill api
     * @param requirements
     * @return
     */
	private Map<String, Double> fetchMultipliers(final Set<RequirementDTO> requirements) {
		final Set<String> keys = requirements.stream().filter(req -> req instanceof IntegerSkillRequirementDTO)
				.map(req -> ((IntegerSkillRequirementDTO) req).getSkillKey()).collect(Collectors.toSet());
		return this.skillService.getMultipliers(keys);
	}

    /**
     * score requirements
     * @param requirements
     * @param multipliers
     * @return
     */
	private List<Integer> scoreRequirements(final Set<RequirementDTO> requirements, Map<String, Double> multipliers) {

		final List<Integer> integerValues = new ArrayList<>(requirements.size());
		requirements.forEach((requirement) -> {
			if (requirement instanceof IntegerSkillRequirementDTO) {
				final Double multiplier = multipliers
						.getOrDefault(((IntegerSkillRequirementDTO) requirement).getSkillKey(), DEFAULT_MULTIPLIER);
				final Double score = ((IntegerSkillRequirementDTO) requirement).getValue() * multiplier;
				integerValues.add(score.intValue());
			}
		});

		return integerValues;
	}

    /**
     * scores a user
     * @param skillResult
     * @param multipliers
     * @return
     */
	private List<Integer> scoreUser(final SkillEvaluationUserResult skillResult,
			final Map<String, Double> multipliers) {
		final List<Integer> shapeValues = new ArrayList<>(skillResult.getShapes().size());
		skillResult.getShapes().forEach((shape) -> {
			if (shape instanceof IntegerShape) {
				final Double multiplier = multipliers.getOrDefault(shape.getSkillKey(), DEFAULT_MULTIPLIER);
				final Double score = ((IntegerShape) shape).getValue() * multiplier;
				shapeValues.add(score.intValue());
			}
		});
		return shapeValues;
	}

}
