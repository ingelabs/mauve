/* getInstance.java -- Ensure names with extra spaces are recognized
   Copyright (C) 2006 Free Software Foundation, Inc.
This file is part of Mauve.

Mauve is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

Mauve is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mauve; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

*/

// Tags: JDK1.4
// Uses: MauveDigest

package gnu.testlet.java.security.Engine;

import gnu.java.security.Engine;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.Provider;
import java.security.Security;

public class getInstance extends Provider implements Testlet
{
  public getInstance()
  {
    super("FakeProvider", 1.0, "");

    put("MessageDigest.foo",
        "gnu.testlet.java.security.MessageDigest.MauveDigest");
    put("Alg.Alias.MessageDigest.bar", "foo");
  }

  public void test (TestHarness harness)
  {
    harness.checkPoint ("Engine");

    Provider provider = this;
    Security.addProvider(provider);
    String signature;

    signature = "getInstance(\"MessageDigest\", \"foo\", provider)";
    try
      {
        harness.check(
            Engine.getInstance("MessageDigest", "foo", provider) != null,
            signature);
      }
    catch (Exception x)
      {
        harness.fail(signature);
        harness.debug(x);
      }

    signature = "getInstance(\"  MessageDigest  \", \"foo\", provider)";
    try
      {
        harness.check(
            Engine.getInstance("  MessageDigest  ", "foo", provider) != null,
            signature);
      }
    catch (Exception x)
      {
        harness.fail(signature);
        harness.debug(x);
      }

    signature = "getInstance(\"MessageDigest\", \"  foo  \", provider)";
    try
      {
        harness.check(
            Engine.getInstance("MessageDigest", "  foo  ", provider) != null,
            signature);
      }
    catch (Exception x)
      {
        harness.fail(signature);
        harness.debug(x);
      }

    signature = "getInstance(\"  MessageDigest  \", \"  foo  \", provider)";
    try
      {
        harness.check(
            Engine.getInstance("  MessageDigest  ", "  foo  ", provider) != null,
            signature);
      }
    catch (Exception x)
      {
        harness.fail(signature);
        harness.debug(x);
      }
  }
}
