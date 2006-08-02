// Tags: JDK1.2

// Copyright (C) 2006 Francis Kung <fkung@redhat.com>

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

package gnu.testlet.java.awt.image.BandCombineOp;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Point;
import java.awt.image.BandCombineOp;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;

/**
 * Checks for the createCompatibleDestRaster method in the
 * {@link BandCombineOp} class.
 */
public class createCompatibleDestRaster implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted). 
   */
  public void test(TestHarness harness)      
  {
    harness.checkPoint("createCompatibleDestRaster");

    // Simple test
    float[][] matrix = new float[][] {new float[] {1, 2, 3},
                                      new float[] {4, 5, 6},
                                      new float[] {7, 8, 9}};
    
    Raster src = Raster.createBandedRaster(DataBuffer.TYPE_INT, 25, 40, 3, new Point(5, 5));
    BandCombineOp op = new BandCombineOp(matrix, null);
    
    try
    {
      Raster dst = op.createCompatibleDestRaster(src);
      harness.check(dst.getNumBands(), 3);
      harness.check(dst.getHeight(), src.getHeight());
      harness.check(dst.getWidth(), src.getWidth());
      harness.check(dst.getDataBuffer().getDataType(), src.getDataBuffer().getDataType());
    }
    catch (IllegalArgumentException e)
    {
      harness.check(false);
    }
    
    src = Raster.createBandedRaster(DataBuffer.TYPE_INT, 25, 40, 3, new Point(5, 5));
    try
    {
      Raster dst = op.createCompatibleDestRaster(src);
      harness.check(dst.getNumBands(), 3);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(false);
    }
    
    // The source (4 bands) is incompatible with the matrix (2 or 3 bands)
    src = Raster.createBandedRaster(DataBuffer.TYPE_INT, 25, 40, 4, new Point(5, 5));
    try
    {
      op.createCompatibleDestRaster(src);
      harness.check(false);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(true);
    }
    
    // The source (1 band) is incompatible with the matrix (2 or 3 bands)
    src = Raster.createBandedRaster(DataBuffer.TYPE_INT, 25, 40, 1, new Point(5, 5));
    try
    {
      op.createCompatibleDestRaster(src);
      harness.check(false);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(true);
    }
  }
}

