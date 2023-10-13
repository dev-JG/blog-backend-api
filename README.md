# 블로그 검색 API 프로젝트
***

jar 파일 다운로드 경로: https://github.com/dev-JG/blog-backend-api/blob/main/blog-api-0.0.1-SNAPSHOT.jar

### 설계
서버
- Java 11
- Spring Boot 2.7.16
- JPA, queryDsl 5.0.0
- H2, EhCache, lombok, Resilience4j

DB / H2
- keyword_search_log 검색키워드별 전시여부 및 검색횟수, 마지막 검색일자를 기록하는 로그성 테이블

서킷브레이커 적용 / Resilience4j
- 카카오 블로그 조회, 실패율이 특정값을 넘어갈 경우 서킷브레이커가 작동, 네이버 블로그 조회로직만 수행하도록 적용
- 동작방식
  - 일반: 카카오호출 -> 실패시 네이버호출
  - 서킷브레이커 동작시: 네이버호출

캐싱방식 / EhCache
- 검색어 Top 10 조회 API에 대해서 로컬캐시적용

키워드 호출카운팅 동시성 이슈
- 비관적락, 배타적 잠금으로 해결하였습니다. 다른 트랜잭션이 동일 키워드를 수정하려고 한다면 락이 풀리기전까지 대기상태에 들어가게됩니다.

***

### API 명세서
블로그 검색 API
- GET, /api/v1/blog/search
- queryParam = query, sort, page(default 1), size(default 10)
- 카카오 블로그 정보를 검색하며, 카카오 장애시 네이버 블로그 검색으로 전환(서킷브레이커 적용)
- 샘플: http://localhost:8080/api/v1/blog/search?query=%EA%B3%BC%EC%9E%90&page=1&size=5

인기검색어 Top 10 조회 API
- GET, /api/v1/search/keyword/popular
- 가장 많이 검색된 키워드의 상위 10개를 조회(전시중인 키워드만)
- 로컬캐시(EhCache)를 적용하여 2분 캐시적용
- 샘플: http://localhost:8080/api/v1/search/keyword/popular
***

### 추가 개선방향

#### 인기검색어 조회 캐싱방식
로컬캐시인 EhCache를 이용해서 캐싱처리를 하였습니다.
로컬캐시는 다중 인스턴스환경에서는 히트율이 떨어질 수 있기 때문에, 추후 개선한다면 Redis나 Memcached를 이용해서 캐싱 방식을 변경할 수 있습니다.