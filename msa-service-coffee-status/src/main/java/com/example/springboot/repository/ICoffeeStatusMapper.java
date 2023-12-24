package com.example.springboot.repository;

import com.example.springboot.repository.dvo.OrderStatusDVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICoffeeStatusMapper {
    int insertCoffeeOrderStatus(OrderStatusDVO orderStatusDVO);
    OrderStatusDVO selectCoffeeOrderStatus();
    int createStatusTable();
}
