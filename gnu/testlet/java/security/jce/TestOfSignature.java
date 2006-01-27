/* TestOfSignature.java -- 
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

package gnu.testlet.java.security.jce;

import gnu.java.security.Registry;
import gnu.java.security.provider.Gnu;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.Signature;

/**
 * Conformance tests for the JCE signature scheme implementations.<p>
 */
public class TestOfSignature implements Testlet
{
  public void test(TestHarness harness)
  {
    setUp();

    testUnknownScheme(harness);
    testDSSRawSignature(harness);
    testRSAPSSRawSignature(harness);
  }

  /** Should fail with an unknown scheme. */
  public void testUnknownScheme(TestHarness harness)
  {
    harness.checkPoint("testUnknownScheme");
    try
      {
        Signature.getInstance("ABC", Registry.GNU_SECURITY);
        harness.fail("testUnknownScheme()");
      }
    catch (Exception x)
      {
        harness.check(true);
      }
  }

  public void testDSSRawSignature(TestHarness harness)
  {
    harness.checkPoint("testDSSRawSignature");
    try
      {
        KeyPairGenerator kpg =
            KeyPairGenerator.getInstance("DSA", Registry.GNU_SECURITY);
        kpg.initialize(512);
        KeyPair kp = kpg.generateKeyPair();

        Signature alice = Signature.getInstance("DSA", Registry.GNU_SECURITY);
//        Signature bob = (Signature) alice.clone();
        Signature bob = Signature.getInstance("DSA", Registry.GNU_SECURITY);

        byte[] message = "1 if by land, 2 if by sea...".getBytes();

        alice.initSign(kp.getPrivate());
        alice.update(message);
        byte[] signature = alice.sign();

        bob.initVerify(kp.getPublic());
        bob.update(message);

        harness.check(bob.verify(signature), "Verify DSA own signature");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("testDSSRawSignature(): " + String.valueOf(x));
      }
  }

  public void testRSAPSSRawSignature(TestHarness harness)
  {
    harness.checkPoint("testRSAPSSRawSignature");
    try
      {
        KeyPairGenerator kpg =
            KeyPairGenerator.getInstance("RSA", Registry.GNU_SECURITY);
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();

        Signature alice =
            Signature.getInstance("RSA-PSS/RAW", Registry.GNU_SECURITY);
//        Signature bob = (Signature) alice.clone();
        Signature bob =
            Signature.getInstance("RSA-PSS/RAW", Registry.GNU_SECURITY);

        byte[] message = "Que du magnifique...".getBytes();

        alice.initSign(kp.getPrivate());
        alice.update(message);
        byte[] signature = alice.sign();

        bob.initVerify(kp.getPublic());
        bob.update(message);

        harness.check(bob.verify(signature), "Verify RSA-PSS own signature");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("testRSAPSSRawSignature(): " + String.valueOf(x));
      }
  }

  private void setUp()
  {
    Security.addProvider(new Gnu()); // dynamically adds our provider
  }
}