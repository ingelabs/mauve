// Tags: JDK1.2

// Copyright (C) 2005, 2006, David Gilbert <david.gilbert@object-refinery.com>

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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

package gnu.testlet.javax.swing.plaf.metal.MetalScrollBarUI;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalScrollBarUI;

/**
 * Some checks for the constructor in the {@link MetalScrollBarUI} class.  
 */
public class constructor implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)  
  {
    // use a known look and feel
    try
    {
      UIManager.setLookAndFeel(new MetalLookAndFeel());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    MyMetalScrollBarUI ui = new MyMetalScrollBarUI();
    harness.check(ui.getTrackBounds(), null);
    harness.check(ui.getThumbBounds(), null);
    harness.check(ui.getScrollBarWidthField(), 0);
    
    JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
    scrollBar.setUI(ui);
    harness.check(ui.getTrackBounds(), new Rectangle(0, 0, 0, 0));
    harness.check(ui.getThumbBounds(), new Rectangle(0, 0, 0, 0));
    
    scrollBar.setBounds(0, 0, 100, 20);
    harness.check(ui.getTrackBounds(), new Rectangle(0, 0, 0, 0));
    harness.check(ui.getThumbBounds(), new Rectangle(0, 0, 0, 0));
    
    JPanel panel = new JPanel(new BorderLayout());
    panel.setSize(100, 20);
    panel.add(scrollBar);
    panel.doLayout();
    harness.check(ui.getTrackBounds(), new Rectangle(0, 0, 0, 0));
    harness.check(ui.getThumbBounds(), new Rectangle(0, 0, 0, 0));
    
    ui.layoutContainer(scrollBar);
    harness.check(ui.getTrackBounds(), new Rectangle(15, 0, 69, 20));    
    harness.check(ui.getThumbBounds(), new Rectangle(15, 0, 17, 20));
  }

}
