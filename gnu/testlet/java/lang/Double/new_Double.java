// Tags: JDK1.0

package gnu.testlet.java.lang.Double;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class new_Double implements Testlet
{
  public void test (TestHarness harness)
    {
      // Some broken implementations convert "7.79625120912E289" to
      // the value 7.796251209119999E289.
      harness.check (new Double("7.79625120912E289").toString (),
		     "7.79625120912E289");
    }
}

