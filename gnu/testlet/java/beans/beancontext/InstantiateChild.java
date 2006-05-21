// Tags: JDK1.2

/*
   Copyright (C) 2006 Andrew John Hughes (gnu_andrew@member.fsf.org)

   This file is part of Mauve.

   Mauve is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   Mauve is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Mauve; see the file COPYING.  If not, write to
   the Free Software Foundation, 59 Temple Place - Suite 330,
   Boston, MA 02111-1307, USA.
*/

package gnu.testlet.java.beans.beancontext;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.beans.beancontext.BeanContextSupport;

public class InstantiateChild
  implements Testlet
{

  private static BeanContextSupport context = new BeanContextSupport();

  public void test(TestHarness h)
  {
    try
      {
	/* Check initial empty status of context */
	h.check(context.isEmpty(), "Empty check");
	h.check(context.size() == 0, "Size of 0 check");
	/* Add child */
	h.check(context.instantiateChild("java.beans.beancontext.BeanContextChildSupport"), 
		"Child instantiated addition check");
	/* Check child is added to context */
	h.check(context.isEmpty() == false, "Non-empty check");
	h.check(context.size() == 1, "Size of 1 check");
      }
    catch (Exception e)
      {
	h.debug(e);
      }
  }
}

