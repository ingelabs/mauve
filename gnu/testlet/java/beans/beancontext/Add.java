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

import java.beans.PropertyVetoException;

import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextChildSupport;
import java.beans.beancontext.BeanContextSupport;

public class Add
  implements Testlet
{

  private static BeanContextSupport context = new BeanContextSupport();
  private static BeanContextChild child = new BeanContextChildSupport();
  private static BeanContextChild brokenChild = new BeanContextChildSupport()
    {
      public void setBeanContext(BeanContext c)
	throws PropertyVetoException
      {
	throw new PropertyVetoException("I don't like you.", null);
      }
    };

  public void test(TestHarness h)
  {
    /* Check child is contextless */
    h.check(child.getBeanContext() == null);
    /* Check initial empty status of context */
    h.check(context.isEmpty());
    h.check(context.size() == 0);
    /* Add child */
    h.check(context.add(child));
    /* Check child is added to context */
    h.check(context.isEmpty() == false);
    h.check(context.size() == 1);
    /* Check child now knows about context */
    h.check(child.getBeanContext() == context);
    /* Check that context maintains set semantics */
    h.check(context.add(child) == false);
    h.check(context.size() == 1);
    /* Check that context catches null */
    try
      {
	context.add(null);
	h.check(false);
      }
    catch (Exception e)
      {
	h.check(e instanceof IllegalArgumentException);
      }
    /* Check correct handling of veto from child */
    try
      {
	context.add(brokenChild);
	h.check(false);
      }
    catch (Exception e)
      {
	h.check(e instanceof IllegalStateException);
	/* Check that child has not been added */
	h.check(context.size() == 1);
      }	
  }

}

