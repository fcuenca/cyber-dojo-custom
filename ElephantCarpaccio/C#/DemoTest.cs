using Moq;
using NUnit.Framework;

[TestFixture]
public class DemoTest
{
    private bool DEMO_TIME = true;

    [Test]
    public void elephant_carpaccio()
    {
        if(!DEMO_TIME) return;

        ElephantCarpaccio.SliceIt();
    }
}
