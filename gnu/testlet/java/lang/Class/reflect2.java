// Tags: JDK1.1
// Uses: rf2_help

// Copyright (C) 2002 Stephen Crawley <crawley@dstc.edu.au>

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
// Boston, MA 02111-1307, USA.  */

package gnu.testlet.java.lang.Class;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.lang.reflect.*;

public class reflect2 implements Testlet
{
  public Class getClass(String name)
  {
    try {
      return Class.forName(name);
    }
    catch (Throwable ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public void test(TestHarness harness)
  {
    Class help = getClass("gnu.testlet.java.lang.Class.rf2_help");
    Class[] inner = help.getClasses();
    Class[] inner2 = help.getDeclaredClasses();
    Class outer = help.getDeclaringClass();
  }
}
