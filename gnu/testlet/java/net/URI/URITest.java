// Tags: JDK1.4

/*
   Copyright (C) 2005 Michael Koch <konqueror@gmx.de>

   This file is part of Mauve.

   Mauve is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   Mauve is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Mauve; see the file COPYING.  If not, write to
   the Free Software Foundation, 59 Temple Place - Suite 330,
   Boston, MA 02111-1307, USA.
*/

package gnu.testlet.java.net.URI;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.net.*;

public class URITest implements Testlet
{
  public void test(TestHarness h)
  {
    try
      {
	URI uri;
	
	h.checkPoint("mauve://user:passwd@hostname:1234/path/to/file?query=value#fragment");
	
	uri = new URI("mauve://user:passwd@hostname:1234/path/to/file?query=value#fragment");

	h.check(uri.getAuthority(), "user:passwd@hostname:1234"); // 1
	h.check(uri.getFragment(), "fragment"); // 2
	h.check(uri.getHost(), "hostname"); // 3
	h.check(uri.getPath(), "/path/to/file"); // 4
	h.check(uri.getPort(), 1234); // 5
	h.check(uri.getQuery(), "query=value"); // 6
	h.check(uri.getRawAuthority(), "user:passwd@hostname:1234"); // 7
	h.check(uri.getRawFragment(), "fragment"); // 8
	h.check(uri.getRawPath(), "/path/to/file"); // 9
	h.check(uri.getRawQuery(), "query=value"); // 10
	h.check(uri.getRawSchemeSpecificPart(), "//user:passwd@hostname:1234/path/to/file?query=value"); // 11
	h.check(uri.getRawUserInfo(), "user:passwd"); // 12
	h.check(uri.getScheme(), "mauve"); // 13
	h.check(uri.getSchemeSpecificPart(), "//user:passwd@hostname:1234/path/to/file?query=value"); // 14
	h.check(uri.getUserInfo(), "user:passwd"); // 15
	h.check(uri.toString(), "mauve://user:passwd@hostname:1234/path/to/file?query=value#fragment"); // 16

	h.checkPoint("g:h");
	
	uri = new URI("g:h");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), null); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), null); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "h"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), "g"); // 13
	h.check(uri.getSchemeSpecificPart(), "h"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g:h"); // 16
	
	h.checkPoint("g");
	
	uri = new URI("g");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "g"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "g"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "g"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g"); // 16
	
	h.checkPoint("./g");
	
	uri = new URI("./g");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "./g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "./g"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "./g"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "./g"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "./g"); // 16
	
	h.checkPoint("g/");
	
	uri = new URI("g/");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "g/"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "g/"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "g/"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "g/"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g/"); // 16
	
	h.checkPoint("/g");
	
	uri = new URI("/g");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "/g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "/g"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "/g"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "/g"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "/g"); // 16
	
	h.checkPoint("//g");
	
	uri = new URI("//g");

	h.check(uri.getAuthority(), "g"); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), "g"); // 3
	h.check(uri.getPath(), ""); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), "g"); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), ""); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "//g"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "//g"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "//g"); // 16
	
	h.checkPoint("?y");
	
	uri = new URI("?y");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), ""); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), "y"); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), ""); // 9
	h.check(uri.getRawQuery(), "y"); // 10
	h.check(uri.getRawSchemeSpecificPart(), "?y"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "?y"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "?y"); // 16
	
	h.checkPoint("g?y");
	
	uri = new URI("g?y");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), "y"); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "g"); // 9
	h.check(uri.getRawQuery(), "y"); // 10
	h.check(uri.getRawSchemeSpecificPart(), "g?y"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "g?y"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g?y"); // 16
	
	h.checkPoint("#s");
	
	uri = new URI("#s");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), "s"); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), ""); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), "s"); // 8
	h.check(uri.getRawPath(), ""); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), ""); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), ""); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "#s"); // 16
	
	h.checkPoint("g#s");
	
	uri = new URI("g#s");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), "s"); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), "s"); // 8
	h.check(uri.getRawPath(), "g"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "g"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "g"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g#s"); // 16
	
	h.checkPoint("g?y#s");
	
	uri = new URI("g?y#s");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), "s"); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), "y"); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), "s"); // 8
	h.check(uri.getRawPath(), "g"); // 9
	h.check(uri.getRawQuery(), "y"); // 10
	h.check(uri.getRawSchemeSpecificPart(), "g?y"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "g?y"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g?y#s"); // 16
	
	h.checkPoint(";x");
	
	uri = new URI(";x");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), ";x"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), ";x"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), ";x"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), ";x"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), ";x"); // 16
	
	h.checkPoint("g;x");
	
	uri = new URI("g;x");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "g;x"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "g;x"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "g;x"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "g;x"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g;x"); // 16
	
	h.checkPoint("g;x?y#s");
	
	uri = new URI("g;x?y#s");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), "s"); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "g;x"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), "y"); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), "s"); // 8
	h.check(uri.getRawPath(), "g;x"); // 9
	h.check(uri.getRawQuery(), "y"); // 10
	h.check(uri.getRawSchemeSpecificPart(), "g;x?y"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "g;x?y"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "g;x?y#s"); // 16
	
	h.checkPoint(".");
	
	uri = new URI(".");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "."); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "."); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "."); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "."); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "."); // 16
	
	h.checkPoint("./");
	
	uri = new URI("./");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "./"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "./"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "./"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "./"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "./"); // 16
	
	h.checkPoint("..");
	
	uri = new URI("..");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), ".."); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), ".."); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), ".."); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), ".."); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), ".."); // 16
	
	h.checkPoint("../");
	
	uri = new URI("../");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "../"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "../"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "../"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "../"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "../"); // 16
	
	h.checkPoint("../g");
	
	uri = new URI("../g");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "../g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "../g"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "../g"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "../g"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "../g"); // 16
	
	h.checkPoint("../..");
	
	uri = new URI("../..");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "../.."); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "../.."); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "../.."); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "../.."); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "../.."); // 16
	
	h.checkPoint("../../");
	
	uri = new URI("../../");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "../../"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "../../"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "../../"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "../../"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "../../"); // 16
	
	h.checkPoint("../../g");
	
	uri = new URI("../../g");

	h.check(uri.getAuthority(), null); // 1
	h.check(uri.getFragment(), null); // 2
	h.check(uri.getHost(), null); // 3
	h.check(uri.getPath(), "../../g"); // 4
	h.check(uri.getPort(), -1); // 5
	h.check(uri.getQuery(), null); // 6
	h.check(uri.getRawAuthority(), null); // 7
	h.check(uri.getRawFragment(), null); // 8
	h.check(uri.getRawPath(), "../../g"); // 9
	h.check(uri.getRawQuery(), null); // 10
	h.check(uri.getRawSchemeSpecificPart(), "../../g"); // 11
	h.check(uri.getRawUserInfo(), null); // 12
	h.check(uri.getScheme(), null); // 13
	h.check(uri.getSchemeSpecificPart(), "../../g"); // 14
	h.check(uri.getUserInfo(), null); // 15
	h.check(uri.toString(), "../../g"); // 16
      }
    catch (URISyntaxException e)
      {
	h.debug(e);
	h.fail("unexpected exception");
      }
  }
}
