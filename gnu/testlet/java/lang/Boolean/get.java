// Tags: JDK1.0

package gnu.testlet.java.lang.Boolean;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.Properties;

public class get implements Testlet
{
  public void test (TestHarness harness)
    {
      // Augment the System properties with the following.
      // Overwriting is bad because println needs the
      // platform-dependent line.separator property.
      Properties p = System.getProperties();
      p.put("e1", "true");
      p.put("e2", "false");

      harness.check (Boolean.getBoolean("e1"));
      harness.check (! Boolean.getBoolean("e2"));
      harness.check (! Boolean.getBoolean("e3"));
    }

  public get ()
    {
    }
}
