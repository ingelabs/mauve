// Tags: JDK1.2

// Copyright (C) 2003 Red Hat, Inc.
// Written by Tom Tromey <tromey@redhat.com>

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

package gnu.testlet.java.io.File;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FilePermission;
import java.io.FilenameFilter;
import java.io.FileFilter;


import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import gnu.testlet.TestSecurityManager;

public class security implements Testlet
{
  public void test (TestHarness harness)
  {
    // Setup
    String tmp = harness.getTempDirectory();
    File tmpdir = new File(tmp + File.separator + "mauve-testdir");
    harness.check(tmpdir.mkdir() || tmpdir.exists(), "temp directory");
    File tmpfile = new File(tmpdir, "testfile");
    harness.check(tmpfile.delete() || !tmpfile.exists(), "no temp file");
    File tmpfile2 = new File(tmpdir, "testfile2");
    harness.check(tmpfile2.delete() || !tmpfile2.exists());

    System.setSecurityManager(new TestSecurityManager());

    boolean ok;

    ok = false;
    try
      {
	tmpdir.canWrite();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.canWrite()");

    ok = false;
    try
      {
	tmpdir.canRead();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.canRead()");

    ok = false;
    try
      {
	tmpfile.createNewFile();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    catch (IOException ignore)
      {
      }
    harness.check(ok, "file.createNewFile()");

    ok = false;
    try
      {
	tmpfile.delete();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "file.delete()");

    ok = false;
    try
      {
	tmpdir.list(null);
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.list(null)");

    ok = false;
    try
      {
	tmpdir.list();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.list()");

    ok = false;
    try
      {
	tmpdir.listFiles();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.listFiles()");

    ok = false;
    try
      {
	tmpdir.listFiles((FilenameFilter) null);
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.listFiles(FilenameFilter)");

    ok = false;
    try
      {
	tmpdir.listFiles((FileFilter) null);
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.listFiles(FileFilter)");

    ok = false;
    try
      {
	tmpdir.createTempFile("pfx", "sfx");
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    catch (IOException ignore)
      {
      }
    harness.check(ok, "dir.createTempFile(2-args)");

    ok = false;
    try
      {
	File.createTempFile("pfx", "sfx", tmpdir);
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    catch (IOException ignore)
      {
      }
    harness.check(ok, "File.createTempFile(3-args)");

    ok = false;
    try
      {
	tmpdir.setReadOnly();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.setReadOnly()");

    File[] roots = File.listRoots();
    harness.check(roots.length, 0, "File.listRoots()");

    ok = false;
    try
      {
	tmpfile.renameTo(tmpfile2);
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "file.renameTo()");

    ok = false;
    try
      {
	tmpdir.setLastModified(0);
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.setLastModified()");

    ok = false;
    try
      {
	tmpdir.deleteOnExit();
      }
    catch (SecurityException _)
      {
	ok = true;
      }
    harness.check(ok, "dir.deleteOnExit()");
  }
}
