// Tags: JDK1.0

package gnu.testlet.java.lang.Character;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class digit implements Testlet
{
  public void test (TestHarness harness)
    {
      harness.check (Character.digit ('9', 10), 9);
      harness.check (Character.digit ('9', 8), -1);
      harness.check (Character.digit ('A', 16), 10);
      harness.check (Character.digit ('a', 11), 10);
      harness.check (Character.digit ((char) ('Z' + 1), 36), -1);
      harness.check (Character.digit ('Z', 36), 35);
      harness.check (Character.digit ('\u0be7', 2), 1);
      harness.check (Character.digit ('\u0f27', 19), 7);
      harness.check (Character.digit ('0', 99), -1);
      harness.check (Character.digit ('0', -5), -1);
      harness.check (Character.digit ('\uffda', 10), -1);
      harness.check (Character.digit ('\u0000', 10), -1);
      harness.check (Character.digit ('A', 10), -1);
      harness.check (Character.digit ('y', 36), 34);
      harness.check (Character.digit ('\u2070', 36), -1);
    }
}
