// Tags: JDK1.2

// Copyright (C) 2004 Sascha Brawer <brawer@dandelis.ch>

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
// Boston, MA 02111-1307, USA.

package gnu.testlet.java.awt.image.SinglePixelPackedSampleModel;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import java.awt.image.SinglePixelPackedSampleModel;


/**
 * Checks whether SinglePixelPackedSampleModel.getSampleSize works.
 *
 * @author Sascha Brawer (brawer@dandelis.ch)
 */
public class getSampleSize
  implements Testlet
{
  public void test(TestHarness harness)
  {
    SinglePixelPackedSampleModel sm;
    DataBuffer dbuf;
    int[] bitMasks;

    bitMasks = new int[] { 0xf0, 0x08, 0x6, 0x1 };
    sm = new SinglePixelPackedSampleModel(DataBuffer.TYPE_BYTE,
                                          5, 3, bitMasks);

    // Check #1
    harness.check(sm.getSampleSize(0), 4);

    // Check #2
    harness.check(sm.getSampleSize(1), 1);

    // Check #3
    harness.check(sm.getSampleSize(2), 2);

    // Check #4
    harness.check(sm.getSampleSize(3), 1);

    // Check #5
    try
    {
      sm.getSampleSize(4);
      harness.check(false);
    }
    catch (RuntimeException ex)
    {
      harness.check(true);
    }

    // Check #6
    try
    {
      sm.getSampleSize(-1);
      harness.check(false);
    }
    catch (RuntimeException ex)
    {
      harness.check(true);
    }


    // Check #7
    int[] sizes = sm.getSampleSize();
    harness.check(sizes.length, 4);
    
    // Check #8
    harness.check(sizes[0], 4);

    // Check #9
    harness.check(sizes[1], 1);

    // Check #10
    harness.check(sizes[2], 2);

    // Check #11
    harness.check(sizes[3], 1);
  }
}
