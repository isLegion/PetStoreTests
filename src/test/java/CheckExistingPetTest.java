import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import test.TestUtils;
import test.apiPetClient.ServicesPetSteps;
import test.data.Category;
import test.data.Pet;
import test.data.Status;
import test.data.Tag;

import java.util.Arrays;
import java.util.List;

import static test.TestUtils.*;
import static test.TestUtils.INCORRECT_TAGS_TYPE_PET;

/**
 * Created by zsmirnova on 27/03/2019.
 */
@Feature("Tests with existing Pets")
@Story("Updating and check existing Pet")
public class CheckExistingPetTest extends BaseTest {

    private ServicesPetSteps apiClient = ServicesPetSteps.getInstance();

    @Test(description = "Check all fields for existing simple default Pet in store")
    public void checkExistingSimplePet(){
        apiClient.uploadPetSuccessfully(pet)
                .checkAllPetFields(pet);
    }

    @Test(description = "Check all fields for existing with all fields Pet in store")
    public void checkExistingFullPet(){
        Pet fullPet = TestUtils.generateFullTemplate();
        apiClient.uploadPetSuccessfully(fullPet)
                .checkAllPetFields(fullPet);
    }

    @Test(description = "Check all fields for not existing Pet in store")
    public void checkNotExistingEmptyPet() {
        Pet pet = Pet.builder().build();
        apiClient.uploadPetSuccessfully(pet)
                .checkIncorrectPetUploading(pet, "Null object");
    }

    @Test(description = "Check updating all fields for existing full Pet in store")
    public void checkFieldsUpdatePet() {
        pet = TestUtils.generateFullTemplate();
        apiClient.uploadPetSuccessfully(pet);
        String newName = TestUtils.getRandomWord();
        List<String> newListUrls = Arrays.asList("firstLink", "secondList", "thirdList");
        List<Tag> newTag = Arrays.asList(Tag.builder().id(Integer.toString(TestUtils.getRandomNumber())).name(TestUtils.getRandomWord()).build(), Tag.builder().id(Integer.toString(TestUtils.getRandomNumber())).name(TestUtils.getRandomWord()).build());
        Status newStatus = Status.sold;
        Category newCategory = Category.builder().id(Integer.toString(TestUtils.getRandomNumber())).name(TestUtils.getRandomWord()).build();
        Pet updatedPet = pet.toBuilder()
                .id(pet.getId())
                .name(newName)
                .status(Status.sold)
                .photoUrls(newListUrls)
                .category(newCategory)
                .tags(newTag)
                .status(newStatus)
                .build();
        Pet actualPet = apiClient.updatePetSuccessfully(updatedPet)
                .getPetById(pet.getId());
        apiClient
                .checkParamInResponseBody(actualPet.getId(),pet.getId())
                .checkParamInResponseBody(actualPet.getName(), newName)
                .checkParamInResponseBody(actualPet.getPhotoUrls(), newListUrls)
                .checkParamInResponseBody(actualPet.getStatus(), newStatus)
                .checkParamInResponseBody(actualPet.getTags(), newTag)
                .checkParamInResponseBody(actualPet.getCategory(), newCategory);
    }

    @Test(description = "Check updating all fields for not existing Pet in store")
    public void checkFieldsUpdateNotExistingPet() {

        apiClient.checkUpdateIncorrectObject(NO_URLS_PET, 400, "bad input");

        apiClient.checkUpdateIncorrectObject(INCORRERECT_ID_PET, 400, "bad input");

        apiClient.checkUpdateIncorrectObject(INCORRECT_CATEGORY_TAG_PET, 500, "something bad happened");

        apiClient.checkUpdateIncorrectObject(INCORRECT_TAGS_TYPE_PET, 500, "something bad happened");

        apiClient.checkUpdateIncorrectObject("", 500, "something bad happened");

    }

    @Test(description = "Check getting not existing Pet in store")
    public void checkNotExistingPet() {
        Pet pet = TestUtils.generateFullTemplate();
        apiClient.checkNotExistingPet(pet.getId());
    }

    @Test(description = "Check that Pet is exist for all statuses")
    public void checkExistingPetWithStatus() {
        Pet pet1 = TestUtils.generateFullTemplate().toBuilder().id(Integer.toString(TestUtils.getRandomNumber())).status(Status.available).build();
        Pet pet2 = TestUtils.generateFullTemplate().toBuilder().id(Integer.toString(TestUtils.getRandomNumber())).status(Status.pending).build();
        Pet pet3 = TestUtils.generateFullTemplate().toBuilder().id(Integer.toString(TestUtils.getRandomNumber())).status(Status.sold).build();
        apiClient.uploadPetSuccessfully(pet1)
                .uploadPetSuccessfully(pet2)
                .uploadPetSuccessfully(pet3)
                .checkPetExistByStatus(pet1, Status.available)
                .checkPetExistByStatus(pet2, Status.pending)
                .checkPetExistByStatus(pet3, Status.sold);

    }

    @Test(description = "Check that Pet is not exist for all statuses")
    public void checkNotExistingPetWithStatus() {
        Pet pet1 = TestUtils.generateFullTemplate().toBuilder().id(Integer.toString(TestUtils.getRandomNumber())).status(Status.available).build();
        Pet pet2 = TestUtils.generateFullTemplate().toBuilder().id(Integer.toString(TestUtils.getRandomNumber())).status(Status.pending).build();
        Pet pet3 = TestUtils.generateFullTemplate().toBuilder().id(Integer.toString(TestUtils.getRandomNumber())).status(Status.sold).build();
        apiClient.uploadPetSuccessfully(pet1)
                .uploadPetSuccessfully(pet2)
                .uploadPetSuccessfully(pet3)
                .checkPetIsNotExistByStatus(pet2, Status.available)
                .checkPetIsNotExistByStatus(pet3, Status.pending)
                .checkPetIsNotExistByStatus(pet1, Status.sold);

    }

    @Test(description = "Check that Pets are exist for all statuses")
    public void checkExistingPetsWithStatus() {
        Pet pet1 = TestUtils.generateFullTemplate();
        Pet pet2 = TestUtils.generateFullTemplate();
        Pet pet3 = TestUtils.generateFullTemplate();
        List<Pet> pets = Arrays.asList(pet1, pet2, pet3);

        apiClient.uploadPetSuccessfully(pet1)
                .uploadPetSuccessfully(pet2)
                .uploadPetSuccessfully(pet3)
                .checkPetsExistByStatus(pets, Status.available);
    }

}
