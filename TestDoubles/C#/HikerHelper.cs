using System.Threading;

public interface HikerHelper 
{
    int GetMultiplier();
    void WaitFor(int minutes);
}

public class RealHelper: HikerHelper
{
    public virtual int GetMultiplier()
    {
        return 120 / 3 - 60 / 2 - 3;
    }

    public virtual void WaitFor(int minutes)
    {
        Thread.Sleep(minutes * 60 * 1000);
    }
}