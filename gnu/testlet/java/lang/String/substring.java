package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class substring implements Testlet
{
  public void test (TestHarness harness)
    {
      String b = new String(" abc\tABC 123\t");

      harness.check (b.substring(4), "	ABC 123	");
      harness.check (b.substring(4, b.length() - 5), "	ABC");

      boolean ok;

      ok = false;
      try
	{
	  b.substring(-1);
	}
      catch (StringIndexOutOfBoundsException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = true;
      try
	{
	  b.substring(b.length());
	}
      catch (StringIndexOutOfBoundsException ex)
	{
	  ok = false;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  b.substring(4, -1);
	}
      catch (StringIndexOutOfBoundsException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  b.substring(4, b.length() + 1);
	}
      catch (StringIndexOutOfBoundsException ex)
	{
	  ok = true;
	}
      harness.check (ok);
    }

  public substring ()
    {
    }
}
