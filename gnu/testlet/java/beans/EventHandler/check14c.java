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
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * <p>This tests the error behavior of the <code>EventHandler</code></p>
 */
public class check14c implements Testlet {

	public interface Listener {

		void listen();

		void listen(Object o);

		void listen(String m);
	}

	public class Event {
		public boolean isBooleanValue() {
			return true;
		}

		public BigInteger getBigValue() {
			return new BigInteger("903281390123809123");
		}

		public int getIntValue() {
			return 0xDEADBEEF;
		}
		
		public Event getSelf() {
			return this;
		}
	}

	public class EventSub extends Event {
		public void getErroneousProperty() throws PersonalException {
			throw new PersonalException();
		}
	}
	
	static class PersonalException extends Exception{
	}
	
	public interface Listener2 {
		public void listen(Event e);
	}

	private boolean calledSetter;

	public void test(TestHarness harness) {
		
		// The first test checks whether a ArrayIndexOutOfBoundsException is thrown
		// when the targetMethod cannot be found (Especially because of missing access
		// rights).
		Listener l = (Listener) EventHandler.create(Listener.class, this,
				"targetMethod");

		boolean correctException = false;
		try {
			l.listen();
		} catch (Exception e) {
			correctException = e.getClass() == ArrayIndexOutOfBoundsException.class;
		}
		harness.check(correctException, true, "missing target method");

		// This checks whether a RuntimeException is thrown when the EventHandler
		// retrieved a property value from the listener first argument but finds no
		// suitable method or property to apply the property.
		Listener2 l2 = (Listener2) EventHandler.create(Listener2.class, this,
				"targetMethod2", "booleanValue");

		correctException = false;
		try {
			l2.listen(new Event());
		} catch (Exception re) {
			correctException = re.getClass() == RuntimeException.class;
		}
		harness.check(correctException, true, "missing property target method");

		// This checks the a similar situation as above. The difference is that
		// the property is retrieved by a more complex expression. The RuntimeException
		// is expected because targetMethod2 does not accept an int or Integer.
		l2 = (Listener2) EventHandler.create(Listener2.class, this,
				"targetMethod2", "getSelf.getSelf.intValue");

		correctException = false;
		try {
			l2.listen(new Event());
		} catch (Exception e) {
			correctException = e.getClass() == RuntimeException.class;
		}
		harness.check(correctException, true);
		
		// The list of "."-concatenated method and property names may have a wrong entry. We expect
		// a RuntimeException then.
		l2 = (Listener2)
		EventHandler.create(Listener2.class, this, "not important", "getSelf.self.getSelf.self.HellBrokeOut", null);

		correctException = false;
		try {
			l2.listen(new EventSub());
		} catch(Exception e) {
			correctException = e.getClass() == RuntimeException.class;
		}
		harness.check(correctException, true, "missing property");

		// One may think that a creation like this will forward the Event instance to the 'setEventProperty'
		// method but this is wrong and will cause a ArrayIndexOutOfBoundsException to be as if could not
		// find a method. In other words: If no property is defined action will never be treated as a
		// property but always like a method name. 
		l2 = (Listener2)
		EventHandler.create(Listener2.class, this, "eventProperty");

		correctException = false;
		try {
			l2.listen(new Event());
		} catch(Exception e) {
			correctException = e.getClass() == ArrayIndexOutOfBoundsException.class;
		}
		harness.check(correctException, true, "action is method");

		// When the target method throws an Exception it will be wrapped in a RuntimeException.
		l2 = (Listener2)
		EventHandler.create(Listener2.class, this, "erroneousTargetMethod");

		correctException = false;
		boolean correctException2 = false;
		try {
			l2.listen(new Event());
		} catch(Exception e) {
			correctException = e.getClass() == RuntimeException.class;
			correctException2 = e.getCause().getClass() == PersonalException.class;
		}
		harness.check(correctException, true, "erroneous target method");
		harness.check(correctException2, true);

		// When the property method throws an Exception a RuntimeException will be
		// thrown.
		l2 = (Listener2)
		EventHandler.create(Listener2.class, this, "not important", "erroneousProperty");

		correctException = false;
		correctException2 = false;
		boolean correctException3 = false;
		try {
			l2.listen(new EventSub());
		} catch(Exception e) {
			correctException = e.getClass() == RuntimeException.class;
			correctException2 = e.getCause().getClass() == InvocationTargetException.class;
			correctException3 = e.getCause().getCause().getClass() == PersonalException.class;
		}
		harness.check(correctException, true, "erroneous property");
		harness.check(correctException2, true);
		harness.check(correctException3, true);
	}

	void targetMethod() {
	}

	public void targetMethod2() {
	}

	public void setEventProperty(Event e) {
	}
	
	public void erroneousTargetMethod() throws PersonalException {
		throw new PersonalException(); 
	}
	

}
