package com.dawidswitonmaniakowski.ing.transactions;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NumberFormatTest {

    @Test
    void testBigNumberForMoneyCount(){
        String number = "12345678901234567891234567891234567890.44";
        BigDecimal input = new BigDecimal(number);
        input = input.add(new BigDecimal("2.01"));

        String afterConversion = input.toPlainString();
        String expected = "12345678901234567891234567891234567892.45";
        assertThat(afterConversion).isEqualTo(expected);
    }
}
