# [프로젝트] 이용권 관리 시스템

## 프로젝트 소개

헬스장, 클라이밍장, 온라인 구독 시스템 등에서 다양하게 사용되는 이용권들을 관리하는 시스템

## 프로젝트 환경

- Java 17
- Gradle
- Spring Boot 2.7.17
- Spring Data JPA
- MySql 8.1.0
- Docker

## 프로젝트 실행방법

> DB 컨테이너 실행법
> 1. docker-compose.yml 파일이 있는 디렉토리로 이동 (최상단 database 디렉토리)
> 2. docker-compose up -d 명령어 실행
> 3. docker-compose ps 명령어로 컨테이너가 실행되었는지 확인 (3307번 포트로 실행됨)
> 4. 스프링 부트 애플리케이션 실행
