// Simple tests of ThreadLocal

// Copyright (C) 2001 Red Hat, Inc.

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
// Boston, MA 02111-1307, USA.

// Tags: JDK1.3

package gnu.testlet.java.lang.ThreadLocal;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class simple extends Thread implements Testlet
{
  public static ThreadLocal maude
    = new ThreadLocal ()
    {
      public Object initialValue ()
      {
	return "Maude";
      }
    };

  public TestHarness myHarness;

  public void run ()
  {
    myHarness.check (maude.get (), "Maude", "Initial value in new thread");
    maude.set ("Wednesday");
    myHarness.check (maude.get (), "Wednesday", "Changed value in new thread");
  }

  public simple (TestHarness harness)
  {
    super ();
    myHarness = harness;
  }

  public simple ()
  {
    super ();
    myHarness = null;
  }

  public void test (TestHarness harness)
  {
    harness.check (maude.get (), "Maude", "Check initial value");

    maude.set ("Liver");
    harness.check (maude.get (), "Liver", "Check changed value");

    try
      {
	simple s = new simple (harness);
	s.start ();
	s.join ();
      }
    catch (InterruptedException _)
      {
      }

    harness.check (maude.get (), "Liver", "Value didn't change");
  }
}
