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

package gnu.testlet.java.lang.ThreadGroup;

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

      // The default SecurityManager.checkAccess(ThreadGroup) method
      // only checks permissions when the threadgroup in question is
      // the system threadgroup, which is defined as the threadgroup
      // with no parent.

      ThreadGroup systemGroup = Thread.currentThread().getThreadGroup();
      while (systemGroup.getParent() != null)
	systemGroup = systemGroup.getParent();

      Thread testThread = new Thread(systemGroup, new TestRunner(harness));

      testThread.start();
      testThread.join();
    }
    catch (Throwable ex) {
      harness.debug(ex);
      harness.check(false, "Unexpected exception");
    }
  }
  
  public static class TestRunner implements Runnable
  {
    private TestHarness harness;

    public TestRunner(TestHarness harness)
    {
      this.harness = harness;
    }

    public void run()
    {
      try {
	ThreadGroup testGroup = Thread.currentThread().getThreadGroup();
	harness.check(testGroup.getParent() == null);

	ThreadGroup nonSystemGroup = new ThreadGroup(testGroup, "test group");
	harness.check(nonSystemGroup.getParent() != null);
	
	Permission[] modifyThreadGroup = new Permission[] {
	  new RuntimePermission("modifyThreadGroup")};

	Permission[] modifyThread = new Permission[] {
	  new RuntimePermission("modifyThread")};

	Permission[] stopThread = new Permission[] {
	  new RuntimePermission("modifyThread"),
	  new RuntimePermission("stopThread")};

	TestSecurityManager2 sm = new TestSecurityManager2(harness);
	try {
	  sm.install();

	  // throwpoint: java.lang.ThreadGroup-ThreadGroup(String)
	  harness.checkPoint("ThreadGroup(String)");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    new ThreadGroup("test");
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }
	
	  // throwpoint: java.lang.ThreadGroup-ThreadGroup(ThreadGroup, String)
	  harness.checkPoint("ThreadGroup(ThreadGroup, String)");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    new ThreadGroup(testGroup, "test");
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }

	  // throwpoint: java.lang.ThreadGroup-checkAccess
	  harness.checkPoint("checkAccess");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    testGroup.checkAccess();
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }

	  // throwpoint: java.lang.ThreadGroup-enumerate(Thread[])
	  harness.checkPoint("enumerate(Thread[])");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    testGroup.enumerate(new Thread[0]);
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }	

	  // throwpoint: java.lang.ThreadGroup-enumerate(Thread[], boolean)
	  harness.checkPoint("enumerate(Thread[], boolean)");
	  for (int i = 0; i <= 1; i++) {
	    try {
	      sm.prepareChecks(modifyThreadGroup);
	      testGroup.enumerate(new Thread[0], i == 1);
	      sm.checkAllChecked(harness);
	    }
	    catch (SecurityException ex) {
	      harness.debug(ex);
	      harness.check(false, "unexpected check");
	    }	
	  }

	  // throwpoint: java.lang.ThreadGroup-enumerate(ThreadGroup[])
	  harness.checkPoint("enumerate(ThreadGroup[])");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    testGroup.enumerate(new ThreadGroup[0]);
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }	

	  // throwpoint: java.lang.ThreadGroup-enumerate(ThreadGroup[], boolean)
	  harness.checkPoint("enumerate(ThreadGroup[], boolean)");
	  for (int i = 0; i <= 1; i++) {
	    try {
	      sm.prepareChecks(modifyThreadGroup);
	      testGroup.enumerate(new ThreadGroup[0], i == 1);
	      sm.checkAllChecked(harness);
	    }
	    catch (SecurityException ex) {
	      harness.debug(ex);
	      harness.check(false, "unexpected check");
	    }	
	  }

	  // throwpoint: java.lang.ThreadGroup-getParent
	  harness.checkPoint("getParent");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    nonSystemGroup.getParent();
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }
	
	  // throwpoint: java.lang.ThreadGroup-setDaemon
	  harness.checkPoint("setDaemon");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    testGroup.setDaemon(false);
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }
	
	  // throwpoint: java.lang.ThreadGroup-setMaxPriority
	  harness.checkPoint("setMaxPriority");
	  try {
	    int priority = testGroup.getMaxPriority();
	    sm.prepareChecks(modifyThreadGroup);
	    testGroup.setMaxPriority(priority);
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
	  }

	  // throwpoint: java.lang.ThreadGroup-suspend
	  harness.checkPoint("suspend");
	  try {
	    sm.prepareChecks(modifyThreadGroup, modifyThread, true);
	    testGroup.suspend();
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

	  // throwpoint: java.lang.ThreadGroup-resume
	  harness.checkPoint("resume");
	  try {
	    sm.prepareChecks(modifyThreadGroup, modifyThread, true);
	    testGroup.resume();
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

	  // throwpoint: TODO: java.lang.ThreadGroup-destroy
	  // XXX I'm not sure you can test for this one.  It's an
	  // XXX error to call this on a non-empty threadgroup, but
	  // XXX the check only happens for the system group which
	  // XXX will not be empty.

	  // throwpoint: java.lang.ThreadGroup-interrupt
	  harness.checkPoint("interrupt");
	  try {
	    sm.prepareChecks(modifyThreadGroup, modifyThread, true);
	    testGroup.interrupt();
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

	  // throwpoint: java.lang.ThreadGroup-stop
	  harness.checkPoint("stop");
	  try {
	    sm.prepareChecks(modifyThreadGroup, stopThread, true);
	    testGroup.stop();
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
	}
	finally {
	  sm.uninstall();
	}

	sm = new SpecialSecurityManager(harness);
	try {
	  sm.install();

	  // throwpoint: java.lang.ThreadGroup-destroy
	  harness.checkPoint("destroy");
	  try {
	    sm.prepareChecks(modifyThreadGroup);
	    nonSystemGroup.destroy();
	    sm.checkAllChecked(harness);
	  }
	  catch (SecurityException ex) {
	    harness.debug(ex);
	    harness.check(false, "unexpected check");
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
  }

  public static class SpecialSecurityManager extends TestSecurityManager2
  {
    public SpecialSecurityManager(TestHarness harness)
    {
      super(harness);
    }
    
    public void checkAccess(ThreadGroup g)
    {
      checkPermission(new RuntimePermission("modifyThreadGroup"));
    }
  }
}
