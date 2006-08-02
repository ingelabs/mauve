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

package gnu.testlet.java.awt.image.AffineTransformOp;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Checks the filter(BufferedImage) method in the {@link AffineTransformOp} class.
 */
public class filterImage implements Testlet
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted). 
   */
  public void test(TestHarness harness)      
  {
    testDefaults(harness);
    //testTransform(harness);   // I don't really like how this test works,
                                // but am leaving it in, in the hopes someone
                                // might be able to base a better test off it
  }
  
  private void testDefaults(TestHarness harness)
  {
    // Create an image to work on
    BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_USHORT_GRAY);
    Graphics2D g = (Graphics2D)img.getGraphics();
    g.draw(new Line2D.Double(0, 0, 20, 20));
    
    AffineTransform xform = new AffineTransform();
    AffineTransformOp op = new AffineTransformOp(xform, AffineTransformOp.TYPE_BICUBIC);
    
    // Src and dst images cannot be the same
    try
    {
      op.filter(img, img);
      harness.check(false);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(true);
    }
    
    // Src and dst are different sizes (allowed)
    BufferedImage dst = new BufferedImage(30, 40, BufferedImage.TYPE_USHORT_GRAY);
    try
    {
      op.filter(img, dst);
      harness.check(true);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(false);
    }
    
    // Src and dst have different tpyes (allowed)
    dst = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
    try
    {
      op.filter(img, dst);
      harness.check(true);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(false);
    }
    
    // Src and dst are different sizes AND different types (not allowed)
    dst = new BufferedImage(30, 40, BufferedImage.TYPE_INT_ARGB);
    try
    {
      op.filter(img, dst);
      harness.check(false);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(true);
    }
  }
  
  private void testTransform(TestHarness harness)
  {
    // Create an image to work on
    BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_USHORT_GRAY);
    Graphics2D g = (Graphics2D)img.getGraphics();
    g.draw(new Line2D.Double(0, 0, 20, 20));
    
    AffineTransform xform = AffineTransform.getRotateInstance(Math.PI / 2, 10, 10);
    AffineTransformOp op = new AffineTransformOp(xform, AffineTransformOp.TYPE_BICUBIC);
    
    BufferedImage dst = new BufferedImage(20, 20, BufferedImage.TYPE_USHORT_GRAY);
    BufferedImage dst2 = op.filter(img, dst);
    
    harness.check(dst, dst2);
    
    // The following values were found by testing against the ref impl, ie
    /*
    int[] pixels = img.getData().getPixels(0, 0, 20, 20, (int[])null);
    for (int i = 0; i < pixels.length; i++)
      {
        if (pixels[i] != 0)
          System.out.println(i + ": " + pixels[i]);
      }
    */
    // Note that changing the interpolation type will result in different values.
    //
    // I'm not fully convinced this test is good, since pixel variations
    // in our transformations are tolerable (and visually insignificant)...
    // but i can't think of any other way to write a test for it.
    // Trying to draw a Line2D.Double(20, 0, 0, 20) and testing against that
    // fails on all implementations.

    int[] pixels = dst.getData().getPixels(0,0,20,20,(int[])null);
    
    int[] pixels2 = new int[400];
    Arrays.fill(pixels2, 0);
    
    pixels2[19] = 65535;
    pixels2[37] = 65535;
    pixels2[38] = 65535;
    pixels2[56] = 65535;
    pixels2[57] = 65535;
    pixels2[75] = 65535;
    pixels2[76] = 65535;
    pixels2[94] = 65535;
    pixels2[95] = 65535;
    pixels2[113] = 65535;
    pixels2[114] = 65535;
    pixels2[132] = 65535;
    pixels2[133] = 65535;
    pixels2[151] = 65535;
    pixels2[152] = 65535;
    pixels2[170] = 65535;
    pixels2[171] = 65535;
    pixels2[189] = 65535;
    pixels2[190] = 65535;
    pixels2[208] = 65535;
    pixels2[209] = 65535;
    pixels2[227] = 65535;
    pixels2[228] = 65535;
    pixels2[246] = 65535;
    pixels2[247] = 65535;
    pixels2[265] = 65535;
    pixels2[266] = 65535;
    pixels2[284] = 65535;
    pixels2[285] = 65535;
    pixels2[303] = 65535;
    pixels2[304] = 65535;
    pixels2[322] = 65535;
    pixels2[323] = 65535;
    pixels2[342] = 65535;
    pixels2[361] = 65535;
    pixels2[380] = 65534;
    
    harness.check(Arrays.equals(pixels, pixels2));
  }
}

