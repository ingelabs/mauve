// Tags: JDK1.2

// Copyright (C) 2005 Roman Kennke <kennke@aicas.com>

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

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Tests if GapContent.insertString works correctly.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class insertString implements Testlet
{

  /**
   * A subclass of GapContent that provides access to the protected methods.
   */
  class TestGapContent extends GapContent
  {
    public int getArrayLength()
    {
      return super.getArrayLength();
    }
    public int getTestGapStart()
    {
      return super.getGapStart();
    }
    public int getTestGapEnd()
    {
      return super.getGapEnd();
    }
  }

  /**
   * Start the test run.
   * 
   * @see gnu.testlet.Testlet#test(gnu.testlet.TestHarness)
   */
  public void test(TestHarness harness)
  {
    testSmallInsert(harness);
    testBiggerInsert(harness);
    testBigInsert(harness);
    testComplexInsert(harness);
  }

  /**
   * Tests the insertion of a small (< gapsize) chunk of text.
   *
   * @param harness the test harness to use
   */
  void testSmallInsert(TestHarness harness)
  {
    harness.checkPoint("smallInsert");
    try
      {
        TestGapContent c = new TestGapContent();
        checkBuffer(harness, c, 10, 1, 10);
        c.insertString(0, "abcdefgh");
        checkBuffer(harness, c, 10, 8, 9);
      }
    catch (BadLocationException ex)
      {
        harness.fail("BadLocationException thrown.");
      }
  }

  /**
   * Tests the insertion of a bigger (= gapsize) chunk of text.
   *
   * @param harness the test harness to use
   */
  void testBiggerInsert(TestHarness harness)
  {
    harness.checkPoint("biggerInsert");
    try
      {
        TestGapContent c = new TestGapContent();
        checkBuffer(harness, c, 10, 1, 10);
        c.insertString(0, "abcdefghi");
        checkBuffer(harness, c, 22, 9, 21);
      }
    catch (BadLocationException ex)
      {
        harness.fail("BadLocationException thrown.");
      }
  }

  /**
   * Tests the insertion of a bigger (= gapsize) chunk of text.
   *
   * @param harness the test harness to use
   */
  void testBigInsert(TestHarness harness)
  {
    harness.checkPoint("bigInsert");
    try
      {
        TestGapContent c = new TestGapContent();
        checkBuffer(harness, c, 10, 1, 10);
        c.insertString(0, "abcdefghijkl");
        checkBuffer(harness, c, 28, 12, 27);
      }
    catch (BadLocationException ex)
      {
        harness.fail("BadLocationException thrown.");
      }
  }

  /**
   * Tests a more complex insertion.
   *
   * @param harness the test harness to use
   */
  void testComplexInsert(TestHarness harness)
  {
    harness.checkPoint("complexInsert");
    try
      {
        TestGapContent c = new TestGapContent();
        checkBuffer(harness, c, 10, 1, 10);
        c.insertString(0, "abcdefghijklmno");
        checkBuffer(harness, c, 34, 15, 33);
        c.insertString(5, "12345");
        checkBuffer(harness, c, 34, 10, 23);
      }
    catch (BadLocationException ex)
      {
        harness.fail("BadLocationException thrown.");
      }
  }

  /**
   * Checks the state of the GapContent's buffer array.
   *
   * @param harness the test harness to use
   * @param c the gap content to check
   * @param arrayLength the expected array length
   * @param gapStart the expected gap start
   * @param gapEnd the expected gap end
   */
  void checkBuffer(TestHarness harness, TestGapContent c, int arrayLength,
                   int gapStart, int gapEnd)
  {
    harness.check(c.getArrayLength(), arrayLength);
    harness.check(c.getTestGapStart(), gapStart);
    harness.check(c.getTestGapEnd(), gapEnd);
  }
}
