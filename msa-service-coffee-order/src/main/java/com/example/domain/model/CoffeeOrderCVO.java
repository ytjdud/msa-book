package com.example.domain.model;

import lombok.Data;
import org.apache.kafka.clients.producer.Callback;

@Data
public class CoffeeOrderCVO {
    private String id;
    private String orderNumber; //주문 번호
    private String coffeeName; //커피 종류
    private String coffeeCount; //커피 개수
    private String customerName; //회원명
}
