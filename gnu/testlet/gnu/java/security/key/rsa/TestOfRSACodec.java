/* TestOfRSACodec.java -- 
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

package gnu.testlet.gnu.java.security.key.rsa;

import gnu.java.security.key.IKeyPairCodec;
import gnu.java.security.key.rsa.GnuRSAPrivateKey;
import gnu.java.security.key.rsa.GnuRSAPublicKey;
import gnu.java.security.key.rsa.RSAKeyPairGenerator;
import gnu.java.security.key.rsa.RSAKeyPairRawCodec;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

/**
 * Conformance tests for the RSA key format encoding/decoding implementation.
 */
public class TestOfRSACodec implements Testlet
{
  private RSAKeyPairGenerator kpg = new RSAKeyPairGenerator();

  private KeyPair kp;

  public void test(TestHarness harness)
  {
    testKeyPairRawCodec(harness);
    testPrivateKeyValueOf(harness);
    testPublicKeyValueOf(harness);
  }

  public void testKeyPairRawCodec(TestHarness harness)
  {
    harness.checkPoint("TestOfRSACodec.testKeyPairRawCodec");
    try
      {
        setUp();

        RSAPublicKey pubK = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey secK = (RSAPrivateKey) kp.getPrivate();

        byte[] pk1, pk2;
        try
          { // an invalid format ID
            pk1 = ((GnuRSAPublicKey) pubK).getEncoded(0);
            harness.fail("Succeeded with unknown format ID");
          }
        catch (IllegalArgumentException x)
          {
            harness.check(true, "Recognised unknown format ID");
          }

        pk1 = ((GnuRSAPublicKey) pubK).getEncoded(IKeyPairCodec.RAW_FORMAT);
        pk2 = ((GnuRSAPrivateKey) secK).getEncoded(IKeyPairCodec.RAW_FORMAT);

        IKeyPairCodec codec = new RSAKeyPairRawCodec();
        PublicKey newPubK = codec.decodePublicKey(pk1);
        PrivateKey newSecK = codec.decodePrivateKey(pk2);

        harness.check(pubK.equals(newPubK),
                      "RSA public key Raw encoder/decoder test");
        harness.check(secK.equals(newSecK),
                      "RSA private key Raw encoder/decoder test");
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfRSACodec.testKeyPairRawCodec");
      }
  }

  public void testPublicKeyValueOf(TestHarness harness)
  {
    harness.checkPoint("TestOfRSACodec.testPublicKeyValueOf");
    try
      {
        setUp();
        RSAPublicKey pubK = (RSAPublicKey) kp.getPublic();

        byte[] pk = ((GnuRSAPublicKey) pubK).getEncoded(IKeyPairCodec.RAW_FORMAT);
        PublicKey newPubK = GnuRSAPublicKey.valueOf(pk);

        harness.check(pubK.equals(newPubK));
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfRSACodec.testPublicKeyValueOf");
      }
  }

  public void testPrivateKeyValueOf(TestHarness harness)
  {
    harness.checkPoint("TestOfRSACodec.testPrivateKeyValueOf");
    setUp();

    try
      {
        RSAPrivateKey privateK = (GnuRSAPrivateKey) kp.getPrivate();

        byte[] pk = ((GnuRSAPrivateKey) privateK).getEncoded(IKeyPairCodec.RAW_FORMAT);
        PrivateKey newSecK = GnuRSAPrivateKey.valueOf(pk);

        harness.check(privateK.equals(newSecK));
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfRSACodec.testPrivateKeyValueOf");
      }
  }

  private void setUp()
  {
    HashMap map = new HashMap();
    map.put(RSAKeyPairGenerator.MODULUS_LENGTH, new Integer(1024));

    kpg.setup(map);
    kp = kpg.generate();
  }
}