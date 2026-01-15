package com.fintech.engine.parser;

import com.fintech.engine.annotations.*;
import com.fintech.engine.exception.FileParsingException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class GenericFileParser {

    public <T> List<T> parse(String filePath, Class<T> clazz)
            throws IOException, ReflectiveOperationException {

        if (!clazz.isAnnotationPresent(FileSource.class)) {
            throw new IllegalArgumentException("Missing @FileSource on class " + clazz.getName());
        }

        FileSource fs = clazz.getAnnotation(FileSource.class);
        String delimiter = fs.delimiter();

        List<T> result = new ArrayList<>();

        List<String> lines = Files.readAllLines(Path.of(filePath));

        for (String line : lines) {
            if (line == null || line.isBlank()) continue;

            String[] parts = line.split(delimiter);

            T obj = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(Column.class)) continue;

                Column col = field.getAnnotation(Column.class);
                int idx = col.index();

                if (idx >= parts.length) {
                    throw new FileParsingException("Missing column at index " + idx);
                }

                String raw = parts[idx].trim();
                Object value = convert(raw, field.getType());

                field.setAccessible(true);
                field.set(obj, value);
            }

            result.add(obj);
        }

        return result;
    }

    private Object convert(String value, Class<?> type) {
        try {
            if (type == String.class) return value;
            if (type == int.class || type == Integer.class) return Integer.parseInt(value);
            if (type == double.class || type == Double.class) return Double.parseDouble(value);
            if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
            if (type == LocalDate.class) return LocalDate.parse(value);

            throw new FileParsingException("Unsupported type: " + type.getName());
        } catch (Exception e) {
            throw new FileParsingException("Invalid value: " + value, e);
        }
    }
}

