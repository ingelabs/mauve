// Tags: JDK1.0

package gnu.testlet.java.lang.Byte;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class new_Byte implements Testlet
{
  public void test (TestHarness harness)
    {
      Byte a = new Byte((byte) 0);
      Byte b = new Byte((byte) 1);
      Byte c = new Byte(Byte.MAX_VALUE);
      Byte d = new Byte((byte) -1);
      Byte e = new Byte(Byte.MIN_VALUE);

      harness.check (a.hashCode(), 0);
      harness.check (b.hashCode(), 1);
      harness.check (c.hashCode(), 127);
      harness.check (d.hashCode(), -1);
      harness.check (e.hashCode(), -128);
    }
}
