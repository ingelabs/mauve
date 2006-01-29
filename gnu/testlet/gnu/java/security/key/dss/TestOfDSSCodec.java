/* TestOfDSSCodec.java -- 
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

package gnu.testlet.gnu.java.security.key.dss;

import gnu.java.security.key.IKeyPairCodec;
import gnu.java.security.key.dss.DSSKeyPairGenerator;
import gnu.java.security.key.dss.DSSKeyPairRawCodec;
import gnu.java.security.key.dss.DSSPrivateKey;
import gnu.java.security.key.dss.DSSPublicKey;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.HashMap;

/**
 * Conformance tests for the DSS key format encoding/decoding implementation.
 */
public class TestOfDSSCodec implements Testlet
{
  private DSSKeyPairGenerator kpg = new DSSKeyPairGenerator();

  private KeyPair kp;

  public void test(TestHarness harness)
  {
    testKeyPairRawCodec(harness);
    testPublicKeyValueOf(harness);
    testPrivateKeyValueOf(harness);
  }

  public void testKeyPairRawCodec(TestHarness harness)
  {
    harness.checkPoint("TestOfDSSCodec.testKeyPairRawCodec");
    setUp();

    DSAPublicKey pubK = (DSAPublicKey) kp.getPublic();
    DSAPrivateKey secK = (DSAPrivateKey) kp.getPrivate();

    byte[] pk1, pk2;
    try
      { // an invalid format ID
        pk1 = ((DSSPublicKey) pubK).getEncoded(0);
        harness.fail("Succeeded with unknown format ID");
      }
    catch (IllegalArgumentException x)
      {
        harness.check(true, "Recognised unknown format ID");
      }

    pk1 = ((DSSPublicKey) pubK).getEncoded(IKeyPairCodec.RAW_FORMAT);
    pk2 = ((DSSPrivateKey) secK).getEncoded(IKeyPairCodec.RAW_FORMAT);

    IKeyPairCodec codec = new DSSKeyPairRawCodec();
    PublicKey newPubK = codec.decodePublicKey(pk1);
    PrivateKey newSecK = codec.decodePrivateKey(pk2);

    harness.check(pubK.equals(newPubK),
                  "DSS public key Raw encoder/decoder test");
    harness.check(secK.equals(newSecK),
                  "DSS private key Raw encoder/decoder test");
  }

  public void testPublicKeyValueOf(TestHarness harness)
  {
    harness.checkPoint("TestOfDSSCodec.testPublicKeyValueOf");
    setUp();

    DSAPublicKey pubK = (DSAPublicKey) kp.getPublic();

    byte[] pk = ((DSSPublicKey) pubK).getEncoded(IKeyPairCodec.RAW_FORMAT);
    PublicKey newPubK = DSSPublicKey.valueOf(pk);

    harness.check(pubK.equals(newPubK),
                  "DSS public key valueOf(<raw-value>) test");
  }

  public void testPrivateKeyValueOf(TestHarness harness)
  {
    harness.checkPoint("TestOfDSSCodec.testPrivateKeyValueOf");
    setUp();

    DSAPrivateKey privateK = (DSAPrivateKey) kp.getPrivate();

    byte[] pk = ((DSSPrivateKey) privateK).getEncoded(IKeyPairCodec.RAW_FORMAT);
    PrivateKey newSecK = DSSPrivateKey.valueOf(pk);

    harness.check(privateK.equals(newSecK),
                  "DSS private key valueOf(<raw-value>) test");
  }

  private void setUp()
  {
    HashMap map = new HashMap();
    map.put(DSSKeyPairGenerator.MODULUS_LENGTH, new Integer(512));
    map.put(DSSKeyPairGenerator.USE_DEFAULTS, new Boolean(false));

    kpg.setup(map);
    kp = kpg.generate();
  }
}