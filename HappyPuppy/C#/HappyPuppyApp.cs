using System;

public class HappyPuppyApp
{
        public void StartSession()
        {
            Console.WriteLine("\n=== Welcome to Happy Puppy! ===");
        }

        public void EndSession()
        {
            Console.WriteLine("=== Thanks, and come again!! ===\n");
        }

        public void ListPuppies()
        {
            ListPresenter presenter = new ListPresenter(AppEnv.ListPuppies);   

            presenter.Execute();
        }

        public void ListOwners()
        {
            ListPresenter presenter = new ListPresenter(AppEnv.ListOwners);

            presenter.Execute();
        }

        public void Adopt(string puppyName, string ownerName)
        {
            AdoptPuppyPresenter presenter = new AdoptPuppyPresenter(
                puppyName, ownerName, AppEnv.AdoptPuppy);

            presenter.Execute();
        }
}