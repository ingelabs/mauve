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
  private static final int NEXT = 0;
  private static final int PRIMARY = 1;
  private static final int SECONDARY = 2;
  private static final int TERTIARY = 3;
  private static TestHarness harness = null;

  public void test(TestHarness harness)
  {
    this.harness = harness;
    try
    {
      // -------- constants -------- 
      harness.check(CollationElementIterator.NULLORDER, -1, "NULLORDER");
  
      RuleBasedCollator en_USCollator = 
        (RuleBasedCollator)Collator.getInstance(new Locale("en", "US", ""));
      CollationElementIterator iterator = 
        en_USCollator.getCollationElementIterator("This is a test");

      // -------- methods -------- 
      int[][] results = new int[][]{
        {6684673,102,0,1}, {5898240,90,0,0}, {5963776,91,0,0}, 
        {6619136,101,0,0}, {256,0,1,0}, {5963776,91,0,0}, {6619136,101,0,0}, 
        {256,0,1,0}, {5373952,82,0,0}, {256,0,1,0}, {6684672,102,0,0}, 
        {5701632,87,0,0}, {6619136,101,0,0}, {6684672,102,0,0}};

      checkOrder (iterator, results);

      // reset()
      harness.checkPoint ("reset()");
      iterator.reset ();
      checkOrder (iterator, results);

      // ------- check empty string --------
      iterator = en_USCollator.getCollationElementIterator("");
      harness.check (iterator.next (), CollationElementIterator.NULLORDER, "next()");
    } 
    catch (Throwable t)
    {
      harness.debug (t);
      harness.fail ("CollationElementIterator");
    }
  }
 
  static void checkOrder (CollationElementIterator iterator, int[][] results)
  {
    try
    {
      // next()
      int next;
      int i = 0;
      while ((next = iterator.next ())  != CollationElementIterator.NULLORDER)
      {
        harness.debug ("next = " + next);
        harness.check (next, results[i][NEXT], "next() " + i);
  
        // primaryOrder()
        int primaryOrder = CollationElementIterator.primaryOrder(next);
        harness.debug ("  primaryOrder = " + primaryOrder);
        harness.check (primaryOrder, results[i][PRIMARY], "primaryOrder() " + i);
  
        // secondaryOrder()
        int secondaryOrder = (int)CollationElementIterator.secondaryOrder(next);
        harness.debug ("  secondaryOrder = " + secondaryOrder);
        harness.check (secondaryOrder, results[i][SECONDARY], "secondaryOrder() " + i);

        // tertiaryOrder()
        int tertiaryOrder = (int)CollationElementIterator.tertiaryOrder(next);
        harness.debug ("  tertiaryOrder = " + tertiaryOrder);
        harness.check (tertiaryOrder, results[i][TERTIARY], "tertiaryOrder() " + i);
        i++;
      }
  
    } 
    catch (Throwable t)
    {
      harness.debug (t);
      harness.fail ("CollationElementIterator");
    }
  }

} // class jdk11

