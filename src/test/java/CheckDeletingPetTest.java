import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import test.TestUtils;
import test.apiPetClient.ServicesPetSteps;
import test.data.Pet;


/**
 * Created by zsmirnova on 27/03/2019.
 */
@Feature("Delete Pet from store")
@Story("Delete Pet")
public class CheckDeletingPetTest extends BaseTest {

    private ServicesPetSteps apiClient = ServicesPetSteps.getInstance();

    @Test(description = "Validation deleting default Pet")
    public void checkDeletingSimplePet(){
        apiClient
                .uploadPetSuccessfully(pet)
                .deletePetById(pet.getId())
                .checkNotExistingPet(pet.getId());
    }

    @Test(description = "Validation deleting Pet with all fields")
    public void checkDeletingFullPet(){
        Pet fullPet = TestUtils.generateFullTemplate();
        apiClient.uploadPetSuccessfully(fullPet)
                .deletePetById(fullPet.getId())
                .checkNotExistingPet(fullPet.getId());
    }

    @Test(description = "Validation deleting not existing Pet")
    public void checkDeletingNotExistingPet(){
        Integer petId = TestUtils.getRandomNumber();
        apiClient.checkDeletingWithErrorPet(Integer.toString(petId), 404);
    }

    @Test(description = "Validation deleting incorrect Pet")
    public void checkDeletingIncorrectPet(){
        String petId = TestUtils.getRandomWord();
        apiClient.checkDeletingWithErrorPet(petId, 404);
    }

}
