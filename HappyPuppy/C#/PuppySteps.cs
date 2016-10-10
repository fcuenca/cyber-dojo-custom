using System;
using TechTalk.SpecFlow;
using NUnit.Framework;
using System.Collections.Generic;


[Binding]
public class PuppySteps
{
    [BeforeScenario]
    public void SetUpScenario()
    {
        AppDriver.InitializeDatabase();
    }

    [Given(@"the following puppies are available for adoption:")]
    public void GivenTheFollowingPuppiesAreAvailableForAdoption(Table table)
    {
        AppDriver.AddTable("puppies", table.Rows);
    }
        
    [Given(@"the following know registered owners:")]
    public void GivenTheFollowingKnowRegisteredOwners(Table table)
    {
        AppDriver.AddTable("owners", table.Rows);
    }

    [When(@"customer ""(.*)"" requests to adopt puppy ""(.*)""")]
    public void WhenCustomerRequestsToAdoptPuppy(
        string customerName, string puppyName)
    {
        AppDriver.App.AdoptPuppy(puppyName, customerName);
    }
    
    [Then(@"""(.*)"" is shown a confirmation message")]
    public void ThenIsShownAConfirmationMessage(string p0)
    {
        AppDriver.App.ShouldHaveReceivedAdoptionConfirmation();
    }

    [Then(@"""(.*)"" is no longer listed as available")]
    public void ThenIsNoLongerListedAsAvailable(string puppyName)
    {
        AppDriver.App.PuppyListShouldNotInclude(puppyName);
    }
    
    [Then(@"""(.*)"" is listed as registered owner")]
    public void ThenIsListedAsRegisteredOwner(string newOwner)
    {
        AppDriver.App.CustomerShouldBeListedAsOwner(newOwner);
    }
}

public class AppDriver
{
    private static Dictionary<string, List<string>> database;
    private static AppDriver app = null;

    private bool adoptionWasConfirmed = false;

    public static void InitializeDatabase()
    {
        database = new Dictionary<string, List<string>>();
    }
    
    public static void AddTable(string tableName, IEnumerable<TableRow> data)
    {
        List<string> table = new List<string>();
        foreach(var s in data) table.Add(s[0]);
        database.Add(tableName, table);
    }

    public static AppDriver App
    {
        get 
        {
            if(app == null)
            {
                AppEnv.repo = new ListBasedRepository(database);
                app = new AppDriver();
            }
            return app;
        }
    }

    public void AdoptPuppy(string puppyName, string customerName)
    {
        adoptionWasConfirmed = false;
        AppEnv.AdoptPuppy.Execute(
            puppyName, customerName, () => adoptionWasConfirmed = true);
    }

    public void CustomerShouldBeListedAsOwner(string newOwner)
    {
        List<string> knownOwners = new List<string>();  
      
        AppEnv.ListOwners.Execute(s => knownOwners.Add(s));

        Assert.That(knownOwners, Has.Member(newOwner));
    }

    public void PuppyListShouldNotInclude(string puppyName)
    {
        List<string> availablePuppies = new List<string>(); 
 
        AppEnv.ListPuppies.Execute(s => availablePuppies.Add(s));

        Assert.That(availablePuppies, Has.No.Member(puppyName));
    }

    public void ShouldHaveReceivedAdoptionConfirmation()
    {
        Assert.That(adoptionWasConfirmed, Is.True);
    }
}
