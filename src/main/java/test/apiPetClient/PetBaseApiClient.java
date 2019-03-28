package test.apiPetClient;

import io.restassured.response.Response;
import test.data.Status;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

/**
 * Created by zsmirnova on 26/03/2019.
 */
public class PetBaseApiClient implements AbstractRequest {


    public Response getPet(String petId) {
        return given()
                .log()
                .all()
                .contentType(JSON)
                .when()
                .log()
                .all()
                .get(petId);
    }

    public Response postPet(Object pet) {
        return given()
                .log()
                .all()
                .contentType(JSON)
                .when()
                .log()
                .all()
                .body(pet)
                .post();
    }

    public Response deletePet(String petId) {
        return given()
                .log()
                .all()
                .contentType(JSON)
                .when()
                .log()
                .all()
                .delete(petId);
    }

    public Response deletePetString(String petId) {
        return given()
                .log()
                .all()
                .contentType(JSON)
                .when()
                .log()
                .all()
                .delete(petId);
    }

    public Response putPet(Object pet) {
        return given()
                .log()
                .all()
                .contentType(JSON)
                .when()
                .log()
                .all()
                .body(pet)
                .put();
    }

    public Response findByStatus(Status status) {
        return given()
                .log()
                .all()
                .contentType(JSON)
                .param("status", status)
                .when()
                .log()
                .all()
                .get("findByStatus");
    }

    public Response uploadImage(String petId, String additionalMetadata, File file) {
        return given()
                .log()
                .all()
                .contentType("multipart/form-data")
                .param("petId", petId)
                .param("additionalMetadata", additionalMetadata)
                .multiPart("file", file,"text/html")
                .expect()
                .when()
                .post(petId + "/uploadImage");
    }

}
