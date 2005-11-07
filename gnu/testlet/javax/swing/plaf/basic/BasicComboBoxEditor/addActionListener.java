// Tags: JDK1.2

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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

package gnu.testlet.javax.swing.plaf.basic.BasicComboBoxEditor;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
* Some tests for the addActionListener() method in the 
* {@link BasicComboBoxEditor} class.
*/
public class addActionListener implements Testlet, ActionListener
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    BasicComboBoxEditor e = new BasicComboBoxEditor();
    JTextField tf = (JTextField) e.getEditorComponent();
    ActionListener[] listeners = tf.getActionListeners();
    harness.check(listeners.length, 0);
    
    e.addActionListener(this);
    listeners = tf.getActionListeners();
    harness.check(listeners.length, 1);
    harness.check(listeners[0], this);
    
    // try null 
    e.addActionListener(null);
    listeners = tf.getActionListeners();
    harness.check(listeners.length, 1);    
  }
  
  public void actionPerformed(ActionEvent e)
  {  
  }
  
}
