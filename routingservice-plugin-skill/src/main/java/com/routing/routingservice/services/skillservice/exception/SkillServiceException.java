package com.routing.routingservice.services.skillservice.exception;

import com.routing.routingservice.feature.routing.PluginException;

/**
 * <b>com.routing.routingservice.services.skillservice.exception.SkillServiceException</b>
 * <p>
 * exception for all skill api errors
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class SkillServiceException extends PluginException {

    public static final SkillServiceException SERVICE_NOT_FOUND = new SkillServiceException(0,
            "skillservice not found");
    public static final SkillServiceException SERVICE_ERROR = new SkillServiceException(0, "skillservice error");
    private static final long serialVersionUID = 1L;

    public SkillServiceException(final Integer reasonCode, final String message) {
        super(reasonCode, message);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SkillServiceException extend [base=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
