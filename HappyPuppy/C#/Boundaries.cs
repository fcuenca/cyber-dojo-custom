
public delegate void DisplayResponse(string s);
public delegate void NotificationResponse();

public interface Request
{
    string Heading { get; }
}

public interface ActionRequest: Request
{
    void Execute(DisplayResponse port);
}

public interface AdoptPuppyRequest: Request
{
    void Execute(string puppyName, string ownerName, NotificationResponse output);
}
