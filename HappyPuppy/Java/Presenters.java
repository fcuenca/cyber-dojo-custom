public class Presenters
{
    public static class ListPresenter 
            implements Boundaries.DisplayResponse
    {
        protected Boundaries.ActionRequest inPort;
    
        public ListPresenter(Boundaries.ActionRequest inPort)
        {
            this.inPort = inPort;
        }
    
        public void Execute()
        {
            System.out.println("\n*** " + inPort.Heading() + ":");
            inPort.Execute(this);
        }

        public void Accept(String s) 
        {
            System.out.println(s);
        }
    }

    public static class AdoptPuppyPresenter 
            implements Boundaries.NotificationResponse
    {
        private String puppyName;
        private String ownerName;
        private Boundaries.AdoptPuppyRequest inPort;
    
        public AdoptPuppyPresenter(String puppyName, String ownerName,
                    Boundaries.AdoptPuppyRequest inPort)
        {
            this.puppyName = puppyName;
            this.ownerName = ownerName;
            this.inPort = inPort;
        }
        
        public void Execute()
        {
            System.out.println("\n*** " + inPort.Heading() + ":");
            inPort.Execute(puppyName, ownerName, this);
        }

        public void ActionWasCompleted()
        {
            System.out.println(puppyName + " was adopted by: " + ownerName);            
        }
    }

}
