import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import test.TestUtils;
import test.apiPetClient.ServicesPetSteps;

import java.net.URISyntaxException;

/**
 * Created by zsmirnova on 27/03/2019.
 */
@Feature("Tests with uploading image for existing Pets")
@Story("Upload image for Pet")
public class CheckUploadImagePetTest extends BaseTest {

    private ServicesPetSteps apiClient = ServicesPetSteps.getInstance();

    private static final String METADATA = "qqqqqqqq";
    private static final String FILENAME = "download.jpeg";

    @Test(description = "Check upload image for existing Pet in Store")
    public void checkUploadImagePet() throws URISyntaxException {
        pet = TestUtils.generateFullTemplate();
        apiClient.uploadPetSuccessfully(pet)
                .checkUploadImageSuccessfully(pet.getId(), METADATA, FILENAME);
    }

    @Test(description = "Check upload image for not existing Pet in Store")
    public void checkUploadImageNotExistingPet() throws URISyntaxException {
        int pet = TestUtils.getRandomNumber();
        apiClient.checkNotExistingPet(Integer.toString(pet))
                .checkUploadImageSuccessfully(Integer.toString(pet), METADATA, FILENAME);
    }


}
