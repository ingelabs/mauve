// Copyright (c) 1998  Cygnus Solutions

// Written by Anthony Green <green@cygnus.com>

package gnu.testlet;

public abstract class Testlet
{
  public abstract void test ();

  public String description ()
    {
      return "A testlet with no description.";
    }
}
