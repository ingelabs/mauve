// tags: JDK1.0 JDK1.1 JDK1.2 PersonalJava EmbeddedJava

package gnu.testlet.java.lang.Boolean;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class value implements Testlet
{
  public void test (TestHarness harness)
    {
      Boolean a = new Boolean("true");
      Boolean b = new Boolean("false");

      harness.check (a.booleanValue());
      harness.check (! b.booleanValue());
      harness.check (a.equals(Boolean.valueOf("TrUE")));
      harness.check (! b.equals(Boolean.valueOf("TrUE")));
    }

  public value ()
    {
    }
}
