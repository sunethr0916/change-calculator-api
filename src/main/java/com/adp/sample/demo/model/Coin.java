package com.adp.sample.demo.model;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum Coin {

    Penny       (new BigDecimal(0.01)),
    Nickle      (new BigDecimal(0.05)),
    Dime        (new BigDecimal(0.10)),
    Quarter     (new BigDecimal(0.25));

    public final BigDecimal value;
}
