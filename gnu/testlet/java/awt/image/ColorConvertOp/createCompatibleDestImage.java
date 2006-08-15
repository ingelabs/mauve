/* createCompatibleDestImage.java -- some checks for the
       createCompatibleDestImage() method of the ColorConvertOp class.
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

package gnu.testlet.java.awt.image.ColorConvertOp;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelInterleavedSampleModel;

/**
 * Checks for the createCompatibleDestImage method in the
 * {@link ColorConvertOp} class.
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
    simpleTest(harness);

    // Try with all possible colorspaces
    colorModelTest(harness, ColorSpace.CS_sRGB);
    colorModelTest(harness, ColorSpace.CS_CIEXYZ);
    colorModelTest(harness, ColorSpace.CS_GRAY);
    colorModelTest(harness, ColorSpace.CS_LINEAR_RGB);
    colorModelTest(harness, ColorSpace.CS_PYCC);
    
    // Specify both source and dest colourspaces
    colorModelTest(harness, ColorSpace.CS_sRGB, ColorSpace.CS_sRGB);
    colorModelTest(harness, ColorSpace.CS_CIEXYZ, ColorSpace.CS_sRGB);
    colorModelTest(harness, ColorSpace.CS_GRAY, ColorSpace.CS_sRGB);
    colorModelTest(harness, ColorSpace.CS_LINEAR_RGB, ColorSpace.CS_sRGB);
    colorModelTest(harness, ColorSpace.CS_PYCC, ColorSpace.CS_sRGB);
    
    colorModelTest(harness, ColorSpace.CS_sRGB, ColorSpace.CS_GRAY);
    colorModelTest(harness, ColorSpace.CS_CIEXYZ, ColorSpace.CS_GRAY);
    colorModelTest(harness, ColorSpace.CS_GRAY, ColorSpace.CS_GRAY);
    colorModelTest(harness, ColorSpace.CS_LINEAR_RGB, ColorSpace.CS_GRAY);
    colorModelTest(harness, ColorSpace.CS_PYCC, ColorSpace.CS_GRAY);
    
    // Specify profile list
    profileTest(harness, new ICC_Profile[] {ICC_Profile.getInstance(ColorSpace.CS_LINEAR_RGB),
                                            ICC_Profile.getInstance(ColorSpace.CS_CIEXYZ),
                                            ICC_Profile.getInstance(ColorSpace.CS_sRGB),
                                            ICC_Profile.getInstance(ColorSpace.CS_GRAY)});
                             
    profileTest(harness, new ICC_Profile[] {ICC_Profile.getInstance(ColorSpace.CS_GRAY),
                                            ICC_Profile.getInstance(ColorSpace.CS_sRGB)});
                             
    profileTest(harness, new ICC_Profile[] {ICC_Profile.getInstance(ColorSpace.CS_GRAY),
                                            ICC_Profile.getInstance(ColorSpace.CS_CIEXYZ)});
  }
  
  private void simpleTest(TestHarness harness)
  {
    harness.checkPoint("createCompatibleDestImage");

    // Simple test
    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
    ColorConvertOp op = new ColorConvertOp(cs, null);
    
    BufferedImage img = new BufferedImage(25, 40, BufferedImage.TYPE_INT_RGB);
    BufferedImage dest = op.createCompatibleDestImage(img, img.getColorModel());
    
    harness.check(dest.getHeight(), 40);
    harness.check(dest.getWidth(), 25);
    harness.check(dest.getColorModel(), img.getColorModel());
    
    // Try a different colour model
    img = new BufferedImage(25, 40, BufferedImage.TYPE_BYTE_GRAY);
    DirectColorModel cm = new DirectColorModel(32, 0x00ff0000, 0x0000ff00, 0x000000ff);
    dest = op.createCompatibleDestImage(img, cm);
    
    harness.check(dest.getHeight(), 40);
    harness.check(dest.getWidth(), 25);
    harness.check(dest.getColorModel(), cm);

    op = new ColorConvertOp(null);
    dest = op.createCompatibleDestImage(img, img.getColorModel());
    harness.check(dest.getHeight(), 40);
    harness.check(dest.getWidth(), 25);
    harness.check(dest.getColorModel(), img.getColorModel());

    // ColorConvertOp's ColorModel can be null, or createCompatibleDestImage's
    // ColorModel can be null, but not both
    try
    {
      dest = op.createCompatibleDestImage(img, null);
      harness.check(false);
    }
    catch (IllegalArgumentException e)
    {
      harness.check(true);
    }
  }
  
  // This should probably go in the BufferedImage constructor 
  // Test all the default color models
  private void colorModelTest(TestHarness harness, int cspace)
  {
    colorModelTest(harness, -1, cspace);
  }
  
  private void colorModelTest(TestHarness harness, int cspace, int cspace2)
  {
    ColorSpace cs;
    ColorSpace cs2;
    ColorConvertOp op;
    
    if (cspace == -1)
      {
        cs2 = ColorSpace.getInstance(cspace2);
        op = new ColorConvertOp(cs2, null);
      }
    else
      {
        cs = ColorSpace.getInstance(cspace);
        cs2 = ColorSpace.getInstance(cspace2);
        op = new ColorConvertOp(cs, cs2, null);
      }

    int[] types = {BufferedImage.TYPE_INT_RGB,
                   BufferedImage.TYPE_INT_ARGB,
                   BufferedImage.TYPE_INT_ARGB_PRE,
                   BufferedImage.TYPE_3BYTE_BGR,
                   BufferedImage.TYPE_4BYTE_ABGR,
                   BufferedImage.TYPE_4BYTE_ABGR_PRE,
                   BufferedImage.TYPE_USHORT_565_RGB,
                   BufferedImage.TYPE_USHORT_555_RGB,
                   BufferedImage.TYPE_BYTE_GRAY,
                   BufferedImage.TYPE_USHORT_GRAY};
    // Skipped types that are not implemented yet:
    // TYPE_CUSTOM, TYPE_INT_BGR, TYPE_BYTE_BINARY, TYPE_BYTE_INDEXED

    for (int i = 0; i < types.length; i++)
      {
        int type = types[i];
        if (cspace == -1)
          harness.checkPoint("colorspace " + cspace2  + ", type: " + type);
        else
          harness.checkPoint("src colorspace " + cspace + ", dest colorspace " + cspace2 + ", type: " + type);
        
        BufferedImage img = new BufferedImage(25, 40, type);
        BufferedImage dest = op.createCompatibleDestImage(img, null);
        dest = op.createCompatibleDestImage(img, null);
        
        harness.check(dest.getColorModel() instanceof ComponentColorModel);
        harness.check(dest.getSampleModel() instanceof PixelInterleavedSampleModel);
        harness.check(dest.getColorModel().isCompatibleSampleModel(dest.getSampleModel()));
        harness.check(dest.getColorModel().getTransferType(), DataBuffer.TYPE_BYTE);
        harness.check(dest.getColorModel().getColorSpace().getType(), cs2.getType());

        harness.check(dest.getColorModel().getPixelSize(),
                      8 * dest.getRaster().getNumDataElements());

        // This ensures that we have the same defaults as the reference implementation
        switch (type)
        {
          case BufferedImage.TYPE_INT_ARGB:
          case BufferedImage.TYPE_INT_ARGB_PRE:
          case BufferedImage.TYPE_4BYTE_ABGR:
          case BufferedImage.TYPE_4BYTE_ABGR_PRE:
            if (cspace2 == ColorSpace.CS_GRAY)
              {
                harness.check(dest.getColorModel().getNumComponents(), 2);
              }
            else
              {
                harness.check(dest.getColorModel().getNumComponents(), 4);
              }
            
            harness.check(dest.getColorModel().getNumColorComponents(),
                          dest.getColorModel().getNumComponents() - 1);
            harness.check(dest.getRaster().getNumDataElements(),
                          dest.getColorModel().getNumColorComponents() + 1);
            harness.check(dest.getColorModel().getTransparency(), ColorModel.TRANSLUCENT);
            harness.check(dest.getColorModel().hasAlpha(), true);
            harness.check(dest.getColorModel().isAlphaPremultiplied(),
                          (type == BufferedImage.TYPE_INT_ARGB_PRE
                              || type == BufferedImage.TYPE_4BYTE_ABGR_PRE));
            harness.check(dest.getRaster().getNumBands(), dest.getRaster().getNumDataElements());
            
            harness.check(dest.getType(), BufferedImage.TYPE_CUSTOM);
            break;
            
          case BufferedImage.TYPE_INT_RGB:
          case BufferedImage.TYPE_3BYTE_BGR:
          case BufferedImage.TYPE_USHORT_565_RGB:
          case BufferedImage.TYPE_USHORT_555_RGB:
          case BufferedImage.TYPE_BYTE_GRAY:
          case BufferedImage.TYPE_USHORT_GRAY:
            if (cspace2 == ColorSpace.CS_GRAY)
              {
                harness.check(dest.getType(), BufferedImage.TYPE_BYTE_GRAY);
                harness.check(dest.getColorModel().getNumComponents(), 1);
              }
            else
              {
                harness.check(dest.getType(), BufferedImage.TYPE_CUSTOM);
                harness.check(dest.getColorModel().getNumComponents(), 3);
              }
            
            harness.check(dest.getColorModel().getNumColorComponents(),
                          dest.getColorModel().getNumComponents());
            harness.check(dest.getRaster().getNumDataElements(), dest.getColorModel().getNumColorComponents());
            harness.check(dest.getColorModel().getTransparency(), ColorModel.OPAQUE);
            harness.check(dest.getColorModel().hasAlpha(), false);
            harness.check(dest.getColorModel().isAlphaPremultiplied(), false);
            harness.check(dest.getRaster().getNumBands(), dest.getRaster().getNumDataElements());
            
            break;
        }
      }
  }
  
  
  private void profileTest(TestHarness harness, ICC_Profile[] profile)
  {
    ColorConvertOp op = new ColorConvertOp(profile, null);

    int[] types = {BufferedImage.TYPE_INT_RGB,
                   BufferedImage.TYPE_INT_ARGB,
                   BufferedImage.TYPE_INT_ARGB_PRE,
                   BufferedImage.TYPE_3BYTE_BGR,
                   BufferedImage.TYPE_4BYTE_ABGR,
                   BufferedImage.TYPE_4BYTE_ABGR_PRE,
                   BufferedImage.TYPE_USHORT_565_RGB,
                   BufferedImage.TYPE_USHORT_555_RGB,
                   BufferedImage.TYPE_BYTE_GRAY,
                   BufferedImage.TYPE_USHORT_GRAY};
    // Skipped types that are not implemented yet:
    // TYPE_CUSTOM, TYPE_INT_BGR, TYPE_BYTE_BINARY, TYPE_BYTE_INDEXED

    for (int i = 0; i < types.length; i++)
      {
        int type = types[i];
        harness.checkPoint("profile " + profile[profile.length-1].getClass() + ", type: " + type);
        
        BufferedImage img = new BufferedImage(25, 40, type);
        BufferedImage dest = op.createCompatibleDestImage(img, null);
        dest = op.createCompatibleDestImage(img, null);
        
        harness.check(dest.getColorModel() instanceof ComponentColorModel);
        harness.check(dest.getSampleModel() instanceof PixelInterleavedSampleModel);
        harness.check(dest.getColorModel().isCompatibleSampleModel(dest.getSampleModel()));
        harness.check(dest.getColorModel().getTransferType(), DataBuffer.TYPE_BYTE);
        harness.check(dest.getColorModel().getColorSpace().getType(),
                      profile[profile.length-1].getColorSpaceType());

        harness.check(dest.getColorModel().getPixelSize(),
                      8 * dest.getRaster().getNumDataElements());

        // This ensures that we have the same defaults as the reference implementation
        switch (type)
        {
          case BufferedImage.TYPE_INT_ARGB:
          case BufferedImage.TYPE_INT_ARGB_PRE:
          case BufferedImage.TYPE_4BYTE_ABGR:
          case BufferedImage.TYPE_4BYTE_ABGR_PRE:
            if (profile[profile.length-1].getColorSpaceType() == ColorSpace.TYPE_GRAY)
              {
                harness.check(dest.getColorModel().getNumComponents(), 2);
              }
            else
              {
                harness.check(dest.getColorModel().getNumComponents(), 4);
              }
            //System.out.println("dest cmpt " + dest.getColorModel().getNumComponents());
            
            harness.check(dest.getColorModel().getNumColorComponents(),
                          dest.getColorModel().getNumComponents() - 1);
            harness.check(dest.getRaster().getNumDataElements(),
                          dest.getColorModel().getNumColorComponents() + 1);
            harness.check(dest.getColorModel().getTransparency(), ColorModel.TRANSLUCENT);
            harness.check(dest.getColorModel().hasAlpha(), true);
            harness.check(dest.getColorModel().isAlphaPremultiplied(),
                          (type == BufferedImage.TYPE_INT_ARGB_PRE
                              || type == BufferedImage.TYPE_4BYTE_ABGR_PRE));
            harness.check(dest.getRaster().getNumBands(), dest.getRaster().getNumDataElements());
            
            harness.check(dest.getType(), BufferedImage.TYPE_CUSTOM);
            break;
            
          case BufferedImage.TYPE_INT_RGB:
          case BufferedImage.TYPE_3BYTE_BGR:
          case BufferedImage.TYPE_USHORT_565_RGB:
          case BufferedImage.TYPE_USHORT_555_RGB:
          case BufferedImage.TYPE_BYTE_GRAY:
          case BufferedImage.TYPE_USHORT_GRAY:
            if (profile[profile.length-1].getColorSpaceType() == ColorSpace.TYPE_GRAY)
              {
                harness.check(dest.getType(), BufferedImage.TYPE_BYTE_GRAY);
                harness.check(dest.getColorModel().getNumComponents(), 1);
              }
            else
              {
                harness.check(dest.getType(), BufferedImage.TYPE_CUSTOM);
                harness.check(dest.getColorModel().getNumComponents(), 3);
              }
            //System.out.println("dest type " + dest.getType());
            //System.out.println("dest cmpt " + dest.getColorModel().getNumComponents());
            
            harness.check(dest.getColorModel().getNumColorComponents(),
                          dest.getColorModel().getNumComponents());
            harness.check(dest.getRaster().getNumDataElements(), dest.getColorModel().getNumColorComponents());
            harness.check(dest.getColorModel().getTransparency(), ColorModel.OPAQUE);
            harness.check(dest.getColorModel().hasAlpha(), false);
            harness.check(dest.getColorModel().isAlphaPremultiplied(), false);
            harness.check(dest.getRaster().getNumBands(), dest.getRaster().getNumDataElements());
            
            break;
        }
      }
  }
}


 