# 구내식단 관리
달랩 10주 프로젝트 (구내식당 식단 관리 및 재료 산정 서비스)

# 실행
## Build Jar
```shell script
./gradlew bootjar
```

## Web 의존성 설치
```shell script
cd cafeteria-manage-web
```

## Docker 실행
```shell script
docker-compose up
```

## 이슈
- 위의 과정으로 진행 시 api docker에서 jdbc url을 못찾는 오류 발생
- 따로 jar 파일 통해 실행 시 실행 가능