// Tags: JDK1.4

// Copyright (C) 2011 Pavel Tisnovsky <ptisnovs@redhat.com>

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
// the Free Software Foundation, Inc., 51 Franklin Street,
// Fifth Floor, Boston, MA 02110-1301 USA.

package gnu.testlet.java.awt.GridLayout;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.GridLayout;

/**
 * Basic checks for the {@link GridLayout#getColumns()} method in the {@link GridLayout} class.  
 */
public class getColumns implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)
  {
    GridLayout layout = new GridLayout();
    harness.check(layout.getColumns(), 0);
    layout.setColumns(10);
    harness.check(layout.getColumns(), 10);
    layout.setColumns(0);
    harness.check(layout.getColumns(), 0);
    layout.setColumns(-10);
    harness.check(layout.getColumns(), -10);

    layout = new GridLayout(10, 20);
    harness.check(layout.getColumns(), 20);
  }

}
