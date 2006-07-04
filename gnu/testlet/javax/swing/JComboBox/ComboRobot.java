/* ComboRobot.java -- ComboBox test
 Copyright (C) 2006 Audrius Meskauskas
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

// Tags: JDK1.4 GUI


package gnu.testlet.javax.swing.JComboBox;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;
import gnu.testlet.org.omg.CORBA.Asserter;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.BitSet;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * The AWT robot based test for JTree.
 */
public class ComboRobot extends Asserter implements Testlet
{
  /**
   * The tree being tested.
   */
  JComboBox combo = new JComboBox(new String[] {"a", "b", "c", "d", "e", "f"});

  JFrame frame;

  Robot r;

  static Random ran = new Random();

  protected void setUp() throws Exception
  {

    frame = new JFrame();
    frame.getContentPane().add(combo);
    frame.setSize(200, 80);
    frame.setVisible(true);

    r = new Robot();
    r.setAutoDelay(50);
    r.delay(500);
  }

  protected void tearDown() throws Exception
  {
    frame.dispose();
  }

  /**
   * Click the mouse on center of the given component.
   */
  public void click(JComponent c, int x, int y)
  {
    Point p = new Point();
    p.x = x;
    p.y = y;

    SwingUtilities.convertPointToScreen(p, c);
    r.mouseMove(p.x, p.y);
    r.mousePress(InputEvent.BUTTON1_MASK);
    r.mouseRelease(InputEvent.BUTTON1_MASK);
  }

  /**
   * Click the given main databas view cell.
   * 
   * @param row
   *          the row
   * @param column
   *          the column
   */
  public void clickPath(JTree tree, TreePath path)
  {
    Rectangle rect = tree.getPathBounds(path);
    Point p = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
    SwingUtilities.convertPointToScreen(p, tree);
    r.mouseMove(p.x, p.y);
    r.mousePress(InputEvent.BUTTON1_MASK);
    r.delay(50);
    r.mouseRelease(InputEvent.BUTTON1_MASK);
    r.delay(50);
  }


  public void type(int code)
  {
    r.keyPress(code);
    r.keyRelease(code);
  }
  
  public void testCombo() throws Exception
  {
    type(KeyEvent.VK_DOWN);
    type(KeyEvent.VK_ENTER);
    assertEquals("Selecting first component with keyboard", "a", getFocus());

    type(KeyEvent.VK_DOWN);
    type(KeyEvent.VK_DOWN);
    type(KeyEvent.VK_ENTER);
    assertEquals("Selecting second component with keyboard", "b", getFocus());

    type(KeyEvent.VK_DOWN);
    type(KeyEvent.VK_DOWN);
    type(KeyEvent.VK_DOWN);

    type(KeyEvent.VK_ENTER);
    assertEquals("Selecting forth component with keyboard", "d", getFocus());

    for (char i = 'a'; i <= 'f'; i++)
      {
        type(KeyEvent.VK_DOWN);
        type(KeyEvent.VK_A + (i-'a'));
        type(KeyEvent.VK_ENTER);
        assertEquals("Selecting with letter key, " + i,
                     new String(new char[] { i }), getFocus());
      }

    Thread.sleep(50000);
  }
  
  public Object getFocus()
  {
    return combo.getSelectedItem();
  }

  public void test(TestHarness harness)
  {
    try
      {
        h = harness;
        setUp();
        try
          {
            testCombo();
          }
        finally
          {
            tearDown();
          }
      }
    catch (Exception e)
      {
        e.printStackTrace();
        h.fail("Exception: " + e);
      }
  }

}
