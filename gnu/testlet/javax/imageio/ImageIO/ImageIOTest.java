// Tags: JDK1.4

// Copyright (C) 2004  Michael Koch <konqueror@gmx.de>

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


package gnu.testlet.javax.imageio.ImageIO;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import javax.imageio.ImageIO;

/**
 * @author Michael Koch (konqueror@gmx.de)
 */
public class ImageIOTest
  implements Testlet
{
  public void test(TestHarness h)
  {
    String[] stringData;

    // check #1: getReaderFormatNames
    stringData = ImageIO.getReaderFormatNames();
    h.check(stringData.length != 0, "empty reader format names");
    
    // check #2: getReaderMIMETypes
    stringData = ImageIO.getReaderMIMETypes();
    h.check(stringData.length != 0, "empty reader mime types");
    
    // check #3: getWriterFormatNames
    stringData = ImageIO.getWriterFormatNames();
    h.check(stringData.length != 0, "empty writer format names");
    
    // check #4: getWriterMIMETypes
    stringData = ImageIO.getWriterMIMETypes();
    h.check(stringData.length != 0, "empty writer mime types");
  }
}
