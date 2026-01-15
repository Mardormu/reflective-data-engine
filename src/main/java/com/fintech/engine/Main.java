package com.fintech.engine;

import com.fintech.engine.model.*;
import com.fintech.engine.parser.GenericFileParser;
import com.fintech.engine.validation.Validator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        try {
            GenericFileParser parser = new GenericFileParser();
            Validator validator = new Validator();

            System.out.println("=== TRANSACTIONS ===");
            List<TransactionLog> transactions =
                    parser.parse("src/test/resources/transactions.txt", TransactionLog.class);

            Map<TransactionLog, Set<String>> tErrors =
                    validator.validate(transactions);

            System.out.println("Records: " + transactions.size());
            System.out.println("Errors: " + tErrors.size());

            System.out.println("\n=== CUSTOMERS ===");
            List<CustomerUpdate> customers =
                    parser.parse("src/test/resources/customers.txt", CustomerUpdate.class);

            Map<CustomerUpdate, Set<String>> cErrors =
                    validator.validate(customers);

            System.out.println("Records: " + customers.size());
            System.out.println("Errors: " + cErrors.size());

            System.out.println("\n=== AUDITS ===");
            List<SecurityAudit> audits =
                    parser.parse("src/test/resources/audits.txt", SecurityAudit.class);

            Map<SecurityAudit, Set<String>> aErrors =
                    validator.validate(audits);

            System.out.println("Records: " + audits.size());
            System.out.println("Errors: " + aErrors.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
