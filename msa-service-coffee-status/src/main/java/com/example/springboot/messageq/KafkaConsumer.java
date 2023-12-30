package com.example.springboot.messageq;

import com.example.springboot.repository.ICoffeeStatusMapper;
import com.example.springboot.repository.dvo.OrderStatusDVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    ICoffeeStatusMapper iCoffeeStatusMapper;

    @KafkaListener(topics = "msa-service-coffee-status")
    public void processMessage(String kafkaMessage) {
        System.out.println("kafkaMessage : =====> " + kafkaMessage);

        OrderStatusDVO orderStatusDVO = new OrderStatusDVO();
        orderStatusDVO.setOrderHistory(kafkaMessage);

        iCoffeeStatusMapper.insertCoffeeOrderStatus(orderStatusDVO);
    }
}
