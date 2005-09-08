// Tags: JDK1.2

// Copyright (C) 2005 Roman Kennke (kennke@aicas.com)

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
// Boston, MA 02111-1307, USA.
package gnu.testlet.javax.swing.text.GapContent;

import javax.swing.text.BadLocationException;
import javax.swing.text.GapContent;
import javax.swing.text.Position;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Tests for correct behaviour of the GapContent Positions.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class PositionTest implements Testlet
{

  /**
   * Starts the test run.
   *
   * @param harness the test harness
   */
  public void test(TestHarness harness)
  {
    testSimple(harness);
  }

  /**
   * This creates two positions in an empty GapContent, one at
   * 0 and one at 1 (start and end respectivly). Then it inserts
   * content and checks if the positions adapt correctly. After
   * that the content is removed again and the positions are checked.
   *
   * @param harness the test harness
   */
  void testSimple(TestHarness harness)
  {
    GapContent c = new GapContent();
    try
      {
        Position p1 = c.createPosition(0);
        Position p2 = c.createPosition(1);
        harness.check(p1.getOffset(), 0);
        harness.check(p2.getOffset(), 1);

        c.insertString(0, "hello");
        harness.check(p1.getOffset(), 0);
        harness.check(p2.getOffset(), 6);

        c.remove(0, 5);
        harness.check(p1.getOffset(), 0);
        harness.check(p2.getOffset(), 1);
      }
    catch (BadLocationException ex)
      {
        harness.fail("BadLocationException");
        harness.debug(ex);
      }
  }
}
