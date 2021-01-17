import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTests {

  @BeforeEach
  void beforeEach() {
    RestAssured.filters(
        new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter());
    RestAssured.baseURI = "http://demowebshop.tricentis.com";
  }

  @Test
  void subscribeWithUnauthorizedUserTest() {
    given()
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .when()
        .post("/subscribenewsletter?Content-Type=application/x-www-form-urlencoded; charset=UTF-8")
        .then()
        .statusCode(200)
        .body("Result", is("Enter valid email"));
  }
}
