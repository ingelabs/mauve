// Tags: JDK1.2

// Copyright (C) 2002 Free Software Foundation, Inc.
// Written by Mark Wielaard (mark@klomp.org)

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

package gnu.testlet.java.net.URLClassLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class getResource implements Testlet
{
  protected TestHarness harness;
  private File tmpdir, tmpfile, subtmpdir, subtmpfile;
  private String jarPath;
  protected URLClassLoader ucl;

  private void setup() throws IOException
  {
    // Setup
    String tmp = harness.getTempDirectory();
    tmpdir = new File(tmp + File.separator + "mauve-testdir");
    if (!tmpdir.mkdir() && !tmpdir.exists())
      throw new IOException("Could not create: " + tmpdir);

    tmpfile = new File(tmpdir, "testfile");
    if (!tmpfile.delete() && tmpfile.exists())
      throw new IOException("Could not remove (old): " + tmpfile);
    tmpfile.createNewFile();

    subtmpdir = new File(tmpdir, "testdir");
    if (!subtmpdir.mkdir() && !subtmpdir.exists())
      throw new IOException("Could not create: " + subtmpdir);

    subtmpfile = new File(subtmpdir, "test");
    if (!subtmpfile.delete() && subtmpfile.exists())
      throw new IOException("Could not remove (old): " + subtmpfile);
    subtmpfile.createNewFile();

    jarPath = tmp + File.separator + "m.jar";
    FileOutputStream fos = new FileOutputStream(jarPath);
    JarOutputStream jos = new JarOutputStream(fos);

    JarEntry je = new JarEntry("jresource");
    jos.putNextEntry(je);
    jos.write(new byte[256]);

    je = new JarEntry("path/in/jar/file");
    jos.putNextEntry(je);
    jos.write(new byte[256]);
    jos.close();
    fos.close();
  }

  private void tearDown()
  {
    if (tmpdir != null && tmpdir.exists())
      {
	if (tmpfile != null && tmpfile.exists())
	  tmpfile.delete();

	if (subtmpdir != null && subtmpdir.exists())
	  {
	    if (subtmpfile != null && subtmpfile.exists())
	      subtmpfile.delete();
	    subtmpdir.delete();
	  }
	tmpdir.delete();
      }

    if (jarPath != null)
      new File(jarPath).delete();
  }

  public void test (TestHarness h)
  {
    harness = h;

    try
      {
	setup();

	URL[] urls = new URL[2];
	urls[0] = tmpdir.toURL();
	urls[1] = new URL("file://" + jarPath);
	ucl = URLClassLoader.newInstance(urls);

	URL u = ucl.getResource(".");
	harness.debug(u != null ? u.toString() : null);
	harness.check(u == null, "no .");

	u = ucl.getResource("..");
	harness.debug(u != null ? u.toString() : null);
	harness.check(u == null, "no ..");

	check("testfile");
	check("testdir/test");
	check("jresource");
	check("path/in/jar/file");
      }
    catch(IOException ioe)
      {
	harness.fail("Unexpected exception: " + ioe);
	harness.debug(ioe);
      }
    finally
      {
	tearDown();
      }
  }

  protected void check(String resource)
  {
    int index = resource.lastIndexOf('/');
    String name, path, dir;
    if (index != -1)
      {
	name = resource.substring(index+1);
	path = resource.substring(0, index);
	index = path.lastIndexOf('/');
	path = path + '/';
	if (index != -1)
	  dir = path.substring(index);
	else
	  dir = '/' + path;
      }
    else
      {
	path = "";
	name = resource;
	dir = "";
      }

    harness.debug(" == resource='" + resource
		    + "'; name='" + name
		    + "'; dir='" + dir
		    + "'; path='" + path + "'");

    URL u = ucl.getResource(resource);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u != null, resource);

    u = ucl.getResource("no-" + resource);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u == null, "no-" + resource);

    u = ucl.getResource(path + '/' + "no-" + name);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u == null, path + '/' + "no-" + name);

    u = ucl.getResource("./" + resource);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u != null, "./" + resource);

    u = ucl.getResource(path + "./" + name);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u != null, path + "./" + name);

    u = ucl.getResource(".\\" + resource);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u == null, "no .\\" + resource);

    if (!dir.equals(""))
      {
	u = ucl.getResource(path + ".." + dir + name);
	harness.debug(u != null ? u.toString() : null);
	harness.check(u != null, path + ".." + dir + name);
      }

    if (!path.equals(""))
      {
	u = ucl.getResource(path + "//" + name);
	harness.debug(u != null ? u.toString() : null);
	harness.check(u != null, path + "//" + name);
      }

    u = ucl.getResource("\\" + resource);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u == null, "no \\" + resource);

    u = ucl.getResource(":" + resource);
    harness.debug(u != null ? u.toString() : null);
    harness.check(u == null, "no :" + resource);
  }
}
