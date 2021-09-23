package com.adp.sample.demo.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CoinsRequest {
    LeastAmountOfCoins("LeastAmountOfCoins"),
    MostAmountOfCoins("MostAmountOfCoins");

    private String str;
}
