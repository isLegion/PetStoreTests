import io.qameta.allure.*;
import org.testng.annotations.Test;
import test.TestUtils;
import test.apiPetClient.ServicesPetSteps;
import test.data.Pet;

import static test.TestUtils.*;

/**
 * Created by zsmirnova on 27/03/2019.
 */
@Feature("Add new pet in store")
public class AddNewPetTest extends BaseTest {

    private ServicesPetSteps apiClient = ServicesPetSteps.getInstance();

    @Test(description = "Validation adding default pet in store")
    @Severity(SeverityLevel.CRITICAL)
    public void checkPositivePostSimplePet() {
        apiClient.uploadPetSuccessfully(pet);
    }

    @Test(description = "Validation adding pet with all fields in store")
    @Severity(SeverityLevel.CRITICAL)
    public void checkPositivePostFullPet() {
        apiClient.uploadPetSuccessfully(TestUtils.generateFullTemplate());
    }

    @Test(description = "Validation upload empty pet in store")
    public void checkUploadEmptyPet() {
        Pet pet = Pet.builder().build();
        apiClient.uploadPetSuccessfully(pet);
    }

    @Test(description = "Validation upload per with incorrect fields")
    public void checkUpdateIncorrectPet() {

        apiClient.checkUploadIncorrectObject(NO_URLS_PET, 400, "bad input");

        apiClient.checkUploadIncorrectObject(INCORRERECT_ID_PET, 400, "bad input");

        apiClient.checkUploadIncorrectObject(INCORRECT_CATEGORY_TAG_PET, 500, "something bad happened");

        apiClient.checkUploadIncorrectObject(INCORRECT_TAGS_TYPE_PET, 500, "something bad happened");

        apiClient.checkUploadIncorrectObject("", 500, "something bad happened");
    }

    @Test(description = "Validation upload pet twice")
    public void checkPositivePostTwiceFullPet() {
        Pet pet = TestUtils.generateFullTemplate();
        apiClient
                .uploadPetSuccessfully(pet)
                .uploadPetSuccessfully(pet);
    }

}
