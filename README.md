
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

# JUnit 소개
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

```mermaid
flowchart TD
    subgraph jupiter ["Jupiter - Junit5"]
        direction TB
        jje[junit-jupiter-engine]
        jja[junit-jupiter-api]
        jje --> jja
    end

    jje --> jpe

    subgraph junit_platform ["JUnit Platform"]
        direction TB
        jpl[junit-platform-launcher]
        jpe[junit-platform-engine]
        jpl --> jpe
    end

    subgraph vintage ["Vintage - Junit4"]
        direction BT
        jve[junit-vintage-engine]
        j[junit:junit]
        jve --> j
    end

    jve --> jpe
```

- **주요 기능**:
  - **어노테이션**: 새로운 어노테이션 `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll` 추가.
  - **동적 테스트**: 런타임에 생성될 수 있는 동적 테스트 지원.
  - **매개변수화된 테스트**: `@ParameterizedTest` 어노테이션을 통한 강화된 매개변수화 테스트.
  - **확장**: 더 포괄적인 사용자 정의 및 서드파티 프레임워크와의 통합을 가능하게 하는 유연한 확장 모델.

## JUnit4 vs. JUnit5
[Difference between JUnit 5 an JUnit 4](https://howtodoinjava.com/junit5/junit-5-vs-junit-4/)

## JUnit5 환경 설정
### Gradle (Kotlin)
```kotlin
dependencies { 
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
}

tasks.withType<Test> { 
    useJUnitPlatform()
}
```

### Gradle (Groovy)
```groovy
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.3'
}

test {
    useJUnitPlatform()
}
```

### Maven
```xml
  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.10.3</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.3.1</version>
      </plugin>
    </plugins>
  </build>
```

## 참고자료
- [JUnit 4 Documentation](https://junit.org/junit4/)
- [JUnit 5 Documentation](https://junit.org/junit5/)

# JUnit5 사용법

## Annotation 목록
[Annotation 목록](https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations)

### 주로 사용하는 Annotation
- **@Test**
  - **@BeforeEach**
  - **@AfterEach**
  - **@BeforeAll**
  - **@AfterAll**
- **@ParameterizedTest**
  - **@ValueSource**
  - **@EnumSource**
  - **@NullAndEmptySource**
  - **@CsvSource**
    - **@CsvFileSource**
  - **@MethodSource**
  - **@ArgumentsSource**
- **@DisplayName**
- **@RepeatedTest**
- **@Disabled**
- **@Nested**
- **@Timeout**

### 사용하지 않길 권장하는 Annotation
> 테스트 메서드 간 실행 순서 의존과 필드는 공유하지 않는게 좋습니다.
- **@TestMethodOrder**
- **@TestClassOrder**
- **@Order**

## Test Lifecycle
- **@BeforeAll**: 테스트 클래스 내의 모든 테스트 메서드가 실행되기 전에 한 번 실행됩니다.
- **@BeforeEach**: 각 테스트 메서드가 실행되기 전에 실행됩니다.
- **@AfterEach**: 각 테스트 메서드가 실행된 후에 실행됩니다.
- **@AfterAll**: 테스트 클래스 내의 모든 테스트 메서드가 실행된 후에 한 번 실행됩니다.

```java
class JunitLifecycleTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all");
    }

    @AfterAll
    static void afterAll() {
      System.out.println("After all");
    }
    
    @BeforeEach
    void setUp() {
        System.out.println("Before each");
    }
    
    @AfterEach
    void tearDown() {
      System.out.println("After each");
    }
    
    @Test
    void test1() {
        System.out.println("Test 1");
    }
    
    @Test
    void test2() {
        System.out.println("Test 2");
    }
}

/*
Before all
Before each
Test 1
After each
Before each
Test 2
After each
After all
*/
```

## Assertions
- JUnit5에서는 `org.junit.jupiter.api.Assertions` 클래스를 통해 다양한 Assertion 메서드를 제공합니다.
- 하지만 주로 `assertj` 라이브러리를 사용하는 것이 좋습니다.
  - https://assertj.github.io/doc/#assertj-overview

```java
// 모든 검증을 실행하고, 그중에 실패한 것이 있는지 확인
assertAll(
    () -> assertEquals(4, calculator.add(2, 2)),
    () -> assertEquals(0, calculator.subtract(2, 2)),
    () -> assertEquals(4, calculator.multiply(2, 2)),
    () -> assertEquals(1, calculator.divide(2, 2))
);
```

### 1. 일반적인 Assertion 메서드
<details>
  <summary>펼쳐보기</summary>

- **isEqualTo()**
  ```java
  assertThat(actual).isEqualTo(expected);
  ```
  - 실제 값이 예상 값과 동일한지 확인합니다.

- **isNotEqualTo()**
  ```java
  assertThat(actual).isNotEqualTo(expected);
  ```
  - 실제 값이 예상 값과 다른지 확인합니다.

- **isNull()**
  ```java
  assertThat(actual).isNull();
  ```
  - 실제 값이 null인지 확인합니다.

- **isNotNull()**
  ```java
  assertThat(actual).isNotNull();
  ```
  - 실제 값이 null이 아닌지 확인합니다.

- **isSameAs()**
  ```java
  assertThat(actual).isSameAs(expected);
  ```
  - 실제 값이 예상 값과 같은 객체인지 확인합니다.

- **isNotSameAs()**
  ```java
  assertThat(actual).isNotSameAs(expected);
  ```
  - 실제 값이 예상 값과 다른 객체인지 확인합니다.
</details>

### 2. 숫자 관련 Assertion 메서드
<details>
  <summary>펼쳐보기</summary>

- **isGreaterThan()**
  ```java
  assertThat(actual).isGreaterThan(expected);
  ```
  - 실제 값이 예상 값보다 큰지 확인합니다.

- **isLessThan()**
  ```java
  assertThat(actual).isLessThan(expected);
  ```
  - 실제 값이 예상 값보다 작은지 확인합니다.

- **isBetween()**
  ```java
  assertThat(actual).isBetween(start, end);
  ```
  - 실제 값이 지정된 범위 내에 있는지 확인합니다.
</details>

### 3. 문자열 관련 Assertion 메서드
<details>
  <summary>펼쳐보기</summary>

- **isEmpty()**
  ```java
  assertThat(actual).isEmpty();
  ```
  - 문자열이 비어 있는지 확인합니다.

- **isNotEmpty()**
  ```java
  assertThat(actual).isNotEmpty();
  ```
  - 문자열이 비어 있지 않은지 확인합니다.

- **isBlank()**
  ```java
  assertThat(actual).isBlank();
  ```
  - 문자열이 비어 있거나 공백인지 확인합니다.

- **isNotBlank()**
  ```java
  assertThat(actual).isNotBlank();
  ```
  - 문자열이 비어 있지 않고 공백이 아닌지 확인합니다.

- **contains()**
  ```java
  assertThat(actual).contains(substring);
  ```
  - 문자열이 특정 하위 문자열을 포함하는지 확인합니다.

- **doesNotContain()**
  ```java
  assertThat(actual).doesNotContain(substring);
  ```
  - 문자열이 특정 하위 문자열을 포함하지 않는지 확인합니다.

- **startsWith()**
  ```java
  assertThat(actual).startsWith(prefix);
  ```
  - 문자열이 특정 접두사로 시작하는지 확인합니다.

- **endsWith()**
  ```java
  assertThat(actual).endsWith(suffix);
  ```
  - 문자열이 특정 접미사로 끝나는지 확인합니다.
</details>

### 4. 리스트 및 배열 관련 Assertion 메서드
<details>
  <summary>펼쳐보기</summary>

- **isEmpty()**
  ```java
  assertThat(actual).isEmpty();
  ```
  - 리스트나 배열이 비어 있는지 확인합니다.

- **isNotEmpty()**
  ```java
  assertThat(actual).isNotEmpty();
  ```
  - 리스트나 배열이 비어 있지 않은지 확인합니다.

- **contains()**
  ```java
  assertThat(actual).contains(element);
  ```
  - 리스트나 배열이 특정 요소를 포함하는지 확인합니다.

- **containsExactly()**
  ```java
  assertThat(actual).containsExactly(elements);
  ```
  - 리스트나 배열이 정확히 지정된 요소들을 포함하고 순서도 일치하는지 확인합니다.

- **containsExactlyInAnyOrder()**
  ```java
  assertThat(actual).containsExactlyInAnyOrder(elements);
  ```
  - 리스트나 배열이 정확히 지정된 요소들을 포함하지만 순서는 상관없는지 확인합니다.

- **containsAnyOf()**
  ```java
  assertThat(actual).containsAnyOf(elements);
  ```
  - 리스트나 배열이 지정된 요소들 중 하나라도 포함하는지 확인합니다.

- **doesNotContain()**
  ```java
  assertThat(actual).doesNotContain(elements);
  ```
  - 리스트나 배열이 지정된 요소들을 포함하지 않는지 확인합니다.
</details>

### 5. 예외 관련 Assertion 메서드
<details>
  <summary>펼쳐보기</summary>

- **assertThatTypeOfException()**
  ```java
  assertThatExceptionOfType(ArithmeticException.class)
    .isThrownBy(() -> Calculator.plus(Integer.MAX_VALUE, 1));
  ```

- **hasMessage()**
  ```java
  assertThatThrownBy(() -> {
      // code that throws exception
  }).hasMessage(expectedMessage);
  ```
  - 예외가 특정 메시지를 포함하는지 확인합니다.

- **isInstanceOf()**
  ```java
  assertThatThrownBy(() -> {
      // code that throws exception
  }).isInstanceOf(ExceptionClass.class);
  ```
  - 예외가 특정 클래스의 인스턴스인지 확인합니다.
</details>

## @ParameterizedTest
[코드 예시](./src/test/java/com/example/tdd/parameterized/ParameterizedTestClassTest.java)

<details>
  <summary>펼쳐보기</summary>

### 1. @ValueSource
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValueSourceTest {

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
    void palindromes(String candidate) {
        assertTrue(isPalindrome(candidate));
    }

    private boolean isPalindrome(String candidate) {
        return candidate.equals(new StringBuilder(candidate).reverse().toString());
    }
}
```

### 2. @EnumSource
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnumSourceTest {

    enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }

    @ParameterizedTest
    @EnumSource(Topping.class)
    void testEnumSource(Topping topping) {
        assertNotNull(topping);
    }
}
```

### 3. @NullAndEmptySource
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NullAndEmptySourceTest {

    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptyStrings(String text) {
        assertTrue(text == null || text.isEmpty());
    }
}
```

### 4. @CsvSource
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvSourceTest {

    @ParameterizedTest
    @CsvSource({
        "test, TEST",
        "tEst, TEST",
        "Java, JAVA"
    })
    void toUpperCase_ShouldGenerateTheExpectedUpperCaseValue(String input, String expected) {
        assertEquals(expected, input.toUpperCase());
    }
}
```

### 5. @CsvFileSource
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void toUpperCase_ShouldGenerateTheExpectedUpperCaseValue(String input, String expected) {
        assertEquals(expected, input.toUpperCase());
    }
}
```

### 6. @MethodSource
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;

public class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithStringProvider(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }
}
```

### 7. @ArgumentsSource
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;

public class ArgumentsSourceTest {

    @ParameterizedTest
    @ArgumentsSource(CustomArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }

    static class CustomArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of("apple", "banana").map(Arguments::of);
        }
    }
}
```

</details>

# TDD
```mermaid
flowchart LR
   t([테스트]) ---> c([코딩]) 
   c ---> r([리팩터링])
   r ---> t
```

# Tip
## Intellij Live Template 적용
![intellij-live-templates-1](docs/intellij-live-templates-1.png)
![docs/intellij-live-templates-2](docs/intellij-live-templates-2.png)
![intellij-live-templates-3](docs/intellij-live-templates-3.png)