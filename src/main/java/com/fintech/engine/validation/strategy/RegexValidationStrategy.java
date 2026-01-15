package com.fintech.engine.validation.strategy;

import com.fintech.engine.annotations.Regex;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

public class RegexValidationStrategy implements ValidationStrategy {

    public boolean isValid(Object value, Annotation a) {
        if (value == null) return true;
        Regex r = (Regex) a;
        return Pattern.matches(r.pattern(), value.toString());
    }

    public String getMessage(Annotation a) {
        return ((Regex) a).message();
    }
}

