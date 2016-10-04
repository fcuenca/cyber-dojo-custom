using System;

public class AdoptPuppyPresenter
{
    private string puppyName;
    private string ownerName;
    private AdoptPuppyRequest inPort;

    public AdoptPuppyPresenter(string puppyName, string ownerName,
                AdoptPuppyRequest inPort)
    {
        this.puppyName = puppyName;
        this.ownerName = ownerName;
        this.inPort = inPort;
    }
    
    public void Execute()
    {
        Console.WriteLine("\n*** " + inPort.Heading + ":");
        inPort.Execute(puppyName, ownerName, () => {
            Console.WriteLine(puppyName + " was adopted by: " + ownerName);
        });
    }
}

public class ListPresenter
{
    protected ActionRequest inPort;

    public ListPresenter(ActionRequest inPort)
    {
        this.inPort = inPort;
    }

    public void Execute()
    {
        Console.WriteLine("\n*** " + inPort.Heading + ":");
        inPort.Execute(s => Console.WriteLine(s));
    }
}


