package com.example.springboot.rest;

import com.example.domain.model.CoffeeOrderCVO;
import com.example.springboot.service.CoffeeOrderServiceImpl;
import com.example.springboot.service.IMsaServiceCoffeeMember;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.messageq.KafkaProducer;

@RestController
@RequestMapping("/coffeeOrder")
public class CoffeeOrderRestController {
    @Autowired
    private CoffeeOrderServiceImpl coffeeOrderServiceImpl;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private IMsaServiceCoffeeMember iMsaServiceCoffeeMember;

    @HystrixCommand
    @RequestMapping(value = "/coffeeOrder", method = RequestMethod.POST)
    public ResponseEntity<CoffeeOrderCVO> coffeeOrder(@RequestBody CoffeeOrderCVO coffeeOrderCVO) {

        //is Member
        if(iMsaServiceCoffeeMember.coffeeMember(coffeeOrderCVO.getCustomerName())){
            System.out.println(coffeeOrderCVO.getCustomerName() + " is a member!");
        }

        //coffee order
        coffeeOrderServiceImpl.coffeeOrder(coffeeOrderCVO);

        //kafka
        kafkaProducer.send("msa-service-coffee-status", coffeeOrderCVO);

        return new ResponseEntity<CoffeeOrderCVO>(coffeeOrderCVO, HttpStatus.OK);
    }
}
