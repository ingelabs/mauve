// Tags: JDK1.0

package gnu.testlet.java.lang.Integer;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.Properties;

public class getInteger implements Testlet
{
  public void test (TestHarness harness)
    {
      // Augment the System properties with the following.
      // Overwriting is bad because println needs the
      // platform-dependent line.separator property.
      Properties p = System.getProperties();
      p.put("e1", Integer.toString(Integer.MIN_VALUE));
      p.put("e2", Integer.toString(Integer.MAX_VALUE));
      p.put("e3", "0" + Integer.toOctalString(Integer.MIN_VALUE));
      p.put("e4", "0" + Integer.toOctalString(Integer.MAX_VALUE));
      p.put("e5", "0x" + Integer.toHexString(Integer.MIN_VALUE));
      p.put("e6", "0x" + Integer.toHexString(Integer.MAX_VALUE));
      p.put("e7", "0" + Integer.toString(Integer.MAX_VALUE, 8));
      p.put("e8", "#" + Integer.toString(Integer.MAX_VALUE, 16));
      p.put("e9", "");
      p.put("e10", " ");
      p.put("e11", "foo");

      harness.check (Integer.getInteger("e1").toString(), "-2147483648");
      harness.check (Integer.getInteger("e2").toString(), "2147483647");
      harness.check (Integer.getInteger("e3"), null);
      harness.check (Integer.getInteger("e4").toString(), "2147483647");
      harness.check (Integer.getInteger("e5", 12345).toString(), "12345");
      harness.check (Integer.getInteger("e6", new Integer(56789)).toString(),
		     "2147483647");
      harness.check (Integer.getInteger("e7", null).toString(), "2147483647");
      harness.check (Integer.getInteger("e8", 12345).toString(), "2147483647");
      harness.check (Integer.getInteger("e9", new Integer(56789)).toString(),
		     "56789");
      harness.check (Integer.getInteger("e10", null), null);
      harness.check (Integer.getInteger("e11"), null);
      harness.check (Integer.getInteger("junk", 12345).toString(), "12345");
      harness.check (Integer.getInteger("junk", new Integer(56789)).toString(),
		     "56789");
      harness.check (Integer.getInteger("junk", null), null);
      harness.check (Integer.getInteger("junk"), null);
    }
}
