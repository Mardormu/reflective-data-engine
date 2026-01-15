package com.fintech.engine.validation.strategy;

import com.fintech.engine.annotations.Range;

import java.lang.annotation.Annotation;

public class RangeValidationStrategy implements ValidationStrategy {

    public boolean isValid(Object value, Annotation a) {
        if (value == null) return true;
        Range r = (Range) a;
        double v = Double.parseDouble(value.toString());
        return v >= r.min() && v <= r.max();
    }

    public String getMessage(Annotation a) {
        return ((Range) a).message();
    }
}

