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

package gnu.testlet.javax.swing.plaf.metal.MetalBorders.ToolBarBorder;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Insets;

import javax.swing.JToolBar;
import javax.swing.plaf.metal.MetalBorders.ToolBarBorder;

/**
* Some tests for the getBorderInsets() method in the {@link ToolBarBorder} 
* class.
*/
public class getBorderInsets implements Testlet
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    test1(harness);
    test2(harness);
  }
  
  public void test1(TestHarness harness)      
  {
    harness.checkPoint("getBorderInsets(Component)");
    ToolBarBorder b = new ToolBarBorder();
    JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
    Insets insets1 = b.getBorderInsets(tb);
    harness.check(insets1, new Insets(2, 16, 2, 2));
    tb.setOrientation(JToolBar.VERTICAL);
    Insets insets2 = b.getBorderInsets(tb);
    harness.check(insets2, new Insets(16, 2, 2, 2));
  }

  public void test2(TestHarness harness)      
  {
    harness.checkPoint("getBorderInsets(Component, Insets)");
    JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
    ToolBarBorder b = new ToolBarBorder();
    Insets insets = b.getBorderInsets(tb, new Insets(1, 2, 3, 4));
    harness.check(insets, new Insets(2, 16, 2, 2));
    tb.setOrientation(JToolBar.VERTICAL);
    insets = b.getBorderInsets(tb, new Insets(1, 2, 3, 4));
    harness.check(insets, new Insets(16, 2, 2, 2));
    
    boolean pass = false;
    try
    {
      b.getBorderInsets(null, null);
    }
    catch (NullPointerException e)
    {
      pass = true;
    }
    harness.check(pass);
  }
  
}
