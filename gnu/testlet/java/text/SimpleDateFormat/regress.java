// Regression test for libgcj/Classpath SimpleDateFormat bugs

// Tags: JDK1.1

// Copyright (c) 1999, 2001  Free Software Foundation

// This file is part of Mauve.

package gnu.testlet.java.text.SimpleDateFormat;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.text.*;
import java.util.*;

public class regress implements Testlet
{
  public void test (TestHarness harness)
  {
    // We don't check the results but just that this works at all.  THis
    // is a regression test for libgcj.
    harness.checkPoint ("parsing regression");
    DateFormat cdf = new SimpleDateFormat ("EEE, dd MMM yyyy HH:mm:ss zzzz");
    boolean ok = true;
    Date d = null;
    try
      {
	d = cdf.parse ("Fri, 18 May 2001 20:18:06 GMT");
      }
    catch (ParseException _)
      {
	ok = false;
      }
    harness.check (ok);

    Calendar k = Calendar.getInstance (TimeZone.getTimeZone ("GMT"));
    k.setTime (d);
    harness.check (k.get(Calendar.HOUR),        8, "check hour");
    harness.check (k.get(Calendar.HOUR_OF_DAY), 20, "check hour-of-day");
  }
}
