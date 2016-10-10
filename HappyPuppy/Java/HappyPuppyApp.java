

public class HappyPuppyApp
{
    public void StartSession()
    {
        System.out.println("\n=== Welcome to Happy Puppy! ===");
    }

    public void EndSession()
    {
        System.out.println("=== Thanks, and come again!! ===\n");
    }

    public void ListPuppies()
    {
        Presenters.ListPresenter presenter = 
            new Presenters.ListPresenter(AppEnv.ListPuppies());   

        presenter.Execute();
    }

    public void ListOwners()
    {
        Presenters.ListPresenter presenter = 
            new Presenters.ListPresenter(AppEnv.ListOwners());

        presenter.Execute();
    }

    public void Adopt(String puppyName, String ownerName)
    {
        Presenters.AdoptPuppyPresenter presenter = 
            new Presenters.AdoptPuppyPresenter(
                puppyName, ownerName, AppEnv.AdoptPuppy());

        presenter.Execute();
    }
}