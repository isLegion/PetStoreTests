package test.apiPetClient;

import io.restassured.response.Response;

public interface AbstractRequest {

    Response getPet(String petId);
    Response postPet(Object pet);
    Response deletePet(String petId);
    Response deletePetString(String petId);
    Response putPet(Object object);
}
