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

package gnu.testlet.java.awt.image.DataBufferShort;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferShort;


/**
 * @author <a href="mailto:brawer@dandelis.ch">Sascha Brawer</a>
 */
public class getElem
  implements Testlet
{
  public void test(TestHarness h)
  {
    DataBuffer buf;
    short[] data = new short[] { -11, -22, -33, -44 };
    
    buf = new DataBufferShort(new short[][] { data, data }, 2,
                              new int[] { 2, 0 });

    h.check(buf.getElem(0), -33);    // Check #1.
    h.check(buf.getElem(1), -44);    // Check #2.
    h.check(buf.getElem(0, 0), -33); // Check #3.
    h.check(buf.getElem(0, 1), -44); // Check #4.
    h.check(buf.getElem(1, 0), -11); // Check #5.
    h.check(buf.getElem(1, 1), -22); // Check #6.
  }
}
