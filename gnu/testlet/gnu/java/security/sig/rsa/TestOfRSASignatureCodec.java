/* TestOfRSASignatureCodec.java -- 
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

package gnu.testlet.gnu.java.security.sig.rsa;

import gnu.java.security.key.rsa.RSAKeyPairGenerator;
import gnu.java.security.sig.BaseSignature;
import gnu.java.security.sig.ISignatureCodec;
import gnu.java.security.sig.rsa.RSAPSSSignature;
import gnu.java.security.sig.rsa.RSAPSSSignatureRawCodec;
import gnu.testlet.TestHarness;
import gnu.testlet.Testlet;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

/**
 * Conformance tests for the RSA signature format encoding/decoding
 * implementation.
 */
public class TestOfRSASignatureCodec implements Testlet
{
  private RSAKeyPairGenerator kpg = new RSAKeyPairGenerator();

  private KeyPair kp;

  public void test(TestHarness harness)
  {
    testSignatureRawCodec(harness);
  }

  public void testSignatureRawCodec(TestHarness harness)
  {
    harness.checkPoint("TestOfRSASignatureCodec.testSignatureRawCodec");
    try
      {
        setUp();
        RSAPublicKey pubK = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey secK = (RSAPrivateKey) kp.getPrivate();

        RSAPSSSignature alice = new RSAPSSSignature();
        RSAPSSSignature bob = (RSAPSSSignature) alice.clone();

        byte[] message = "1 if by land, 2 if by sea...".getBytes();

        HashMap map = new HashMap();
        map.put(BaseSignature.SIGNER_KEY, secK);
        alice.setupSign(map);
        alice.update(message, 0, message.length);
        Object signature = alice.sign();

        ISignatureCodec codec = new RSAPSSSignatureRawCodec();

        byte[] encodedSignature = codec.encodeSignature(signature);
        Object decodedSignature = codec.decodeSignature(encodedSignature);

        map.put(BaseSignature.VERIFIER_KEY, pubK);
        bob.setupVerify(map);
        bob.update(message, 0, message.length);

        harness.check(bob.verify(decodedSignature));
      }
    catch (Exception x)
      {
        harness.debug(x);
        harness.fail("TestOfRSASignatureCodec.testSignatureRawCodec");
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