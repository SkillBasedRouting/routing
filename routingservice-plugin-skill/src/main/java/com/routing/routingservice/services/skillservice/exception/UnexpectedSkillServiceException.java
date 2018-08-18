package com.routing.routingservice.services.skillservice.exception;

/**
 * <b>com.routing.routingservice.services.skillservice.exception.UnexpectedSkillServiceException</b>
 * <p>
 * exception for an unexpected response of skill api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class UnexpectedSkillServiceException extends SkillServiceException {

    private static final long serialVersionUID = 1L;

    private Integer responseCode;
    private String responseMessage;

    public UnexpectedSkillServiceException(final Integer responseCode, final String responseMessage) {
        super(0, "skillservice error: " + responseMessage);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UnexpectedSkillServiceException [responseCode=");
        builder.append(responseCode);
        builder.append(", responseMessage=");
        builder.append(responseMessage);
        builder.append(", base=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
