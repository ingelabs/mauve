/* constructors.java -- some checks for the constructors in the
       ComponentSampleModel class.
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

package gnu.testlet.java.awt.image.ComponentSampleModel;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.util.Arrays;

public class constructors implements Testlet
{
  public void test(TestHarness harness)
  {
    testConstructor1(harness);
    testConstructor2(harness);
    testDefensiveCopies(harness);
  }
  
  public void testConstructor1(TestHarness harness)
  {
    harness.checkPoint("testConstructor1()");
    
    ComponentSampleModel m = new ComponentSampleModel(DataBuffer.TYPE_BYTE,
            10, 20, 3, 30, new int[] {0, 1, 2});
    harness.check(m.getDataType(), DataBuffer.TYPE_BYTE);
    harness.check(m.getWidth(), 10);
    harness.check(m.getHeight(), 20);
    harness.check(m.getPixelStride(), 3);
    harness.check(m.getScanlineStride(), 30);
    harness.check(Arrays.equals(m.getBandOffsets(), new int[] {0, 1, 2}));
    
    // try bad type
    boolean pass = false;
    try
    {
      m = new ComponentSampleModel(DataBuffer.TYPE_UNDEFINED, 10, 20, 3, 30, 
              new int[] {0, 1, 2});
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // try zero width
    pass = false;
    try
    {
      m = new ComponentSampleModel(DataBuffer.TYPE_BYTE, 0, 20, 3, 30, 
              new int[] {0, 1, 2});
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);

    // try zero height
    pass = false;
    try
    {
      m = new ComponentSampleModel(DataBuffer.TYPE_BYTE, 10, 0, 3, 30, 
              new int[] {0, 1, 2});
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // try negative pixel stride
    pass = false;
    try
    {
      m = new ComponentSampleModel(DataBuffer.TYPE_BYTE, 10, 20, -1, 30, 
              new int[] {0, 1, 2});
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);

    // try negative scanline stride
    pass = false;
    try
    {
      m = new ComponentSampleModel(DataBuffer.TYPE_BYTE, 10, 20, 3, -1, 
              new int[] {0, 1, 2});
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // try width * height > Integer.MAX_VALUE
    pass = false;
    try
    {
      m = new ComponentSampleModel(DataBuffer.TYPE_BYTE, 3, 
              Integer.MAX_VALUE / 2, 3, 30, new int[] {0, 1, 2});
    }
    catch (IllegalArgumentException e)
    {
      pass = true;
    }
    harness.check(pass);
    
    // try null band offsets
    pass = false;
    try
    {
      m = new ComponentSampleModel(DataBuffer.TYPE_BYTE, 10, 20, 3, 30, null);
    }
    catch (NullPointerException e)
    {
      pass = true;
    }
    harness.check(pass);    
  }
  
  public void testConstructor2(TestHarness harness)
  {
    harness.checkPoint("testConstructor2()");
    
  }
  
  public void testDefensiveCopies(TestHarness harness)
  {
    harness.checkPoint("testDefensiveCopies()");
    int[] bandOffsets = new int[] {0, 1, 2};
    MyComponentSampleModel m = new MyComponentSampleModel(DataBuffer.TYPE_BYTE,
            10, 20, 3, 30, bandOffsets);
    harness.check(bandOffsets != m.getBandOffsetsDirect());
    harness.check(Arrays.equals(bandOffsets, m.getBandOffsetsDirect()));

    int[] bankIndices = new int[] {0, 0, 0};
    MyComponentSampleModel m2 = new MyComponentSampleModel(DataBuffer.TYPE_BYTE,
            10, 20, 3, 30, bandOffsets, bankIndices);
    harness.check(bandOffsets != m2.getBandOffsetsDirect());
    harness.check(Arrays.equals(bandOffsets, m2.getBandOffsetsDirect()));
    harness.check(bankIndices != m2.getBankIndicesDirect());
    harness.check(Arrays.equals(bankIndices, m2.getBankIndicesDirect()));

  }
}
