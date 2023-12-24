package com.example.springboot.repository.dvo;

import lombok.Data;

@Data
public class OrderStatusDVO {
    private String id;
    private String orderHistory; // 주문 내역
}
