// Copyright (c) 1998  Cygnus Solutions

// Written by Tom Tromey <tromey@cygnus.com>

package gnu.testlet;

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
}
