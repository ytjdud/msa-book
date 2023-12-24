package com.example.springboot.rest;

import com.example.springboot.repository.ICoffeeStatusMapper;
import com.example.springboot.repository.dvo.OrderStatusDVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeOrderStatusRestController {

    @Autowired
    ICoffeeStatusMapper iCoffeeStatusMapper;

    // 주문 처리 상태 확인
    @HystrixCommand
    @RequestMapping(value = "/coffeeOrderStatus", method = RequestMethod.POST)
    public ResponseEntity<OrderStatusDVO> coffeeOrderStatus() {
        OrderStatusDVO orderStatusDVO = iCoffeeStatusMapper.selectCoffeeOrderStatus();
        return new ResponseEntity<OrderStatusDVO>(orderStatusDVO, HttpStatus.OK);
    }

    // 테스트 테이블 생성
    @RequestMapping(value = "/createStatusTable", method = RequestMethod.PUT)
    public void createStatusTable() {
        iCoffeeStatusMapper.createStatusTable();
    }
}
