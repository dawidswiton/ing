package com.dawidswitonmaniakowski.ing.transactions;

import com.dawidswitonmaniakowski.ing.Statistics;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Slf4j
class PerformanceTesterTest {

    Random r = new Random();

    List<String> accounts;
    Set<String> accountsSet = new HashSet<>();

    @Test
    @Disabled("only performance testing")
    void speedTest() {
        List<Long> times = new ArrayList<>();

        for (int z = 0; z < 100; z++) {

            List<BankTrans> transactions = new ArrayList<>();
            int numberOfTransactions = 100000; //generateNumber(100000);
            int numberOfDifferentAccounts = generateNumber(10000);
            generateAccounts(numberOfDifferentAccounts);

            for (int i = 0; i < numberOfTransactions; i++) {
                String debit = generateDebitAccountNumber(numberOfDifferentAccounts);
                String credit = generateCreditAccountNumber(numberOfDifferentAccounts,debit);
                BigDecimal amount = generateRandomAmount(1000000);
                transactions.add(
                        new BankTrans(
                                debit,
                                credit,
                                amount
                        ));
            }

            Instant start = Instant.now();
            //main execution
            ReportGeneratorImpl impl = new ReportGeneratorImpl();
            impl.generate(transactions.toArray(new BankTrans[transactions.size()]));

            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();

            times.add(timeElapsed);
            log.info("numberOfTransactions: " + numberOfTransactions);
            log.info("numberOfDifferentAccounts: " + numberOfDifferentAccounts);
            log.info("time: " + timeElapsed);

            //log output
//            for(var account : generate) {
//                log.info("account:" + account.getAccount());
//                log.info("credit:" + account.getCreditCount());
//                log.info("debit:" + account.getDebitCount());
//                log.info("amount:" + account.getBalance());
//            }
        }
        Statistics.statistics(times);
    }

    private int generateNumber(int max) {
        return r.nextInt(max) + 1;
    }

    private String generateDebitAccountNumber(int numberOfDifferentAccounts) {
        return accounts.get(generateNumber(numberOfDifferentAccounts) - 1);
    }
    private String generateCreditAccountNumber(int numberOfDifferentAccounts, String debit) {
        String account;
        do {
            account = accounts.get(generateNumber(numberOfDifferentAccounts) - 1);
        }
        while(account.equals(debit));
        return account;
    }

    private BigDecimal generateRandomAmount(int max) {
        BigDecimal amount = new BigDecimal(generateNumber(max));

        return amount.divide(
                new BigDecimal(100),
                RoundingMode.HALF_UP
        );
    }

    private void generateAccounts(int numberOfAccounts) {
        while (accountsSet.size() < numberOfAccounts) {
            accountsSet.add(generateAccount());
        }
        accounts = new ArrayList<>(accountsSet);
    }

    private String generateAccount() {
        char[] account = new char[32];
        for (int i = 0; i < 32; i++) {
            account[i] = generateChar();
        }
        return String.valueOf(account);
    }

    private char generateChar() {
        return (char) (r.nextInt(10) + 48);
    }
}
