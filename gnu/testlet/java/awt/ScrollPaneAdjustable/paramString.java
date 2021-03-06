/* paramString.java 
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

// Tags: 1.4

package gnu.testlet.java.awt.ScrollPaneAdjustable;

import java.awt.ScrollPane;
import java.awt.ScrollPaneAdjustable;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class paramString implements Testlet
{

  public void test(TestHarness harness)
  {
    ScrollPane sp = new ScrollPane();
    ScrollPaneAdjustable vspa = (ScrollPaneAdjustable) sp.getVAdjustable();
    ScrollPaneAdjustable hspa = (ScrollPaneAdjustable) sp.getHAdjustable();
    harness.check(vspa.paramString(), 
             "vertical,[0..0],val=0,vis=0,unit=1,block=1,isAdjusting=false");
    harness.check(hspa.paramString(), 
             "horizontal,[0..0],val=0,vis=0,unit=1,block=1,isAdjusting=false");
  }

}
