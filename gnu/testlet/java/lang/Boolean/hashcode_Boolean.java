// tags: JDK1.0 JDK1.1 JDK1.2 PersonalJava EmbeddedJava

package gnu.testlet.java.lang.Boolean;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class hashcode_Boolean implements Testlet
{
  public void test (TestHarness harness)
    {
      Boolean a = new Boolean("true");
      Boolean b = new Boolean("false");

      harness.check (a.hashCode(), 1231);
      harness.check (b.hashCode(), 1237);
    }

  public String description ()
    {
      return "Boolean.hashCode()";
    }

  public hashcode_Boolean ()
    {
    }
}
