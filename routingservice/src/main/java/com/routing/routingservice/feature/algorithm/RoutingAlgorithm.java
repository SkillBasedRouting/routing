package com.routing.routingservice.feature.algorithm;

import com.routing.routingservice.feature.routing.scoring.model.ScoringResult;

/**
 * <b>com.routing.routingservice.feature.algorithm.RoutingAlgorithm</b>
 * <p>
 *   a routing algorithm is used to find the best user of a scoring
 *
 *   e.g. given following scoring:
 *
 *              desired User1 User2
 *   SkillA     50      60    70
 *   SkillB     30      100   40
 *
 *   the scoring algorithm should return either
 *
 *   "User1" or "User2"
 *
 *   if both users are equal skilled one of them should be returned
 *
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public interface RoutingAlgorithm {

    /**
     * find the best user of a scoring result
     * if more than one user is possible, one of them will be returned
     * @param result scoring result
     * @return user as it is stored as key in the "actual" map in scoring result
     */
	String findBestUser(ScoringResult result);

}
