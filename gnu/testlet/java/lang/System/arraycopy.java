package gnu.testlet.java.lang.System;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class arraycopy implements Testlet
{
  public void fill (int[] a)
  {
    for (int i = 0; i < a.length; ++i)
      a[i] = i;
  }

  public void check (TestHarness harness, int[] expect, int[] result)
    {
      boolean ok = expect.length == result.length;
      for (int i = 0; ok && i < expect.length; ++i)
	if (expect[i] != result[i])
	  ok = false;
      harness.check (ok);
    }

  public Object copy (Object from, int a, Object to, int b, int c)
    {
      try
	{
	  System.arraycopy (from, a, to, b, c);
	}
      catch (ArrayStoreException xa)
	{
	  return "caught ArrayStoreException";
	}
      catch (IndexOutOfBoundsException xb)
	{
	  return "caught IndexOutOfBoundsException";
	}
      catch (NullPointerException xc)
	{
	  return "caught NullPointerException";
	}
      catch (Throwable xd)
	{
	  return "caught unexpected exception";
	}

      return null;
    }

  public void test (TestHarness harness)
    {
      int[] x, y;

      x = new int[5];
      y = new int[5];
      fill (x);

      harness.check (copy (x, 0, y, 0, x.length), null);
      int[] one = { 0, 1, 2, 3, 4 };
      check (harness, y, one);

      harness.check (copy (x, 1, y, 0, x.length - 1), null);
      harness.check (copy (x, 0, y, x.length - 1, 1), null);
      int[] two = { 1, 2, 3, 4, 0 };
      check (harness, y, two);

      Object[] z = new Object[5];
      harness.check (copy (x, 0, z, 0, x.length),
		     "caught ArrayStoreException");

      harness.check (copy (x, 0, y, 0, -23),
		     "caught IndexOutOfBoundsException");

      harness.check (copy (null, 0, y, 0, -23),
		     "caught NullPointerException");

      harness.check (copy (x, 0, null, 0, -23),
		     "caught NullPointerException");

      String q = "metonymy";
      harness.check (copy (q, 0, y, 0, 19),
		     "caught ArrayStoreException");

      harness.check (copy (x, 0, q, 0, 19),
		     "caught ArrayStoreException");

      double[] v = new double[5];
      harness.check (copy (x, 0, v, 0, 5),
		     "caught ArrayStoreException");

      harness.check (copy (x, -1, y, 0, 1),
		     "caught IndexOutOfBoundsException");

      harness.check (copy (x, 0, z, 0, x.length),
		     "caught ArrayStoreException");

      harness.check (copy (x, 0, y, -1, 1),
		     "caught IndexOutOfBoundsException");

      harness.check (copy (x, 3, y, 0, 5),
		     "caught IndexOutOfBoundsException");

      harness.check (copy (x, 0, y, 3, 5),
		     "caught IndexOutOfBoundsException");

      Object[] w = new Object[5];
      String[] ss = new String[5];
      for (int i = 0; i < 5; ++i)
	{
	  w[i] = i + "";
	  ss[i] = (i + 23) + "";
	}
      w[3] = new Integer (23);

      harness.check (copy (w, 0, ss, 0, 5),
		     "caught ArrayStoreException");
      harness.check (ss[0], "0");
      harness.check (ss[1], "1");
      harness.check (ss[2], "2");
      harness.check (ss[3], "26");
      harness.check (ss[4], "27");
    }

  public arraycopy ()
    {
    }
}
