/* getHeaderFields.java --
   Copyright (c) 2004 by Free Software Foundation, Inc.
   Written by Michael Koch <konqueror@gmx.de>

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

import java.io.IOException;
import java.net.*;
import java.util.*;

public class getHeaderFields implements Testlet
{
  private void check(TestHarness h, String urlString)
  {
    try
      {
	URL url = new URL(urlString);
	URLConnection conn = url.openConnection();
	conn.connect();

	Map headers = conn.getHeaderFields();
    
	h.check(headers != null, "returned valid Map object");

	if (! headers.isEmpty())
	  {
	    Map.Entry entry = (Map.Entry) headers.entrySet().toArray()[0];
	    h.check(entry.getKey() instanceof String, "key is instance of java.lang.String");
	    h.check(entry.getValue() instanceof List, "value is instance of java.util.List");
	  }
      }
    catch (MalformedURLException e)
      {
	// Ignored. We know the URIs are valid.
      }
    catch (IOException e)
      {
	h.fail("Test failed for " + urlString);
      }
  }
  
  public void test(TestHarness h)
  {
    // Returns a map of headers.
    check(h, "http://www.gnu.org/");

    // Returns an empty map.
    check(h, "ftp://ftp.gnu.org");

    // FIXME: We should check jar: and other protocol implementations here too.
  }
}
