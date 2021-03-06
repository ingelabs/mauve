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
// the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
// Boston, MA 02110-1301 USA.

package gnu.testlet.javax.swing.border.TitledBorder;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Some checks for the getTitleJustification() method of the 
 * {@link TitledBorder} class.
 */
public class getTitleJustification implements Testlet {

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)       
  {
    TitledBorder b = new TitledBorder(new EmptyBorder(1, 1, 1, 1));
    harness.check(b.getTitleJustification(), TitledBorder.LEADING);
    b.setTitleJustification(TitledBorder.LEFT);
    harness.check(b.getTitleJustification(), TitledBorder.LEFT);
  }

}
