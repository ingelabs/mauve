/* createCompatibleDestRaster.java -- some checks for the 
       createCompatibleDestRaster() method in the LookupOp class.
   Copyright (C) 2006 David Gilbert <david.gilbert@object-refinery.com>
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

// Tags: JDK1.4

package gnu.testlet.java.awt.image.LookupOp;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.image.ByteLookupTable;
import java.awt.image.DataBuffer;
import java.awt.image.LookupOp;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;

public class createCompatibleDestRaster implements Testlet
{
  public void test(TestHarness harness)
  {
    byte[] bytes = new byte[] {(byte) 0xAA, (byte) 0xBB};
    ByteLookupTable t = new ByteLookupTable(0, bytes);
    LookupOp op = new LookupOp(t, null);
    Raster raster = Raster.createWritableRaster(
        new MultiPixelPackedSampleModel(DataBuffer.TYPE_BYTE, 10, 20, 8), null);
    Raster dest = op.createCompatibleDestRaster(raster);
    harness.check(dest.getWidth(), 10);
    harness.check(dest.getHeight(), 20);
    harness.check(dest.getNumBands(), 1);
    harness.check(dest.getSampleModel() instanceof MultiPixelPackedSampleModel);
  
    // try null argument
    boolean pass = false;
    try
    {
      op.createCompatibleDestRaster(null);
    }
    catch (NullPointerException e)
    {
      pass = true;
    }
    harness.check(pass);
  }
}
