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

package gnu.testlet.java.lang.System;

import java.security.Permission;
import java.util.Properties;
import java.util.PropertyPermission;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import gnu.testlet.TestSecurityManager2;

public class security implements Testlet
{
  public void test(TestHarness harness)
  {
    try {
      harness.checkPoint("setup");

      String library_name = "blah";
      String library_path = "/path/to/libnothing.so";

      String a_variable = "PATH";
      String not_a_variable = "PITH";
      harness.check(System.getenv(a_variable) != null);
      harness.check(System.getenv(not_a_variable) == null);
      
      Properties properties = System.getProperties();

      String a_property = "java.vm.name";
      String not_a_property = "java.monkey.flump";
      harness.check(properties.containsKey(a_property));
      harness.check(!properties.containsKey(not_a_property));
      
      Permission[] exitVM = new Permission[] {
	new RuntimePermission("exitVM")};

      Permission[] loadLibrary_name = new Permission[] {
	new RuntimePermission("loadLibrary." + library_name)};
      Permission[] loadLibrary_path = new Permission[] {
	new RuntimePermission("loadLibrary." + library_path)};

      Permission[] readVariable = new Permission[] {
	new RuntimePermission("getenv." + a_variable)};
      Permission[] readNonVariable = new Permission[] {
	new RuntimePermission("getenv." + not_a_variable)};

      Permission[] readWriteAllProperties = new Permission[] {
	new PropertyPermission("*", "read,write")};

      Permission[] readProperty = new Permission[] {
	new PropertyPermission(a_property, "read")};
      Permission[] readNonProperty = new Permission[] {
	new PropertyPermission(not_a_property, "read")};

      Permission[] setIO = new Permission[] {
	new RuntimePermission("setIO")};
      
      Permission[] writeProperty = new Permission[] {
	new PropertyPermission(a_property, "write")};
      Permission[] writeNonProperty = new Permission[] {
	new PropertyPermission(not_a_property, "write")};

      Permission[] setSecurityManager = new Permission[] {
	new RuntimePermission("setSecurityManager")};
      
      Permission[] noPerms = new Permission[] {};

      TestSecurityManager2 sm = new TestSecurityManager2(harness);
      try {
	sm.install();

	// security: java.lang.System-exit
	harness.checkPoint("exit");
	try {
	  sm.prepareChecks(exitVM, noPerms, true);
	  System.exit(0);
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
	
	// security: java.lang.System-runFinalizersOnExit
	harness.checkPoint("runFinalizersOnExit");
	try {
	  sm.prepareChecks(exitVM, noPerms);
	  System.runFinalizersOnExit(false);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-load
	harness.checkPoint("load");
	try {
	  sm.prepareChecks(loadLibrary_name, noPerms, true);
	  System.load(library_name);
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

	// security: java.lang.System-loadLibrary
	harness.checkPoint("loadLibrary");
	try {
	  sm.prepareChecks(loadLibrary_path, noPerms, true);
	  System.loadLibrary(library_path);
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

	// security: TODO: java.lang.System-getenv()

	// security: java.lang.System-getenv(String)
	harness.checkPoint("getenv(String)");
	try {
	  sm.prepareChecks(readVariable, noPerms);
	  System.getenv(a_variable);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}
	try {
	  sm.prepareChecks(readNonVariable, noPerms);
	  System.getenv(not_a_variable);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-getProperties
	harness.checkPoint("getProperties");
	try {
	  sm.prepareChecks(readWriteAllProperties, noPerms);
	  System.getProperties();
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-setProperties
	harness.checkPoint("setProperties");
	try {
	  sm.prepareChecks(readWriteAllProperties, noPerms);
	  System.setProperties(properties);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-getProperty(String)
	harness.checkPoint("getProperty(String)");
	try {
	  sm.prepareChecks(readProperty, noPerms);
	  System.getProperty(a_property);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}
	try {
	  sm.prepareChecks(readNonProperty, noPerms);
	  System.getProperty(not_a_property);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-getProperty(String, String)
	harness.checkPoint("getProperty(String, String)");
	try {
	  sm.prepareChecks(readProperty, noPerms);
	  System.getProperty(a_property, "quadrant");
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}
	try {
	  sm.prepareChecks(readNonProperty, noPerms);
	  System.getProperty(not_a_property, "blade");
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-setIn
	harness.checkPoint("setIn");
	try {
	  sm.prepareChecks(setIO, noPerms);
	  System.setIn(System.in);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-setOut
	harness.checkPoint("setOut");
	try {
	  sm.prepareChecks(setIO, noPerms);
	  System.setOut(System.out);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-setErr
	harness.checkPoint("setErr");
	try {
	  sm.prepareChecks(setIO, noPerms);
	  System.setErr(System.err);
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: java.lang.System-setProperty
	harness.checkPoint("setProperty");
	try {
	  sm.prepareChecks(writeProperty, noPerms);
	  System.setProperty(a_property, properties.getProperty(a_property));
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}
	try {
	  sm.prepareChecks(writeNonProperty, noPerms);
	  System.setProperty(not_a_property, "hello mum");
	  sm.checkAllChecked(harness);
	}
	catch (SecurityException ex) {
	  harness.debug(ex);
	  harness.check(false, "unexpected check");
	}

	// security: TODO: java.lang.System-clearProperty

	// security: java.lang.System-setSecurityManager
	harness.checkPoint("setSecurityManager");
	try {
	  sm.prepareChecks(setSecurityManager, noPerms);
	  System.setSecurityManager(sm);
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
