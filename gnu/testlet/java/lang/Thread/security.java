// Copyright (C) 2006 Red Hat, Inc.
// Written by Gary Benson <gbenson@redhat.com>

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

package gnu.testlet.java.lang.Thread;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Permission;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import gnu.testlet.TestSecurityManager2;

public class security implements Testlet
{
  public void test(TestHarness harness)
  {
    try {
      harness.checkPoint("setup");

      Thread testThread = new Thread();

      // we need a different classloader for some of the checks to occur.
      Class testClass = new URLClassLoader(new URL[] {
	new File(harness.getSourceDirectory()).toURL()}, null).loadClass(
	  getClass().getName());
      harness.check(getClass().getClassLoader() != testClass.getClassLoader());

      Method getContextClassLoaderTest = testClass.getMethod(
	"testGetContextClassLoader", new Class[] {Thread.class});

      Thread currentThread = Thread.currentThread();
      Throwable threadDeath = new ThreadDeath();
      Throwable notThreadDeath = new ClassNotFoundException();

      ThreadGroup group = new ThreadGroup("test group");
      Runnable runnable = new Runnable()
      {
	public void run()
	{
	}
      };

      Permission[] getClassLoader = new Permission[] {
	new RuntimePermission("getClassLoader")};

      Permission[] setContextClassLoader = new Permission[] {
	new RuntimePermission("setContextClassLoader")};

      Permission[] modifyThread = new Permission[] {
	new RuntimePermission("modifyThread")};

      Permission[] stopThread = new Permission[] {
	new RuntimePermission("modifyThread"),
	new RuntimePermission("stopThread")};

      Permission[] modifyThreadGroup = new Permission[] {
	new RuntimePermission("modifyThreadGroup")};

      TestSecurityManager2 sm = new TestSecurityManager2(harness);
      try {
	sm.install();

	// throwpoint: java.lang.Thread-getContextClassLoader
	harness.checkPoint("getContextClassLoader");
	try {
	  sm.prepareChecks(getClassLoader);
 	  getContextClassLoaderTest.invoke(null, new Object[] {testThread});
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-setContextClassLoader
	harness.checkPoint("setContextClassLoader");
	try {
	  ClassLoader loader = testThread.getContextClassLoader();
	  sm.prepareChecks(setContextClassLoader);
	  testThread.setContextClassLoader(loader);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-checkAccess
	harness.checkPoint("checkAccess");
	try {
	  sm.prepareChecks(modifyThread);
	  testThread.checkAccess();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-interrupt
	harness.checkPoint("interrupt");
	try {
	  sm.prepareChecks(modifyThread);
	  testThread.interrupt();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-suspend
	harness.checkPoint("suspend");
	try {
	  sm.prepareChecks(modifyThread);
	  testThread.suspend();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-resume
	harness.checkPoint("resume");
	try {
	  sm.prepareChecks(modifyThread);
	  testThread.resume();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-setPriority
	harness.checkPoint("setPriority");
	try {
	  int priority = testThread.getPriority();
	  sm.prepareChecks(modifyThread);
	  testThread.setPriority(priority);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-setName
	harness.checkPoint("setName");
	try {
	  sm.prepareChecks(modifyThread);
	  testThread.setName("a test thread");
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-setDaemon
	harness.checkPoint("setDaemon");
	try {
	  sm.prepareChecks(modifyThread);
	  testThread.setDaemon(false);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// throwpoint: java.lang.Thread-stop()
	harness.checkPoint("stop()");
	try {
	  sm.prepareChecks(stopThread);
	  testThread.stop();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	try {
	  sm.prepareChecks(modifyThread, true);
	  currentThread.stop();
	  harness.check(false, "shouldn't be reached");	  
	}
	catch (SecurityException ex) {
	  if (ex.getMessage().equals(TestSecurityManager2.successMessage)) {
	    harness.check(true);
	  }
	  else {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }
	}

	// throwpoint: java.lang.Thread-stop(Throwable)
	harness.checkPoint("stop(Throwable)");
	try {
	  sm.prepareChecks(stopThread);
	  testThread.stop(threadDeath);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	try {
	  sm.prepareChecks(stopThread);
	  testThread.stop(notThreadDeath);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}
	
	try {
	  sm.prepareChecks(modifyThread, true);
	  currentThread.stop(threadDeath);
	  harness.check(false, "shouldn't be reached");	  
	}
	catch (SecurityException ex) {
	  if (ex.getMessage().equals(TestSecurityManager2.successMessage)) {
	    harness.check(true);
	  }
	  else {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }
	}

	try {
	  sm.prepareChecks(stopThread, true);
	  currentThread.stop(notThreadDeath);
	  harness.check(false, "shouldn't be reached");	  
	}
	catch (SecurityException ex) {
	  if (ex.getMessage().equals(TestSecurityManager2.successMessage)) {
	    harness.check(true);
	  }
	  else {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }
	}

	// throwpoint: java.lang.Thread-Thread(ThreadGroup, Runnable)
	// throwpoint: java.lang.Thread-Thread(ThreadGroup, Runnable, String)
	// throwpoint: java.lang.Thread-Thread(ThreadGroup, Runnable, String, long)
	// throwpoint: java.lang.Thread-Thread(ThreadGroup, String)
	harness.checkPoint("ThreadGroup constructors");
	for (int i = 1; i <= 4; i++) {
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    switch (i) {
	    case 1:
	      new Thread(group, runnable);
	      break;
	    case 2:
	      new Thread(group, runnable, "test thread");
	      break;
	    case 3:
	      new Thread(group, runnable, "test thread", 1024);
	      break;
	    case 4:
	      new Thread(group, "test thread");
	      break;
	    }
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }
	}
      }
      finally {
	sm.uninstall();
      }
    }
    catch (Throwable ex) {
      harness.debug(ex);
      harness.check(false, "Unexpected exception");
    }
  }

  // Stuff for the getContextClassLoader tests
  public static void testGetContextClassLoader(Thread t)
  {
    t.getContextClassLoader();
  }
}
