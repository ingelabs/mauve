// Copyright (C) 2007 Andrew John Hughes <gnu_andrew@member.fsf.org>

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

// Tags: JDK1.5

package gnu.testlet.javax.management.ObjectName;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

public class Parsing implements Testlet
{
  public void test(TestHarness h)
  {
    try
      {
	h.checkPoint("Default name");
	ObjectName name = new ObjectName("*:*");
	h.check(true);
	h.checkPoint("Mixed keys and wildcards");
	name =
	  new ObjectName("jboss.management.local:j2eeType=ServiceModule,*,name=jbossmq-httpil.sar");
	h.check(true);
	h.checkPoint("Match any domain with specific keys");
	name = new ObjectName("*:library=Classpath,project=GNU");
	h.check(true);
	h.checkPoint("Match any domain with specific keys and wildcard at end");
	name = new ObjectName("*:library=Classpath,project=GNU,*");
	h.check(true);
	h.checkPoint("Match the FSF domain with specific keys and wildcard at end");
	name = new ObjectName("fsf:library=Classpath,project=GNU,*");
	h.check(true);
	h.checkPoint("Match the FSF domain with specific keys");
	name = new ObjectName("fsf:library=Classpath,project=GNU");
	h.check(true);
      }
    catch (MalformedObjectNameException e)
      {
	h.debug(e);
      }
  }
}

