/*************************************************************************
/* jdk11.java -- Test JDK 1.1 features in java.text.CollationElementIterator
/*
/* Copyright (c) 2003 C. Brian Jones (cbj@gnu.org)
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

package gnu.testlet.java.text.CollationElementIterator;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.text.CollationElementIterator;
import java.util.Locale;

public class jdk11 implements Testlet
{
  private static final int PRIMARY = 1;
  private static final int SECONDARY = 2;
  private static final int TERTIARY = 3;

  private static TestHarness harness = null;

  public void test(TestHarness harness)
  {
    // FIXME ... add more test strings for the en_US locale
    // FIXME ... add tests for characters that compare equal
    // FIXME ... add tests for other locales
    final String[] TEST_STRINGS = {
      "X",
      "12",
      "abcdefghijklmnopqrstuvwxyz",
      "0123456789",
      " _,;:!?/.`^~'\"()[]{}@$*\\&#%+<=>|",
      "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ",
    };
    final int[] TEST_ORDERS = {
      PRIMARY,
      PRIMARY,
      PRIMARY, 
      PRIMARY,
      PRIMARY,
      TERTIARY,
    };
    
    this.harness = harness;
    try
    {
      // -------- constants -------- 
      harness.check(CollationElementIterator.NULLORDER, -1, "NULLORDER");
  
      RuleBasedCollator en_USCollator = (RuleBasedCollator) 
	Collator.getInstance(new Locale("en", "US", ""));
      CollationElementIterator iterator = 
        en_USCollator.getCollationElementIterator("abcdefg");

      // -------- methods -------- 
      checkOrder(iterator, 7, PRIMARY, "en_US");

      // reset()
      harness.checkPoint("reset()");
      iterator.reset();
      checkOrder(iterator, 7, PRIMARY, "en_US");

      // ------- check empty string --------
      iterator = en_USCollator.getCollationElementIterator("");
      harness.check (iterator.next(), CollationElementIterator.NULLORDER, 
		     "next()");

      // ------- detailed checks of collation orders -------
      for (int i = 0; i < TEST_STRINGS.length; i++) {
	iterator = en_USCollator.getCollationElementIterator(TEST_STRINGS[i]);
	checkOrder(iterator, TEST_STRINGS[i].length(), TEST_ORDERS[i], 
		   "test string #" + i);
      }
    } 
    catch (Throwable t)
    {
      harness.debug(t);
      harness.fail("CollationElementIterator");
    }
  }
 
  static void checkOrder (CollationElementIterator iterator, 
			  int count, int order, String tag)
  {
    try
    {
      // next()
      int key = iterator.next();
      int[] prev = {key, 
		    CollationElementIterator.primaryOrder(key),
		    CollationElementIterator.secondaryOrder(key),
		    CollationElementIterator.tertiaryOrder(key)
      };
      harness.debug("first = {" + prev[0] + ", " + prev[1] + ", " +
		    prev[2] + ", " + prev[3] + "}");
      harness.check(key != CollationElementIterator.NULLORDER, "first " + tag);
      
      int i = 1;
      while ((key = iterator.next()) != CollationElementIterator.NULLORDER) {
	int[] next = {key, 
		      CollationElementIterator.primaryOrder(key),
		      CollationElementIterator.secondaryOrder(key),
		      CollationElementIterator.tertiaryOrder(key)
	};

	harness.debug("next (" + i + ") = {" + 
		      next[0] + ", " + next[1] + ", " +
		      next[2] + ", " + next[3] + "}");
	
        harness.check(next[0] > prev[0], 
		      "next() " + i + " " + tag);
	if (order == PRIMARY) {
	  harness.check(next[1] > prev[1],
			"no primary difference " + i + " " + tag);
	}
	else if (order == SECONDARY) {
	  harness.check((next[1] > prev[1]) || 
			(next[1] == prev[1] && next[2] > prev[2]),
			"no secondary difference" + i + " " + tag);
	}
	else if (order == TERTIARY) {
	  harness.check((next[1] > prev[1]) || 
			(next[1] == prev[1] && next[2] > prev[2]) ||
			(next[1] == prev[1] && next[2] == prev[2] && 
			 next[3] > prev[3]),
			"no tertiary difference" + i + " " + tag);
	}	
	prev = next;
        i++;
      }
      if (count != i) {
	harness.debug("count is " + count + ", i is " + i);
      }
      harness.check(count == i, "wrong number of keys " + tag);
    } 
    catch (Throwable t)
    {
      harness.debug (t);
      harness.fail ("CollationElementIterator with localeName");
    }
  }

} // class jdk11

