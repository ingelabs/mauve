/* isSelectedIndex.java -- some checks for the isSelectedIndex()
       method in the DefaultListSelectionModel class.
   Copyright (C) 2006 David Gilbert <david.gilbert@object-refinery.com>
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

// Tags: JDK1.5

package gnu.testlet.javax.swing.DefaultListSelectionModel;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.DefaultListSelectionModel;

public class isSelectedIndex implements Testlet
{
  public void test(TestHarness harness)
  {
    DefaultListSelectionModel m = new DefaultListSelectionModel();
    harness.check(m.isSelectedIndex(99), false);
    m.addSelectionInterval(99, 99);
    harness.check(m.isSelectedIndex(99), true);
    m.clearSelection();
    harness.check(m.isSelectedIndex(99), false);
    
    // try a negative index
    harness.check(m.isSelectedIndex(-1), false);
    harness.check(m.isSelectedIndex(-2), false);
  }
}
