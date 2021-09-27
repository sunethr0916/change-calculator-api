package com.adp.sample.demo.service;

import com.adp.sample.demo.model.Coin;
import com.adp.sample.demo.model.CoinsRequest;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ChangeCalculatorImplTests {

    @InjectMocks
    private ChangeCalculator changeCalculator = new ChangeCalculatorImpl();

    private static final Map<Coin, Integer> coinCount = getDefaultMap();
    private static Properties prop;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private static Map<Coin, Integer> getDefaultMap() {
        Map<Coin, Integer> defaultMap = new HashMap<>();
        for (Coin c : Coin.values())
            defaultMap.put(c, 0);
        return defaultMap;
    }

    @Test
    public void calculateChange_returns_change_with_leastAmountOfCoins(){
        Map<Coin, Integer> change = changeCalculator.calculateChange(10, CoinsRequest.LeastAmountOfCoins);
        assertEquals(BigDecimal.valueOf(change.get(Coin.Quarter)), new BigDecimal("40"));
        assertEquals(BigDecimal.valueOf(change.get(Coin.Dime)), new BigDecimal("0"));
        assertEquals(BigDecimal.valueOf(change.get(Coin.Penny)), new BigDecimal("0"));
        assertEquals(BigDecimal.valueOf(change.get(Coin.Nickle)), new BigDecimal("0"));
    }

    @Test
    public void calculateChange_returns_change_with_mostAmountOfCoins(){
        Map<Coin, Integer> change = changeCalculator.calculateChange(10, CoinsRequest.MostAmountOfCoins);
        assertEquals(BigDecimal.valueOf(change.get(Coin.Dime)), new BigDecimal("40"));
        assertEquals(BigDecimal.valueOf(change.get(Coin.Penny)), new BigDecimal("100"));
        assertEquals(BigDecimal.valueOf(change.get(Coin.Nickle)), new BigDecimal("100"));
        assertEquals(BigDecimal.valueOf(change.get(Coin.Quarter)), new BigDecimal("0"));
    }

    @Test
    public void calculateChange_returns_throws_illegalArgumentException_when_coins_are_not_available_to_process(){
        assertThrows(IllegalArgumentException.class, () -> changeCalculator.calculateChange(50, CoinsRequest.MostAmountOfCoins),
                "Insufficient coins!!");
    }
}
