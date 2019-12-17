# 구내식단 관리
달랩 10주 프로젝트 (구내식당 식단 관리 및 재료 산정 서비스)

## 실행
### Build Jar
```shell script
./gradlew bootjar
```

### Web 의존성 설치
```shell script
bash -c "cd cafeteria-manage-web && npm install"
```

### Docker 실행
```shell script
# env 파일은 원하는대로 수정해서 사용하시면 됩니다.
cp .env.default .env 
docker-compose up
```