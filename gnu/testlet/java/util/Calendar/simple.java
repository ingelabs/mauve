// Tags: JDK1.1

// Copyright (c) 2001  Jeff Sturm

// This file is part of Mauve.

package gnu.testlet.java.util.Calendar;

import gnu.testlet.Testlet;
import gnu.testlet.TestHarness;
import java.text.*;
import java.util.*;

public class simple implements Testlet
{
  public void test (TestHarness harness)
  {
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    Calendar calendar = Calendar.getInstance();

    Date date;
    try
      {
	date = format.parse("04/30/2001");
      }
    catch (ParseException _)
      {
	harness.debug (_);
	harness.fail ("couldn't run any tests");
	return;
      }

    calendar.setTime(date);
    harness.check (format.format(date), "04/30/2001");

    harness.check ("weekday = " + calendar.get(Calendar.DAY_OF_WEEK),
		   "weekday = 2");

    calendar.add(Calendar.DATE, 1);
    date = calendar.getTime();
    harness.check (format.format (date), "05/01/2001");

    harness.check ("weekday = " + calendar.get(Calendar.DAY_OF_WEEK),
		   "weekday = 3");

    calendar.add(Calendar.MONTH, 1);
    date = calendar.getTime();
    harness.check (format.format(date), "06/01/2001");

    harness.check ("weekday = " + calendar.get(Calendar.DAY_OF_WEEK),
		   "weekday = 6");
  }
}
