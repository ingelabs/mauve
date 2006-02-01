// Tags: JDK1.5

// Copyright (C) 2005, 2006 David Gilbert <david.gilbert@object-refinery.com>

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

package gnu.testlet.javax.swing.plaf.metal.MetalComboBoxUI;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalComboBoxUI;

/**
 * Some checks for the getPreferredSize() method in the 
 * {@link MetalComboBoxUI} class.  
 */
public class getPreferredSize implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)  
  {
    testNonEditable(harness);
    testEditable(harness);
  }
  
  private void testNonEditable(TestHarness harness)  
  {
    harness.checkPoint("testNonEditable()");
    JComboBox cb = new JComboBox();
    MyMetalComboBoxUI ui = new MyMetalComboBoxUI();
    cb.setUI(ui);
    Insets insets = ui.getArrowButton().getInsets();
    int additionalWidth = insets.left + insets.right + 2;
    int additionalHeight = insets.top + insets.bottom + 2;
    FontMetrics fm = cb.getFontMetrics(cb.getFont());
    
    // the following width calculation is a guess.  We know the value
    // depends on the font size, and that it is relatively small, so after
    // trying out a few candidates this one seems to give the right result
    int width = fm.charWidth(' ') + additionalWidth + 1 + fm.getHeight();
    int height = fm.getHeight() + additionalHeight;
    harness.check(ui.getPreferredSize(cb), new Dimension(width, height));
                   // the width is the display width plus the button width and
                   // the button width is equal to 'height'
    
    cb.setModel(new DefaultComboBoxModel(new Object[] {"X"}));
    width = fm.charWidth('X') + additionalWidth + 1 + fm.getHeight();
    harness.check(ui.getPreferredSize(cb), new Dimension(width, height));
    
    cb.setPrototypeDisplayValue("XX");    
    width = fm.stringWidth("XX") + additionalWidth + 1 + fm.getHeight();
    harness.check(ui.getPreferredSize(cb), new Dimension(width, height));
    
  }

  private void testEditable(TestHarness harness)
  {
    harness.checkPoint("testEditable()");
    JComboBox cb = new JComboBox();
    MyMetalComboBoxUI ui = new MyMetalComboBoxUI();
    cb.setUI(ui);
    JTextField tf = (JTextField) cb.getEditor().getEditorComponent();
    cb.setEditable(true);
    Insets tfInsets = tf.getInsets();
    System.out.println("tfInsets = " + tfInsets);
    FontMetrics fm = cb.getFontMetrics(cb.getFont());
    int height = fm.getHeight() + tfInsets.top + tfInsets.bottom + 5;
    int width = fm.stringWidth("m") * tf.getColumns() + tfInsets.left 
        + tfInsets.right + height + 3; 
    harness.check(ui.getPreferredSize(cb), new Dimension(width, height));    
    cb.setModel(new DefaultComboBoxModel(new Object[] {"X"}));
    harness.check(ui.getPreferredSize(cb), new Dimension(width, height));        
    cb.setPrototypeDisplayValue("XX");    
    harness.check(ui.getPreferredSize(cb), new Dimension(width, height));
    
    // repeat the tests with a different font
    JComboBox cb2 = new JComboBox();
    MyMetalComboBoxUI ui2 = new MyMetalComboBoxUI();
    cb2.setUI(ui2);
    JTextField tf2 = (JTextField) cb2.getEditor().getEditorComponent();
    cb2.setEditable(true);
    Insets tfInsets2 = tf2.getInsets();
    cb2.setFont(new Font("Dialog", Font.PLAIN, 24));
    FontMetrics fm2 = cb2.getFontMetrics(cb2.getFont());
    int height2 = fm2.getHeight() + tfInsets2.top + tfInsets2.bottom + 5;
    int width2 = fm2.stringWidth("m") * tf2.getColumns() + tfInsets2.left 
        + tfInsets2.right + height2 + 3; 
    harness.check(ui2.getPreferredSize(cb2), new Dimension(width2, height2));    
    cb2.setModel(new DefaultComboBoxModel(new Object[] {"X"}));
    harness.check(ui2.getPreferredSize(cb2), new Dimension(width2, height2));        
    cb2.setPrototypeDisplayValue("XX");    
    harness.check(ui2.getPreferredSize(cb2), new Dimension(width2, height2));
  }
  
}
