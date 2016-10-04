using System;
using NUnit.Framework;
using System.Collections.Generic;

[TestFixture]
public class RepositoryTests
{
    private ListBasedRepository repo;

    [SetUp]
    public void SetUp()
    {
         repo = new ListBasedRepository(CreateTestDatabase());   
    }

    [Test]
    public void retrieving_puppies()
    {
        Assert.That(repo.RetrievePuppies(), Is.EqualTo(
            new string[] { "Spike", "Pluto", "Daisy" }));
    }

    [Test]
    public void retrieving_owners()
    {
        Assert.That(repo.RetrieveOwners(), Is.EqualTo(
            new string[] { "Tom", "Jane" }));
    }

    [Test]
    public void removing_puppies()
    {
        repo.RemovePuppy("Daisy");

        Assert.That(repo.RetrievePuppies(), Has.No.Member("Daisy") );
    }

    [Test]
    public void adding_owners()
    {
        repo.AddOwner("Fernando");
        Assert.That(repo.RetrieveOwners(), Has.Member("Fernando") );
    }

    private Dictionary<string, List<string>> CreateTestDatabase()
    {
        Dictionary<string, List<string>> db = 
        new Dictionary<string, List<string>>();

        db.Add("owners", 
            new List<string>() { "Tom", "Jane" });

        db.Add("puppies", 
            new List<string>()
                { "Spike", "Pluto", "Daisy" });

        return db;
     }
    
}
