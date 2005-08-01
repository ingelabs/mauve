// Tags: JDK1.2

// Copyright (C) 2005 Free Software Foundation, Inc.
// Written by Jeroen Frijters  <jeroen@frijters.net>

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

package gnu.testlet.java.lang.ClassLoader;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

public class initialize implements Testlet
{
  static class TestLoader extends ClassLoader
  {
    static TestLoader ref;
    static ClassLoader throwException() { throw new Error(); }
    TestLoader() { super(throwException()); }
    protected void finalize() { ref = this; }

    static void runTests(TestHarness harness) throws Exception
    {
      harness.checkPoint("loadClass");
      try
      {
        ref.loadClass("java.lang.Object");
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      try
      {
        ref.loadClass("java.lang.Object", false);
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      harness.checkPoint("findClass");
      try
      {
        ref.findClass("java.lang.Object");
        harness.check(false);
      }
      catch(ClassNotFoundException _)
      {
        harness.check(true);
      }

      harness.checkPoint("defineClass");
      try
      {
        ref.defineClass(new byte[0], 0, 0);
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      try
      {
        ref.defineClass("Foo", new byte[0], 0, 0);
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      try
      {
        ref.defineClass("Foo", new byte[0], 0, 0, null);
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      harness.checkPoint("resolveClass");
      try
      {
        ref.resolveClass(String.class);
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      harness.checkPoint("findSystemClass");
      try
      {
        ref.findSystemClass("java.lang.Object");
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      harness.checkPoint("setSigners");
      try
      {
        ref.setSigners(String.class, new Object[0]);
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      harness.checkPoint("findLoadedClass");
      try
      {
        ref.findLoadedClass("java.lang.Object");
        harness.check(false);
      }
      catch(SecurityException _)
      {
        harness.check(true);
      }

      harness.checkPoint("definePackage");
      try
      {
        ref.definePackage("Foo", "", "", "", "", "", "", null);
        harness.check(false);
      }
      catch(NullPointerException _)
      {
        harness.check(true);
      }

      try
      {
        ref.getPackage("Foo");
        harness.check(false);
      }
      catch(NullPointerException _)
      {
        harness.check(true);
      }

      try
      {
        ref.getPackages();
        harness.check(false);
      }
      catch(NullPointerException _)
      {
        harness.check(true);
      }

    }
  }

  public void test(TestHarness harness)
  {
    try { new TestLoader(); } catch(Error x) {}
    System.gc();
    System.runFinalization();
    if (TestLoader.ref == null)
      harness.debug("Unable to obtain finalized ClassLoader instance");
    else
    {
      try
      {
        TestLoader.runTests(harness);
      }
      catch(Exception x)
      {
        harness.debug(x);
        harness.check(false);
      }
    }
  }
}
