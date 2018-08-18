package com.routing.routingservice.feature.strategy;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>com.routing.routingservice.feature.strategy.Strategy</b>
 * <p>
 * TODO: comment
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface Strategy {

    Class value();

    class StrategyLiteral extends AnnotationLiteral<Strategy> implements Strategy {

        private Class value;

        public StrategyLiteral(final Class value) {
            this.value = value;
        }

        @Override
        public Class value() {
            return this.value;
        }
    }

}
