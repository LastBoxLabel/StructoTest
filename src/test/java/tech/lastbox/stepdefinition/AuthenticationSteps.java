package tech.lastbox.stepdefinition;

import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import tech.lastbox.auth.LoginRequest;
import tech.lastbox.auth.RegisterRequest;

import static io.restassured.RestAssured.given;

public class AuthenticationSteps {
    private final Faker faker = new Faker();
    private String email;
    private String username;
    private String name;
    private String password;

    private Response response;

    @Dado("que forneco dados validos")
    public void que_forneco_dados_validos() {
        this.email = faker.internet().emailAddress();
        this.username = faker.name().username();
        this.name = faker.name().name();
        this.password = faker.internet().password();
    }

    @Quando("realizo o registro")
    public void realizo_o_registro() {
        response = given()
                        .contentType("application/json")
                        .body(new RegisterRequest(name, username, email, password))
                    .when()
                        .post("http://localhost:8080/register");
    }

    @Entao("obtenho sucesso")
    public void obtenho_sucesso() {
        response.then().statusCode(201);
    }

    @E("consigo realizar o login")
    public void consigo_realizar_login() {
        given()
                .contentType("application/json")
                .body(new LoginRequest(email, password))
                .when()
                .post("http://localhost:8080/login")
                .then().statusCode(200);
    }
}
