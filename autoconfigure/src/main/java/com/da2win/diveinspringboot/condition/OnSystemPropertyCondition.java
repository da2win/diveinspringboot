package com.da2win.diveinspringboot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

/**
 *
 */
public class OnSystemPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        MultiValueMap<String, Object> attributes = annotatedTypeMetadata.getAllAnnotationAttributes(ConditionalOnSystemProperty.class.getName());

        String propertyName = String.valueOf(attributes.get("name"));

        String propertyValue = String.valueOf(attributes.get("value"));

        String javaPropertyValue = System.getenv(propertyName.substring(1, propertyName.length() - 1));

        return javaPropertyValue.equals(propertyValue.substring(1, propertyValue.length() - 1));
    }
}
