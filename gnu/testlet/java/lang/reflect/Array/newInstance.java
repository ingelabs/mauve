// Tags: JDK1.1

// Copyright (C) 2000 Red Hat, Inc.

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

package gnu.testlet.java.lang.reflect.Array;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.lang.reflect.Array;

public class newInstance implements Testlet
{
  public void test (TestHarness harness)
  {
    int val = 0;
    try
      {
	Object x = Array.newInstance (Void.TYPE, 10);
	val = 1;
      }
    catch (IllegalArgumentException iae)
      {
	val = 2;
      }
    catch (Throwable t)
      {
	val = 3;
      }

    harness.check (val, 2);
  }
}
