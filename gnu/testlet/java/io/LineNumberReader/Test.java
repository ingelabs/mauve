/*************************************************************************
/* Test.java -- Tests LineNumberReader
/*
/* Copyright (c) 1998 Free Software Foundation, Inc.
/* Written by Aaron M. Renn (arenn@urbanophile.com)
/*
/* This program is free software; you can redistribute it and/or modify
/* it under the terms of the GNU General Public License as published 
/* by the Free Software Foundation, either version 2 of the License, or
/* (at your option) any later version.
/*
/* This program is distributed in the hope that it will be useful, but
/* WITHOUT ANY WARRANTY; without even the implied warranty of
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/* GNU General Public License for more details.
/*
/* You should have received a copy of the GNU General Public License
/* along with this program; if not, write to the Free Software Foundation
/* Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307 USA
/*************************************************************************/

// Tags: JDK1.1

package gnu.testlet.java.io.LineNumberReader;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.io.*;

public class Test implements Testlet
{

public void
test(TestHarness harness)
{
  try
    {
      String str = "In 6th grade I had a crush on this girl named Leanne\n" +
        "Dean.  I thought she was pretty hot.  I saw her at my ten year\n" +
        "high school reunion.  I still think she's pretty hot.  (She's\n" +
        "married to my brother's college roommate).\n";

      StringReader sbr = new StringReader(str);
      LineNumberReader lnr = new LineNumberReader(sbr);

      lnr.setLineNumber(2);

      char[] buf = new char[32];
      int chars_read; 
      while ((chars_read = lnr.read(buf)) != -1)
        {
          str = new String(buf, 0, chars_read);
          if (str.indexOf("\r") != -1)
            {
              harness.debug("\nFound unexpected \\r");
              harness.check(false);
            } 
          harness.debug(str, false);
        }

      harness.check(lnr.getLineNumber(), 6, "getLineNumber - first series");
    }
  catch(IOException e)
    {
      harness.debug(e);
      harness.check(false);
    }

  try
    {
      String str = "Exiting off the expressway in Chicago is not an easy\n" +
        "thing to do.  For example, at Fullerton you have to run a\n" +
        "gauntlet of people selling flowers, begging for money, or trying\n" +
        "to 'clean' your windshield for tips.";

      StringReader sbr = new StringReader(str);
      LineNumberReader lnr = new LineNumberReader(sbr);

      char[] buf = new char[32];
      int chars_read; 
      while ((chars_read = lnr.read(buf)) != -1)
        harness.debug(new String(buf, 0, chars_read), false);
      harness.debug("");

      harness.check(lnr.getLineNumber(), 3, "getLineNumber - second test");
    }
  catch(IOException e)
    {
      harness.debug(e);
      harness.check(false);
    }
}

} // class Test

