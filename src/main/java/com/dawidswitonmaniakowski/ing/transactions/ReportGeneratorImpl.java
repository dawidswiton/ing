package com.dawidswitonmaniakowski.ing.transactions;

import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

@RestController
class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public Account[] generate(Transaction[] transactions) {

        AmountCalculator calculator = new AmountCalculator();
        Map<String, Account> calculatedAmount = calculator.execute(transactions);

        Stream<Account> sortedByAccount = calculatedAmount
                .values().stream()
                .sorted(Comparator.comparing(Account::getAccount));

        return sortedByAccount.toArray(Account[]::new);
    }
}
