// Copyright (c) 1998  Cygnus Solutions

// Written by Tom Tromey <tromey@cygnus.com>

// KNOWN BUGS:
// * Current usage is bogus.  Should scan directory tree for `*.java'
//   files and read them.  A few things here:
//   - should examine Tags and omit files we don't care about
//   - should look for /*{ ... }*/ and treat contents as expected
//     output of test.  In this case we should redirect System.out
//     to a temp file we create.

package gnu.testlet;
import java.io.*;

public class SimpleTestHarness extends TestHarness
{
  private int count = 0;
  private int failures = 0;
  private int total = 0;
  private boolean verbose = false; // FIXME: no way to set this.
  private String description;
  private String last_check;
  private File srcdir;

  public void check (boolean result)
    {
      String d = (description
		  + ((last_check == null) ? "" : (": " + last_check))
		  + " (number " + (count + 1) + ")");
      if (! result)
	{
	  System.out.println("FAIL: " + d);
	  ++failures;
	}
      else if (verbose)
	{
	  System.out.println("PASS: " + d);
	}
      ++count;
      ++total;
    }

  public File getSourceDirectory ()
    {
      return srcdir;
    }

  public void checkPoint (String name)
    {
      last_check = name;
      count = 0;
    }

  private void runtest (String name)
    {
      // Try to ensure we start off with a reasonably clean slate.
      System.gc();
      System.runFinalization();

      checkPoint (null);

      Testlet t = null;
      try
	{
	  Class k = Class.forName (name);
	  t = (Testlet) (k.newInstance());
	}
      catch (Throwable ex)
	{
	  System.out.println ("FAIL: uncaught exception loading " + name
			      + ": " + ex.toString());
	  ++failures;
	  ++total;
	}

      if (t != null)
	{
	  description = name;
	  try
	    {
	      t.test (this);
	    }
	  catch (Throwable ex)
	    {
	      System.out.println ("FAIL: " + description
				  + ": uncaught exception at number "
				  + (count + 1)
				  + ": " + ex.toString());
	      ++failures;
	      ++total;
	    }
	}
    }

  private int done ()
    {
      System.out.println(failures + " of " + total + " tests failed");
      return failures > 0 ? 1 : 0;
    }

  private SimpleTestHarness (String srcdir)
    {
      this.srcdir = new File (srcdir);
    }

  // Each argument is the name of a test to run.  FIXME.
  public static void main (String[] args)
    {
      SimpleTestHarness harness = new SimpleTestHarness (args[0]);
      BufferedReader r
	= new BufferedReader (new InputStreamReader (System.in));
      while (true)
	{
	  String cname = null;
	  try
	    {
	      cname = r.readLine ();
	    }
	  catch (IOException x)
	    {
	      // Nothing.
	    }
	  if (cname == null)
	    break;
	  if (cname.endsWith ("~"))
	    {
	      // This means the file is a helper and not a test.
	      continue;
	    }
	  harness.runtest (cname);
	}

      System.exit(harness.done());
    }
}
