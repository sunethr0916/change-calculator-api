package com.adp.sample.demo.service;

import com.adp.sample.demo.model.Coin;
import com.adp.sample.demo.model.CoinsRequest;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

@Service
public class ChangeCalculatorImpl implements ChangeCalculator {

    private static final Map<Coin, Integer> coinCount = getDefaultMap();
    private static Properties prop;

    static {
        InputStream is = null;
        prop = new Properties();
        is = ClassLoader.class.getResourceAsStream("/application.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        coinCount.put(Coin.Penny, Integer.valueOf(prop.getProperty("coins.availability")));
        coinCount.put(Coin.Nickle, Integer.valueOf(prop.getProperty("coins.availability")));
        coinCount.put(Coin.Dime, Integer.valueOf(prop.getProperty("coins.availability")));
        coinCount.put(Coin.Quarter, Integer.valueOf(prop.getProperty("coins.availability")));
    }

    public void addCoins(Coin type, int amount) {
        coinCount.put(type, amount + getCoinCount(type));
    }

    public int getCoinCount(Coin type) {
        return coinCount.get(type);
    }

    private static Map<Coin, Integer> getDefaultMap() {
        Map<Coin, Integer> defaultMap = new HashMap<>();
        for (Coin c : Coin.values())
            defaultMap.put(c, 0);
        return defaultMap;
    }


    public Map<Coin, Integer> getChange(int amount, CoinsRequest coinsRequest) {
        Map<Coin, Integer> changeResult = getDefaultMap();

        if (getChangeImpl(amount, true, getDefaultMap(), coinsRequest)) {
            getChangeImpl(amount, false, changeResult, coinsRequest);
            return changeResult;
        }

        throw new IllegalArgumentException("Insufficient coins!!");
    }

    private boolean getChangeImpl(int amount, boolean isMock, Map<Coin, Integer> out, CoinsRequest coinsRequest) {
        List<Coin> types = Arrays.asList(Coin.values());
        if (coinsRequest.equals(CoinsRequest.LeastAmountOfCoins)) {
            Collections.reverse(types);
        }
        MathContext mathContext = new MathContext(4);
        BigDecimal amountLeft = BigDecimal.valueOf(amount);

        for (Coin c : types) {
            while (amountLeft.compareTo(c.value.round(mathContext)) >= 0 && getCoinCount(c) > 0) {
                amountLeft = amountLeft.subtract(c.value, mathContext);
                out.put(c, out.get(c) + 1);
                if (isMock == false) {
                    addCoins(c, -1);
                }
            }
        }

        return amountLeft.compareTo(BigDecimal.ZERO) <= 0;
    }

    @Override
    public Map<Coin, Integer> calculateChange(int billAmount, CoinsRequest coinsRequest) {
        return getChange(billAmount, coinsRequest);
    }
}
