/* TestOfDSSSignatureCodec.java -- 
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

package gnu.testlet.gnu.java.security.sig.dss;

import gnu.java.security.key.dss.DSSKeyPairGenerator;
import gnu.java.security.sig.BaseSignature;
import gnu.java.security.sig.ISignatureCodec;
import gnu.java.security.sig.dss.DSSSignature;
import gnu.java.security.sig.dss.DSSSignatureRawCodec;
import gnu.java.security.sig.dss.DSSSignatureX509Codec;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.KeyPair;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.HashMap;

/**
 * Conformance tests for the DSS signature format encoding/decoding
 * implementation.
 */
public class TestOfDSSSignatureCodec implements Testlet
{
  private DSSKeyPairGenerator kpg = new DSSKeyPairGenerator();

  private KeyPair kp;

  public void test(TestHarness harness)
  {
    testSignatureRawCodec(harness);
    testSignatureASN1Codec(harness);
  }

  public void testSignatureRawCodec(TestHarness harness)
  {
    harness.checkPoint("TestOfDSSSignatureCodec.testSignatureRawCodec");
    setUp();

    DSAPublicKey publicK = (DSAPublicKey) kp.getPublic();
    DSAPrivateKey privateK = (DSAPrivateKey) kp.getPrivate();

    DSSSignature alice = new DSSSignature();
    DSSSignature bob = (DSSSignature) alice.clone();

    byte[] message = "1 if by land, 2 if by sea...".getBytes();

    HashMap map = new HashMap();
    map.put(BaseSignature.SIGNER_KEY, privateK);
    alice.setupSign(map);
    alice.update(message, 0, message.length);
    Object signature = alice.sign();

    ISignatureCodec codec = new DSSSignatureRawCodec();

    byte[] encodedSignature = codec.encodeSignature(signature);
    Object decodedSignature = codec.decodeSignature(encodedSignature);

    map.put(BaseSignature.VERIFIER_KEY, publicK);
    bob.setupVerify(map);
    bob.update(message, 0, message.length);

    harness.check(bob.verify(decodedSignature),
                  "Signature Raw encoder/decoder test");
  }

  private void testSignatureASN1Codec(TestHarness harness)
  {
    harness.checkPoint("testSignatureASN1Codec");
    setUp();

    DSAPublicKey publicK = (DSAPublicKey) kp.getPublic();
    DSAPrivateKey privateK = (DSAPrivateKey) kp.getPrivate();

    DSSSignature alice = new DSSSignature();
    DSSSignature bob = (DSSSignature) alice.clone();

    byte[] message = "1 if by land, 2 if by sea...".getBytes();

    HashMap map = new HashMap();
    map.put(BaseSignature.SIGNER_KEY, privateK);
    alice.setupSign(map);
    alice.update(message, 0, message.length);
    Object signature = alice.sign();

    ISignatureCodec codec = new DSSSignatureX509Codec();

    byte[] encodedSignature = codec.encodeSignature(signature);
    Object decodedSignature = codec.decodeSignature(encodedSignature);

    map.put(BaseSignature.VERIFIER_KEY, publicK);
    bob.setupVerify(map);
    bob.update(message, 0, message.length);

    harness.check(bob.verify(decodedSignature),
                  "Signature X.509 encoder/decoder test");
  }

  // helper methods ----------------------------------------------------------

  private void setUp()
  {
    HashMap map = new HashMap();
    map.put(DSSKeyPairGenerator.MODULUS_LENGTH, new Integer(512));
    map.put(DSSKeyPairGenerator.USE_DEFAULTS, new Boolean(false));

    kpg.setup(map);
    kp = kpg.generate();
  }
}