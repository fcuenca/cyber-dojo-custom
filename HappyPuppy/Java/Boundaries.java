
public class Boundaries
{
    public interface DisplayResponse
    {
        void Accept(String s);
    }

    public interface NotificationResponse
    {
        void ActionWasCompleted();
    }

    public interface Request
    {
        String Heading();
    }

    public interface ActionRequest extends Request
    {
        void Execute(DisplayResponse port);
    }

    public interface AdoptPuppyRequest extends Request
    {
        void Execute(String puppyName, String ownerName, 
                Boundaries.NotificationResponse output);
    }
}

