package gnu.testlet.java.lang.Short;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class hash implements Testlet
{
  public void test (TestHarness harness)
    {
      Short a = new Short((short) 0);
      Short b = new Short((short) 1);
      Short c = new Short((short) -1);
      Short d = new Short(Short.MAX_VALUE);
      Short e = new Short(Short.MIN_VALUE);

      harness.check (a.hashCode(), 0);
      harness.check (b.hashCode(), 1);
      harness.check (c.hashCode(), -1);
      harness.check (d.hashCode(), 32767);
      harness.check (e.hashCode(), -32768);
    }

  public hash ()
    {
    }
}
