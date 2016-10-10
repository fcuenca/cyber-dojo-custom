
public class AppEnv
{
    public static Repository.HappyPuppyRepository repo = null;

    public static Boundaries.AdoptPuppyRequest AdoptPuppy()
    { 
        return new UseCases.AdoptPuppyUseCase(repo);
    }

    public static Boundaries.ActionRequest ListPuppies()
    {
        return new UseCases.ListPuppiesUseCase(repo); 
    }

    public static Boundaries.ActionRequest ListOwners()
    {
        return new UseCases.ListOwnersUseCase(repo);
    }
}