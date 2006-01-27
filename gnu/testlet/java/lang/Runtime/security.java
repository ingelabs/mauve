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

package gnu.testlet.java.lang.Runtime;

import java.io.File;
import java.io.FilePermission;
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

      Runtime runtime = Runtime.getRuntime();

      String sCommand = "/bin/true";
      harness.check(new File(sCommand).isFile());
      String[] aCommand = new String[] {sCommand};

      Thread thread = new Thread();

      String library_name = "blah";
      String library_path = "/path/to/libnothing.so";

      Permission[] executeCommand = new Permission[] {
	new FilePermission(sCommand, "execute")};

      Permission[] exitVM = new Permission[] {
	new RuntimePermission("exitVM")};

      Permission[] shutdownHooks = new Permission[] {
	new RuntimePermission("shutdownHooks")};

      Permission[] loadLibrary_name = new Permission[] {
	new RuntimePermission("loadLibrary." + library_name)};
      Permission[] loadLibrary_path = new Permission[] {
	new RuntimePermission("loadLibrary." + library_path)};

      Permission[] noPerms = new Permission[] {};

      TestSecurityManager2 sm = new TestSecurityManager2(harness);
      try {
	sm.install();

	// security: java.lang.Runtime-exec(String)
	harness.checkPoint("exec(String)");
	try {
	  sm.prepareChecks(executeCommand, noPerms);
	  runtime.exec(sCommand).waitFor();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-exec(String, String[])
	harness.checkPoint("exec(String, String[])");
	try {
	  sm.prepareChecks(executeCommand, noPerms);
	  runtime.exec(sCommand, null).waitFor();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-exec(String, String[], File)
	harness.checkPoint("exec(String, String[], File)");
	try {
	  sm.prepareChecks(executeCommand, noPerms);
	  runtime.exec(sCommand, null, null).waitFor();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-exec(String[])
	harness.checkPoint("exec(String[])");
	try {
	  sm.prepareChecks(executeCommand, noPerms);
	  runtime.exec(aCommand).waitFor();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-exec(String[], String[])
	harness.checkPoint("exec(String[], String[])");
	try {
	  sm.prepareChecks(executeCommand, noPerms);
	  runtime.exec(aCommand, null).waitFor();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-exec(String[], String[], File)
	harness.checkPoint("exec(String[], String[], File)");
	try {
	  sm.prepareChecks(executeCommand, noPerms);
	  runtime.exec(aCommand, null, null).waitFor();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-exit
	harness.checkPoint("exit");
	try {
	  sm.prepareChecks(exitVM, noPerms, true);
	  runtime.exit(0);
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
	
	// security: java.lang.Runtime-runFinalizersOnExit
	harness.checkPoint("runFinalizersOnExit");
	try {
	  sm.prepareChecks(exitVM, noPerms);
	  Runtime.runFinalizersOnExit(false);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-addShutdownHook
	harness.checkPoint("addShutdownHook");
	try {
	  sm.prepareChecks(shutdownHooks, noPerms);
	  runtime.addShutdownHook(thread);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}
	
	// security: java.lang.Runtime-removeShutdownHook
	harness.checkPoint("removeShutdownHook");
	try {
	  sm.prepareChecks(shutdownHooks, noPerms);
	  runtime.removeShutdownHook(thread);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.Runtime-load
	harness.checkPoint("load");
	try {
	  sm.prepareChecks(loadLibrary_name, noPerms, true);
	  runtime.load(library_name);
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

	// security: java.lang.Runtime-loadLibrary
	harness.checkPoint("loadLibrary");
	try {
	  sm.prepareChecks(loadLibrary_path, noPerms, true);
	  runtime.loadLibrary(library_path);
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
    }
    catch (Throwable ex) {
      harness.debug(ex);
      harness.check(false, "Unexpected exception");
    }
  }
}
