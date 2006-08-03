/* createCompatibleDestImage.java -- some checks for the
       createCompatibleDestImage() method of the AffineTransformOp class.
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

package gnu.testlet.java.awt.image.AffineTransformOp;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;

/**
 * Checks for the createCompatibleDestImage method in the
 * {@link AffineTransformOp} class.
 */
public class createCompatibleDestImage implements Testlet 
{

  /**
   * Runs the test using the specified harness.
   * 
   * @param harness  the test harness (<code>null</code> not permitted). 
   */
  public void test(TestHarness harness)      
  {
    harness.checkPoint("createCompatibleDestImage");

    // Simple test
    AffineTransform xform = new AffineTransform();
    AffineTransformOp op = new AffineTransformOp(xform, AffineTransformOp.TYPE_BICUBIC);
    
    BufferedImage img = new BufferedImage(25, 40, BufferedImage.TYPE_INT_RGB);
    
    BufferedImage dest = op.createCompatibleDestImage(img, img.getColorModel());
    
    harness.check(dest.getHeight(), 40);
    harness.check(dest.getWidth(), 25);
    harness.check(dest.getColorModel(), img.getColorModel());
    
    // Try a different colour model
    img = new BufferedImage(25, 40, BufferedImage.TYPE_INT_RGB);
    DirectColorModel cm = new DirectColorModel(16, 0x0f00, 0x00f0, 0x000f);
    dest = op.createCompatibleDestImage(img, cm);
    
    harness.check(dest.getHeight(), 40);
    harness.check(dest.getWidth(), 25);
    harness.check(dest.getColorModel(), cm);
    
    // And the default color model
    dest = op.createCompatibleDestImage(img, null);
    harness.check(dest.getColorModel(), new DirectColorModel(32, 0x00ff0000, 0x0000ff00, 0x000000ff, 0xff000000));
    //harness.check(dest.getColorModel(), img.getColorModel()) makes more sense,
    // but that fails on the reference implementation...
    
    /*
    // Throw the right exceptions
    img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    xform.scale(0.00000000000000001, 0.00000000000000001);
    op = new AffineTransformOp(xform, AffineTransformOp.TYPE_BICUBIC);
    try
    {
      op.createCompatibleDestImage(img, null);
      harness.check(false);
    }
    catch (RasterFormatException e)
    {
      harness.check(true);
    }
    */
  }
}

