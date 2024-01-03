# 책 『 자바 기반 마이크로서비스 이해와 아키텍처 구축하기 』 실습코드

## 변경사항

- Zuul(deprecated) --> Spring Cloud Gateway 
- 'eureka', '~~zuul~~ gateway' --> 서브모듈로 통합 
  - 저자 깃허브 코드 참고  
  https://github.com/architectstory/msa-book-additional/blob/master/README.md
- deprecated 된 몇몇 모듈/어노테이션 수정
- javax 관련 코드 jakarta(Spring 3.x) 에 맞게 수정


## 프로젝트 실행환경

- kafka_2.13-3.5.2 https://kafka.apache.org/downloads 다운로드 하여 로컬 PC에 설치
- IntelliJ
- JDK 17
- Spring boot 3.1.7
- H2 Database

## 포트

|  시스템  |     시스템      | 포트번호 |                비고                 |
|:-----:|:------------:|:----:|:---------------------------------:|
| H2 DB |  (콘솔 브라우저)   | 8082 |              default              |
|       |     (서버)     | 9092 |              default              |
| Kafka  |  zookeeper   | 2181 |              default              |
|        | kafka server | 9094 | default:9092 --> h2 와의 충돌로 **변경** |
| server |    config    | 8888 |                                   |
|  |    eureka    | 9091 |                                   |
|  |   gateway    | 9999 |                                   |
| coffee |    member    | 8081 |                                   |
|  |    order     | 8083 |                                   |
|  |    status    | 8084 |                                   |

## 시스템 기동

### Kafka -> Eureka Server -> Config Server -> Gateway Server -> _Microservices_

#### 0. 개인 별 수정
- H2 Database
    1. 각 microservice 의 DB 생성
    2. 각 application.yml 의 spring:datasource:url, spring:datasource:username, spring:datasource:password 알맞게 수정
- Kafka Topic 생성  
  참고: https://kafka.apache.org/documentation/#quickstart  
  주의: 카프카 토픽 생성 명령어 입력 시 localhost:**9094**
  - 코드 수정 원치 않으면 , `$ bin/kafka-topics.sh --create --topic msa-service-coffee-status --bootstrap-server localhost:9092`  
    (이 때 토픽: msa-service-coffee-status)
  - 개인 토픽 생성 시
      1. KafkaConsumer.java 의 @KafkaListener(topics= "_내가 만든 토픽 이름으로_") 수정
      2. CoffeeOrderRestController 의 kafkaProducer.send("_내가 만든 토픽 이름으로_", coffeeOrderCVO); 수정
  
#### 1. Kafka 서버 기동하기  
- 참고: https://kafka.apache.org/documentation/#quickstart  
- 주의: producer/consumer 실행 시 localhost:**9094** 로 **변경**  

#### 2. Eureka Server 기동  
http://localhost:9091 에 접속하여 서버 확인 가능

#### 3. Config Server 기동  

#### 4. Gateway 기동  

#### 5. 마이크로서비스 기동  
   - coffee-order  
   - coffee-member  
   - coffee-status  

## 테스트 테이블 및 데이터 생성

#### 1. Coffee-member  
회원 테이블 생성 [PUT] http://localhost:8081/coffeeMember/createMemberTable  
회원 데이터 입력 [PUT] http://localhost:8081/coffeeMember/insertMemberData  
이후 h2 DB 에서 확인하기

#### 2. Coffee-Status  
주문 처리 상태 테이블 생성 [PUT] http://localhost:8084/coffeeStatus/createStatusTable  
이후 h2 DB 에서 확인하기

#### 3. Coffee-Order
커피 주문 [POST] http://localhost:8083/coffeeOrder, 
Body(json): {"id":"","orderNumber":"1","coffeeName":"espresso","coffeeCount":"2","customerName":"kevin"}  
이후 status 의 h2 DB 에서 확인
