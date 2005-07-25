// Tags: JDK1.2

// Copyright (C) 2005 David Gilbert <david.gilbert@object-refinery.com>

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

package gnu.testlet.javax.swing.table.TableColumn;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.table.TableColumn;

/**
 * Some tests for the setMinWidth() method in the {@link TableColumn} class.
 */
public class setMinWidth implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)      
  {
    TableColumn c = new TableColumn();
    c.setMinWidth(11);
    harness.check(c.getMinWidth(), 11);
    
    // spec says that current and preferred width will be updated if they are 
    // less than the min width
    harness.check(c.getWidth(), 75);
    harness.check(c.getPreferredWidth(), 75);
    c.setMinWidth(88);
    harness.check(c.getMinWidth(), 88);
    harness.check(c.getWidth(), 88);
    harness.check(c.getPreferredWidth(), 88);
    
    // the spec doesn't say anything about the max value, unfortunately
  }

}
