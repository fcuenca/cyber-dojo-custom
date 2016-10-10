using System.Collections.Generic;


public abstract class UseCase
{
    protected HappyPuppyRepository repo; 

    public UseCase(HappyPuppyRepository repo)
    {
       this.repo = repo;
    }
}

public abstract class DisplayListUseCase: UseCase, ActionRequest
{
    public abstract string Heading { get; }

    public DisplayListUseCase(HappyPuppyRepository repo): base(repo)
    {
    }

    public void Execute(DisplayResponse port) 
    {
       foreach(string s in GetList())
       {
            port(s);
       }
    }

    protected abstract IEnumerable<string> GetList();
}

public class ListPuppiesUseCase: DisplayListUseCase
{ 
    public override string Heading
    {
        get { return "AVAILABLE PUPPIES"; }
    }

    public ListPuppiesUseCase(HappyPuppyRepository repo): base(repo)
    {
    }

    protected override IEnumerable<string> GetList()
    {
        return repo.RetrievePuppies();
    }
}

public class ListOwnersUseCase: DisplayListUseCase
{  
    public override string Heading
    {
        get { return "KNOWN OWNERS"; }
    }

    public ListOwnersUseCase(HappyPuppyRepository repo): base(repo)
    {
    }

    protected override IEnumerable<string> GetList()
    {
        return repo.RetrieveOwners();
    }
}

public class AdoptPuppyUseCase: UseCase, AdoptPuppyRequest
{    
    public string Heading
    {
        get { return "ADOPTION IN PROGRESS"; }
    }

    public AdoptPuppyUseCase(HappyPuppyRepository repo): base(repo)
    {
    }

    public void Execute(string puppyName, string ownerName,
                        NotificationResponse outPort)
    {
            repo.RemovePuppy(puppyName);
            repo.AddOwner(ownerName);
            outPort();
    }
}
