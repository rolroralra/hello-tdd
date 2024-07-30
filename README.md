# 목차
- JUnit5 소개
  - JUnit 역사
    - JUnit4, JUnit5 차이점
    - SpockFramework 도 있음. groovy 기반 테스트 프레임워크 (생략해도 됨)
  - JUnit5 사용법 (기본)
    - 어노테이션 목록
      - [Annotations](https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations)
      - @BeforeEach
      - @AfterEach
      - @BeforeAll
      - @AfterAll
      - @Disabled
      - @Timeout
      - @Tag
      - etc...
    - Assumption API
    - Assertions API
      - [Assertions](https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions)
    - Assertj
      - [AssertJ](https://assertj.github.io/doc/)
  - JUnit5 ParameterizedTest (매개변수화 테스트)
    - @ValueSource
    - @EnumSource
    - @CsvSource, @CsvFileSource
    - @MethodSource
    - etc...
- Unit Test (단위 테스트)
  - 코드 예시
- Acceptance Test (인수테스트)
  - RestAssured를 이용한 인수테스트
    - 코드 예시
  - MockMvc를 이용한 인수테스트
    - 코드 예시
- Mockito
  - Mockito 소개
  - Mockito 사용법
    - Mocking
    - Stubbing
    - Verification
    - Argument Matchers
    - etc...
- TestContainers
  - Test Container 소개
    - [Test Container](https://www.testcontainers.org/)
  - Test Container 사용법
    - 코드 예시    
  - SpringBoot TestContainers 기능 추가
    - [SpringBoot TestContainers](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)
  - 참고 자료
    - [Tilt](https://tilt.dev/)

## JUnit 소개
- JUnit는 자바 프로그래밍 언어를 위한 인기 있는 단위 테스트 프레임워크입니다. 
- 테스트 주도 개발(TDD)에 중요한 역할을 하며, 시간이 지나면서 크게 발전해 왔습니다.

## JUnit 4
- **출시**: 2006년
- **주요 기능**:
  - **어노테이션**: `@Test`, `@Before`, `@After`, `@BeforeClass`, `@AfterClass`와 같은 어노테이션을 도입하여 테스트 설정 및 해제를 단순화.
  - **유연한 테스트 실행**: 테스트 작성 및 관리의 유연성 향상.
  - **가정**: `Assume`을 사용한 조건부 테스트 건너뛰기 지원.
  - **룰**: `@Rule`을 통한 테스트 메서드의 추가 또는 대체 동작 지원.

## JUnit 5
- **출시**: 2017년
- **모듈형 아키텍처**: 세 가지 하위 프로젝트로 나뉨:
  - **JUnit Platform**: JVM에서 테스트 프레임워크를 실행하기 위한 기반 제공.
  - **JUnit Jupiter**: 새로운 프로그래밍 모델 및 확장 모델로 테스트와 확장 작성.
  - **JUnit Vintage**: JUnit 3 및 JUnit 4 테스트의 호환성을 제공.

- **주요 기능**:
  - **어노테이션**: 새로운 어노테이션 `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll` 추가.
  - **동적 테스트**: 런타임에 생성될 수 있는 동적 테스트 지원.
  - **매개변수화된 테스트**: `@ParameterizedTest` 어노테이션을 통한 강화된 매개변수화 테스트.
  - **확장**: 더 포괄적인 사용자 정의 및 서드파티 프레임워크와의 통합을 가능하게 하는 유연한 확장 모델.

## JUnit4 vs. JUnit5
[Difference between JUnit 5 an JUnit 4](https://howtodoinjava.com/junit5/junit-5-vs-junit-4/)

## 참고자료
- [JUnit 4 Documentation](https://junit.org/junit4/)
- [JUnit 5 Documentation](https://junit.org/junit5/)