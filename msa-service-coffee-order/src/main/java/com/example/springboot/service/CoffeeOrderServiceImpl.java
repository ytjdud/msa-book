package com.example.springboot.service;

import com.example.domain.repository.ICoffeeOrderRepository;
import com.example.domain.service.CoffeeOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoffeeOrderServiceImpl extends CoffeeOrder {

    public CoffeeOrderServiceImpl(ICoffeeOrderRepository iCoffeeOrderRepository) {
        super(iCoffeeOrderRepository);
    }
}
