/* testNullProducer.java --
   Copyright (C) 2006 Red Hat
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

// Tags: JDK1.5

package gnu.testlet.java.awt.image.PixelGrabber;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;

public class testNullProducer implements Testlet
{
  public void test (TestHarness harness)
  {
    boolean exCaught = false;
    
    try 
    {
      new PixelGrabber((ImageProducer) null, 0, 0, 0, 0, new int[5], 0, 0);
    }
    catch (NullPointerException npe)
    {
      exCaught = true;
    }
    
    harness.check(!exCaught);
  }
}
