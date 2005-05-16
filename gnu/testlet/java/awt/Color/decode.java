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
 * Some checks for the decode() method in the {@link Color} class.  
 */
public class decode implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness)  
  {
    Color c = Color.decode("0x010203");
    harness.check(c.getRed(), 1);
    harness.check(c.getGreen(), 2);
    harness.check(c.getBlue(), 3);
  
    // try a null argument - see bug parade 6211249
    boolean pass = false;
    try
    {
      Color.decode(null);   
    }
    catch (NullPointerException e)
    {
      pass = true;   
    }
    harness.check(pass);
    
    // try a non-numeric string
    pass = false;
    try
    {
      Color.decode("XYZ");
    }
    catch (NumberFormatException e) 
    {
      pass = true;   
    }
    harness.check(pass);
  
  }

}
