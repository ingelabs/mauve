// Tags: JDK1.4

// Copyright (C) 2004 David Gilbert (david.gilbert@object-refinery.com)

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

package gnu.testlet.java.util.SimpleTimeZone;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.util.SimpleTimeZone;

/**
 * Some checks for the hashCode() method in the SimpleTimeZone class.
 */
public class hashCode
  implements Testlet
{
  /**
   * Runs the tests.
   * 
   * @param harness  the test harness.
   */
  public void test(TestHarness harness)
  {
    SimpleTimeZone z1 = new SimpleTimeZone(5 * 60 * 60 * 1000, "Z1");
    SimpleTimeZone z2 = new SimpleTimeZone(5 * 60 * 60 * 1000, "Z1");
	harness.check(z1.equals(z2));    
	harness.check(z1.hashCode(), z2.hashCode());  
  }

}