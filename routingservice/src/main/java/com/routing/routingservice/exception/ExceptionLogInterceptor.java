package com.routing.routingservice.exception;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * <b>com.routing.routingservice.exception.ExceptionLogInterceptor</b>
 * <p>
 * logs all occuring exceptions and throws generic error on unexpected exceptions
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
public class ExceptionLogInterceptor {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object mdbInterceptor(InvocationContext ctx) throws Exception {
        try {
            return ctx.proceed();
        } catch (RoutingServiceException e) {
            this.logger.error("error: " + e);
            throw e;
        } catch (Exception e) {
            this.logger.error("unexpected error: " + e);
            e.printStackTrace();
            throw RoutingServiceException.GENERIC_ERROR;
        }
    }

}
