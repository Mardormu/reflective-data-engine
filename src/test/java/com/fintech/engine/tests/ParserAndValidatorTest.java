package com.fintech.engine.tests;

import com.fintech.engine.model.*;
import com.fintech.engine.parser.GenericFileParser;
import com.fintech.engine.validation.Validator;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ParserAndValidatorTest {

    @Test
    void testCustomerValidation() throws Exception {

        Path path = Path.of(Objects.requireNonNull(
                getClass().getClassLoader().getResource("customers.txt")
        ).toURI());

        GenericFileParser parser = new GenericFileParser();
        List<CustomerUpdate> list = parser.parse(path.toString(), CustomerUpdate.class);

        Validator validator = new Validator();
        Map<CustomerUpdate, Set<String>> errors = validator.validate(list);

        assertEquals(1, errors.size());
    }

    @Test
    void testTransactionParsing() throws Exception {

        Path path = Path.of(Objects.requireNonNull(
                getClass().getClassLoader().getResource("transactions.txt")
        ).toURI());

        GenericFileParser parser = new GenericFileParser();
        List<TransactionLog> list = parser.parse(path.toString(), TransactionLog.class);

        assertEquals(2, list.size());
    }
}
