//Tags: JDK1.2

//Copyright (C) 2005 Roman Kennke <kennke@aicas.com>

//This file is part of Mauve.

//Mauve is free software; you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation; either version 2, or (at your option)
//any later version.

//Mauve is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with Mauve; see the file COPYING.  If not, write to
//the Free Software Foundation, 59 Temple Place - Suite 330,
//Boston, MA 02111-1307, USA.

package gnu.testlet.javax.swing.DefaultListSelectionModel;

import javax.swing.DefaultListSelectionModel;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * This checks some functionality of the setLeadSelection method.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class setLeadSelectionIndex implements Testlet
{

  /**
   * Starts the test run.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    testSetMinusOne(harness);
    testSetPositive(harness);
  }

  /**
   * Checks the behaviour when trying to set the lead selection index to -1.
   * It should set the lead selection index to -1 if and only if the
   * anchor selection index is also -1.
   */
  private void testSetMinusOne(TestHarness harness)
  {
    harness.checkPoint("setMinusOne");
    DefaultListSelectionModel m = new DefaultListSelectionModel();
    // Set to some value. (works only if anchor selection index is >= 0).
    m.setAnchorSelectionIndex(1);
    m.setLeadSelectionIndex(1);
    harness.check(m.getLeadSelectionIndex(), 1);
    // This should have no effect.
    m.setLeadSelectionIndex(-1);
    harness.check(m.getLeadSelectionIndex(), 1);
    // Now set the anchor selection index to -1 and it will work with the
    // lead selection index too.
    m.setAnchorSelectionIndex(-1);
    m.setLeadSelectionIndex(-1);
    harness.check(m.getLeadSelectionIndex(), -1);
  }

  /**
   * Checks the behaviour when trying to set the lead selection index to a
   * positive integer.
   * It should set the lead selection index only if the anchor selection index
   * is also >=.
   */
  private void testSetPositive(TestHarness harness)
  {
    harness.checkPoint("setPositive");
    DefaultListSelectionModel m = new DefaultListSelectionModel();
    // The lead and anchor selection index is -1 initially.
    harness.check(m.getLeadSelectionIndex(), -1);
    harness.check(m.getAnchorSelectionIndex(), -1);
    // This will not work unless the anchor selection index is != -1
    m.setLeadSelectionIndex(0);
    harness.check(m.getLeadSelectionIndex(), -1);
    // Now set the anchor to 0 and it will also work for the lead.
    m.setAnchorSelectionIndex(0);
    m.setLeadSelectionIndex(0);
    harness.check(m.getLeadSelectionIndex(), 0);
  }
}
