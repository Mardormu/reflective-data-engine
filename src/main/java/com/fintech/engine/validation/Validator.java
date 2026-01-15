package com.fintech.engine.validation;

import com.fintech.engine.annotations.*;
import com.fintech.engine.validation.strategy.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class Validator {

    private final Map<Class<? extends Annotation>, ValidationStrategy> strategies = Map.of(
            NotNull.class, new NotNullValidationStrategy(),
            Regex.class, new RegexValidationStrategy(),
            Range.class, new RangeValidationStrategy()
    );

    public <T> Map<T, Set<String>> validate(List<T> objects) {

        Map<T, Set<String>> errors = new HashMap<>();

        for (T obj : objects) {
            Set<String> objErrors = new HashSet<>();

            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                Object value;
                try {
                    value = field.get(obj);
                } catch (Exception e) {
                    continue;
                }

                for (Annotation a : field.getAnnotations()) {
                    ValidationStrategy strategy = strategies.get(a.annotationType());
                    if (strategy != null) {
                        if (!strategy.isValid(value, a)) {
                            objErrors.add(strategy.getMessage(a));
                        }
                    }
                }
            }

            if (!objErrors.isEmpty()) {
                errors.put(obj, objErrors);
            }
        }

        return errors;
    }
}

