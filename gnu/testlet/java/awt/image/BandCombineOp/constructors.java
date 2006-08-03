/* constructors.java -- some checks for the constructors in the
              BandCombineOp class.
   Copyright (C) 2006 Francis Kung <fkung@redhat.com>
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.
*/

// Tags: JDK1.2

package gnu.testlet.java.awt.image.BandCombineOp;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.image.BandCombineOp;
import java.awt.image.ImagingOpException;
import java.util.Arrays;

/**
 * Some checks for the constructors in the {@link BandCombineOp} class.
 */
public class constructors implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted). 
   */
  public void test(TestHarness harness)      
  {
    harness.checkPoint("(constructor)");

    float[][] matrix = new float[][] {new float[]{1, 2, 3},
                                      new float[]{4, 5, 6},
                                      new float[]{7, 8, 9}};
                                      
    BandCombineOp op = new BandCombineOp(matrix, null);
    
    float[][] resultMatrix = op.getMatrix();
    float[][] expectedMatrix = new float[][] {new float[]{1, 2, 3, 0},
                                              new float[]{4, 5, 6, 0},
                                              new float[]{7, 8, 9, 0}};
      // The ref impl seems to add a column of zeros, so we test against that
      // for compatibility 
    
    if (expectedMatrix.length != resultMatrix.length)
      harness.check(false);
    else
      for (int i = 0; i < expectedMatrix.length; i++)
        harness.check(Arrays.equals(expectedMatrix[i], resultMatrix[i]));
    
    // Try creating with an empty matrix - this should be allowed
    matrix = new float[][] {new float[]{},
                            new float[]{}};
    
    try
    {
      new BandCombineOp(matrix, null);
      harness.check(true);
    }
    catch (ImagingOpException e)
    {
      harness.check(false);
    }

    op = new BandCombineOp(matrix, null);
    resultMatrix = op.getMatrix();
    expectedMatrix = new float[][] {new float[]{0},
                                    new float[]{0}};
    
    if (expectedMatrix.length != resultMatrix.length)
      harness.check(false);
    else
      for (int i = 0; i < expectedMatrix.length; i++)
        harness.check(Arrays.equals(expectedMatrix[i], resultMatrix[i]));
  }
}
