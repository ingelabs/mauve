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
  private Test[] hosts = new Test[] {
    new Test("", "", true),
    new Test("localhost", "localhost", true), // XXX need more!
  };

  private Test[] ports = new Test[] {
    // no restriction
    new Test("", "", true),
    new Test("", ":80", true),
    new Test("", ":-80", true),
    new Test("", ":80-", true),
    new Test("", ":70-90", true),
    // one port
    new Test(":80", "", false),
    new Test(":80", ":70", false),
    new Test(":80", ":80", true),
    new Test(":80", ":-80", false),
    new Test(":80", ":80-", false),
    new Test(":80", ":70-90", false),
    new Test(":80", ":80-80", true),
    new Test(":80", ":90-90", false),
    // up to and including x
    new Test(":-80", "", false),
    new Test(":-80", ":70", true),
    new Test(":-80", ":80", true),
    new Test(":-80", ":90", false),
    new Test(":-80", ":-70", true),
    new Test(":-80", ":-80", true),
    new Test(":-80", ":-90", false),
    new Test(":-80", ":70-", false),
    new Test(":-80", ":80-", false),
    new Test(":-80", ":90-", false),
    new Test(":-80", ":60-70", true),
    new Test(":-80", ":70-90", false),
    new Test(":-80", ":90-100", false),
    new Test(":-80", ":70-70", true),
    new Test(":-80", ":80-80", true),
    new Test(":-80", ":90-90", false),
    // x and above
    new Test(":80-", "", false),
    new Test(":80-", ":70", false),
    new Test(":80-", ":80", true),
    new Test(":80-", ":90", true),
    new Test(":80-", ":-70", false),
    new Test(":80-", ":-80", false),
    new Test(":80-", ":-90", false),
    new Test(":80-", ":70-", false),
    new Test(":80-", ":80-", true),
    new Test(":80-", ":90-", true),
    new Test(":80-", ":60-70", false),
    new Test(":80-", ":70-90", false),
    new Test(":80-", ":90-100", true),
    new Test(":80-", ":70-70", false),
    new Test(":80-", ":80-80", true),
    new Test(":80-", ":90-90", true),
    // double-ended range
    new Test(":75-85", "", false),
    new Test(":75-85", ":70", false),
    new Test(":75-85", ":80", true),
    new Test(":75-85", ":90", false),
    new Test(":75-85", ":-70", false),
    new Test(":75-85", ":-80", false),
    new Test(":75-85", ":-90", false),
    new Test(":75-85", ":70-", false),
    new Test(":75-85", ":80-", false),
    new Test(":75-85", ":90-", false),
    new Test(":75-85", ":70-80", false),
    new Test(":75-85", ":75-85", true),
    new Test(":75-85", ":80-90", false),
    new Test(":75-85", ":70-90", false),
    new Test(":75-85", ":70-70", false),
    new Test(":75-85", ":80-80", true),
    new Test(":75-85", ":90-90", false),
    // bit loss
    new Test(":80", ":65616", false), // 65616 & 0xFFFF = 80
    new Test(":80", ":-65456", false), // -65456 & 0xFFFF = 80
    // also 4294967376?
  };
  
  public void test(TestHarness harness)
  {
    harness.checkPoint("hostport checking");
    
    for (int i = 0; i < hosts.length; i++) {
      for (int j = 0; j < ports.length; j++) {
	Test test = new Test(hosts[i], ports[j]);
	
	SocketPermission px = new SocketPermission(test.x, "connect");
	SocketPermission py = new SocketPermission(test.y, "connect");

	harness.check(px.implies(py) == test.expect);
      }
    }

    harness.checkPoint("actions checking");
    for (int i = 1; i < 1 << actions.length; i++) {
      for (int j = 1; j < 1 << actions.length; j++) {
	SocketPermission pi = new SocketPermission("localhost", makeAction(i));
	SocketPermission pj = new SocketPermission("localhost", makeAction(j));

	harness.check(pi.implies(pj) == ((maybeAddResolve(i) & j) == j));
      }
    }
  }

  // stuff for hosts checking
  private static class Test
  {
    String x, y;
    boolean expect;

    Test(String x, String y, boolean expect)
    {
      this.x = x;
      this.y = y;
      this.expect = expect;
    }
    
    Test(Test host, Test port)
    {
      x = host.x + port.x;
      y = host.y + port.y;
      expect = host.expect && port.expect;
    }
  }

  // stuff for actions checking
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
