using System;
using Moq;
using NUnit.Framework;

[TestFixture]
public class RouletteRunner
{
    private bool DISABLED = false;

    [Test]
    public void run_simulation()
    {
        if(DISABLED) return;

        Console.WriteLine("\n<<<");
        Console.WriteLine("*** your simulation goes here ***");
        Console.WriteLine("<<<");
    }
}
