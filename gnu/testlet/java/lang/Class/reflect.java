// Tags: JDK1.1
// Uses: rf_help

// Copyright (C) 2000 Cygnus Solutions

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

package gnu.testlet.java.lang.Class;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.lang.reflect.*;

public class reflect implements Testlet
{
  public Class getClass (String name)
  {
    // gcj can't handle `.class' notation yet.
    Class k = null;
    try
      {
	k = Class.forName (name);
      }
    catch (Throwable _)
      {
	// Nothing.
      }
    return k;
  }

  public Object getCons (Class k, Class[] types, boolean decl)
  {
    try
      {
	return (decl
		? k.getDeclaredConstructor (types)
		: k.getConstructor (types));
      }
    catch (Throwable _)
      {
	return _;
      }
  }

  public Object getField (Class k, String name, boolean decl)
  {
    try
      {
	return (decl
		? k.getDeclaredField (name)
		: k.getField (name));
      }
    catch (Throwable _)
      {
	return _;
      }
  }

  public void test (TestHarness harness)
  {
    Class reflect_class = getClass ("gnu.testlet.java.lang.Class.reflect");
    Class rf_help_class = getClass ("gnu.testlet.java.lang.Class.rf_help");

    Class[] ptz = new Class[0];
    Class[] pt1 = new Class[1];
    pt1[0] = Integer.TYPE;

    harness.checkPoint ("getConstructor");
    // This class doesn't have an explicit constructor, so we make
    // sure that the implicit one can be found.
    Object cons = getCons (reflect_class, ptz, false);
    harness.check(cons instanceof Constructor);

    cons = getCons (reflect_class, pt1, false);
    harness.check(cons instanceof NoSuchMethodException);

    cons = getCons (rf_help_class, ptz, false);
    harness.check(cons instanceof NoSuchMethodException);

    harness.checkPoint("getConstructors");
    try
      {
	Constructor[] cls = reflect_class.getConstructors();
	harness.check(cls.length, 1);
	harness.check(cls[0].getName(), "gnu.testlet.java.lang.Class.reflect");
      }
    catch (SecurityException se)
      {
	// One per check above.
	harness.check(false);
	harness.check(false);
      }

    try
      {
	Constructor[] cls = rf_help_class.getConstructors();
	harness.check(cls.length, 0);
      }
    catch (SecurityException se)
      {
	// One per check above.
	harness.check(false);
      }

    harness.checkPoint ("getDeclaredConstructor");
    cons = getCons (rf_help_class, ptz, true);
    harness.check(cons instanceof Constructor);
    cons = getCons (rf_help_class, pt1, true);
    harness.check(cons instanceof NoSuchMethodException);

    harness.checkPoint("getDeclaredConstructors");
    try
      {
	Constructor[] cls = reflect_class.getDeclaredConstructors();
	harness.check(cls.length, 1);
	harness.check(cls[0].getName(), "gnu.testlet.java.lang.Class.reflect");
      }
    catch (SecurityException se)
      {
	// One per check above.
	harness.check(false);
	harness.check(false);
      }

    try
      {
	Constructor[] cls = rf_help_class.getDeclaredConstructors();
	harness.check(cls.length, 1);
	harness.check(cls[0].getName(), "gnu.testlet.java.lang.Class.rf_help");
      }
    catch (SecurityException se)
      {
	// One per check above.
	harness.check(false);
	harness.check(false);
      }

    harness.checkPoint ("getDeclaredField");
    Object f = getField (rf_help_class, "size", true);
    harness.check(f instanceof Field);
    harness.check(((Field) f).getModifiers(), Modifier.PRIVATE);
    f = getField (rf_help_class, "value", true);
    harness.check(f instanceof Field);
    harness.check(((Field) f).getModifiers(), Modifier.STATIC);
  }
}
