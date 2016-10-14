public class Hiker 
{
    private HikerHelper helper;

    public Hiker(HikerHelper helper) 
    {
        this.helper = helper;
    }

    public int AnswerFor(int number) 
    {
        return number * helper.GetMultiplier();
    }

    public int AnswerAfterDelay(int minutes)
    {
        helper.WaitFor(minutes);
        return 42;
    }
}
