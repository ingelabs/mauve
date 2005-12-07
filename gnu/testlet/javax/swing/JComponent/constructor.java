// Tags: JDK1.2
// Uses: TestLayout

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

import javax.swing.JComponent;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Checks if the constructor is working correctly and the fields of the
 * JComponent are initialized as they should.
 *
 * @author Roman Kennke (kennke@aicas.com)
 */
public class constructor implements Testlet
{

  /**
   * Starts the testcase.
   *
   * @param harness the test harness to use
   */
  public void test(TestHarness harness)
  {
    JComponent c = new JComponent(){};
    harness.check(c.getLayout(), null);
  }

}
