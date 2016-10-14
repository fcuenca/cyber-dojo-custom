
public class Hiker 
{
    private HikerHelper helper;

    public Hiker(HikerHelper helper) 
    {
        this.helper = helper;
    }

    public int answerFor(int number) 
    {
        return number * helper.multiplier();
    }

    public int answerAfterDelay(int minutes)
    {
        helper.waitFor(minutes);
        return 42;
    }
}
