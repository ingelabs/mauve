// Copyright (c) 2007 Hernadi Laszlo

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
/**
 * 
 */
package gnu.testlet.java.util.Scanner;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Random;
import java.util.Scanner;

/**
 * @author E0327023 Hernadi Laszlo
 * @ 15.02.2007 - 13:37:48
 *
 */
public class BigDecimalInteger extends Base
{

  /**
   * The amount of BigDecimals and BigIntegers to generate and test
   */
  private final static int AMOUNT = 5000;

  public BigDecimalInteger ()
  {
    this.isEnabled = false;
  }

  /* (non-Javadoc)
   * @see gnu.testlet.java.util.Scanner.TestBase#doTest()
   */
  @Override protected void doTest ()
  {
    BigDecimal[]decimals = new BigDecimal[AMOUNT];
    BigInteger[]integers = new BigInteger[decimals.length];
    long runID = System.currentTimeMillis ();
    Random rand = new Random (runID);
    StringBuilder sBuff = new StringBuilder (1000);
    String inStr;
    int i;
    BigDecimal tmpDec;
    BigInteger tmpInt;
    boolean fund;
    boolean failed;
    int runsLeft = 10;

    for (i = 0; i < decimals.length; i++)
      {
	decimals[i] = new BigDecimal (rand.nextDouble () - 0.5);
	integers[i] = BigInteger.valueOf (rand.nextInt ());
//                      if (i % 100 == 0)
//                              System.out.println(i);
      }

    sBuff.append (decimals[0] + " " + integers[0]);
    for (i = 1; i < decimals.length; i++)
      {
	sBuff.append (" " + decimals[i] + " " + integers[i]);
//                      if (i % 100 == 0)
//                              System.out.println(i);
      }

    inStr = sBuff.toString ();
//              Scanner s = new Scanner (inStr);
    Scanner s = new Scanner (inStr);
    //s.setUseLocale (false);
//              System.out.println(inStr);

//              System.out.println("len : " + inStr.length());

    i = 0;
    while (s.hasNext () && runsLeft > 0)
      {
	fund = false;
	failed = false;
	if (s.hasNextBigDecimal ())
	  {
	    tmpDec = s.nextBigDecimal ();
	    if (!tmpDec.equals (decimals[i]))
	      {
		failed = true;
		this.myHarness.fail ("#" + i +
				     " : bad result by nextBigDeciaml() : (" +
				     tmpDec + " != " + decimals[i] +
				     ") possibly needed : \"" + s.next () +
				     "\"");
	      }
	    fund = true;
	  }
	else
	  {
	    this.myHarness.fail ("no BigDecimal found...");
	  }
	if (s.hasNextBigInteger ())
	  {
	    tmpInt = s.nextBigInteger ();
	    if (!tmpInt.equals (integers[i]))
	      {
		failed = true;
		this.myHarness.fail ("#" + i +
				     " : bad result by nextBigInteger() : " +
				     tmpInt + " != " + integers[i]);
	      }
	    fund = true;
	  }
	else
	  {
	    this.myHarness.fail ("no BigInteger found...");
	  }

//                      if (i % 100 == 0)
//                              System.out.println(i);
	if (!fund)
	  {
	    this.myHarness.fail ("\"" + s.next () +
				 "\" is neighter BigDecimal nor BigInteger");
	  }
	i++;
	if (failed)
	  {
	    runsLeft--;
	  }
      }
    this.myHarness.check (i, decimals.length,
			  "not all read (" + i + " / " + decimals.length +
			  ")");
  }

}
