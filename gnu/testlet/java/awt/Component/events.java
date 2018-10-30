package gnu.testlet.java.awt.Component;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Component;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;

public class events implements Testlet
{

  private class TestAWTComponent extends Component
  {

    public boolean dispatched = false;

    @Override
    protected void processEvent(AWTEvent e)
    {
      super.processEvent(e);
      dispatched = true;
    }

    public TestAWTComponent()
    {
      super.enableEvents(AWTEvent.ACTION_EVENT_MASK);
    }

  }

  public void test(TestHarness harness)
  {
    // Action events should be dispatched if enabled
    // See https://gcc.gnu.org/bugzilla/show_bug.cgi?id=70663
    TestAWTComponent sample = new TestAWTComponent();
    ActionEvent e = new ActionEvent(sample, ActionEvent.ACTION_PERFORMED, null);
    sample.dispatchEvent(e);
    harness.check(sample.dispatched);
  }

}
