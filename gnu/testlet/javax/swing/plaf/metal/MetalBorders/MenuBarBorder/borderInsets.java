// Tags: JDK1.4

// Copyright (C) 2005 David Gilbert <david.gilbert@object-refinery.com>

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
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.javax.swing.plaf.metal.MetalBorders.MenuBarBorder;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Insets;

import javax.swing.plaf.metal.MetalBorders.MenuBarBorder;

/**
* Some tests for the borderInsets in the {@link MenuBarBorder} class.
*/
public class borderInsets implements Testlet
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    harness.check(MyMenuBarBorder.getBorderInsets(), new Insets(1, 0, 1, 0));
  }

}