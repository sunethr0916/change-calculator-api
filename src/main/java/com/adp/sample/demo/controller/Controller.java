package com.adp.sample.demo.controller;

import com.adp.sample.demo.model.Coin;
import com.adp.sample.demo.model.CoinsRequest;
import com.adp.sample.demo.service.ChangeCalculator;
import com.adp.sample.demo.validations.ValuesAllowed;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Validated
@RestController
@RequestMapping(value = "${app.context.path}", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @Autowired
    private ChangeCalculator changeCalculator;

    @PostMapping(value = "/change-calculator", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns change for given bill", response = ResponseEntity.class, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Coin, Integer>> changeCalculator(@RequestParam @ValuesAllowed(values = {1, 2, 5, 10, 20, 50, 100}) int billAmount,
                                                               @RequestParam("coinsRequest") CoinsRequest coinsRequest) {
        Map<Coin, Integer> result = changeCalculator.calculateChange(billAmount, coinsRequest);
        return new ResponseEntity<Map<Coin, Integer>>(result, HttpStatus.OK);
    }
}
