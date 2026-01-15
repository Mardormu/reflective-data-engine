package com.fintech.engine.validation.strategy;

import java.lang.annotation.Annotation;

public interface ValidationStrategy {
    boolean isValid(Object value, Annotation annotation);
    String getMessage(Annotation annotation);
}

