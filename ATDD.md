Spring Boot에서 개발한 API의 정상 동작을 테스트하는 다양한 방법들이 있습니다. `TestRestTemplate` 외에도 다양한 방식으로 API를 테스트할 수 있습니다. 여기 몇 가지 방법을 소개합니다:

### 1. **MockMvc**
- **개요**: `MockMvc`는 Spring MVC 애플리케이션을 서블릿 컨테이너 없이 테스트할 수 있는 강력한 도구입니다. HTTP 요청을 모킹(mocking)하여 컨트롤러를 직접 테스트할 수 있습니다.
- **특징**:
    - 애플리케이션을 전체적으로 로드할 필요 없이 빠르게 테스트할 수 있습니다.
    - Spring MVC 환경에서 컨트롤러를 통합적으로 테스트할 수 있습니다.
- **사용 예시**:
  ```java
  import org.junit.jupiter.api.Test;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
  import org.springframework.boot.test.context.SpringBootTest;
  import org.springframework.test.web.servlet.MockMvc;

  import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
  import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

  @SpringBootTest
  @AutoConfigureMockMvc
  public class MyApiTest {

      @Autowired
      private MockMvc mockMvc;

      @Test
      public void testGetEndpoint() throws Exception {
          mockMvc.perform(get("/api/my-endpoint"))
                  .andExpect(status().isOk());
      }
  }
  ```

### 2. **WebTestClient**
- **개요**: `WebTestClient`는 WebFlux에서 사용되지만, Spring MVC에서도 사용할 수 있는 비동기식 테스트 클라이언트입니다. 비동기 및 반응형 프로그래밍을 테스트할 때 유용합니다.
- **특징**:
    - `WebClient`와 유사한 방식으로 HTTP 요청을 보내며, 비동기적으로 동작합니다.
    - Spring WebFlux 또는 Spring MVC 기반의 애플리케이션을 테스트할 때 사용됩니다.
- **사용 예시**:
  ```java
  import org.junit.jupiter.api.Test;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
  import org.springframework.boot.test.context.SpringBootTest;
  import org.springframework.test.web.reactive.server.WebTestClient;

  @SpringBootTest
  @AutoConfigureWebTestClient
  public class MyApiTest {

      @Autowired
      private WebTestClient webTestClient;

      @Test
      public void testGetEndpoint() {
          webTestClient.get().uri("/api/my-endpoint")
                  .exchange()
                  .expectStatus().isOk()
                  .expectBody()
                  .jsonPath("$.key").isEqualTo("expectedValue");
      }
  }
  ```

### 3. **Rest Assured**
- **개요**: `Rest Assured`는 주로 Java 환경에서 REST API를 테스트하기 위한 DSL(Domain-Specific Language)로, 매우 직관적이고 강력한 기능을 제공합니다.
- **특징**:
    - 외부 라이브러리로서, HTTP 요청을 생성하고 응답을 검증하는 데 유용합니다.
    - 다양한 HTTP 요청 메서드와 복잡한 JSON 응답 구조를 검증할 수 있습니다.
- **사용 예시**:
  ```java
  import io.restassured.RestAssured;
  import org.junit.jupiter.api.Test;
  import org.springframework.boot.test.context.SpringBootTest;

  import static io.restassured.RestAssured.given;
  import static org.hamcrest.Matchers.equalTo;

  @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  public class MyApiTest {

      @Test
      public void testGetEndpoint() {
          given().port(8080)
                  .when().get("/api/my-endpoint")
                  .then().statusCode(200)
                  .body("key", equalTo("expectedValue"));
      }
  }
  ```

### 4. **Cucumber와 BDD (Behavior Driven Development)**
- **개요**: `Cucumber`는 BDD를 위한 도구로, 자연어로 작성된 시나리오를 통해 API를 테스트할 수 있습니다. 일반적으로 REST API의 엔드투엔드(E2E) 테스트에 많이 사용됩니다.
- **특징**:
    - 자연어(Gherkin)를 사용하여 테스트 시나리오를 작성할 수 있습니다.
    - 다양한 단계(Step)를 조합하여 복잡한 시나리오를 구성할 수 있습니다.
- **사용 예시**:
  ```gherkin
  Feature: My API feature
    Scenario: Test GET endpoint
      Given the server is running
      When I send a GET request to "/api/my-endpoint"
      Then the response status should be 200
      And the response should contain "expectedValue"
  ```

### 5. **Postman 또는 Newman**
- **개요**: `Postman`은 GUI 기반의 API 테스트 도구로, 간편하게 API를 테스트할 수 있습니다. `Newman`은 Postman 컬렉션을 CLI에서 실행할 수 있는 도구입니다.
- **특징**:
    - API를 수동으로 테스트하거나, Postman을 통해 테스트 스크립트를 작성할 수 있습니다.
    - 작성된 Postman 컬렉션을 `Newman`을 통해 자동화된 테스트로 실행할 수 있습니다.
- **사용 예시**:
    - Postman에서 테스트 시나리오를 작성한 후, CLI에서 다음과 같이 실행합니다:
  ```bash
  newman run MyPostmanCollection.json
  ```

### 6. **Spring Boot Integration Tests**
- **개요**: 전체 애플리케이션 컨텍스트를 로드하여 통합 테스트를 수행할 수 있습니다. 실제 애플리케이션 환경에서 API를 호출하여 테스트할 수 있습니다.
- **특징**:
    - 실제로 애플리케이션이 구동된 상태에서 테스트하므로, 전반적인 통합 테스트에 적합합니다.
- **사용 예시**:
  ```java
  import org.junit.jupiter.api.Test;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.context.SpringBootTest;
  import org.springframework.boot.web.server.LocalServerPort;
  import org.springframework.web.client.RestTemplate;

  import static org.assertj.core.api.Assertions.assertThat;

  @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  public class MyApiIntegrationTest {

      @LocalServerPort
      private int port;

      @Autowired
      private RestTemplate restTemplate;

      @Test
      public void testGetEndpoint() {
          String url = "http://localhost:" + port + "/api/my-endpoint";
          String response = this.restTemplate.getForObject(url, String.class);
          assertThat(response).contains("expectedValue");
      }
  }
  ```

위 방법들은 각기 다른 목적에 따라 API를 테스트할 수 있는 다양한 도구와 기법들입니다. API 테스트는 개발 과정에서 매우 중요한 부분이므로, 각 테스트 도구의 장단점을 파악하고, 상황에 맞게 적절히 사용하면 좋습니다.