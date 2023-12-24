package com.example.springboot.repository;

import com.example.domain.model.OrderEntity;
import com.example.domain.repository.ICoffeeOrderRepository;
import com.example.springboot.repository.jpa.ICoffeeOrderJpaRepository;
import com.example.springboot.repository.jpa.OrderEntityJPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CoffeeOrderRepository implements ICoffeeOrderRepository {

    @Autowired
    private ICoffeeOrderJpaRepository iCoffeeOrderJpaRepository;

    @Override
    public String coffeeOrderSave(OrderEntity orderEntity) {
        OrderEntityJPO orderEntityJPO = new OrderEntityJPO();
        orderEntityJPO.setOrderNumber(orderEntity.getOrderNumber());
        orderEntityJPO.setCoffeeName(orderEntity.getCoffeeName());
        orderEntityJPO.setCoffeeCount(orderEntity.getCoffeeCount());
        orderEntityJPO.setCustomerName(orderEntity.getCustomerName());

        iCoffeeOrderJpaRepository.save(orderEntityJPO);

        return orderEntityJPO.getId();
    }
}
