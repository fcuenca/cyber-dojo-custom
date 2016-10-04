using System;
using NUnit.Framework;
using System.IO;

[TestFixture]
public class PresenterTests
{
    private StringWriter _consoleOut;

    [SetUp]
    public void SetUp()
    {
        _consoleOut = captureStdOut();
    }

    [TearDown]
    public void TearDown()
    {
        restoreStdOut();
    }

    [Test]
    public void ListPresenter_displays_use_case_heading()
    {
        ListPresenter cmd = new ListPresenter(
            new FakeActionRequest("THIS IS THE HEADING", "IRRELEVANT"));

        cmd.Execute();

        Assert.That(_consoleOut.ToString(), 
            Is.StringContaining("THIS IS THE HEADING"));
    }

    [Test]
    public void ListPresenter_triggers_the_output_port_supplying_a_callback()
    {
        var fakePort = new FakeActionRequest("IRRELEVANT", "IRRELEVANT");
        ListPresenter cmd = new ListPresenter(fakePort);

        cmd.Execute();

        Assert.That(fakePort.observedPort, Is.Not.Null);
    }

    [Test]
    public void ListPresenter_displays_results_on_console()
    {
        ListPresenter cmd = new ListPresenter(
            new FakeActionRequest("IRRELEVANT", "FAKE OUTPUT"));

        cmd.Execute();

        Assert.That(_consoleOut.ToString(), 
            Is.StringContaining("FAKE OUTPUT"));
    }

    [Test]
    public void AdoptPuppyPresenter_displays_use_case_heading()
    {
        AdoptPuppyPresenter cmd = new AdoptPuppyPresenter(
            "IRRELEVANT", "IRRELEVANT", 
            new FakeAdoptPuppyRequest("ADOPT PUPPY HEADING"));

        cmd.Execute();

        Assert.That(_consoleOut.ToString(), 
            Is.StringContaining("ADOPT PUPPY HEADING"));
    }

    [Test]
    public void AdoptPuppyPresenter_triggers_execution_through_input_port()
    {
        var fakePort = new FakeAdoptPuppyRequest("IRRELEVANT");
        AdoptPuppyPresenter cmd = new AdoptPuppyPresenter(
            "the puppy", "the owner", fakePort);

        cmd.Execute();

        Assert.That(fakePort.observedPuppy, Is.EqualTo("the puppy"));
        Assert.That(fakePort.observedOwner, Is.EqualTo("the owner"));
    }

    [Test]
    public void AdoptPuppyPresenter_displays_adoption_confirmation()
    {
        AdoptPuppyPresenter cmd = new AdoptPuppyPresenter(
            "Spike", "Tom", 
            new FakeAdoptPuppyRequest("IRRELEVANT"));

        cmd.Execute();

        Assert.That(_consoleOut.ToString(), 
            Is.StringContaining("Spike was adopted by: Tom"));
    }

    private StringWriter captureStdOut()
    {
        var consoleOut = new StringWriter();
        Console.SetOut(consoleOut);
        return consoleOut;
    }

    private void restoreStdOut()
    {
        var standardOutput = new StreamWriter(Console.OpenStandardOutput());
        standardOutput.AutoFlush = true;
        Console.SetOut(standardOutput); 
    }
}
