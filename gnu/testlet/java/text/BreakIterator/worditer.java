// Test character iteration of BreakIterator.

// Copyright (c) 1999  Cygnus Solutions
// Written by Tom Tromey <tromey@cygnus.com>

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

// Tags: JDK1.1

package gnu.testlet.java.text.BreakIterator;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;

import java.text.BreakIterator;
import java.util.Locale;

public class worditer implements Testlet
{
  public void check (String in, String[] out, BreakIterator bi,
		     TestHarness harness)
  {
    bi.setText (in);
    harness.checkPoint (in);

    int index = 0;
    int from = bi.current();
    harness.check (from, 0);
    while (true)
      {
	int to = bi.next();
	if (to == BreakIterator.DONE)
	  break;
	harness.check (in.substring (from, to), out[index]);
	++index;
	from = to;
      }

    harness.check (index, out.length);
  }

  public void test (TestHarness harness)
  {
    // Just to be explicit: we're only testing the US locale here.
    Locale loc = Locale.US;
    Locale.setDefault (loc);

    BreakIterator bi = BreakIterator.getWordInstance (loc);

    String[] r1 = { "How", " ", "much", " ", "time", " ", "is", " ",
		    "left", "?", "  ", "We", " ", "don't", " ",
		    "know", "." };
    check ("How much time is left?  We don't know.", r1,
	   bi, harness);

    String[] r2 = { "I", " ", "am", " ", "not", "!" };
    check ("I am not!", r2, bi, harness);

    String[] r3 = { "\u2029", "X" };
    check ("\u2029X", r3, bi, harness);
  }
}
