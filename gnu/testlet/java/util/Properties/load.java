// Tags: JDK1.1

// Copyright (C) 2000 Red Hat Inc.

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

package gnu.testlet.java.util.Properties;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.util.Properties;
import java.io.*;

public class load implements Testlet
{
  public void test (TestHarness harness)
  {
    Properties p = new Properties ();
    p.setProperty ("a space", "a value");
    p.setProperty ("two  spaces", "spacy  ");

    ByteArrayOutputStream baos = new ByteArrayOutputStream ();
    try
      {
	p.store (baos, null);
      }
    catch (IOException _)
      {
      }

    Properties in = new Properties ();
    ByteArrayInputStream bais = new ByteArrayInputStream (baos.toByteArray ());

    try
      {
	in.load (bais);
      }
    catch (IOException _)
      {
      }

    // Sigh.  Work around gcj bug: Hashtable.equals doesn't work as of
    // Oct 5, 2000.
    // harness.check (in.equals (p));
    harness.check (in.size(), 2);
    harness.check (in.getProperty ("a space"), "a value");
    harness.check (in.getProperty ("two  spaces"), "spacy  ");
  }
}
