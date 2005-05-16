// Tags: JDK1.1

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
// the Free Software Foundation, 59 Temple Place - Suite 330,
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.awt.Color;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Color;

/**
 * Some checks for the hashCode() method in the {@link Color} class works.  
 */
public class hashCode implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)  
  {
    Color c1 = new Color(1, 2, 3);
    Color c2 = new Color(1, 2, 3);
    harness.check(c1.hashCode() == c2.hashCode());
    
    harness.check(Color.black.hashCode() == new Color(0, 0, 0).hashCode());
    harness.check(Color.white.hashCode() == new Color(255, 255, 255).hashCode());
  }

}
