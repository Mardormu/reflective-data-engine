package com.fintech.engine.validation.strategy;

import com.fintech.engine.annotations.NotNull;
import java.lang.annotation.Annotation;

public class NotNullValidationStrategy implements ValidationStrategy {

    public boolean isValid(Object value, Annotation a) {
        return value != null && !(value instanceof String s && s.isBlank());
    }

    public String getMessage(Annotation a) {
        return ((NotNull) a).message();
    }
}

