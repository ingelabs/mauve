// Tags: JDK1.0

package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class charAt implements Testlet
{
  public void test (TestHarness harness)
    {
      String b = new String(" abc\tABC 123\t");

      boolean ok;

      ok = false;
      try
	{
	  b.charAt(b.length());
	}
      catch (StringIndexOutOfBoundsException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  b.charAt(-1);
	}
      catch (StringIndexOutOfBoundsException ex)
	{
	  ok = true;
	}
      harness.check (ok);
    }
}
