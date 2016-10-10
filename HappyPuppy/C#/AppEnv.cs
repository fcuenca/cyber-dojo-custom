
public class AppEnv
{
    public static HappyPuppyRepository repo = null;

    public static AdoptPuppyRequest AdoptPuppy
    {
        get { return new AdoptPuppyUseCase(repo); }
    }

    public static ActionRequest ListPuppies
    {
        get { return new ListPuppiesUseCase(repo); }
    }

    public static ActionRequest ListOwners
    {
        get { return new ListOwnersUseCase(repo); }
    }
}
