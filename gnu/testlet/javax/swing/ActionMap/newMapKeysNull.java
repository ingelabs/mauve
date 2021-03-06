//Tags: JDK1.2

//Copyright (C) 2005 Red Hat.

//This file is part of Mauve.

//Mauve is free software; you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation; either version 2, or (at your option)
//any later version.

//Mauve is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.

//You should have received a copy of the GNU General Public License
//along with Mauve; see the file COPYING.  If not, write to
//the Free Software Foundation, 59 Temple Place - Suite 330,
//Boston, MA 02111-1307, USA.

package gnu.testlet.javax.swing.ActionMap;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import javax.swing.ActionMap;

/**
 * This tests whether keys() and allKeys() return null for new ActionMaps.
 */ 
public class newMapKeysNull implements Testlet {
	
	public void test(TestHarness harness) {
          ActionMap map = new ActionMap();
          if (map.keys() != null)
            harness.fail("New ActionMap should return null for keys()");
          if (map.allKeys() != null)
            harness.fail ("New ActionMap should return null for allKeys()");
	}

}
