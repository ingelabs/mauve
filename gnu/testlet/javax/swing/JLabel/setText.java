// Tags: JDK1.2
// Uses: TestLabel

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

package gnu.testlet.javax.swing.JLabel;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class setText implements Testlet
{

  public void test(TestHarness harness)
  {
    testRepaint(harness);
    testRevalidate(harness);
  }

  /**
   * Tests if setText triggers a repaint. setText should trigger a
   * repaint() call whenever the actual value of the property changes.
   *
   * @param harness the test harness to use
   */
  private void testRepaint(TestHarness harness)
  {
    TestLabel c = new TestLabel();
    // Set to null, so that we know the state.
    c.setText(null);
    c.repaintCalled = false;
    // Change state and check if repaint is called.
    c.setText("Test1");
    harness.check(c.repaintCalled, true);
    // Don't change state.
    c.repaintCalled = false;
    c.setText("Test1");
    harness.check(c.repaintCalled, false);
    // Change state and check if repaint is called.
    c.repaintCalled = false;
    c.setText("Text2");
    harness.check(c.repaintCalled, true);
    // Don't change state.
    c.repaintCalled = false;
    c.setText("Text2");
    harness.check(c.repaintCalled, false);
  }

  /**
   * Tests if setEnabled triggers a revalidate.
   *
   * @param harness the test harness to use
   */
  private void testRevalidate(TestHarness harness)
  {
    TestLabel c = new TestLabel();
    // Set to false, so that we know the state.
    c.setText(null);
    c.revalidateCalled = false;
    // Change state and check if repaint is called.
    c.setText("Test1");
    harness.check(c.revalidateCalled, true);
    // Don't change state.
    c.revalidateCalled = false;
    c.setText("Test1");
    harness.check(c.revalidateCalled, false);
    // Change state and check if repaint is called.
    c.revalidateCalled = false;
    c.setText("Test2");
    harness.check(c.revalidateCalled, true);
    // Don't change state.
    c.revalidateCalled = false;
    c.setText("Test2");
    harness.check(c.revalidateCalled, false);
  }

}
