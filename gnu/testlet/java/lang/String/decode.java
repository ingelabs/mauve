// Tags: JDK1.0

// Copyright (C) 1998 Cygnus Solutions

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

package gnu.testlet.java.lang.String;
import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.UnsupportedEncodingException;

public class decode implements Testlet
{
  public void test (TestHarness harness)
    {
      byte[] bstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };
      char[] cstr = { 'a', 'b', 'c', '\t', 'A', 'B', 'C', ' ', '1', '2', '3' };

      String a = new String(bstr);
      String b = new String(bstr, 3, 3);
      String c = "";
      String d = "";

      try
	{
	  c = new String(bstr, "8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	}

      try
	{
	  d = new String(bstr, 3, 3, "8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	}

      harness.check (a, "abc	ABC 123");
      harness.check (b, "	AB");
      harness.check (c, "abc	ABC 123");
      harness.check (d, "	AB");

      boolean ok = false;
      try
	{
	  c = new String(bstr, "foobar8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      ok = false;
      try
	{
	  d = new String(bstr, 3, 3, "foobar8859_1");
	}
      catch (UnsupportedEncodingException ex)
	{
	  ok = true;
	}
      harness.check (ok);

      harness.check (String.copyValueOf(cstr), "abc	ABC 123");
      harness.check (String.copyValueOf(cstr, 3, 3), "	AB");
    }
}
