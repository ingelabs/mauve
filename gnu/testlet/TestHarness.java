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
      check (result == null ? expected == null : result.equals(expected));
    }
  public void check (long result, long expected)
    {
      check (result == expected);
    }
  public void check (int result, int expected)
    {
      check (result == expected);
    }

  // This returns the top level source directory.  It might be a
  // relative path.  This can be used to locate data files and the
  // like.
  public abstract File getSourceDirectory ();

  // This can be used to mark a known place in a testlet.  It is
  // useful if you have a large number of tests -- it makes it easier
  // to find a failing test in the source code.
  public abstract void checkPoint (String name);
}
