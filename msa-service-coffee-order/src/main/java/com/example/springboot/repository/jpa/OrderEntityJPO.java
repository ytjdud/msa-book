package com.example.springboot.repository.jpa;

import com.example.domain.model.OrderEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Setter
@Getter
public class OrderEntityJPO extends OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String orderNumber; //주문번호
    String coffeeName; //커피 종류
    String coffeeCount; //커피 개수
    String customerName; //회원명

}
