
public class HikerHelper 
{
    public int multiplier() 
    {
        return 9;
    }

    public void waitFor(int minutes)
    {
        try 
        {
            Thread.sleep(minutes * 60 * 1000);
        } 
        catch (InterruptedException e) {}
    }
}
