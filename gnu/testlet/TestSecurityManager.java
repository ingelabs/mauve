// Copyright (c) 2003  Red Hat, Inc.
// Written by Tom Tromey <tromey@redhat.com>

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

package gnu.testlet;

import java.security.Permission;

public class TestSecurityManager extends SecurityManager
{
  public boolean runChecks = true;

  public void setRunChecks(boolean runChecks)
  {
    this.runChecks = runChecks;
  }

  public void checkPermission(Permission perm)
  {
    if (runChecks && ! checkContext())
      super.checkPermission(perm);
  }

  public void checkPermission(Permission perm, Object context)
  {
    if (runChecks && ! checkContext())
      super.checkPermission(perm, context);
  }

  private boolean checkContext()
  {
    Class[] stack = getClassContext();
    // stack[0] is this class.
    // stack[1] is another method in TestSecurityManager.
    // stack[2] is the class that inspired the security check.
    // We apply a fairly simple test.
    // First, we skip any frames after stack[2] that come from the
    // same class as stack[2].  This lets us skip implementation
    // details of that class.  Then if the next stack element is
    // a testlet, we fail.  Otherwise, we pass.  This way things like
    // class initializers automatically pass.
    // This is a bogus approach.  More work here is needed.
    int i;
    for (i = 3; i < stack.length && stack[i] == stack[2]; ++i)
      ;
    return (i >= stack.length
	    || stack[i].getName().indexOf("gnu.testlet") == -1);
  }
}
