// Copyright (c) 1998  Cygnus Solutions

// Written by Tom Tromey <tromey@cygnus.com>

package gnu.testlet;

import java.io.File;

public abstract class TestHarness
{
  // These methods are used to determine whether a test has passed.
  public abstract void check (boolean result);
  public void check (Object result, Object expected)
    {
      boolean ok = (result == null
		    ? expected == null
		    : result.equals(expected));
      check (ok);
      if (! ok)
	debug ("got " + result + " but expected " + expected);
    }
  public void check (long result, long expected)
    {
      boolean ok = (result == expected);
      check (ok);
      if (! ok)
	debug ("got " + result + " but expected " + expected);
    }
  public void check (int result, int expected)
    {
      boolean ok = (result == expected);
      check (ok);
      if (! ok)
	debug ("got " + result + " but expected " + expected);
    }

  // This returns the top level source directory.  It might be a
  // relative path.  This can be used to locate data files and the
  // like.
  public abstract File getSourceDirectory ();

  // This can be used to mark a known place in a testlet.  It is
  // useful if you have a large number of tests -- it makes it easier
  // to find a failing test in the source code.
  public abstract void checkPoint (String name);

  // This will print a message when in verbose mode.
  public abstract void verbose (String message);

  // These will print a message when in debug mode.  In the Throwable
  // case, what is printed is the stack trace.
  public abstract void debug (String message);
  public abstract void debug (Throwable ex);
}
