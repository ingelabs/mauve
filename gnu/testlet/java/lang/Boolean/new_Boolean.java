// tags: JDK1.0 JDK1.1 JDK1.2 PersonalJava EmbeddedJava

package gnu.testlet.java.lang.Boolean;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class new_Boolean implements Testlet
{
  public void test (TestHarness harness)
    {
      Boolean a = new Boolean("true");
      Boolean b = new Boolean("TRUE");
      Boolean c = new Boolean("tRuE");
      Boolean d = new Boolean("false");
      Boolean e = new Boolean("foo");
      Boolean f = new Boolean("");
      Boolean g = new Boolean(true);
      Boolean h = new Boolean(false);

      harness.check(a.booleanValue());
      harness.check(b.booleanValue());
      harness.check(c.booleanValue());
      harness.check(! d.booleanValue());
      harness.check(! e.booleanValue());
      harness.check(! f.booleanValue());
      harness.check(g.booleanValue());
      harness.check(! h.booleanValue());
    }

  public String description ()
    {
      return "creating Boolean objects";
    }

  public new_Boolean ()
    {
    }
}
