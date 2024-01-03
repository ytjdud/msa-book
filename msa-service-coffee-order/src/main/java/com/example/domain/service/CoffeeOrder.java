package com.example.domain.service;

import com.example.domain.model.CoffeeOrderCVO;
import com.example.domain.model.OrderEntity;
import com.example.domain.repository.ICoffeeOrderRepository;

public class CoffeeOrder implements ICoffeeOrder{

    private final ICoffeeOrderRepository iCoffeeOrderRepository;

    public CoffeeOrder(ICoffeeOrderRepository iCoffeeOrderRepository) {
        this.iCoffeeOrderRepository = iCoffeeOrderRepository;
    }

    @Override
    public Long coffeeOrder(CoffeeOrderCVO coffeeOrderCVO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(coffeeOrderCVO.getOrderNumber());
        orderEntity.setCoffeeName(coffeeOrderCVO.getCoffeeName());
        orderEntity.setCoffeeCount(coffeeOrderCVO.getCoffeeCount());
        orderEntity.setCustomerName(coffeeOrderCVO.getCustomerName());

        iCoffeeOrderRepository.coffeeOrderSave(orderEntity);

        return orderEntity.getId();
    }
}
