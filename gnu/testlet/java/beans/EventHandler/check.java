//Tags: JDK1.4

//Copyright (C) 2005 Robert Schuster <thebohemian@gmx.net>

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

package gnu.testlet.java.beans.EventHandler;

import java.beans.EventHandler;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * <p>This tests whether the EventHandler implementation calls the right methods when there are multiple
 * possibilities.</p>
 * 
 * The correct behavior is to always call the method that has no arguments, if it exists.
 */
public class check implements Testlet {

	public interface Listener {
		
		void listen();
		
		void listen(Object o);
		
		void listen(String m);
	}
	
	private boolean rightMethodCalled;
	private boolean wrongMethodCalled;
	
	public void test(TestHarness harness) {
		Listener l = (Listener)
			EventHandler.create(Listener.class, this, "targetMethod");
		
		l.listen();
		harness.check(rightMethodCalled, true);
		harness.check(wrongMethodCalled, false);
		
		rightMethodCalled = wrongMethodCalled = false;
		l.listen(new Object());
		harness.check(rightMethodCalled, true);
		harness.check(wrongMethodCalled, false);

		rightMethodCalled = wrongMethodCalled = false;
		l.listen("GNU!");
		harness.check(rightMethodCalled, true);
		harness.check(wrongMethodCalled, false);
	}
	
	public void targetMethod() {
		rightMethodCalled = true;
	}
	
	public void targetMethod(Object o) {
		wrongMethodCalled = true;
	}
	
	public void targetMethod(String m) {
		wrongMethodCalled = true;
	}
	
}
