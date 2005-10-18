// Tags: JDK1.2
// Uses: TestComponent

// Copyright (C) 2005 Roman Kennke <kennke@aicas.com>

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

package gnu.testlet.javax.swing.JComponent;

import java.awt.Dimension;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Tests if setPreferredSize works correctly.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class setPreferredSize implements Testlet
{
  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    testRepaint(harness);
    testRevalidate(harness);
  }

  /**
   * Tests if setPreferredSize triggers a repaint.
   *
   * @param harness the test harness to use
   */
  private void testRepaint(TestHarness harness)
  {
    Dimension s1 = new Dimension(100, 100);
    Dimension s2 = new Dimension(200, 200);
    TestComponent c = new TestComponent();
    // Set to s1, so that we know the state.
    c.setPreferredSize(s1);
    c.repaintCalled = false;
    // Change state and check if repaint is called.
    c.setPreferredSize(s2);
    harness.check(c.repaintCalled, false);
    // Don't change state.
    c.repaintCalled = false;
    c.setPreferredSize(s2);
    harness.check(c.repaintCalled, false);
    // Change state and check if repaint is called.
    c.repaintCalled = false;
    c.setPreferredSize(s1);
    harness.check(c.repaintCalled, false);
    // Don't change state.
    c.repaintCalled = false;
    c.setPreferredSize(s1);
    harness.check(c.repaintCalled, false);
  }

  /**
   * Tests if setPreferredSize triggers a revalidate.
   *
   * @param harness the test harness to use
   */
  private void testRevalidate(TestHarness harness)
  {
    Dimension s1 = new Dimension(100, 100);
    Dimension s2 = new Dimension(200, 200);
    TestComponent c = new TestComponent();
    // Set to s1, so that we know the state.
    c.setPreferredSize(s1);
    c.revalidateCalled = false;
    // Change state and check if repaint is called.
    c.setPreferredSize(s2);
    harness.check(c.revalidateCalled, false);
    // Don't change state.
    c.revalidateCalled = false;
    c.setPreferredSize(s2);
    harness.check(c.revalidateCalled, false);
    // Change state and check if repaint is called.
    c.revalidateCalled = false;
    c.setPreferredSize(s1);
    harness.check(c.revalidateCalled, false);
    // Don't change state.
    c.revalidateCalled = false;
    c.setPreferredSize(s1);
    harness.check(c.revalidateCalled, false);
  }
}
