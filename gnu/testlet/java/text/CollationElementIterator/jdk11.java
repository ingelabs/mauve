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

// We use CollationElementIterator.setText, a 1.2 invention.
// Tags: JDK1.2

package gnu.testlet.java.text.CollationElementIterator;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.text.CollationElementIterator;
import java.util.Locale;

public class jdk11 implements Testlet
{
// JDK 1.4 collation rule string for locale English (United States)
  String JDK_1_4_EN_US_RULES =
    "='\u200A'=\u200B=\u200C=\u200D=\u200E=\000 =\001 =\002 =\003 =\004" +
    "=\005 =\006 =\007 =\b ='\t'='\013' =\016=\017 ='\020' =\021 =\022 " +
    "=\023=\024 =\025 =\026 =\027 =\030=\031 =\032 =\033 =\034 =\035=\036" +
    " =\037 =\177=\u0080 =\u0081 =\u0082 =\u0083 =\u0084 =\u0085=\u0086" +
    " =\u0087 =\u0088 =\u0087 =\u0089 =\u008A=\u008B =\u008C =\u008D =\u008E" +
    " =\u0070 =\u0071=\u0072 =\u0073 =\u0074 =\u0075 =\u0076 =\u0077=\u0078" +
    " =\u0077 =\u0079 =\u007A =\u007B =\u007C=\u007D =\u007E;' ';'\u0090" +
    "';'\u2000';'\u2001';'\u2002';'\u2003';'\u2004';'\u2005';'\u2006';'" +
    "\u2007';'\u2008';'\u2007';'\u2009';'\u3000';'\uEDEE';'\r' ;'\t' ;'" +
    "\n';'\f';'\013';\u0301;\u0300;\u0306;\u0302;\u030B;\u0309;\u030C;\u0308" +
    ";\u030A;\u0303;\u0307;\u0304;\u0337;\u0327;\u0328;\u0323;\u0332;\u0305" +
    ";\u0307;\u030D;\u030E;\u0310;\u0311;\u0312;\u0313;\u0314;\u0315;\u0316" +
    ";\u0317;\u0318;\u0317;\u0319;\u031A;\u031B;\u031C;\u031D;\u031E;\u0320" +
    ";\u0321;\u0322;\u0324;\u0325;\u0326;\u0327;\u0329;\u032A;\u032B;\u032C" +
    ";\u032D;\u032E;\u0330;\u0331;\u0333;\u0334;\u0335;\u0336;\u0338;\u0337" +
    ";\u0339;\u033A;\u033B;\u033C;\u033D;\u033E;\u0342;\u0344;\u0345;\u0360" +
    ";\u0361;\u0483;\u0484;\u0485;\u0486;\u20C0;\u20C1;\u20C2;\u20C3;\u20C4" +
    ";\u20C5;\u20C6;\u20C7;\u20C8;\u20C7;\u20C9;\u20CA;\u20CB;\u20CC;\u20CD" +
    ";\u20CE;\u20D0;\u20D1,'-';\u009C;\u2010;\u2011;\u2012;\u2013;\u2014" +
    ";\u2015;\u2212<'_'<\u009E<','<';'<':'<'!'<\u0091<'?'<\u00AE<'/'<'." +
    "'<\u00A4<'`'<'^'<\u0098<'~'<\u00A7<\u00A8<'''<'\"'<\u009A<\u00AA<'" +
    "('<')'<'['<']'<'{'<'}'<\u0097<\u00A6<\u0097<\u009D<'@'<\u0094<\u0D3E" +
    "<\u0092<\u2091<\u2092<'$'<\u209A<\u209B<\u2093<\u2094<\u2095<\u2096" +
    "<\u2097<\u0093<\u2098<\u2099<\u2097<\u0095<'*'<'\\'<'&'<'#'<'%'<'+" +
    "'<\u00A1<\u00E7<\u00C7<'<'<'='<'>'<\u009B<'|'<\u0096<\u00A0<\u00A5" +
    "<0<1<2<3<4<5<6<7<8<9<\u00AB<\u00AC<\u00AD<a,A<b,B<c,C<d,D<\u00E0,\u00C0" +
    "<e,E<f,F<g,G<h,H<i,I<j,J<k,K<l,L<m,M<n,N<o,O<p,P<q,Q<r,R<s, S & SS" +
    ",\u00CE<t,T& TH, \u00CD &TH, \u00ED <u,U<v,V<w,W<x,X<y,Y<z,Z&AE,\u00B6" +
    "&AE,\u00D6&OE,\u0152&OE,\u0153";

  private static final int PRIMARY = 1;
  private static final int SECONDARY = 2;
  private static final int TERTIARY = 3;
  private static final int NONE = 4;

  private static TestHarness harness = null;

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
        i++;
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


  static void checkEquiv (CollationElementIterator iterator, 
			  String[] sets, int order, String tag)
  {
    try
    {
      for (int i = 0; i < sets.length; i++) {
	iterator.setText(sets[i]);
	int key = iterator.next();
	int[] prev = {key, 
		      CollationElementIterator.primaryOrder(key),
		      CollationElementIterator.secondaryOrder(key),
		      CollationElementIterator.tertiaryOrder(key)
	};
	harness.debug("first = {" + prev[0] + ", " + prev[1] + ", " +
		      prev[2] + ", " + prev[3] + "}");
	harness.check(key != CollationElementIterator.NULLORDER, 
		      "first " + tag);
	
	int j = 1;
	while ((key = iterator.next()) != CollationElementIterator.NULLORDER) {
	  j++;
	  int[] next = {key, 
			CollationElementIterator.primaryOrder(key),
			CollationElementIterator.secondaryOrder(key),
			CollationElementIterator.tertiaryOrder(key)
	  };
	  
	  harness.debug("next (" + i + ", " + j + ") = {" + 
			next[0] + ", " + next[1] + ", " +
			next[2] + ", " + next[3] + "}");
	  
	  if (order == PRIMARY) {
	    harness.check(next[1] > prev[1],
			  "not primary ordered " + i + ", " + j + " " + tag);
	  }
	  else if (order == SECONDARY) {
	    harness.check(next[1] == prev[1] && next[2] > prev[2],
			  "not secondary ordered" + i + ", " + j + " " + tag);
	  }
	  else if (order == TERTIARY) {
	    harness.check(next[1] == prev[1] && next[2] == prev[2] && 
			  next[3] > prev[3],
			  "not tertiary ordered" + i + ", " + j + " " + tag);
	  }
	  else if (order == NONE) {
	    harness.check(next[1] == prev[1], "keys not equal");
	  }
	  prev = next;
	}
	if (sets[i].length() != j) {
	  harness.debug("length[" + i + "] is " + sets[i].length() +
			", j is " + j);
	}
	harness.check(sets[i].length() == j, 
		      "wrong number of keys (" + j + ") " + tag);
      }
    } 
    catch (Throwable t)
      {
	harness.debug (t);
	harness.fail ("CollationElementIterator with localeName");
      }
  }


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
      " _,;:!?/.`^~'\"()[]{}@$*\\&#%+<=>|A",
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

    final String[][] TEST_STRINGS2 = {
      {"aA", "bB", "cC", "dD", "eE", "fF", "gG", "hH", "iI", "jJ", "kK", 
       "lL", "mM", "nN", "oO", "pP", "qQ", "rR", "sS", "tT", "uU", "vV", 
       "wW", "xX", "yY", "zZ"},
    };
    final int[] TEST_ORDERS2 = {
      TERTIARY,
    };
    
    jdk11.harness = harness;
    try
    {
      // -------- constants -------- 
      harness.check(CollationElementIterator.NULLORDER, -1, "NULLORDER");
  
//       RuleBasedCollator en_USCollator = (RuleBasedCollator) 
// 	Collator.getInstance(new Locale("en", "US", ""));

      // Used to get the collator as above, but this assumes that the
      // en_US locale's collation rules are reasonably complete.
      // Since the point of this class is test the iterator, it is
      // better to use a collator with hard-wired collation rules of
      // known quality.
      RuleBasedCollator en_USCollator = 
        new RuleBasedCollator(JDK_1_4_EN_US_RULES);

      CollationElementIterator iterator = 
        en_USCollator.getCollationElementIterator("abcdefg");

      // -------- methods -------- 
      checkOrder(iterator, 7, PRIMARY, "initial test");

      // reset()
      harness.checkPoint("reset()");
      iterator.reset();
      checkOrder(iterator, 7, PRIMARY, "initial test after reset()");

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

      // ------- detailed checks of collation equivalences -------
      for (int i = 0; i < TEST_STRINGS2.length; i++) {
	checkEquiv(iterator, TEST_STRINGS2[i], TEST_ORDERS2[i], 
		   "test set #" + i);
      }
    } 
    catch (Throwable t)
    {
      harness.debug(t);
      harness.fail("CollationElementIterator");
    }
  }
 

} // class jdk11

