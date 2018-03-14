# 쿠폰 발급

## 웹 어플리케이션 개발 언어 및 프레임 워크
**java**, **spring-boot**

## 문제 해결 전략
### 쿠폰번호 생성 전략 - CouponGenerater
1. 정규식 내용에 포함 되는 리스트를 배열에 넣는다. 
2. ThreadLocalRandom을 사용해서 랜덤값을 추출 한다.
3. 배열 갯수로 나눈 나머지를 구한다. 
4. 나머지의 양수 값을 구한다.
5. 나머지값을 배열 인덱스에 넣어 인덱스에 해당하는 문자를 가져온다.

### Email 저장 전략
 + 한 트랜젝션으로 묶어서 처리
 + 쿠폰과 이메일 중복 검사 - 각각 유니크 키로 제약 조건 설정
 + 이메일 입력 정규식 검사  

### 서버에 Rest Api 구현 - /issued-coupons
 + get : 화면예제에 나온것 처럼 PageableDefault를 3으로 줘서 3개씩 나오도록 한다.
 + post : email을 받고 email 외 다른값들이 들어오면 해킹 공격으로 간주하고 익셉션 발생시킨다.

### Exception Code
 + 409 : 만든 쿠폰 번호가 중복 되었을때
 + 400 : 이미 이메일이 입력되었을때
 + 406 : 이메일 형식이 맞지 않거나 의도하지 않은 값이 들어 왔을때

### pagination
 + spring data 에서 제공 하는 PagingAndSortingRepository 사용

### 사용 데이터 베이스
 + h2 - 기본 트렌젝션 격리 레벨 Read commited 사용

## 테스트  
#### IssuedCouponControllerTest
- testGetListIssuedCoupon - get 요청, 응답값 확인
- testIssueCouponByEmail - post 요청, 응답값 확인
- testIssueCouponByWrongFormatEmail - post 요청, 잘못된 이메일 형식, 응답값 확인

#### CouponGeneraterTest
- testCouponDupulicate - 쿠폰 생성 중복 확인 

#### IssuedCouponRepositoryTest
- testFindByExistEmail - 이미 존재하는 이메일 검사 
- testFindByNotExistEmail - 존재 하지 않는 이메일 검사 
- testFindByNotIssuedCoupon - 발급되지 않은 쿠폰 번호 검사 

#### IssuedCouponServiceTest
- testFindByEmailAndCouponNumber - 쿠폰 발급시 중복되는 쿠폰 번호가 입력될수 있는지 thread 생성 하여 검사. 


## 프로젝트 빌드 및 실행 방법 : 

1. git clone https://github.com/mioya/preTestPay.git 프로젝트를 다운 
2. mvn install -Dmaven.test.skip=true
3. cd target
4. java -jar coupon-0.0.1-SNAPSHOT.jar
5. http://localhost:8080 에서 사이트가 뜬것을 확인 가능. 