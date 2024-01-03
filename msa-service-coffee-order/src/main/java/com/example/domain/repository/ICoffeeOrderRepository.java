package com.example.domain.repository;

import com.example.domain.model.OrderEntity;

public interface ICoffeeOrderRepository {
    public Long coffeeOrderSave(OrderEntity orderEntity);
}
