using System;
using NUnit.Framework;
using System.Collections.Generic;

[TestFixture]
public class HappyPuppyTest
{
    private bool DISABLED = true;

    [Test]
    public void run_the_app()
    {
        if(DISABLED) return;

        InitializeDatabase();
        RunApplication();
    }

    private void RunApplication()
    {
        HappyPuppyApp hp = new HappyPuppyApp();

        hp.StartSession();
        hp.ListPuppies(); 
        hp.ListOwners();
        hp.Adopt("Brook", "Fernando");
        hp.ListOwners();
        hp.ListPuppies();
        hp.EndSession();
    }

    private void InitializeDatabase()
    {
            Dictionary<string, List<string>> db = 
            new Dictionary<string, List<string>>();

            db.Add("owners", 
                new List<string>() { "Mary", "Peter" });

            db.Add("puppies", 
                new List<string>()
                    { "Brook", "Hanna", "Maggie Mae", "Ruby Sue" });

            UseCases.repo = new ListBasedRepository(db);
      }
}
