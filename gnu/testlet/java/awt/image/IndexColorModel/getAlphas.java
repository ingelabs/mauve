// Tags: JDK1.2 

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

package gnu.testlet.java.awt.image.IndexColorModel;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.image.IndexColorModel;

/**
 * Some checks for the getAlphas() method.
 */
public class getAlphas implements Testlet 
{

  private static final byte[] R4 = {(byte) 1, (byte) 2, (byte) 3, (byte) 4}; 
  private static final byte[] G4 = {(byte) 5, (byte) 6, (byte) 7, (byte) 8};
  private static final byte[] B4 = {(byte) 9, (byte) 10, (byte) 11, (byte) 12};
  private static final byte[] A4 = {(byte) 13, (byte) 14, (byte) 15, (byte) 16};

  /**
   * Runs the test using the specified harness. 
   * 
   * @param harness  the test harness (<code>null</code> not permitted).
   */
  public void test(TestHarness harness) 
  {
    IndexColorModel m1 = new IndexColorModel(2, 4, R4, G4, B4, A4);
    byte[] a = new byte[4];
    m1.getAlphas(a);
    harness.check(a[0], (byte) 13);
    harness.check(a[1], (byte) 14);
    harness.check(a[2], (byte) 15);
    harness.check(a[3], (byte) 16);
 
    // try null array
    boolean pass = false;
    try
    {
      m1.getAlphas(null);
    }
    catch (NullPointerException e)
    {
      pass = true;   
    }
    harness.check(pass);
 
    // try array too small to hold results
    pass = false;
    try
    {
      m1.getAlphas(new byte[3]);
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      pass = true; 
    }
    harness.check(pass);
  }  

}
