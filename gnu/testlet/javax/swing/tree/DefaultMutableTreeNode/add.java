// Tags: JDK1.2

// Copyright (C) 2004  Michael Koch <konqueror@gmx.de>

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

package gnu.testlet.javax.swing.tree.DefaultMutableTreeNode;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.tree.*;

public class add implements Testlet
{
  public void test(TestHarness h)
  {
    DefaultMutableTreeNode A, B, C;

    A = new DefaultMutableTreeNode();
    B = new DefaultMutableTreeNode();
    C = new DefaultMutableTreeNode(null, false);

    boolean ok = false;

    try
      {
        A.add(null);
      }
    catch (IllegalArgumentException e)
      {
        ok = true;
      }

    h.check(ok, "accepts null");

    ok = false;

    try
      {
        C.add(A);
      }
    catch (IllegalStateException e)
      {
        ok = true;
      }

    h.check(ok, "rejects additions");

    A.add(B);
    A.add(C);
    
    h.check(A.isNodeChild(B));
    h.check(A.isNodeChild(C));
    h.check(!B.isNodeChild(C));
    
    B.add(C);

    h.check(A.isNodeChild(B));
    h.check(!A.isNodeChild(C));
    h.check(B.isNodeChild(C));
  }
}