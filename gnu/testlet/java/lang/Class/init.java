// Tags: JDK1.1
//  
// Copyright (C) 2004, Free Software Foundation, Inc.
// Contributed by Mark J. Wielaard (mark@klomp.org)
//   
// This file is part of Mauve.
//    
// Mauve is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.
//     
// Mauve is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//      
// You should have received a copy of the GNU General Public License
// along with Mauve; see the file COPYING.  If not, write to
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.

package gnu.testlet.java.lang.Class;

import gnu.testlet.*;
import java.lang.reflect.*;
 
public class init implements Testlet
{
  static boolean initI = false;
  static boolean initC = false;
  static boolean invokedM = false;

  interface I
  {
    static long l = init.initI();
    void m();
  }

  static class C implements I
  {
    static long l = init.initC();
    public void m()
    {
      invokedM = true;
    }
  }

  public void test(TestHarness h)
  {
    try
      {
	// Sanity checks.
	h.check(!initI);
	h.check(!initC);
	h.check(!invokedM);
	
	// Should no initialize any class.
	Class i = new I[0].getClass().getComponentType();
	h.check(!initC);
	h.check(!initI);
	
	// Still should not initialize anything
	Method m = i.getDeclaredMethod("m", null);
	h.check(!initC);
	h.check(!initI);
	
	// Finally C gets initialized. But note that I does not!
	Object o = new C();
	h.check(initC);
	h.check(!initI);
	
	// And finally also I gets initialized and m gets invoked.
	m.invoke(o, null);
	h.check(initI);
	h.check(invokedM);
      }
    catch (NoSuchMethodException nsme)
      {
	h.debug(nsme);
	h.check(false);
      }
    catch (InvocationTargetException ite)
      {
	h.debug(ite);
	h.check(false);
      }
    catch (IllegalAccessException iae)
      {
	h.debug(iae);
	h.check(false);
      }
  }

  static long initI()
  {
    initI = true;
    return 5;
  }

  static long initC()
  {
    initC = true;
    return 5;
  }
}
