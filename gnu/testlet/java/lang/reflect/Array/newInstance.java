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

    harness.checkPoint("Void.TYPE");
    int val = 0;
    Object x = null;
    try
      {
	x = Array.newInstance (Void.TYPE, 10);
	val = 1;
      }
    catch (IllegalArgumentException iae)
      {
	val = 2;
      }
    catch(NullPointerException npe)
      {
	val = 4;
      }
    catch (Throwable t)
      {
	val = 3;
      }

    //    System.err.println("val is " + val + " x is null: " + (x == null));
    harness.check (val, 1);
    try {
    harness.check (x, null); //The Sun-based JDKs don't return null here
    } catch(InternalError ie)
      {
	harness.check(null, ie);
      }
    /*try {
    System.err.println("x's class: " + x.getClass());
    } catch(Throwable t)
      {
	t.printStackTrace();
      }*/

    harness.checkPoint("Integer.TYPE");

    try
      {
	x = Array.newInstance (Integer.TYPE, 10);
	val = 1;
      }
    catch (IllegalArgumentException iae)
      {
	val = 2;
      }
    catch(NullPointerException npe)
      {
	val = 4;
      }
    catch (Throwable t)
      {
	val = 3;
      }

    harness.check(val, 1);

    val = 999;

    try {
      val = ((int[]) x).length;
    } catch(ClassCastException cce)
      {
	val = 99;
      }

    harness.check(val, 10);

    val = 0;

    try {
      if(x.getClass().getComponentType() == Integer.TYPE)
	{
	  val = 1;
	}
      else
	{
	  /*	  System.err.println("x type is " + x.getClass());
	  System.err.println("Comp type is " + x.getClass().getComponentType());
	  System.err.println("Compare to " + Integer.TYPE);
	  */
	}
    } catch(Throwable t)
      {
	val = 2;
      }

    harness.check(val, 1);

    harness.checkPoint("NegativeArraySize");

    try
      {
	x = Array.newInstance (String.class, -101);
	val = 1;
      }
    catch(NegativeArraySizeException nas)
      {
	val = 2;
      }
    catch(Throwable t)
      {
	val = 3;
      }

    harness.check(val, 2);

    harness.checkPoint("String");

    try
      {
	x = Array.newInstance (String.class, 100);
	val = 1;
      }
    catch (IllegalArgumentException iae)
      {
	val = 2;
      }
    catch(NullPointerException npe)
      {
	val = 4;
      }
    catch (Throwable t)
      {
	val = 3;
      }

    harness.check(val, 1);

    try {
      val = ((String[]) x).length;
    } catch(ClassCastException cce)
      {
	val = 99;
      }

    harness.check(val, 100);

    val = 0;

    try {
      if( x.getClass().getComponentType() == String.class)
	{
	  val = 1;
	}
    } catch(Throwable t)
      {
	val = 2;
      }

    harness.check(val, 1);
    
  }
}
