package com.dawidswitonmaniakowski.ing.transactions;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

class AmountCalculator {
    Map<String, Account> accounts = new HashMap<>();

    Map<String, Account> execute(BankTrans[] transactions) {

        for (BankTrans transaction : transactions) {
            findAccount(transaction.getDebitAccount()).debit(transaction.getAmount());
            findAccount(transaction.getCreditAccount()).credit(transaction.getAmount());
        }

        return accounts;
    }

    private Account findAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if(isNull(account)){
            return accountCreator(accountNumber);
        }
        return account;
    }

    private Account accountCreator(String accountNumber) {
        Account account = new Account(accountNumber);
        accounts.put(accountNumber, account);
        return account;
    }
}
