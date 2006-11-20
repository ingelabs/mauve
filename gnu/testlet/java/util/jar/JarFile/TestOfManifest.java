/* TestOfManifest.java
   Copyright (C) 2006 Free Software Foundation, Inc.
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

// Tags: JDK1.4

package gnu.testlet.java.util.jar.JarFile;

import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.io.File;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Simple test for validating Marco Trudel's patch for parsing long file names
 * in a Jar file's manifest.
 */
public class TestOfManifest
    implements Testlet
{
  private static final String FILENAME = "jfaceSmall.jar";
  private static final String FILEPATH = "gnu#testlet#java#util#jar#JarFile#"
      + FILENAME;

  /* (non-Javadoc)
   * @see gnu.testlet.Testlet#test(gnu.testlet.TestHarness)
   */
  public void test(TestHarness harness)
  {
    checkManifestEntries(harness);
  }

  private void checkManifestEntries(TestHarness harness)
  {
    harness.checkPoint("checkManifestEntries");
    try
      {
        File file = harness.getResourceFile(FILEPATH);
        JarFile jarFile = new JarFile(file);
        readEntries(jarFile); // will parse the signatures
        boolean ok = readCertificates(harness, jarFile);
        harness.check(ok, "Jar entry MUST be signed");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("checkManifestEntries: " + x);
      }
  }

  private static void readEntries(JarFile jarFile) throws Exception
  {
    Enumeration entries = jarFile.entries();
    while (entries.hasMoreElements())
      {
        JarEntry entry = (JarEntry) entries.nextElement();
        InputStream stream = null;
        try
          {
            stream = jarFile.getInputStream(entry);
            byte[] ba = new byte[8192];
            while (true)
              {
                int ret = stream.read(ba);
                if (ret < 0)
                  break;
              }
          }
        finally
          {
            if (stream != null)
              try
                {
                  stream.close();
                }
              catch (Exception ignored)
                {
                }
          }
      }
  }

  private boolean readCertificates(TestHarness harness, JarFile jarFile)
  {
    for (Enumeration entries = jarFile.entries(); entries.hasMoreElements();)
      {
        JarEntry entry = (JarEntry) entries.nextElement();
        if (entry.isDirectory())
          continue;
        Certificate[] certs = entry.getCertificates();
        if (certs == null || certs.length == 0) // No certificate
          {
            if (! entry.getName().startsWith("META-INF"))
              {
                harness.verbose("Entry " + entry.getName() + " in jar file "
                                + FILENAME + " does not have a certificate");
                return false;
              }
          }
      }
    return true;
  }
}
