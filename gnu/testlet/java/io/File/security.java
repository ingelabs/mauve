// Tags: JDK1.2

// Copyright (C) 2003 Red Hat, Inc.
// Copyright (C) 2004 Stephen Crawley.
// Written by Tom Tromey <tromey@redhat.com>
// Extensively modified by Stephen Crawley <crawley@dstc.edu.au>

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
import java.security.Permission;
import java.util.PropertyPermission;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import gnu.testlet.TestSecurityManager2;

public class security implements Testlet
{
  public void test (TestHarness harness)
  {
    // Setup
    String tmp = harness.getTempDirectory();
    File tmpdir = new File(tmp + File.separator + "mauve-testdir");
    harness.check(tmpdir.mkdir() || tmpdir.exists(), "temp directory");
    File tmpdir2 = new File(tmpdir, "nested-dir");
    harness.check(tmpdir2.mkdir() || tmpdir2.exists(), "temp directory 2");
    File tmpfile = new File(tmpdir, "testfile");
    harness.check(tmpfile.delete() || !tmpfile.exists(), "no temp file");
    File tmpfile2 = new File(tmpdir, "testfile2");
    harness.check(tmpfile2.delete() || !tmpfile2.exists());

    Permission[] noPerms = new Permission[0];
    Permission tmpdirReadPerm =
      new FilePermission(tmpdir.toString(), "read");
    Permission tmpdirWritePerm =
      new FilePermission(tmpdir.toString(), "write");
    Permission tmpdirDeletePerm =
      new FilePermission(tmpdir.toString(), "delete");

    Permission tmpdir2WritePerm =
      new FilePermission(tmpdir2.toString(), "write");
    Permission tmpdir2DeletePerm =
      new FilePermission(tmpdir2.toString(), "delete");

    Permission tmpfileReadPerm =
      new FilePermission(tmpfile.toString(), "read");
    Permission tmpfileWritePerm =
      new FilePermission(tmpfile.toString(), "write");
    Permission tmpfileDeletePerm =
      new FilePermission(tmpfile.toString(), "delete");

    Permission tmpallWritePerm =
      new FilePermission(tmp + File.separator + "*", "write");
    Permission tmpdirallWritePerm =
      new FilePermission(tmpdir.toString() + File.separator + "*", "write");
    Permission tmpfile2WritePerm =
      new FilePermission(tmpfile2.toString(), "write");

    Permission rootReadPerm =
      new FilePermission(File.separator, "read");

    Permission tmpdirPropPerm =
      new PropertyPermission("java.io.tmpdir", "read");

    TestSecurityManager2 sm = new TestSecurityManager2(harness);

    // Keep a record of created temp files so we can delete them later.
    File tf1 = null;
    File tf2 = null;
    try {
      sm.install();
	
      harness.checkPoint("dir.canWrite");
      try {
	sm.prepareChecks(new Permission[]{tmpdirWritePerm}, noPerms);
	tmpdir.canWrite();
	sm.checkAllChecked(harness);
      }
      catch (SecurityException ex) {
	harness.debug(ex);
	harness.check(false, "dir.canWrite - unexpected exception");
      }

      harness.checkPoint("dir.canRead");
      try {
	sm.prepareChecks(new Permission[]{tmpdirReadPerm}, noPerms);
	tmpdir.canRead();
	sm.checkAllChecked(harness);
      }
      catch (SecurityException ex) {
	harness.debug(ex);
	harness.check(false, "dir.canRead - unexpected exception");
      }

      harness.checkPoint("file.createNewFile");
      try {
	sm.prepareChecks(new Permission[]{tmpfileWritePerm}, noPerms);
	tmpfile.createNewFile();
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "file.createNewFile - unexpected exception");
      }

      harness.checkPoint("file.delete");
      try {
	sm.prepareChecks(new Permission[]{tmpfileDeletePerm}, noPerms);
	tmpfile.delete();
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "file.delete - unexpected exception");
      }

      harness.checkPoint("dir.list(null)");
      try {
	sm.prepareChecks(new Permission[]{tmpdirReadPerm}, noPerms);
	tmpdir.list(null);
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "dir.list(null) - unexpected exception");
      }

      harness.checkPoint("dir.list()");
      try {
	sm.prepareChecks(new Permission[]{tmpdirReadPerm}, noPerms);
	tmpdir.list();
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "dir.list() - unexpected exception");
      }

      harness.checkPoint("dir.listFiles()");
      try {
	sm.prepareChecks(new Permission[]{tmpdirReadPerm}, noPerms);
	tmpdir.listFiles();
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "dir.listFiles() - unexpected exception");
      }

      harness.checkPoint("dir.listFiles(FilenameFilter)");
      try {
	sm.prepareChecks(new Permission[]{tmpdirReadPerm}, noPerms);
	tmpdir.listFiles((FilenameFilter) null);
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false,
		      "dir.listFiles(FilenameFilter) - unexpected exception");
      }

      harness.checkPoint("dir.listFiles(FileFilter)");
      try {
	sm.prepareChecks(new Permission[]{tmpdirReadPerm}, noPerms);
	tmpdir.listFiles((FileFilter) null);
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false,
		      "dir.listFiles(FileFilter) - unexpected exception");
      }

      harness.checkPoint("createTempFile(2-args)");
      try {
	sm.prepareChecks(new Permission[]{tmpallWritePerm},
			 new Permission[]{tmpdirPropPerm});
	tf1 = File.createTempFile("pfx", "sfx");
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "createTempFile(2-args) - unexpected exception");
      }

      harness.checkPoint("createTempFile(3-args)");
      try {
	sm.prepareChecks(new Permission[]{tmpdirallWritePerm}, noPerms);
	tf2 = File.createTempFile("pfx", "sfx", tmpdir);
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "createTempFile(3-args) - unexpected exception");
      }

      harness.checkPoint("dir.setReadOnly");
      try {
	sm.prepareChecks(new Permission[]{tmpdir2WritePerm}, noPerms);
	tmpdir2.setReadOnly();
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "dir.setReadOnly - unexpected exception");
      }

      // Make sure we remove the read only temp dir
      harness.checkPoint("dir.delete");
      try {
	sm.prepareChecks(new Permission[]{tmpdir2DeletePerm}, noPerms);
	tmpdir2.delete();
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "dir.delete - unexpected exception");
      }
      
      harness.checkPoint("listRoots()");
      try {
	// sm.prepareChecks(new Permission[]{rootReadPerm}, noPerms);
	sm.prepareChecks(noPerms, noPerms);
	File[] roots = File.listRoots();
	// harness.check(roots.length, 1, "File.listRoots()");
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "listRoots() - unexpected exception");
      }

      harness.checkPoint("file.renameTo");
      try {
	sm.prepareChecks(new Permission[]{tmpfileWritePerm, 
					  tmpfile2WritePerm}, 
			 noPerms);
	tmpfile.renameTo(tmpfile2);
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "file.renameTo - unexpected exception");
      }

      harness.checkPoint("dir.setLastModified()");
      try {
	sm.prepareChecks(new Permission[]{tmpdirWritePerm}, noPerms);
	tmpdir.setLastModified(0);
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "dir.setLastModified() - unexpected exception");
      }

      harness.checkPoint("dir.deleteOnExit()");
      try {
	sm.prepareChecks(new Permission[]{tmpdirDeletePerm}, noPerms);
	tmpdir.deleteOnExit();
	sm.checkAllChecked(harness);
      }
      catch (Exception ex) {
	harness.debug(ex);
	harness.check(false, "dir.deleteOnExit() - unexpected exception");
      }
    }
    catch (Throwable ex) {
      harness.debug(ex);
      harness.check(false, "outer handler - unexpected exception");
    }
    finally {
      sm.uninstall();

      if (tmpfile != null) tmpfile.delete();
      if (tmpfile2 != null) tmpfile2.delete();
      if (tf1 != null) tf1.delete();
      if (tf2 != null) tf2.delete();
      if (tmpdir != null) tmpdir.delete();
    }
  }
}
