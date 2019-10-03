package br.edu.ifsp.pocjbacon.integration;

import br.edu.ifsp.pocjbacon.base.BaseIntegrationTest;
import br.edu.ifsp.pocjbacon.entity.Contact;
import br.edu.ifsp.pocjbacon.factory.ContactFactory;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.http.ContentType.JSON;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactIntegrationTest extends BaseIntegrationTest {

    private final ContactFactory contactFactory;

    @Autowired
    public ContactIntegrationTest(final ContactFactory contactFactory) {
        this.contactFactory = contactFactory;
    }

    @Test
    void shouldPersistContact() {
        Contact contact = contactFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .contentType(JSON)
                    .body(contact)
                .when()
                    .post("/contacts")
                .then()
                    .log().all()
                    .statusCode(201);
        // @formatter:on

    }

    @Test
    void shouldReturnContact() {
        String name = "Caio Cresencio";
        Contact contact = contactFactory.create(empty -> {
            empty.setName(name);
        });

        // @formatter:off
        RestAssured
                .given()
                .log().all()
                .when()
                .get("/contacts/{id}", contact.getId())
                .then()
                .log().all()
                .statusCode(200)
                .body("name", Matchers.equalTo(name));
        // @formatter:on
    }

    @Test
    void shouldReturn3Contacts() {
        contactFactory.create(3);

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/contacts")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("$", Matchers.hasSize(3));
        // @formatter:on

    }

    @Test
    void shouldDeleteContact() {
        Contact contact = contactFactory.create();

        // @formatter:on
        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/contacts/{id}", contact.getId())
                .then()
                    .log().all()
                    .statusCode(204);
        // @formatter:on

    }
}
