// $Id$
//
// Copyright (C) 2003, Free Software Foundation, Inc.
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
//
// Tags: JDK1.4

package gnu.testlet.java.security.Security;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Set;

/**
 * Test of <code>getAlgorithms(String)</code> method in {@link Security}.
 *
 * @version $Revision$
 * @see Security#getAlgorithms(String)
 */
public class getAlgorithms extends Provider implements Testlet
{
  getAlgorithms()
  {
    super("getAlgorithms", 1.0, "");

    put("Coffee.Foo", "whatever");

    put("Tea.Bar", "whatever");
    put("Tea.Bar ImplementedIn", "Vapourware");
    put("Tea.Bar MinCapacity", "100");

    put("Tea.Baz", "whatever");
    put("Tea.Baz ImplementedIn", "Vapourware");
    put("Tea.Baz MinCapacity", "100");
  }

  public void test (TestHarness harness)
  {
    harness.checkPoint ("getAlgorithms");

    String signature;
    Set set = new HashSet();

    Security.addProvider(this);

    signature = "getAlgorithms(\"foo\")";
    set = Security.getAlgorithms("foo");
    harness.check(set.size() == 0, signature);

    signature = "getAlgorithms(\"Coffee\")";
    set = Security.getAlgorithms("Coffee");
    harness.check(set.size() == 1, signature);

    signature = "getAlgorithms(\"Tea\")";
    set = Security.getAlgorithms("Tea");
    harness.check(set.size() == 2 && set.contains("BAR") && set.contains("BAZ"),
                  signature);
  }
}
