# 예약 구매 서비스
이 서비스는 사용자들이 **이웃들과 소통하고 쇼핑을 즐기며 편리하게 예약 구매를 할 수 있는 플랫폼**을 제공합니다.<br><br>
대용량 트래픽을 다뤄보기 위한 **학습용 프로젝트**입니다.

<br>

## 1. HOW TO RUN
### 선행 조건

- Java : 17<br>
- DockerVersion : 3.9

### docker-compose 시작

1. ./gradlew build
2. docker-compose up

### Test 코드 실행 (주문 시도 1만회)
-> 임의의 상품을 추가하고 해당 상품을 1만회 동시에 주문합니다.
<br>
1. docker build -t http_request_tool .
2. docker run http_request_tool



### API 문서
https://dull-apogee-965.notion.site/6df35cdb93dd43939d1c37b998f14074?v=d1df5713b5fa4a5ebab5903050405d7f&pvs=4

<br>

## 2. 구성
### UserService 모듈
- 회원가입, 내 정보 변경 등 회원 관리 API 모듈
### ActivityService 모듈
- 포스팅, 댓글, 좋아요, 팔로우 등 SNS 활동 API 모듈
### NewsFeedService 모듈
- 뉴스피드 읽기, 검색 API 모듈
### ProductService 모듈
- 상품 목록, 상품 등록 등 상품 관련 API 모듈
### OrderService 모듈
- 주문 확인, 주문, 결제 모듈
### StockService 모듈
- 실시간 재고 관리 모듈

<br>

## 3. 시스템 설계
### ERD
![img.png](ERD.png)

### 소프트웨어 아키텍처
![img_1.png](architecture.png)

<br>

## 4. 기술 특장점
### Kafka를 활용한 이벤트 기반 아키텍처
OrderService msa는 Apache Kafka를 활용한 이벤트 기반 아키텍처를 채택했습니다.
Kafka의 메시지 큐 시스템은 높은 처리량과 낮은 지연 시간을 제공하여 실시간 데이터 스트림 처리에 적합합니다.
따라서, 사용자들은 즉각적인 이벤트 업데이트를 받을 수 있으며, 시스템은 확장성과 유연성을 유지할 수 있습니다.

### Redis를 활용한 실시간 재고 확인 서비스
실시간 재고 확인 기능은 서비스의 핵심 기능 중 하나입니다.
이를 위해 Redis를 선택하여 신속한 응답 시간과 확장 가능한 재고 관리 시스템을 구축했습니다.
Redis의 인메모리 데이터 저장 방식은 매우 빠른 읽기 및 쓰기 속도를 제공하여 고객이 제품의 재고 상태를 즉시 확인할 수 있습니다.

## 5. 마무리 말
- 대용량 트래픽을 다뤄보는 학습용 프로젝트입니다.<br>스케일 아웃을 적용해 볼 계획입니다.
- Kafka 적용 일기 - [예약 구매 프로젝트 개발](https://velog.io/@dksekfbs72/series/%EC%98%88%EC%95%BD-%EA%B5%AC%EB%A7%A4-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)
