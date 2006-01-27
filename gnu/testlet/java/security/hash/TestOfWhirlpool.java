/* TestOfWhirlpool.java -- 
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

// Tags: GNU-CRYPTO JDK1.4

package gnu.testlet.java.security.hash;

import gnu.java.security.hash.IMessageDigest;
import gnu.java.security.hash.Whirlpool;
import gnu.java.security.util.Util;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

/**
 * Conformance tests for the {@link Whirlpool} hash implementation.
 */
public class TestOfWhirlpool implements Testlet
{
  private static final String TV1 =
      "EBAA1DF2E97113BE187EB0303C660F6E643E2C090EF2CDA9A2EA6DCF5002147D"
      + "1D0E1E9D996E879CEF9D26896630A5DB3308D5A0DC235B199C38923BE2259E03";

  private static final String TV2 =
      "5777FC1F8467A1C004CD9130439403CCDAA9FDC86092D9CFFE339E6008612374"
      + "D04C8FC0C724707FEAE6F7CEB1E030CABF652A673DA1849B02654AF76EEE24A7";

  private IMessageDigest algorithm, clone;

  public void test(TestHarness harness)
  {
    harness.checkPoint("TestOfWhirlpool");
    try
      {
        algorithm = new Whirlpool();
        harness.check(algorithm.selfTest(), "selfTest");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfWhirlpool.selfTest");
      }

    try
      {
        algorithm = new Whirlpool();
        algorithm.update((byte) 0x00);
        byte[] md = algorithm.digest();
        harness.check(TV1.equals(Util.toString(md)), "test8ZeroBits");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfWhirlpool.test8ZeroBits");
      }

    try
      {
        algorithm = new Whirlpool();
        algorithm.update((byte) 0x00);
        algorithm.update((byte) 0x00);
        byte[] md = algorithm.digest();
        harness.check(TV2.equals(Util.toString(md)), "test16ZeroBits");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfWhirlpool.test16ZeroBits");
      }

    try
      {
        algorithm = new Whirlpool();
        algorithm.update((byte) 0x00);
        clone = (IMessageDigest) algorithm.clone();
        byte[] md = algorithm.digest();
        harness.check(TV1.equals(Util.toString(md)), "testCloning #1");

        clone.update((byte) 0x00);
        md = clone.digest();
        harness.check(TV2.equals(Util.toString(md)), "testCloning #2");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfWhirlpool.testCloning");
      }
  }
}