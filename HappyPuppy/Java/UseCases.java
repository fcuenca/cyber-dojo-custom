
public class UseCases
{
    public static abstract class UseCase
    {
        protected Repository.HappyPuppyRepository repo; 
    
        public UseCase(Repository.HappyPuppyRepository repo)
        {
           this.repo = repo;
        }
    }

    public static abstract class DisplayListUseCase 
        extends UseCase 
        implements Boundaries.ActionRequest
    {
        public abstract String Heading();
    
        public DisplayListUseCase(Repository.HappyPuppyRepository repo)
        {
            super(repo);
        }
    
        public void Execute(Boundaries.DisplayResponse port) 
        {
           for(String s: GetList())
           {
                port.Accept(s);
           }
        }
    
        protected abstract Iterable<String> GetList();
    }

    public static class ListPuppiesUseCase extends DisplayListUseCase
    { 
        @Override
        public String Heading()
        {
            return "AVAILABLE PUPPIES";
        }
    
        public ListPuppiesUseCase(Repository.HappyPuppyRepository repo)
        {
            super(repo);
        }

        @Override    
        protected Iterable<String> GetList()
        {
            return repo.RetrievePuppies();
        }
    }

    public static class ListOwnersUseCase extends DisplayListUseCase
    {  
        @Override
        public String Heading()
        {
           return "KNOWN OWNERS"; 
        }
    
        public ListOwnersUseCase(Repository.HappyPuppyRepository repo)
        {
            super(repo);
        }
    
        @Override
        protected Iterable<String> GetList()
        {
            return repo.RetrieveOwners();
        }
    }

    public static class AdoptPuppyUseCase 
        extends UseCase 
        implements Boundaries.AdoptPuppyRequest
    {    
        public String Heading()
        {
           return "ADOPTION IN PROGRESS"; 
        }
    
        public AdoptPuppyUseCase(Repository.HappyPuppyRepository repo)
        {    
            super(repo);
        }
    
        public void Execute(String puppyName, String ownerName,
                            Boundaries.NotificationResponse outPort)
        {
                repo.RemovePuppy(puppyName);
                repo.AddOwner(ownerName);
                outPort.ActionWasCompleted();
        }
    }

}
