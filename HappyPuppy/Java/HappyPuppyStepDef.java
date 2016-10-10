import cucumber.api.*;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import java.util.*;

public class HappyPuppyStepDef 
{
    @Before
    public void SetUpScenario()
    {
        AppDriver.InitializeDatabase();
    }

    @Given("^the following puppies are available for adoption:$")
    public void theFollowingPuppiesAreAvailableForAdoption(List<String> puppies) throws Throwable 
    {
        AppDriver.AddTable("puppies", puppies);
    }
    
    @Given("^the following know registered owners:$")
    public void theFollowingKnowRegisteredOwners(List<String> owners) throws Throwable 
    {

        AppDriver.AddTable("owners", owners);
    }
    
    @When("^customer \"([^\"]*)\" requests to adopt puppy \"([^\"]*)\"$")
    public void customerRequestsToAdoptPuppy(
        String customerName, String puppyName) throws Throwable 
    {
        AppDriver.App().AdoptPuppy(puppyName, customerName);
    }
    
    @Then("^\"([^\"]*)\" is shown a confirmation message$")
    public void isShownAConfirmationMessage(String arg1) throws Throwable 
    {
        AppDriver.App().ShouldHaveReceivedAdoptionConfirmation();
    }
    
    @Then("^\"([^\"]*)\" is no longer listed as available$")
    public void isNoLongerListedAsAvailable(String puppyName) throws Throwable 
    {
        AppDriver.App().PuppyListShouldNotInclude(puppyName);
    }
    
    @Then("^\"([^\"]*)\" is listed as registered owner$")
    public void isListedAsRegisteredOwner(String newOwner) throws Throwable 
    {
        AppDriver.App().CustomerShouldBeListedAsOwner(newOwner);
    }

}
