// Copyright (c) 1998  Cygnus Solutions
// Written by Tom Tromey <tromey@cygnus.com>

// This file is part of Mauve.

// Mauve is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.

// Mauve is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with Mauve; see the file COPYING.  If not, write to
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.  */

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

  // These methods are like the above, but checkpoint first.
  public void check (boolean result, String name)
    {
      checkPoint (name);
      check (result);
    }
  public void check (Object result, Object expected, String name)
    {
      checkPoint (name);
      check (result, expected);
    }
  public void check (int result, int expected, String name)
    {
      checkPoint (name);
      check (result, expected);
    }
  public void check (long result, long expected, String name)
    {
      checkPoint (name);
      check (result, expected);
    }

  // This returns the top level source directory.  It might be a
  // relative path.  This can be used to locate data files and the
  // like.
  public abstract String getSourceDirectory ();

  // This can be used to mark a known place in a testlet.  It is
  // useful if you have a large number of tests -- it makes it easier
  // to find a failing test in the source code.
  public abstract void checkPoint (String name);

  // This will print a message when in verbose mode.
  public abstract void verbose (String message);

  // These will print a message when in debug mode.  In the Throwable
  // case, what is printed is the stack trace.
  public abstract void debug (String message);
  public abstract void debug (String message, boolean newline);
  public abstract void debug (Throwable ex);
  public abstract void debug (Object[] o, String desc);
}
