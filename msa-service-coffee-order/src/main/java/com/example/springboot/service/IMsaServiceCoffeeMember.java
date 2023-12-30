package com.example.springboot.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "msa-service-coffee-member", url = "http://localhost:8081/coffeeMember")
public interface IMsaServiceCoffeeMember {

    @RequestMapping(value = "/coffeeMember/v1.0/{memberName}", method = RequestMethod.GET)
    boolean coffeeMember(@PathVariable("memberName") String memberName);
}
