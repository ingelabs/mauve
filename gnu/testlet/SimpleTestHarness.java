// Copyright (c) 1998  Cygnus Solutions

// Written by Tom Tromey <tromey@cygnus.com>

// KNOWN BUGS:
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
  private boolean verbose = false;
  private boolean debug = false;
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

  public void verbose (String message)
    {
      if (verbose)
	System.out.println (message);
    }

  public void debug (String message)
    {
      if (debug)
	System.out.println (message);
    }

  public void debug (Throwable ex)
    {
      if (debug)
	ex.printStackTrace(System.out);
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
	  String d = "FAIL: uncaught exception loading " + name;
	  if (verbose)
	    d += ": " + ex.toString();
	  System.out.println (d);
	  debug (ex);
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
	      String d = ("FAIL: " + description
			  + ": uncaught exception at "
			  + ((last_check == null) ? "" : 
			     ("\"" + last_check + "\""))
			  +" number "
			  + (count + 1));
	      if (verbose)
		d += ": " + ex.toString();
	      System.out.println (d);
	      debug (ex);
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

  private SimpleTestHarness (String srcdir, boolean verbose, boolean debug)
    {
      this.srcdir = new File (srcdir);
      this.verbose = verbose;
      this.debug = debug;
    }

  public static void main (String[] args)
    {
      String srcdir = null;
      boolean verbose = false;
      boolean debug = false;
      int i;

      for (i = 0; i < args.length - 1; i++) 
	{
	  if (args[i].equals("-verbose")) 
	    {
	      verbose = true;
	      continue;
	    }
	  else if (args[i].equals("-debug")) 
	    {
	      debug = true;
	      continue;
	    }
	  else
	    break;
        }
      if (i == args.length - 1)
        srcdir = args[i];

      if (srcdir == null) 
	{
	  System.err.println ("usage: SimpleTestHarness [-verbose] [-debug] SRCDIR");
	  System.exit(1);
	}

      SimpleTestHarness harness
	= new SimpleTestHarness (args[args.length - 1], verbose, debug);

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
