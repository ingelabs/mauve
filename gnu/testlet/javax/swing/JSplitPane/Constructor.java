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

package gnu.testlet.javax.swing.JSplitPane;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

/**
 * This tests whether the constructors for JSplitPane assign 
 * the correct Components as leftComponent and rightComponent.
 */ 
public class Constructor implements Testlet {
	
	public void test(TestHarness harness) {
          JSplitPane pane = new JSplitPane();
          harness.checkPoint ("demo constructor");
          harness.check(pane.getLeftComponent().getClass() == JButton.class);
          harness.check(pane.getRightComponent().getClass() == JButton.class);

          pane = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, false, null, null);
          harness.checkPoint ("general constructor with 2 nulls");
          harness.check(pane.getLeftComponent() == null);
          harness.check(pane.getRightComponent() == null);                    
	}

}
