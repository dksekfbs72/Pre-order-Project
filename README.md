# 예약 구매 서비스

## 1. 개요
뉴스피드를 활용한 이웃간 커뮤니케이션, 쇼핑몰의 결합 서비스입니다.

<br>

## 2. 이용 안내
### - API 문서
https://dull-apogee-965.notion.site/6df35cdb93dd43939d1c37b998f14074?v=d1df5713b5fa4a5ebab5903050405d7f&pvs=4

<br>

## 3. 구성
### - UserService 모듈
- 회원가입, 내 정보 변경 등 회원 관리 API 모듈
### - ActivityService 모듈
- 포스팅, 댓글, 좋아요, 팔로우 등 SNS 활동 API 모듈
### - NewsFeedService 모듈
- 뉴스피드 읽기, 검색 API 모듈

<br>

## 4. HOW TO RUN
### - UserService Build
1. cd /UserService
2. ./gradlew build
### - NewsFeedService Build
1. cd /NewsFeedService
2. ./gradlew build
### - ActivityService Build
1. cd /ActivityService
2. ./gradlew build
### - docker-compose 시작
1. docker-compose up

<br>

## 5. 개발 환경
JavaVersion - 17
SpringBoot - 3.2.2
DockerVersion - 3.9
