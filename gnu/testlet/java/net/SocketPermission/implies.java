// Copyright (C) 2006 Red Hat, Inc.
// Written by Gary Benson <gbenson@redhat.com>

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

package gnu.testlet.java.net.SocketPermission;

import java.net.SocketPermission;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class implies implements Testlet
{
  public void test(TestHarness harness)
  {
    harness.checkPoint("action checking");
    for (int i = 1; i < 1 << actions.length; i++) {
      for (int j = 1; j < 1 << actions.length; j++) {
	SocketPermission pi = new SocketPermission("localhost", makeAction(i));
	SocketPermission pj = new SocketPermission("localhost", makeAction(j));

	harness.checkPoint(pi.getActions() + " implies " + pj.getActions());
	
	harness.check(pi.implies(pj) == ((maybeAddResolve(i) & j) == j));
      }
    }
  }

  // stuff for action checking
  private static String[] actions = {"accept", "connect", "listen", "resolve"};
  private static String makeAction(int mask)
  {
    String result = "";
    for (int i = 0; i < actions.length; i++) {
      if ((mask & (1 << i)) != 0) {
	if (result.length() > 0)
	  result += ",";
	result += actions[i];
      }
    }
    return result;
  }
  
  // All other actions imply resolve
  private static int maybeAddResolve(int mask)
  {
    boolean addit = false;
    int addwhat = 0;
    
    for (int i = 0; i < actions.length; i++) {
      if (actions[i].equals("resolve"))
	addwhat = 1 << i;
      else if ((mask & (1 << i)) != 0)
	addit = true;
    }
    if (addit)
      mask |= addwhat;
    return mask;
  }
}
