import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Owner("Bakhtiyar Z")
@Feature("Subscribe button")
@Tag("api")
public class DemoWebShopTests {

  @BeforeAll
  public static void setUp() {
    RestAssured.filters(
        new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter());
    RestAssured.baseURI = "http://demowebshop.tricentis.com";
  }

  @Test
  @DisplayName("Verification to subscribe by an unauthorized user")
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
