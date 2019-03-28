package test.apiPetClient;

import io.qameta.allure.Step;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import test.data.Pet;
import test.data.Status;


import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class ServicesPetSteps extends PetBaseApiClient {

    private static ServicesPetSteps client;

    public static ServicesPetSteps getInstance() {
        if (client == null) {
            synchronized (ServicesPetSteps.class) {
                if (client == null) {
                    client = new ServicesPetSteps();
                }
            }
        }
        return client;
    }

    @Step("Check error message {0} for response {1}")
    public ServicesPetSteps checkErrorResponseMessage(String message, Response response){
        assertThat(response.getBody().jsonPath().get("message").toString()).isEqualTo(message);
        return this;
    }

    @Step("Check correct status {status}")
    public ServicesPetSteps checkStatusResponse(int status, Response response){
        assertThat(((RestAssuredResponseImpl) response).getStatusCode()).isEqualTo(status);
        return this;
    }

    @Step("Check parameter {expectedValue} is equal to {actualValue}")
    public ServicesPetSteps checkParamInResponseBody(Object expectedValue, Object actualValue){
        assertThat(expectedValue).isEqualTo(actualValue);
        return this;
    }

    @Step("Upload Pet with id {pet.id}")
    public ServicesPetSteps uploadPetSuccessfully(Pet pet) {
        Response response = postPet(pet);
        checkStatusResponse(200, response);
        return this;
    }

    @Step("Update Pet with id {pet.id}")
    public ServicesPetSteps updatePetSuccessfully(Pet pet) {
        Response response = putPet(pet);
        checkStatusResponse(200, response);
        return this;
    }

    @Step("Check upload Incorrect Pet {pet} with message {message} and status {status} ")
    public ServicesPetSteps checkUploadIncorrectObject(Object pet, int status, String message) {
        Response response = postPet(pet);
        checkStatusResponse(status, response);
        checkErrorResponseMessage(message, response);
        return this;
    }

    @Step("Check update Incorrect Pet {pet} with message {message} and status {status} ")
    public ServicesPetSteps checkUpdateIncorrectObject(Object pet, int status, String message) {
        Response response = putPet(pet);
        checkStatusResponse(status, response);
        checkErrorResponseMessage(message, response);
        return this;
    }

    @Step("Delete Pet with is {pet.id}")
    public ServicesPetSteps deletePetById(String petId) {
        Response response = deletePet(petId);
        checkStatusResponse(200, response);
        return this;
    }

    @Step("Check pet {petis} was deleted with error {status}")
    public ServicesPetSteps checkDeletingWithErrorPet(String petId, int status) {
        Response response = deletePet(petId);
        checkStatusResponse(status, response);
        return this;
    }

    @Step("Check all fields for Pet {pet}")
    public ServicesPetSteps checkAllPetFields(Pet expectedPet) {
        Pet actualPet = getPetById(expectedPet.getId());
        assertThat(actualPet).isEqualTo(expectedPet);
        return this;
    }

    @Step("Get Pet by id {petId}")
    public Pet getPetById(String petId){
        Response response = getPet(petId);
        checkStatusResponse(200, response);
        return response.as(Pet.class);
    }

    @Step("Check Pet with id {petId} is not exist")
    public ServicesPetSteps checkNotExistingPet(String petId){
        Response response = getPet(petId);
        checkErrorResponseMessage("Pet not found", response);
        checkStatusResponse(404, response);
        return this;
    }

    @Step("Check uploading incorrect Pet with id {pet.id} with message {message}")
    public ServicesPetSteps checkIncorrectPetUploading(Pet pet, String message) {
        if(pet.getId() != null) {
            Response response = getPet(pet.getId());
            checkErrorResponseMessage(message, response);
        }
        assertThat(message).isEqualTo("Null object");
        return this;
    }

    @Step("Sizes Pets in list of Pets with status {status}")
    public long sizePetsInStore(Pet pet, Status status) {
        Pet actualPet = getPetById(pet.getId());
        Response response = findByStatus(status);
        List<Pet> pets = Arrays.asList(response.getBody().as(Pet[].class));
        return pets.stream().filter(pet1 -> pet1.getId().equals(actualPet.getId())).count();
    }

    @Step("Check Pet with is {pey.id} is exist for status {status}")
    public ServicesPetSteps checkPetExistByStatus(Pet pet, Status status){
        assertThat(sizePetsInStore(pet, status)).isEqualTo(1);
        return this;
    }

    @Step("Check Pet with id {pet.id} is not exist for status {status}")
    public ServicesPetSteps checkPetIsNotExistByStatus(Pet pet, Status status){
        assertThat(sizePetsInStore(pet, status)).isEqualTo(0);
        return this;
    }

    @Step("Check list of Pets exist for status {status}")
    public ServicesPetSteps checkPetsExistByStatus(List<Pet> pets, Status status){
        List<Pet> actualPets = pets.stream().map(pet -> getPetById(pet.getId())).collect(Collectors.toList());
        Response response = findByStatus(status);
        checkStatusResponse(200, response);
        List<Pet> allPets = Arrays.asList(response.getBody().as(Pet[].class));
        assertThat(allPets).containsAll(actualPets);
        return this;
    }

    @Step("Check uploading Image for petId {petId}")
    public ServicesPetSteps checkUploadImageSuccessfully(String petId, String metadata, String file) throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource(file);
        assert res != null;
        File file1 = Paths.get(res.toURI()).toFile();
        Response response = uploadImage(petId, metadata, file1);
        checkStatusResponse(200, response);
        return this;
    }


}
