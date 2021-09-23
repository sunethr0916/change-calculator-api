package com.adp.sample.demo.service;

import com.adp.sample.demo.model.Coin;
import com.adp.sample.demo.model.CoinsRequest;

import java.util.Map;

public interface ChangeCalculator {

    public Map<Coin, Integer> calculateChange(int billAmount, CoinsRequest coinsRequest);
}
