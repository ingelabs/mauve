// Tags: JDK1.2

/* Jar.java -- Tests Jar URL connection

   Copyright (c) 2005 by Free Software Foundation, Inc.
   Written by Tom Tromey <tromey@redhat.com>

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published
   by the Free Software Foundation, version 2. (see COPYING)

   This program is distributed in the hope that it will be useful, but
   WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software Foundation
   Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307 USA */

package gnu.testlet.java.net.URLConnection;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.net.*;
import java.io.*;

public class Jar implements Testlet
{
  public void test(TestHarness harness)
  {
    harness.checkPoint("jar: URL with missing entry");
    boolean ok = false;
    try
      {
	File jarfile = harness.getResourceFile("gnu#testlet#java#util#jar#JarFile#jartest.jar");
	String filename = jarfile.toString();

	URL url = new URL("jar:file:" + filename + "!/nosuchfile.txt");

	InputStream is = url.openStream();
      }
    catch (FileNotFoundException _)
      {
	ok = true;
      }
    catch (Throwable e)
      {
	harness.debug(e);
      }
    harness.check(ok);
  }
}
